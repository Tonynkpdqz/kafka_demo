package com.nkpdqz.kafka_demo.consumer.real_con;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.boot.autoconfigure.jms.JmsProperties;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("consumer")
public class Consumer {

    @KafkaListener(topics = {"GroupQueue"},containerFactory = "kafkaListenerContainerFactory")
    public void listen(List<ConsumerRecord> recordList, Acknowledgment ack){
        try {
            for (ConsumerRecord record : recordList) {
                System.out.println(record.offset() + record.key().toString() + record.value().toString());
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            ack.acknowledge();
        }
    }
}
