package com.kairosds.cursospb2.kafkaconsumer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kairosds.cursospb2.kafkaconsumer.pelicula.Pelicula;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.test.EmbeddedKafkaBroker;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = KafkaconsumerApplication.class)
@EmbeddedKafka(bootstrapServersProperty = "${spring.kafka.bootstrap-servers}", ports = 9092)
@Import( value = {KafkaProducerConfig.class})
@ActiveProfiles("test")
public class PeliculaConsumerEmbeddedKafkaTest {

    @Autowired
    private static EmbeddedKafkaBroker kafkaBroker;

    @Value("${spring.kafka.bootstrap-servers}")
    private String bootstrapServers;

    @Value("${kafka.consumer.groupId}")
    private String kafkaConsumerGroupId;

    @Value("${topics.pelicula}")
    private String topicPelicula;

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void test1() throws com.fasterxml.jackson.core.JsonProcessingException {

        System.out.println(bootstrapServers);
        System.out.println(kafkaConsumerGroupId);

        final var p = Pelicula.builder()
                .autor("Sergio")
                .titulo("El mas alla").duracionMinutos(123.54).build();

        final var pJsonString = this.objectMapper.writeValueAsString(p);

        kafkaTemplate.send(topicPelicula, pJsonString);



    }
}
