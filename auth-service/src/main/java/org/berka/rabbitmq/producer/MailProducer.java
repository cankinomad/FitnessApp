package org.berka.rabbitmq.producer;

import lombok.RequiredArgsConstructor;
import org.berka.rabbitmq.model.MailModel;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MailProducer {

    private final RabbitTemplate rabbitTemplate;

    @Value("${rabbitmq.auth-exchange}")
    private String directExchange;


    @Value("${rabbitmq.mail-bindingKey}")
    private String mailBindingKey;

    public void sendMessage(MailModel model){
        rabbitTemplate.convertAndSend(directExchange,mailBindingKey,model);
    }
}
