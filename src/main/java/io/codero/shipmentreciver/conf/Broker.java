package io.codero.shipmentreciver.conf;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "spring.active-broker")
public enum Broker {
    KAFKA, RABBITMQ
}
