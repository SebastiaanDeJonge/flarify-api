package org.sebastiaandejonge.flarify.transfer;


public class ActiveRegionTransfer implements TransferInterface {

    private int region;
    private long totalSolarFlares;

    public int getRegion() {
        return region;
    }

    public void setRegion(int region) {
        this.region = region;
    }

    public long getTotalSolarFlares() {
        return totalSolarFlares;
    }

    public void setTotalSolarFlares(long totalSolarFlares) {
        this.totalSolarFlares = totalSolarFlares;
    }
}
