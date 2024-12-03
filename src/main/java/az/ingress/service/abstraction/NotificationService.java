package az.ingress.service.abstraction;

import az.ingress.model.dto.NotificationDto;

public interface NotificationService {
    void handleNotification(NotificationDto notificationDto);
}
