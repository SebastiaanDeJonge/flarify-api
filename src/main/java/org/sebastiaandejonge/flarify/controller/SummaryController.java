package org.sebastiaandejonge.flarify.controller;

import org.sebastiaandejonge.flarify.business.BusinessFacade;
import org.sebastiaandejonge.flarify.transfer.SummaryTransfer;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
@RequestMapping("/summary")
public class SummaryController extends AbstractController {

    public SummaryController(BusinessFacade businessFacade) {
        super(businessFacade);
    }

    @CrossOrigin
    @GetMapping("/between/{startDate}/and/{endDate}")
    public SummaryTransfer summary(
            @PathVariable("startDate") String formattedStartDate,
            @PathVariable("endDate") String formattedEndDate,
            HttpServletResponse response
    ) {
        Date startDate = new Date(), endDate = new Date();

        try {
            startDate = new SimpleDateFormat(DATE_FORMAT).parse(formattedStartDate);
            endDate = new SimpleDateFormat(DATE_FORMAT).parse(formattedEndDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        response.setContentType("application/json");
        return getBusinessFacade().getSummary(
                startDate,
                endDate
        );
    }
}
