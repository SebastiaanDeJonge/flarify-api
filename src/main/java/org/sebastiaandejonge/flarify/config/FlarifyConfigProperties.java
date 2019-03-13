package org.sebastiaandejonge.flarify.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "flarify")
public class FlarifyConfigProperties {

    private String apiKey;
    public final Donki donki = new Donki();
    public final Mongodb mongodb = new Mongodb();

    public String getApiKey() {
        return apiKey;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    public Donki getDonki() {
        return donki;
    }

    /**
     * NASA DONKI API settings
     */
    public class Donki {

        private String baseUrl;

        public String getBaseUrl() {
            return baseUrl;
        }
        public void setBaseUrl(String baseUrl) {
            this.baseUrl = baseUrl;
        }
    }

    public Mongodb getMongodb() {
        return mongodb;
    }

    /**
     * MongoDB properties
     */
    public class Mongodb {

        private String databaseName;

        public String getDatabaseName() {
            return databaseName;
        }
        public void setDatabaseName(String databaseName) {
            this.databaseName = databaseName;
        }
    }
}
