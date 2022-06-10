package com.sunilbainsla.kafka;

import lombok.extern.log4j.Log4j2;
import org.apache.kafka.streams.kstream.KStream;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.function.Consumer;

@Service
@Log4j2
public class ApplicationConfig {

    @Bean
    public Consumer<KStream<Object, String>> paymentProcessor() {

        return kstream -> kstream.foreach(this::businessLogic);
    }

    private void businessLogic(Object key, String val) {
        log.info("topic6Consumer: {}", val);

    }
}
