package com.nkpdqz.kafka_demo.producer.real_pro;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.util.concurrent.FailureCallback;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.SuccessCallback;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
public class ProducerController {

    @Autowired
    private KafkaTemplate kafkaTemplate;

    @GetMapping("/api/test/push")
    public String testPush(){
        ListenableFuture send = kafkaTemplate.send("GroupQueue", UUID.randomUUID(), "a new message");
        send.addCallback(new SuccessCallback() {
            @Override
            public void onSuccess(Object o) {
                System.out.println("success");
            }
        }, new FailureCallback() {
            @Override
            public void onFailure(Throwable throwable) {
                System.out.println("fail");
            }
        });
        return "success";
    }
}
