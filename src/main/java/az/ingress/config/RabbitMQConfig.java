package az.ingress.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {
    private final String notificationQ;
    private final String notificationDLQ;
    private final String notificationQExchange;
    private final String notificationDLQExchange;
    private final String notificationQKey;
    private final String notificationDQLKey;

    public RabbitMQConfig(@Value("${rabbitmq.queue.notification}") String notificationQ,
                          @Value("${rabbitmq.queue.notification-dlq}") String notificationDLQ) {
        this.notificationQ = notificationQ;
        this.notificationDLQ = notificationDLQ;
        this.notificationQExchange = notificationQ + "_Exchange";
        this.notificationDLQExchange = notificationDLQ + "_Exchange";
        this.notificationQKey = notificationQ + "_Key";
        this.notificationDQLKey = notificationDLQ + "_Key";
    }

    @Bean
    DirectExchange notificationDLQExchange() {
        return new DirectExchange(notificationDLQExchange);
    }

    @Bean
    DirectExchange notificationQExchange() {
        return new DirectExchange(notificationQExchange);
    }

    @Bean
    Queue notificationDLQ() {
        return QueueBuilder.durable(notificationDLQ).build();
    }

    @Bean
    Queue notificationQ() {
        return QueueBuilder.durable(notificationQ)
                .withArgument("x-dead-letter-exchange", notificationDLQExchange)
                .withArgument("x-dead-letter-routing-key", notificationDQLKey)
                .build();
    }

    @Bean
    Binding notificationDLQBuilding() {
        return BindingBuilder.bind(notificationDLQ())
                .to(notificationDLQExchange()).with(notificationDQLKey);
    }

    @Bean
    Binding notificationQBinding() {
        return BindingBuilder.bind(notificationQ())
                .to(notificationQExchange()).with(notificationQKey);
    }

}
