package az.ingress.util

import az.ingress.config.ConfigurationProperties
import az.ingress.model.dto.NotificationDto
import spock.lang.Specification
import spock.lang.Unroll

import static az.ingress.model.enums.ChannelType.MAIL
import static az.ingress.model.enums.ChannelType.TELEGRAM
import static az.ingress.util.NotificationUtil.determineTargetQueue

class NotificationUtilTest extends Specification {
    ConfigurationProperties rabbitMQProperties

    def setup() {
        rabbitMQProperties = Mock()
        rabbitMQProperties.getMailQueue() >> "mailQueue"
        rabbitMQProperties.getTelegramQueue() >> "telegramQueue"
    }

    @Unroll
    def "determineTargetQueue should return #expectedQueue for #channelType channel type"() {
        given:
        def notificationDto = NotificationDto.builder()
                .channelType(channelType)
                .build()

        when:
        def result = determineTargetQueue(notificationDto, rabbitMQProperties)

        then:
        result == expectedQueue

        where:
        channelType | expectedQueue
        TELEGRAM    | "telegramQueue"
        MAIL        | "mailQueue"
    }

}
