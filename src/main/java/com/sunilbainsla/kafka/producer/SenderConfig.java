package com.sunilbainsla.kafka.producer;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.CommonClientConfigs;
import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

@Configuration
public class SenderConfig {

  @Value("${kafka.bootstrap-servers}")
  private String bootstrapServers;

  @Bean
  public Map<String, Object> producerConfigs() {
    Map<String, Object> props = new HashMap<>();
    // list of host:port pairs used for establishing the initial connections to the Kakfa cluster
    props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,
        bootstrapServers);
    props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,
        StringSerializer.class);
    props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,
        StringSerializer.class);

    props.put(ProducerConfig.ACKS_CONFIG, "all");
    //default is 0 and 2147483647>2.3
  //  Users should generally prefer to leave this config unset and instead use delivery.
    //  timeout.ms to control retry behavior.
    props.put(ProducerConfig.RETRIES_CONFIG, Integer.toString(Integer.MAX_VALUE));
    //default RETRY_BACKOFF_MS_CONFIG 100ms The amount of time to wait before attempting to retry a failed request to a given topic partition
    //>=request.timeout.ms + linger.ms +RETRY_BACKOFF_MS_CONFIG
    props.put(ProducerConfig.RETRY_BACKOFF_MS_CONFIG, 100);
    //Default:	30000 (30 seconds)
    props.put(ProducerConfig.REQUEST_TIMEOUT_MS_CONFIG, 30001);
    //default is 2 mins
   // props.put("delivery.timeout.ms",120000); // 2 mins


    //max.in.flight.requests.per.connection = 1, we can guarantee that Kafka will preserve message
    // order in the event that some messages will require multiple retries before they
    // are successfully acknowledged
    //Setting max.in.flight.requests.per.connection=1can significantly decrease your throughput
    // Default value is 5
    props.put(ProducerConfig.MAX_IN_FLIGHT_REQUESTS_PER_CONNECTION,1);


    //props.put(.)

    return props;
  }

  @Bean
  public ProducerFactory<String, String> producerFactory() {
    return new DefaultKafkaProducerFactory<>(producerConfigs());
  }

  @Bean
  public KafkaTemplate<String, String> kafkaTemplate() {
    return new KafkaTemplate<>(producerFactory());
  }

  @Bean
  public Sender sender() {
    return new Sender();
  }
}
