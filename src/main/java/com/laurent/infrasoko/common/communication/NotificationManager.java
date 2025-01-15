package com.laurent.infrasoko.common.communication;

import com.laurent.infrasoko.common.exception.InvalidOperationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class NotificationManager {

    private final EmailNotificationService emailService;
    private final SmsNotificationService smsService;

    public NotificationManager(EmailNotificationService emailService, SmsNotificationService smsService) {
        this.emailService = emailService;
        this.smsService = smsService;
    }

    public void sendNotification(NotificationChannel channel, String recipient, String subject, String message,
                                 String templateName, Map<String, Object> templateData) {
        sendNotification(channel, List.of(recipient), subject, message, templateName, templateData);
    }

    public void sendNotification(NotificationChannel channel, List<String> recipients, String subject, String message,
                                 String templateName, Map<String, Object> templateData) {
        switch (channel) {
            case EMAIL -> emailService.send(recipients, subject, message, templateName, templateData);
            case SMS -> smsService.send(recipients, subject, message, templateName, templateData);
            default -> throw new InvalidOperationException("Unsupported notification channel: " + channel);
        }
    }

    public enum NotificationChannel {
        EMAIL,
        SMS
    }
}
