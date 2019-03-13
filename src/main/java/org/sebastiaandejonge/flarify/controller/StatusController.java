package org.sebastiaandejonge.flarify.controller;

import org.sebastiaandejonge.flarify.business.BusinessFacade;
import org.sebastiaandejonge.flarify.transfer.StatusTransfer;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StatusController extends AbstractController {

    public StatusController(BusinessFacade businessFacade) {
        super(businessFacade);
    }

    @GetMapping("/")
    public StatusTransfer status() {

        StatusTransfer transfer = new StatusTransfer();
        transfer.setSolarFlares(getBusinessFacade().getTotalSolarFlareCount());
        return transfer;
    }
}
