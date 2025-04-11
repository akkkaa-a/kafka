package ru.val.kafka.producer.api.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.val.kafka.producer.domain.service.AdService;
import ru.val.kafka.producer.api.dto.UserAdDto;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
public class UserController {

    private final AdService adService;

    @PostMapping("/user")
    public void userAction(@RequestBody List<UserAdDto> actions) {
        log.info("Send ad");
        adService.sendAction(actions);
        log.info("Event sent from repo");
    }

}
