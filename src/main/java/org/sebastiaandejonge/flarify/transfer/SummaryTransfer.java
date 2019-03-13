package org.sebastiaandejonge.flarify.transfer;

import java.util.Date;

public class SummaryTransfer implements TransferInterface {

    private Date startDate;
    private Date endDate;
    private long solarFlares = 0;
    private SolarFlareTransfer mostPowerful;
    private double mostPowerfulPicometre;
    private ActiveRegionTransfer mostActiveRegion;

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public long getSolarFlares() {
        return solarFlares;
    }

    public void setSolarFlares(long solarFlares) {
        this.solarFlares = solarFlares;
    }

    public SolarFlareTransfer getMostPowerful() {
        return mostPowerful;
    }

    public void setMostPowerful(SolarFlareTransfer mostPowerful) {
        this.mostPowerful = mostPowerful;
    }

    public double getMostPowerfulPicometre() {
        return mostPowerfulPicometre;
    }

    public void setMostPowerfulPicometre(double mostPowerfulPicometre) {
        this.mostPowerfulPicometre = mostPowerfulPicometre;
    }

    public ActiveRegionTransfer getMostActiveRegion() {
        return mostActiveRegion;
    }

    public void setMostActiveRegion(ActiveRegionTransfer mostActiveRegion) {
        this.mostActiveRegion = mostActiveRegion;
    }
}
