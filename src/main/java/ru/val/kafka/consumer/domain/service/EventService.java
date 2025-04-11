package ru.val.kafka.consumer.domain.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.val.kafka.consumer.domain.model.Event;
import ru.val.kafka.consumer.domain.model.UserAd;

@Slf4j
@Service
@RequiredArgsConstructor
public class EventService {

    private final static ObjectMapper MAPPER = new ObjectMapper()
            .registerModule(new JavaTimeModule())
            .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

    public void writeAd(Event event) {
        UserAd userAd;

        try {
            userAd = MAPPER.readValue(event.getData(), UserAd.class);
        } catch (JsonProcessingException e) {
            log.error(e.getMessage());
            throw new RuntimeException(e);
        }

        System.out.println("Received UserAction: "
                + userAd.getUserId() + " performed action: "
                + userAd.getAdId());
    }

}
