package org.sebastiaandejonge.flarify.persistence.repository;

import org.sebastiaandejonge.flarify.persistence.entity.SolarFlare;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Date;

public interface SolarFlareRepository extends ReactiveCrudRepository<SolarFlare, String> {

    Flux<SolarFlare> findByBeginTimeBetween(Date startDate, Date endDate);

    Mono<Long> countByBeginTimeBetween(Date startDate, Date endDate);

    Mono<SolarFlare> findTopByBeginTimeIsNotNullOrderByBeginTimeDesc();

    Mono<SolarFlare> findTopByBeginTimeBetweenOrderByClassTypeDescClassSuffixDesc(Date startDate, Date endDate);
}
