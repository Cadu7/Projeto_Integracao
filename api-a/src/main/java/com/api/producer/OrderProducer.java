package com.api.producer;

import com.api.model.Funcionario;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import java.util.Properties;

@ApplicationScoped
@Transactional
public class OrderProducer {

    Producer<String, String> producer;
    @ConfigProperty(name = "quarkus.kafka.producer.bootstrap-servers")
    String server;
    @ConfigProperty(name = "quarkus.kafka.producer.key-serializer")
    String serializer;
    private Properties props;

    public void setup() {
        props = new Properties();
        props.put("acks", "all");
        props.put("bootstrap.servers", server);
        props.put("key.serializer", serializer);
        props.put("value.serializer", serializer);

        producer = new KafkaProducer<>(props);
    }

    public void publisher(Funcionario func) {
        setup();
        ProducerRecord<String, String> sender = new ProducerRecord<String, String>("funcionario", func.toString());
        producer.send(sender);
    }

}
