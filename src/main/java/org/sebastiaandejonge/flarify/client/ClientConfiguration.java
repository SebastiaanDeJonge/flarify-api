package org.sebastiaandejonge.flarify.client;

import org.sebastiaandejonge.flarify.client.mapper.TransferMapper;
import org.sebastiaandejonge.flarify.config.FlarifyConfigProperties;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ClientConfiguration {

    @Bean
    public TransferMapper transferMapper() {
        return new TransferMapper();
    }

    @Bean
    public DonkiClient donkiClient(
            RestTemplateBuilder restTemplateBuilder,
            FlarifyConfigProperties configProperties,
            TransferMapper transferMapper
    ) {
        return new DonkiClient(restTemplateBuilder, configProperties, transferMapper);
    }
}
