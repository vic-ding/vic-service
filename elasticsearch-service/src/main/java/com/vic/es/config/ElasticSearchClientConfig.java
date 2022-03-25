package com.vic.es.config;


import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ElasticSearchClientConfig {

//    @Value("${order.elasticsearch.ip}")
//    private String hostName;
//
//    @Value("${order.elasticsearch.port}")
//    private int port;
//
//    @Value("${order.elasticsearch.userName}")
//    private String userName;
//
//    @Value("${order.elasticsearch.password}")
//    private String password;

//    @Bean
//    public RestHighLevelClient orderClient() {
//        CredentialsProvider credentialsProvider = new BasicCredentialsProvider();
//        credentialsProvider.setCredentials(AuthScope.ANY, new UsernamePasswordCredentials(userName, password));
//        return new RestHighLevelClient(
//                RestClient.builder(
//                        new HttpHost(hostName, port)).setHttpClientConfigCallback(httpClientBuilder -> httpClientBuilder.setDefaultCredentialsProvider(credentialsProvider))
//        );
//    }


    @Bean
    public RestHighLevelClient localClient() {
        return new RestHighLevelClient(
                RestClient.builder(
                        new HttpHost("127.0.0.1", 9200, "http")
                )
        );
    }

}
