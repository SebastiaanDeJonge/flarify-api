package org.sebastiaandejonge.flarify.transfer;

public class StatusTransfer implements TransferInterface {

    private String status = "OK";
    private long solarFlares;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public long getSolarFlares() {
        return solarFlares;
    }

    public void setSolarFlares(long solarFlares) {
        this.solarFlares = solarFlares;
    }
}
