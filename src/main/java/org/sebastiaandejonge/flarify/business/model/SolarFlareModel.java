package org.sebastiaandejonge.flarify.business.model;

import org.sebastiaandejonge.flarify.business.helper.DateHelper;
import org.sebastiaandejonge.flarify.business.mapper.SolarFlareMapper;
import org.sebastiaandejonge.flarify.client.DonkiClient;
import org.sebastiaandejonge.flarify.persistence.entity.SolarFlare;
import org.sebastiaandejonge.flarify.persistence.repository.SolarFlareRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;

import java.util.Date;
import java.util.concurrent.atomic.AtomicLong;

/**
 * The solar flare model contains the main business logic regarding solar flares
 */
public class SolarFlareModel {

    private static final Logger log = LoggerFactory.getLogger(SolarFlareModel.class);

    private DonkiClient donkiClient;
    private SolarFlareMapper solarFlareMapper;
    private SolarFlareRepository solarFlareRepository;

    public SolarFlareModel(
            SolarFlareRepository solarFlareRepository,
            DonkiClient donkiClient,
            SolarFlareMapper solarFlareMapper
    ) {
        this.donkiClient = donkiClient;
        this.solarFlareMapper = solarFlareMapper;
        this.solarFlareRepository = solarFlareRepository;
    }

    /**
     * Collects fresh data directly from NASA's DONKI API, stores the results in the database and returns the Flux
     * object containing them.
     *
     * @param startDate The start date from which to collect
     * @param endDate The end date from which to collect (until this date)
     */
    private void collectForRange(Date startDate, Date endDate) {
        Flux<SolarFlare> flux = solarFlareMapper.transferCollectionToEntityFlux(
                donkiClient.getBetweenRange(startDate, endDate)
        );
        solarFlareRepository.saveAll(flux)
                .count()
                .subscribe(total -> log.info("Collected {} data sets solar flares", total));
    }

    /**
     * Gets previously stored solar flares from the database and returns them as a Flux object. This method will not
     * verify if data within the given range has actually already been collected.
     *
     * @param startDate The start date
     * @param endDate The end date (until this date)
     * @return The Flux object containing the SolarFlare entities within the range
     */
    public Flux<SolarFlare> getForRange(Date startDate, Date endDate) {
        return solarFlareRepository.findByBeginTimeBetween(startDate, endDate);
    }

    /**
     * Counts all solar flares which are currently persisted inside the database
     *
     * @return The total number of persisted solar flares
     */
    public long countAll() {

        AtomicLong total = new AtomicLong();
        solarFlareRepository.count()
                .map(count -> {
                    total.set(count);
                    return count;
                })
                .block();

        return total.get();
    }

    /**
     * Updates the local solar flare cache. If no data exists within the persistence layer, a full import will be
     * performed. If data does exist, only an incremental update will be performed based on the last entry.
     */
    public void updateCache() {

        log.info("Updating solar flare cache");
        Date fallbackStartDate = DateHelper.getFallbackDate();
        solarFlareRepository.count()
                .map(total -> {
                    if (total > 0) {
                        solarFlareRepository.findTopByBeginTimeIsNotNullOrderByBeginTimeDesc()
                                .map(solarFlare -> {
                                    log.info("Entries exist, performing incremental update");
                                    log.info(
                                            "Last entry found at {}",
                                            DateHelper.getPrintableString(solarFlare.getBeginTime())
                                    );
                                    collectForRange(solarFlare.getBeginTime(), null);
                                    return solarFlare;
                                })
                                .subscribe();
                    } else {
                        log.info("No entries found, performing a full import");
                        collectForRange(fallbackStartDate, null);
                    }
                    return total;
                })
                .subscribe();
    }
}
