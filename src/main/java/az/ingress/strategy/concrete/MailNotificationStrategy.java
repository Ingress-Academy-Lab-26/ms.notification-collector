package az.ingress.strategy.concrete;

import az.ingress.config.ConfigurationProperties;
import az.ingress.model.dto.NotificationDto;
import az.ingress.model.enums.ChannelType;
import az.ingress.queue.sender.QueueSender;
import az.ingress.strategy.abstraction.AbstractNotificationStrategy;
import org.springframework.stereotype.Component;

import static az.ingress.model.enums.ChannelType.MAIL;

@Component
public class MailNotificationStrategy extends AbstractNotificationStrategy {
    public MailNotificationStrategy(QueueSender queueSender, ConfigurationProperties configurationProperties) {
        super(queueSender, configurationProperties);
    }

    @Override
    public void sendNotification(NotificationDto dto) {
        sendToQueue(dto);
    }

    @Override
    public ChannelType getChannel() {
        return MAIL;
    }
}
