package ru.yandex.practicum.notificationservice.config;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Configuration
@ConditionalOnProperty(value = "spring.kafka.consumer.enabled", havingValue = "true", matchIfMissing = true)
public class KafkaConfig {

    @Value("${spring.kafka.consumer.bootstrap-server}")
    private String bootstrapServer;

    @Value("${spring.kafka.consumer.truststore-path}")
    private String truststorePath;

    @Value("${spring.kafka.consumer.truststore-password}")
    private String truststorePassword;

    @Value("${spring.kafka.consumer.keystore-path}")
    private String keystorePath;

    @Value("${spring.kafka.consumer.keystore-password}")
    private String keystorePassword;

    @Bean
    public ConsumerFactory<UUID, String> consumerFactory() {
        Map<String, Object> props = new HashMap<>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServer);
        props.put("ssl.truststore.location", truststorePath);
        props.put("ssl.truststore.password", truststorePassword);
        props.put("ssl.keystore.location", keystorePath);
        props.put("ssl.keystore.password", keystorePassword);
        return new DefaultKafkaConsumerFactory<>(props);
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<UUID, String> kafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<UUID, String> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory());
        return factory;
    }
}
