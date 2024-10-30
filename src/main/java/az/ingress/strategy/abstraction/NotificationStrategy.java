package az.ingress.strategy.abstraction;

import az.ingress.model.dto.NotificationDto;
import az.ingress.model.enums.ChannelType;

public interface NotificationStrategy {
    void sendNotification(NotificationDto dto);
    ChannelType getChannel();
}
