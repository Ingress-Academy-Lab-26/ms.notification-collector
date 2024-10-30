package az.ingress.strategy.abstraction;

import az.ingress.config.ConfigurationProperties;
import az.ingress.model.dto.NotificationDto;
import az.ingress.queue.sender.QueueSender;
import az.ingress.util.NotificationUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public abstract class AbstractNotificationStrategy implements NotificationStrategy {
    private final QueueSender queueSender;
    private final ConfigurationProperties configurationProperties;

    protected void sendToQueue(NotificationDto dto) {
        var targetQueue = NotificationUtil.determineTargetQueue(dto, configurationProperties);
        queueSender.sendMessageToQ(targetQueue, dto.getPayload());
    }
}
