package org.sebastiaandejonge.flarify.client.request;

import org.sebastiaandejonge.flarify.config.FlarifyConfigProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * A basic request builder for the NASA DONKI API
 */
public class DonkiRequest {

    private FlarifyConfigProperties configProperties;
    private Logger logger = LoggerFactory.getLogger(DonkiRequest.class);
    private Date startDate;
    private Date endDate;


    public DonkiRequest(FlarifyConfigProperties configProperties, Date startDate, Date endDate) {

        this.configProperties = configProperties;
        this.startDate = startDate;
        this.endDate = endDate;

        logger.info("NASA API key: " + this.configProperties.getApiKey());
        logger.info("DONKI base URL: " + this.configProperties.donki.getBaseUrl());
    }

    /**
     * Generates the URL for the built request
     * @return The URL
     */
    public String getUrl() {

        UriComponents uriComponents = this.createUriComponentsBuilder().build();
        String uri = uriComponents.toUriString();
        logger.info("Generated solar flare URL request URL: " + uri);
        return uri;
    }

    /**
     * Creates the URI components builder
     * @return the URI components builder
     */
    private UriComponentsBuilder createUriComponentsBuilder() {

        UriComponentsBuilder uriComponentsBuilder = UriComponentsBuilder.fromUriString(configProperties.donki.getBaseUrl());
        addQueryParameters(uriComponentsBuilder);
        return uriComponentsBuilder;
    }

    /**
     * Add the mandatory and optional query parameters to the URI components builder
     * @param uriComponentsBuilder The URI components builder including the query parameters
     */
    private void addQueryParameters(UriComponentsBuilder uriComponentsBuilder) {

        uriComponentsBuilder.queryParam("api_key", configProperties.getApiKey());
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        if (startDate != null) {
            uriComponentsBuilder.queryParam("startDate", dateFormat.format(startDate));
        }
        if (endDate != null) {
            uriComponentsBuilder.queryParam("endDate", dateFormat.format(endDate));
        }
    }
}
