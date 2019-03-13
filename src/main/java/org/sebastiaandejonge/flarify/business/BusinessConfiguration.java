package org.sebastiaandejonge.flarify.business;

import org.modelmapper.ModelMapper;
import org.sebastiaandejonge.flarify.business.mapper.SolarFlareMapper;
import org.sebastiaandejonge.flarify.business.model.SolarFlareModel;
import org.sebastiaandejonge.flarify.business.model.SummaryModel;
import org.sebastiaandejonge.flarify.client.DonkiClient;
import org.sebastiaandejonge.flarify.persistence.operations.SolarFlareOperations;
import org.sebastiaandejonge.flarify.persistence.repository.SolarFlareRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;

@EnableReactiveMongoRepositories
@Configuration
public class BusinessConfiguration {

    @Bean
    public SolarFlareMapper solarFlareMapper(ModelMapper modelMapper) {
        return new SolarFlareMapper(modelMapper);
    }

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @Bean
    public SolarFlareModel solarFlareModel(
            SolarFlareRepository solarFlareRepository,
            DonkiClient donkiClient,
            SolarFlareMapper solarFlareMapper
    ) {
        return new SolarFlareModel(
                solarFlareRepository,
                donkiClient,
                solarFlareMapper
        );
    }

    @Bean
    public SummaryModel summaryModel(
            SolarFlareRepository solarFlareRepository,
            SolarFlareOperations solarFlareOperations
    ) {
        return new SummaryModel(solarFlareRepository, solarFlareOperations);
    }
}
