package az.ingress.strategy.factory;

import az.ingress.model.enums.ChannelType;
import az.ingress.strategy.abstraction.NotificationStrategy;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class NotificationStrategyFactory {
    private final Map<ChannelType, NotificationStrategy> strategies;

    public NotificationStrategyFactory(List<NotificationStrategy> notificationStrategies) {
        this.strategies = new HashMap<>();
        notificationStrategies.forEach(it -> strategies.put(it.getChannel(), it));
    }

    public NotificationStrategy getStrategy(ChannelType type) {
        return strategies.get(type);
    }
}