package sbt2;

import org.elasticsearch.client.Client;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;
import org.thymeleaf.spring5.expression.Mvc;

import java.net.InetAddress;
import java.net.UnknownHostException;

@Configuration
@EnableElasticsearchRepositories(basePackages = "repository")
public class Config {

    @Bean
    public Client client(){
        TransportAddress address = null;

        try {
            address = new InetSocketTransportAddress(InetAddress.getByName("localhost"), 9300);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }

//        return new PreBuiltTransportClient(Settings.EMPTY)                                                              //moet genoeg zijn indien cluster.name de default "elasticsearch" blijft
        return new PreBuiltTransportClient(settings())
                .addTransportAddress(address);
    }

    @Bean
    public ElasticsearchOperations elasticsearchTemplate(){
        return new ElasticsearchTemplate(client());
    }

    @Bean
    public Settings settings(){
        return Settings.builder().put("cluster.name", "newCluster").build();
    }

    @Bean
    public Mvc mvc(){
        return new Mvc();
    }
}
