package ru.val.kafka.producer.domain.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.val.kafka.producer.domain.model.Event;
import ru.val.kafka.producer.api.dto.UserAdDto;
import ru.val.kafka.producer.infrastructure.producer.UserSendRepository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AdService {

    private final UserSendRepository userSendRepository;

    public void sendAction(List<UserAdDto> userAds) {
        List<Event<UserAdDto>> events = buildEvent(userAds);
        events.forEach(userSendRepository::sendUserAction);
    }

    private static <T> List<Event<T>> buildEvent(List<T> userAds) {
        List<Event<T>> events = new ArrayList<>();

        userAds.forEach(userAd-> {
            Event<T> event = new Event<>();
            event.setEventId(UUID.randomUUID().toString());
            event.setEventDate(LocalDateTime.now());
            event.setData(userAd);

            events.add(event);
        });

        return events;
    }
}
