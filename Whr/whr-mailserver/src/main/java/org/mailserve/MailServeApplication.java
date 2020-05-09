package org.mailserve;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Collection;
import java.util.Iterator;
import java.util.Queue;

@SpringBootApplication
public class MailServeApplication {
    public static void main(String[] args) {
        SpringApplication.run(MailServeApplication.class, args);

    }

    @Bean
    Queue queue() {
        return new Queue(MailConstants.MAIL_QUEUE_NAME);
    }
}

