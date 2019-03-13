package org.sebastiaandejonge.flarify;

import org.sebastiaandejonge.flarify.config.FlarifyConfigProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@EnableReactiveMongoRepositories
@EnableConfigurationProperties(FlarifyConfigProperties.class)
public class FlarifyApplication {

    public static void main(String[] args) {
        SpringApplication.run(FlarifyApplication.class, args);
    }

}
