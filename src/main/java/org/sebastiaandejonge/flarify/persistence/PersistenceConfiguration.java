package org.sebastiaandejonge.flarify.persistence;

import com.mongodb.reactivestreams.client.MongoClient;
import com.mongodb.reactivestreams.client.MongoClients;
import org.sebastiaandejonge.flarify.config.FlarifyConfigProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractReactiveMongoConfiguration;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;

@EnableReactiveMongoRepositories
@Configuration
public class PersistenceConfiguration extends AbstractReactiveMongoConfiguration {

    private final FlarifyConfigProperties configProperties;

    public PersistenceConfiguration(FlarifyConfigProperties configProperties) {
        this.configProperties = configProperties;
    }

    @Bean
    @Override
    public MongoClient reactiveMongoClient() {
        return MongoClients.create();
    }

    @Override
    protected String getDatabaseName() {
        return configProperties.mongodb.getDatabaseName();
    }
}
