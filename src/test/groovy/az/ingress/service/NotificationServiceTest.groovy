package az.ingress.service

import az.ingress.exception.QueueException
import az.ingress.model.dto.NotificationDto
import az.ingress.service.abstraction.NotificationService
import az.ingress.service.concrete.NotificationServiceHandler
import az.ingress.strategy.abstraction.NotificationStrategy
import az.ingress.strategy.factory.NotificationStrategyFactory
import io.github.benas.randombeans.EnhancedRandomBuilder
import io.github.benas.randombeans.api.EnhancedRandom
import spock.lang.Specification

class NotificationServiceTest extends Specification {
    EnhancedRandom random = EnhancedRandomBuilder.aNewEnhancedRandom()
    NotificationStrategyFactory notificationStrategyFactory
    NotificationStrategy notificationStrategy
    NotificationService notificationService

    def setup() {
        notificationStrategyFactory = Mock()
        notificationStrategy = Mock()
        notificationService = new NotificationServiceHandler(notificationStrategyFactory)
    }

    def "TestHandleNotification success case"() {
        given:
        def notificationDto = random.nextObject(NotificationDto)

        when:
        notificationService.handleNotification(notificationDto)

        then:
        1 * notificationStrategyFactory.getStrategy(notificationDto.getChannelType()) >> notificationStrategy
        1 * notificationStrategy.sendNotification(notificationDto)
    }

    def "TestHandleNotification when strategy throws exception"() {
        given:
        def notificationDto = random.nextObject(NotificationDto)

        when:
        notificationService.handleNotification(notificationDto)

        then:
        1 * notificationStrategyFactory.getStrategy(notificationDto.getChannelType()) >> notificationStrategy
        1 * notificationStrategy.sendNotification(notificationDto) >> {
            throw new Exception()
        }
        thrown(QueueException)
    }

    def "TestHandleNotification when strategy throw JsonProcessingException"() {
        given:
        def notificationDto = random.nextObject(NotificationDto)

        when:
        notificationService.handleNotification(notificationDto)

        then:
        1 * notificationStrategyFactory.getStrategy(notificationDto.getChannelType()) >> notificationStrategy
        1 * notificationStrategy.sendNotification(notificationDto) >> {
            throw new Exception()
        }
        noExceptionThrown()
    }

}
