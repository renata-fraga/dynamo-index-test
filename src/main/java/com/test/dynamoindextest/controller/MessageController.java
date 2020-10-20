package com.test.dynamoindextest.controller;


import com.test.dynamoindextest.model.Message;
import com.test.dynamoindextest.repository.MessageRepository;
import com.test.dynamoindextest.util.CompositionHelper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping(path = "/messages", produces = MediaType.APPLICATION_JSON_VALUE)
public class MessageController {

    private final MessageRepository messageRepository;

    @PostMapping(path = "/{quantidade}")
    public void publishMessage(@PathVariable("quantidade") int quantidade) throws InterruptedException {
        for (int i = 0; i < quantidade; i++) {
            log.info("Logando {} de {}", i, quantidade);

            Message message = new Message();
            message.setTo("999");
            message.setFrom("998");
            message.setCompositionToFrom(CompositionHelper.concatValues(message.getTo(), message.getFrom()));
            message.setSendDate(System.currentTimeMillis());
            message.setStatus("SEND");

            messageRepository.save(message);

            TimeUnit.MILLISECONDS.sleep(2);
        }
    }

    @GetMapping
    public ResponseEntity<Page<Message>> getMessages(@RequestParam String to,
        @RequestParam String from,
        @RequestParam Long sendDate,
        @RequestParam int page, @RequestParam int size) {

        return ResponseEntity.ok(messageRepository.findByCompositionToFromAndSendDateLessThanEqual(CompositionHelper.concatValues(to, from),
            sendDate, PageRequest.of(page, size)));
    }

}
