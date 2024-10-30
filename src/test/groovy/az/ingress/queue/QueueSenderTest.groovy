package az.ingress.queue

import az.ingress.model.dto.NotificationDto
import az.ingress.queue.sender.QueueSender
import com.fasterxml.jackson.databind.ObjectMapper
import io.github.benas.randombeans.EnhancedRandomBuilder
import io.github.benas.randombeans.api.EnhancedRandom
import org.springframework.amqp.core.AmqpTemplate
import spock.lang.Specification

class QueueSenderTest extends Specification {
    EnhancedRandom random = EnhancedRandomBuilder.aNewEnhancedRandom()
    AmqpTemplate amqpTemplate
    ObjectMapper objectMapper
    QueueSender queueSender

    def setup() {
        amqpTemplate = Mock()
        objectMapper = Mock()
        queueSender = new QueueSender(amqpTemplate, objectMapper)
    }

    def "TestSendMessageToQ"() {
        given:
        def queueName = random.nextObject(String)
        def notificationDto = random.nextObject(NotificationDto)

        when:
        queueSender.sendMessageToQ(queueName, notificationDto)

        then:
        1 * amqpTemplate.convertAndSend(queueName, objectMapper.writeValueAsString(notificationDto))
    }

}
