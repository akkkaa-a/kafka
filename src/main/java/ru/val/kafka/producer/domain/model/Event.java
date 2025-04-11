package ru.val.kafka.producer.domain.model;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class Event<T> {

    private String eventId;

    private LocalDateTime eventDate;

    private T data;

}
