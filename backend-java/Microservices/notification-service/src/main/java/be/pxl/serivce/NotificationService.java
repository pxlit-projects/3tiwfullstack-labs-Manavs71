package be.pxl.serivce;

import be.pxl.domain.Notification;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {
    private static final Logger log = LoggerFactory.getLogger(NotificationService.class);

    public void sendMessage(Notification notification) {
        log.info("Receiving notification...");
        log.info("Sending... {}", notification.getMessage());
        log.info("TO {}", notification.getSender());
    }
}
