package az.ingress.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
@Data
public class ConfigurationProperties {

    @Value("${rabbitmq.queue.notification}")
    private String notificationQueue;

    @Value("${rabbitmq.queue.notification-dlq}")
    private String notificationDLQ;

    @Value("${rabbitmq.queue.mail}")
    private String mailQueue;

    @Value("${rabbitmq.queue.telegram}")
    private String telegramQueue;

}
