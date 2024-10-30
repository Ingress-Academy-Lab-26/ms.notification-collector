package az.ingress.util;

import az.ingress.config.ConfigurationProperties;
import az.ingress.model.dto.NotificationDto;

public class NotificationUtil {

    public static String determineTargetQueue(NotificationDto notificationDto, ConfigurationProperties configurationProperties) {
        return switch (notificationDto.getChannelType()) {
            case MAIL -> configurationProperties.getMailQueue();
            case TELEGRAM -> configurationProperties.getTelegramQueue();
        };
    }

}
