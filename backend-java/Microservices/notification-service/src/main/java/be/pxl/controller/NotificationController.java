package be.pxl.controller;

import be.pxl.domain.Notification;
import be.pxl.serivce.NotificationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.bouncycastle.crypto.agreement.jpake.JPAKEUtil;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/notification")
public class NotificationController {

    private final NotificationService notificationService;


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void sendMessage(@RequestBody Notification notification){
        notificationService.sendMessage(notification);
    }

}
