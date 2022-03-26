package com.vic.es.config;


import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ElasticSearchClientConfig {

    @Value("${local.elasticsearch.hostName}")
    private String hostName;

    @Value("${local.elasticsearch.port}")
    private int port;

    @Bean
    public RestHighLevelClient localClient() {
        return new RestHighLevelClient(
                RestClient.builder(
                        new HttpHost(hostName, port,"http")
                )
        );
    }

}
