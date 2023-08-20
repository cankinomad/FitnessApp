package org.berka.rabbitmq.consumer;

import lombok.RequiredArgsConstructor;
import org.berka.rabbitmq.model.RegisterModel;
import org.berka.service.UserProfileService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RegisterConsumer {

    private final UserProfileService service;

    @RabbitListener(queues = "${rabbitmq.register-queue}")
    public void registerUser(RegisterModel model){
        service.registerWithRabbitMq(model);
    }
}
