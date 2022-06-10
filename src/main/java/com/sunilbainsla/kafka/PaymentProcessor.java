package com.sunilbainsla.kafka;

import lombok.extern.log4j.Log4j2;
import org.apache.kafka.streams.processor.Processor;
import org.apache.kafka.streams.processor.ProcessorContext;

@Log4j2
public class PaymentProcessor implements Processor<String, String> {
    @Override
    public void init(ProcessorContext processorContext) {

    }

    @Override
    public void process(String s, String s2) {
        log.info("sdadadsadadadadadadadaddsasad" + s + s2);

    }

    @Override
    public void close() {

    }
}
