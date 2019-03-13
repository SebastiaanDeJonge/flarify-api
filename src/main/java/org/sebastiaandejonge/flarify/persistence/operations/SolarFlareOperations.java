package org.sebastiaandejonge.flarify.persistence.operations;

import org.sebastiaandejonge.flarify.persistence.entity.SolarFlare;
import org.sebastiaandejonge.flarify.transfer.ActiveRegionTransfer;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.aggregation.TypedAggregation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.util.Date;

import static org.springframework.data.mongodb.core.aggregation.Aggregation.*;

@Service
public class SolarFlareOperations {

    private ReactiveMongoTemplate reactiveMongoTemplate;

    public SolarFlareOperations(ReactiveMongoTemplate reactiveMongoTemplate) {
        this.reactiveMongoTemplate = reactiveMongoTemplate;
    }

    /**
     * Aggregates the solar flares to find all active regions and their number of solar flares within a given date range
     *
     * @param startDate The start date
     * @param endDate The end date (until, not included in the result)
     */
    public Flux<ActiveRegionTransfer> findActiveAreaSolarFlareCountsWithBeginTimeBetween(Date startDate, Date endDate) {
        return reactiveMongoTemplate.aggregate(
                TypedAggregation.newAggregation(
                        SolarFlare.class,
                        match(
                                Criteria.where("beginTime").exists(true)
                                        .andOperator(
                                                Criteria.where("beginTime").gte(startDate),
                                                Criteria.where("beginTime").lt(endDate)
                                        )
                        ),
                        project("activeRegion"),
                        group("activeRegion")
                                .count().as("totalSolarFlares")
                                .addToSet("activeRegion").as("region"),
                        sort(Sort.Direction.DESC, "totalSolarFlares")
                ),
                ActiveRegionTransfer.class
        );
    }

    /**
     * Aggregates the solar flares to find the region with the most solar flares within a given date range
     *
     * @param startDate The start date
     * @param endDate The end date (until, not included in the result)
     */
    public Flux<ActiveRegionTransfer> findActiveAreaWithHighestSolarFlareCountAndBeginTimeBetween(Date startDate, Date endDate) {
        return reactiveMongoTemplate.aggregate(
                TypedAggregation.newAggregation(
                        SolarFlare.class,
                        match(
                                Criteria.where("beginTime").exists(true)
                                        .andOperator(
                                                Criteria.where("beginTime").gte(startDate),
                                                Criteria.where("beginTime").lt(endDate)
                                        )
                        ),
                        project("activeRegion"),
                        group("activeRegion")
                                .count().as("totalSolarFlares")
                                .addToSet("activeRegion").as("region"),
                        sort(Sort.Direction.DESC, "totalSolarFlares"),
                        limit(1)
                ),
                ActiveRegionTransfer.class
        );
    }
}
