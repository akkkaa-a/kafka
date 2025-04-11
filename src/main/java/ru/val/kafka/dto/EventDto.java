package ru.val.kafka.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class EventDto {

    private String eventId;

    private LocalDateTime eventDate;

    private String data;

}
