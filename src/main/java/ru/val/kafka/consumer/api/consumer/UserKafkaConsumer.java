package ru.val.kafka.consumer.api.consumer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;
import ru.val.kafka.consumer.api.converter.EventResponseConverter;
import ru.val.kafka.consumer.domain.model.Event;
import ru.val.kafka.consumer.domain.service.EventService;
import ru.val.kafka.dto.EventDto;

@Slf4j
@Component
@RequiredArgsConstructor
public class UserKafkaConsumer {

    private final EventService eventService;

    @KafkaListener(
            topics = "${spring.kafka.topic.name}",
            groupId = "${spring.kafka.consumer.group-id}",
            autoStartup = "${spring.kafka.consumer.auto-startup}",
            properties = "spring.json.value.default.type=ru.val.kafka.dto.EventDto"
    )
    public void listen(@Header(KafkaHeaders.OFFSET) Long offset,
                       @Header(KafkaHeaders.RECEIVED_PARTITION) Integer partition,
                       @Payload EventDto responseDto,
                       Acknowledgment acknowledgment
    ) {
        try {
            log.info("partition={}; offset={}; Start process", partition, offset);
            Event event = EventResponseConverter.toEvent(responseDto);
            eventService.writeAd(event);
            log.info("partition={}; offset={}; Process success", partition, offset);
            acknowledgment.acknowledge();
        } catch (Exception e) {
            log.error("partition={}; offset={}; Process failed", partition, offset);
            log.error(e.getMessage(), e);
            throw e;
        }
    }

}
