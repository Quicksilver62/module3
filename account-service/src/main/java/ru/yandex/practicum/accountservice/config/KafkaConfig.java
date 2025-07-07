package ru.yandex.practicum.accountservice.config;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Configuration
@ConditionalOnProperty(value = "spring.kafka.producer.enabled", havingValue = "true", matchIfMissing = true)
public class KafkaConfig {

    @Value("${spring.kafka.producer.bootstrap-server}")
    private String bootstrapServer;

    @Value("${spring.kafka.producer.truststore-path}")
    private String truststorePath;

    @Value("${spring.kafka.producer.truststore-password}")
    private String truststorePassword;

    @Value("${spring.kafka.producer.keystore-path}")
    private String keystorePath;

    @Value("${spring.kafka.producer.keystore-password}")
    private String keystorePassword;

    @Bean
    public ProducerFactory<UUID, String> producerFactory() {
        Map<String, Object> props = new HashMap<>();
        props.put("retries", 5);
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServer);
        props.put("ssl.truststore.location", truststorePath);
        props.put("ssl.truststore.password", truststorePassword);
        props.put("ssl.keystore.location", keystorePath);
        props.put("ssl.keystore.password", keystorePassword);
        return new DefaultKafkaProducerFactory<>(props);
    }

    @Bean
    public KafkaTemplate<UUID, String> kafkaTemplate() {
        return new KafkaTemplate<>(producerFactory());
    }
}
