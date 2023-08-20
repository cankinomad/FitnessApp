package org.berka.rabbitmq.producer;

import lombok.RequiredArgsConstructor;
import org.berka.rabbitmq.model.RegisterModel;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RegisterProducer {
    @Value("${rabbitmq.auth-exchange}")
    private String authExchange;
    @Value("${rabbitmq.register-bindingKey}")
    private String registerBindingKey;

     final RabbitTemplate rabbitTemplate;

    public void registerUser(RegisterModel model) {
        rabbitTemplate.convertAndSend(authExchange,registerBindingKey,model);
    }
}
