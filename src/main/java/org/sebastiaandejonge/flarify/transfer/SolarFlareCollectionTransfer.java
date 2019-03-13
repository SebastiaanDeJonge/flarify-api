package org.sebastiaandejonge.flarify.transfer;

import java.util.ArrayList;

public class SolarFlareCollectionTransfer implements TransferInterface {

    private ArrayList<SolarFlareTransfer> solarFlareTransfers = new ArrayList<>();

    public ArrayList<SolarFlareTransfer> getSolarFlareTransfers() {
        return solarFlareTransfers;
    }

    public void setSolarFlareTransfers(ArrayList<SolarFlareTransfer> solarFlareTransfers) {
        this.solarFlareTransfers = solarFlareTransfers;
    }

    public void addSolarFlareTransfer(SolarFlareTransfer solarFlareTransfer) {
        solarFlareTransfers.add(solarFlareTransfer);
    }

    public void removeSolarFlareTransfer(SolarFlareTransfer solarFlareTransfer) {
        solarFlareTransfers.remove(solarFlareTransfer);
    }
}
