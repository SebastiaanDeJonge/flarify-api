package org.sebastiaandejonge.flarify.business.helper;

import org.sebastiaandejonge.flarify.transfer.SolarFlareTransfer;

/**
 * Solar flare helper
 */
public class SolarFlareHelper {

    /**
     * Calculates the picometre (watts/m2) value for the given solar flare transfer.
     *
     * @param transfer The transfer containing valid solar flare data
     * @return The picometre (watts/m2) value for the given transfer
     */
    public static double calculateWattsPerSquareMeterForTransfer(SolarFlareTransfer transfer) {

        int multiplier = -8;
        switch (transfer.getClassType()) {
            case "B":
                multiplier = -7;
                break;
            case "C":
                multiplier = -6;
                break;
            case "M":
                multiplier = -5;
                break;
            case "X":
                multiplier = -4;
                break;
        }
        return transfer.getClassSuffix() * Math.pow(10.0, multiplier);
    }
}
