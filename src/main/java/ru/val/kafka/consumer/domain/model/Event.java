package ru.val.kafka.consumer.domain.model;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class Event {

    private String eventId;

    private LocalDateTime eventDate;

    private String data;

}
