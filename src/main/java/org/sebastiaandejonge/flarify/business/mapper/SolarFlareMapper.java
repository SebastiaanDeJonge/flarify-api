package org.sebastiaandejonge.flarify.business.mapper;

import org.bouncycastle.jcajce.provider.digest.SHA3;
import org.bouncycastle.util.encoders.Hex;
import org.bson.types.ObjectId;
import org.modelmapper.ModelMapper;
import org.sebastiaandejonge.flarify.persistence.entity.SolarFlare;
import org.sebastiaandejonge.flarify.transfer.SolarFlareCollectionTransfer;
import org.sebastiaandejonge.flarify.transfer.SolarFlareTransfer;
import reactor.core.publisher.Flux;

import java.util.ArrayList;

/**
 * The SolarFlareMapper is used to transform SolarFlareTransfer models to SolarFlare entities and vice versa.
 */
public class SolarFlareMapper {

    private ModelMapper modelMapper;

    public SolarFlareMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    /**
     * Maps an entire SolarFlareCollectionTransfer to a reactive Flux object containing mapped entities.
     *
     *
     * @param collectionTransfer The SolarFlareCollectionTransfer
     * @return The mapped SolarFlare entities inside the Flux object
     */
    public Flux<SolarFlare> transferCollectionToEntityFlux(SolarFlareCollectionTransfer collectionTransfer) {
        ArrayList<SolarFlare> entityCollection = new ArrayList<>();
        for (SolarFlareTransfer transfer : collectionTransfer.getSolarFlareTransfers()) {
            entityCollection.add(transferToEntity(transfer));
        }
        return Flux.fromIterable(entityCollection);
    }

    /**
     * Maps the given SolarFlareTransfer to the SolarFlare entity. The ObjectId (which is used as the unique identifier
     * within the database) is generated based on the flare ID if it was not previously set.
     *
     * @param transfer The input transfer, may be a clean non-existing item, or already existing
     * @return The SolarFlare entity
     */
    private SolarFlare transferToEntity(SolarFlareTransfer transfer) {

        SolarFlare entity = modelMapper.map(transfer, SolarFlare.class);
        if (entity.getId() == null && entity.getFlareId() != null) {
            SHA3.DigestSHA3 digestSHA3 = new SHA3.Digest512();
            byte[] digest = digestSHA3.digest(entity.getFlareId().getBytes());
            entity.setId(new ObjectId(Hex.toHexString(digest).substring(0, 24)));
        }
        return entity;
    }
}
