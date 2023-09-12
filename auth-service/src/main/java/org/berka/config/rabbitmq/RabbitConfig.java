package org.berka.config.rabbitmq;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {

    @Value("${rabbitmq.auth-exchange}")
    private String authExchange;

    @Value("${rabbitmq.register-queue}")
    private String registerQueue;

    @Value("${rabbitmq.register-bindingKey}")
    private String registerBindingKey;



    @Value("${rabbitmq.mail-queue}")
    private String mailQueueName;
    @Value("${rabbitmq.mail-bindingKey}")
    private String mailBindingKey;


    @Bean
    DirectExchange exchangeAuth(){
        return new DirectExchange(authExchange);
    }

    @Bean
    Queue registerQueue(){
        return new Queue(registerQueue);
    }

    @Bean
    public Binding registerBindingKey(final DirectExchange exchangeAuth,final Queue registerQueue){
        return BindingBuilder.bind(registerQueue).to(exchangeAuth).with(registerBindingKey);
    }


    @Bean
    Queue mailQueue() {
        return new Queue(mailQueueName);
    }

    @Bean
    public Binding mailActivation(final Queue mailQueue, final DirectExchange exchangeAuth){
        return BindingBuilder.bind(mailQueue).to(exchangeAuth).with(mailBindingKey);
    }
}
