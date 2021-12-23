package io.aimc.shipmentreciver.conf;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaTopicConfig {
    @Value("${spring.kafka.portion-topic}")
    private String portionTopic;

    @Bean
    public NewTopic portionTopic() {
        return TopicBuilder.name(portionTopic).build();
    }
}