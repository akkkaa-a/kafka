package ru.val.kafka.producer.infrastructure.producer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Repository;
import ru.val.kafka.producer.domain.model.Event;
import ru.val.kafka.producer.infrastructure.configuration.Environment;
import ru.val.kafka.producer.infrastructure.converter.EventConverter;
import ru.val.kafka.dto.EventDto;

@Slf4j
@Repository
@RequiredArgsConstructor
public class UserSendRepository {

    @Value(Environment.KAFKA_EVENT_TOPIC_NAME_KEY)
    private String topic;
    private final KafkaTemplate<String, EventDto> kafkaTemplate;

    public void sendUserAction(Event<?> event) {
        EventDto eventDto = EventConverter.toEventDto(event);

        log.info("Send event by kafka");
        kafkaTemplate.send(topic, eventDto);
        log.info("Event send by kafka");
    }

}
