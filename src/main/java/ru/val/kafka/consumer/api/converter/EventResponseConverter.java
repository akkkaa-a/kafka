package ru.val.kafka.consumer.api.converter;

import org.apache.commons.lang3.ObjectUtils;
import ru.val.kafka.consumer.domain.model.Event;
import ru.val.kafka.dto.EventDto;

public class EventResponseConverter {

    public static Event toEvent(EventDto dto) {
        if (ObjectUtils.isEmpty(dto)) {
            return null;
        }

        Event event = new Event();
        event.setEventId(dto.getEventId());
        event.setEventDate(dto.getEventDate());
        event.setData(dto.getData());

        return event;
    }

}
