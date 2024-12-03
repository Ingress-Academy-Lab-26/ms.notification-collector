package az.ingress.queue.listener;

import az.ingress.exception.QueueException;
import az.ingress.model.dto.NotificationDto;
import az.ingress.service.abstraction.NotificationService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import static az.ingress.model.enums.ExceptionConstant.QUEUE_EXCEPTION;

@Slf4j
@Component
@RequiredArgsConstructor
public class OrderProcessingAndStockMonitoringListener {
    private final NotificationService notificationService;
    private final ObjectMapper objectMapper;

    @RabbitListener(queues = "${rabbitmq.queue.notification}")
    public void listener(String message) {
        try {
            var dto = objectMapper.readValue(message, NotificationDto.class);
            notificationService.handleNotification(dto);
        } catch (JsonProcessingException ex) {
            log.error("ActionLog.consume.error message invalid format: {}", message);
        } catch (Exception ex) {
            throw new QueueException(QUEUE_EXCEPTION.getCode(), QUEUE_EXCEPTION.getMessage());
        }
    }

}