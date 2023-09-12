package org.berka.rabbitmq.consumer;

import lombok.RequiredArgsConstructor;
import org.berka.rabbitmq.model.MailModel;
import org.berka.service.MailSenderService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MailConsumer {

    private final MailSenderService mailSenderService;

    @RabbitListener(queues = "${rabbitmq.mail-queue}")
    public void sendMail(MailModel mailModel){
        mailSenderService.sendMail(mailModel);
    }
}
