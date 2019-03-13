package org.sebastiaandejonge.flarify.business;

import org.sebastiaandejonge.flarify.business.model.SolarFlareModel;
import org.sebastiaandejonge.flarify.business.model.SummaryModel;
import org.sebastiaandejonge.flarify.transfer.SummaryTransfer;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * This is the access point of the business layer. Any data generated through the business layer will pass through here
 * back to other layers.
 */
@Component
public class BusinessFacade {

    private SummaryModel summaryModel;
    private SolarFlareModel solarFlareModel;

    public BusinessFacade(SummaryModel summaryModel, SolarFlareModel solarFlareModel) {

        this.summaryModel = summaryModel;
        this.solarFlareModel = solarFlareModel;
    }

    /**
     * Generates a summary for the given date range
     *
     * @param startDate The start date
     * @param endDate The end date
     * @return The summary for the given range
     */
    public SummaryTransfer getSummary(Date startDate, Date endDate) {

        return summaryModel.generateSummary(startDate, endDate);
    }

    /**
     * Counts all solar flares which are currently persisted
     *
     * @return The total number of persisted solar flares
     */
    public long getTotalSolarFlareCount() {

        return solarFlareModel.countAll();
    }
}
