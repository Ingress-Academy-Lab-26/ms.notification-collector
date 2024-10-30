package az.ingress.service.concrete;

import az.ingress.aop.annotation.Log;
import az.ingress.model.dto.NotificationDto;
import az.ingress.service.abstraction.NotificationService;
import az.ingress.strategy.factory.NotificationStrategyFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Log
@Service
@RequiredArgsConstructor
public class NotificationServiceHandler implements NotificationService {
    private final NotificationStrategyFactory strategyFactory;

    @Override
    public void handleNotification(NotificationDto notificationDto) {
        strategyFactory.getStrategy(notificationDto.getChannelType()).sendNotification(notificationDto);
    }
}
