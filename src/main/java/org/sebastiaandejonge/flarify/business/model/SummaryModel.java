package org.sebastiaandejonge.flarify.business.model;

import org.modelmapper.ModelMapper;
import org.sebastiaandejonge.flarify.business.helper.SolarFlareHelper;
import org.sebastiaandejonge.flarify.persistence.operations.SolarFlareOperations;
import org.sebastiaandejonge.flarify.persistence.repository.SolarFlareRepository;
import org.sebastiaandejonge.flarify.transfer.SolarFlareTransfer;
import org.sebastiaandejonge.flarify.transfer.SummaryTransfer;

import java.util.Date;

/**
 * The summary model contains the main business logic regarding summary generation
 */
public class SummaryModel {

    private SolarFlareRepository solarFlareRepository;
    private SolarFlareOperations solarFlareOperations;

    public SummaryModel(
            SolarFlareRepository solarFlareRepository,
            SolarFlareOperations solarFlareOperations
    ) {
        this.solarFlareRepository = solarFlareRepository;
        this.solarFlareOperations = solarFlareOperations;
    }

    /**
     * Builds up the summary data transfer for the specified period.
     *
     * @todo Group the operations so they can be run in parallel
     * @param startDate The start date of the period
     * @param endDate The end date of the period (until)
     * @return The generate summary transfer
     */
    public SummaryTransfer generateSummary(Date startDate, Date endDate) {
        SummaryTransfer summaryTransfer = new SummaryTransfer();
        summaryTransfer.setStartDate(startDate);
        summaryTransfer.setEndDate(endDate);

        ModelMapper modelMapper = new ModelMapper();

        // The strongest solar flare
        solarFlareRepository
                .findTopByBeginTimeBetweenOrderByClassTypeDescClassSuffixDesc(startDate, endDate)
                .map(entity -> modelMapper.map(entity, SolarFlareTransfer.class))
                .map(transfer -> {
                    summaryTransfer.setMostPowerfulPicometre(
                            SolarFlareHelper.calculateWattsPerSquareMeterForTransfer(transfer)
                    );
                    summaryTransfer.setMostPowerful(transfer);
                    return transfer;
                })
                .block();

        // The most active region
        solarFlareOperations.findActiveAreaWithHighestSolarFlareCountAndBeginTimeBetween(startDate, endDate)
                .map(activeRegionTransfer -> {
                    summaryTransfer.setMostActiveRegion(activeRegionTransfer);
                    return activeRegionTransfer;
                })
                .blockLast();

        // Count the number of flares
        solarFlareRepository.countByBeginTimeBetween(startDate, endDate)
                .map(count -> {
                    summaryTransfer.setSolarFlares(count);
                    return count;
                })
                .block();

        return summaryTransfer;
    }
}
