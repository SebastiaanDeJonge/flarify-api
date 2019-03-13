package org.sebastiaandejonge.flarify.controller;

import org.sebastiaandejonge.flarify.business.BusinessFacade;

/**
 * Abstract controller template
 */
public abstract class AbstractController {

    static String DATE_FORMAT = "yyyy-MM-dd";

    private BusinessFacade businessFacade;

    public AbstractController(BusinessFacade businessFacade) {
        this.businessFacade = businessFacade;
    }

    BusinessFacade getBusinessFacade() {
        return businessFacade;
    }
}
