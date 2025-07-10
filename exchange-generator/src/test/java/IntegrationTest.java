import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.MockConsumer;
import org.apache.kafka.clients.consumer.OffsetResetStrategy;
import org.apache.kafka.common.TopicPartition;
import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.kafka.test.hamcrest.KafkaMatchers;

import java.util.Collections;
import java.util.Map;

@SpringBootTest
@EmbeddedKafka
public class IntegrationTest {

    @Test
    public void testProcessor(){
        try (var mockConsumer = new MockConsumer<>(OffsetResetStrategy.EARLIEST)) {
            var topic = "test-topic";
            var partition = new TopicPartition(topic, 0);
            mockConsumer.assign(Collections.singletonList(partition));
            mockConsumer.updateBeginningOffsets(Map.of(partition, 0L));

            mockConsumer.addRecord(new ConsumerRecord<>(topic, 0, 0L, "Yandex", "Practicum"));

            var record = mockConsumer.poll(java.time.Duration.ofMillis(100)).iterator().next();

            MatcherAssert.assertThat(record, KafkaMatchers.hasKey("Yandex"));
            MatcherAssert.assertThat(record, KafkaMatchers.hasValue("Practicum"));
        }
    }
}
