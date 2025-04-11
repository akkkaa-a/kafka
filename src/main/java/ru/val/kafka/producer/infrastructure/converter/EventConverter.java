package ru.val.kafka.producer.infrastructure.converter;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.apache.commons.lang3.ObjectUtils;
import ru.val.kafka.dto.EventDto;
import ru.val.kafka.producer.domain.model.Event;

public class EventConverter {

    private final static ObjectMapper MAPPER = new ObjectMapper()
            .registerModule(new JavaTimeModule())
            .setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);

    public static EventDto toEventDto(Event<?> event) {
        if (ObjectUtils.isEmpty(event)) {
            return null;
        }

        EventDto eventDto = new EventDto();
        eventDto.setEventId(event.getEventId());
        eventDto.setEventDate(event.getEventDate());

        try {
            eventDto.setData(MAPPER.writeValueAsString(event.getData()));
        } catch (JsonProcessingException e) {
            //ignore
        }

        return eventDto;
    }
}
