package com.sunilbainsla.kafka.kstream;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.common.utils.Bytes;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.Topology;
import org.apache.kafka.streams.kstream.GlobalKTable;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.Materialized;
import org.apache.kafka.streams.processor.Processor;
import org.apache.kafka.streams.processor.ProcessorContext;
import org.apache.kafka.streams.state.KeyValueStore;

import java.util.Properties;

public class GlobalKTab {
    static final String CUSTOMER_STORE = "customer-store1";
    static final String CUSTOMER_TOPIC = "customer1";

    public static void main(String[] args) {
        Properties props = new Properties();
        props.put(StreamsConfig.APPLICATION_ID_CONFIG, AppConfigs.globalApplicationID);
        props.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, AppConfigs.bootstrapServers);
        props.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.String().getClass());
        props.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, Serdes.String().getClass());
        props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
        props.put(StreamsConfig.CACHE_MAX_BYTES_BUFFERING_CONFIG, 0);
        StreamsBuilder streamsBuilder = new StreamsBuilder();

        // Add a global store for customers. The data from this global store
        // will be fully replicated on each instance of this application.
        //        streamsBuilder.addGlobalStore(
        //                Stores.keyValueStoreBuilder(Stores.persistentKeyValueStore("customer-store"), Serdes.String(), Serdes.String()),
        //                "customer",
        //                Consumed.with(Serdes.String(), Serdes.String()),
        //                () -> new GlobalStoreUpdater<>("customer-store"));
        System.out.println("globalStream   000000");
        final GlobalKTable<String, String>
                customers =
                streamsBuilder.globalTable(CUSTOMER_TOPIC, Materialized.<String, String, KeyValueStore<Bytes, byte[]>>as(CUSTOMER_STORE)
                        .withKeySerde(Serdes.String()).withValueSerde(Serdes.String()));
        KStream<String, String> globalStream = streamsBuilder.stream(customers.queryableStoreName());
        System.out.println("globalStream   " + globalStream);
        Topology topology = streamsBuilder.build();
        KafkaStreams streams = new KafkaStreams(topology, props);
        System.out.println("Starting stream.");

        streams.start();

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            System.out.println("Shutting down stream");
            streams.cleanUp();
            streams.close();
        }));
    }

    // Processor that keeps the global store updated.
    private static class GlobalStoreUpdater<K, V> implements Processor<K, V> {

        private final String storeName;

        public GlobalStoreUpdater(final String storeName) {
            this.storeName = storeName;
        }

        private KeyValueStore<K, V> store;

        @Override
        public void init(final ProcessorContext processorContext) {
            store = (KeyValueStore<K, V>) processorContext.getStateStore(storeName);
        }

        @Override
        public void process(K k, V v) {
            store.put(k, v);
        }

        @Override
        public void close() {
            // No-op
        }

    }
}
