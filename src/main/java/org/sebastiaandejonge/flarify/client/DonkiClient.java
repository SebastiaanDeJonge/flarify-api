package org.sebastiaandejonge.flarify.client;

import org.sebastiaandejonge.flarify.client.mapper.TransferMapper;
import org.sebastiaandejonge.flarify.client.request.DonkiRequest;
import org.sebastiaandejonge.flarify.config.FlarifyConfigProperties;
import org.sebastiaandejonge.flarify.transfer.SolarFlareCollectionTransfer;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.Date;

/**
 * The DONKI client handles requests to the NASA DONKI API and processes the responses into transfer objects
 */
public class DonkiClient {

    private final RestTemplate restTemplate;
    private FlarifyConfigProperties configProperties;
    private TransferMapper transferMapper;

    public DonkiClient(
            RestTemplateBuilder restTemplateBuilder,
            FlarifyConfigProperties configProperties,
            TransferMapper transferMapper
    ) {

        restTemplate = restTemplateBuilder.build();
        this.configProperties = configProperties;
        this.transferMapper = transferMapper;
    }

    /**
     * Gets the collection from the DONKI API within the given range
     * @param startDate The start date of the range
     * @param endDate The end date of the range (not included in the result)
     * @return The collection
     */
    public SolarFlareCollectionTransfer getBetweenRange(Date startDate, Date endDate) {

        DonkiRequest request = new DonkiRequest(
                configProperties,
                startDate,
                endDate
        );
        String url = request.getUrl();

        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
        return transferMapper.jsonResponseToCollectionTransfer(response.getBody());
    }
}
