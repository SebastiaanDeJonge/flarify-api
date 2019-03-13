package org.sebastiaandejonge.flarify.client.mapper;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.sebastiaandejonge.flarify.transfer.SolarFlareCollectionTransfer;
import org.sebastiaandejonge.flarify.transfer.SolarFlareTransfer;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;

/**
 * The transfer mapper handles the mapping from the DONKI response (JSON) to transfer objects.
 */
public class TransferMapper {

    /**
     * Maps an entire response to a transfer collection and returns it
     * @param jsonResponse The JSON response from the DONKI API
     * @return The collection containing all transfers
     */
    public SolarFlareCollectionTransfer jsonResponseToCollectionTransfer(String jsonResponse) {

        SolarFlareCollectionTransfer collectionTransfer = new SolarFlareCollectionTransfer();
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            JsonNode rootNode = objectMapper.readTree(jsonResponse);
            for (Iterator<JsonNode> nodeIterator = rootNode.elements(); nodeIterator.hasNext();) {
                JsonNode childNode = nodeIterator.next();
                SolarFlareTransfer transfer = jsonNodeToTransfer(childNode);
                collectionTransfer.getSolarFlareTransfers().add(transfer);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return collectionTransfer;
    }

    /**
     * Maps a single JSON node to a transfer object
     * @param jsonNode The JSON node
     * @return The transfer object
     */
    private SolarFlareTransfer jsonNodeToTransfer(JsonNode jsonNode) {

        String FIELD_FLARE_ID = "flrID";
        String FIELD_ACTIVE_REGION = "activeRegionNum";
        String FIELD_BEGIN_TIME = "beginTime";
        String FIELD_CLASS_TYPE = "classType";
        String FIELD_END_TIME = "endTime";
        String FIELD_PEAK_TIME = "peakTime";
        String FORMAT_DATE = "yyyy-MM-dd'T'HH:mm'Z'";

        SolarFlareTransfer transfer = new SolarFlareTransfer();

        if (jsonNode.has(FIELD_FLARE_ID)) {
            transfer.setFlareId(jsonNode.get(FIELD_FLARE_ID).asText());
        }

        if (jsonNode.has(FIELD_ACTIVE_REGION)) {
            transfer.setActiveRegion(jsonNode.get(FIELD_ACTIVE_REGION).asInt());
        }

        if (jsonNode.has(FIELD_BEGIN_TIME) && !jsonNode.get(FIELD_BEGIN_TIME).isNull()) {
            try {
                Date beginTime = new SimpleDateFormat(FORMAT_DATE).parse(
                        jsonNode.get(FIELD_BEGIN_TIME).asText()
                );
                transfer.setBeginTime(beginTime);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

        if (jsonNode.has(FIELD_PEAK_TIME) && !jsonNode.get(FIELD_PEAK_TIME).isNull()) {
            try {
                Date peakTime = new SimpleDateFormat(FORMAT_DATE).parse(
                        jsonNode.get(FIELD_PEAK_TIME).asText()
                );
                transfer.setPeakTime(peakTime);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

        if (jsonNode.has(FIELD_END_TIME) && !jsonNode.get(FIELD_END_TIME).isNull()) {
            try {
                Date endTime = new SimpleDateFormat(FORMAT_DATE).parse(
                        jsonNode.get(FIELD_END_TIME).asText()
                );
                transfer.setEndTime(endTime);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

        if (jsonNode.has(FIELD_CLASS_TYPE)) {
            String fullClassType = jsonNode.get(FIELD_CLASS_TYPE).asText();
            if (fullClassType.length() > 0) {
                transfer.setClassType(fullClassType.substring(0, 1));

                double suffix;
                if (fullClassType.length() > 1) {
                    suffix = Double.parseDouble(fullClassType.substring(1));
                } else {
                    suffix = (double) 1;
                }
                transfer.setClassSuffix(suffix);
            }
        }

        return transfer;
    }
}
