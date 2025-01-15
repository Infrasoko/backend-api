package com.laurent.infrasoko.common.communication;

import com.laurent.infrasoko.common.exception.NotificationException;
import com.laurent.infrasoko.core.property.SmsProperties;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

@Service
public class SmsNotificationService implements NotificationService {
    private static final Logger logger = LoggerFactory.getLogger(SmsNotificationService.class);

    // Phone number regex pattern (basic international format)
    private static final String PHONE_REGEX = "^\\+[1-9]\\d{1,14}$";
    private static final Pattern phonePattern = Pattern.compile(PHONE_REGEX);

    private final String fromNumber;

    public SmsNotificationService(SmsProperties smsProperties) {
        Twilio.init(smsProperties.getAccountSid(), smsProperties.getAuthToken());
        this.fromNumber = smsProperties.getFromNumber();
    }

    @Override
    public void send(String recipient, String subject, String message, String templateName,
                     Map<String, Object> templateData) {
        send(List.of(recipient), subject, message, templateName, templateData);
    }

    @Override
    public void send(List<String> recipients, String subject, String message, String templateName,
                     Map<String, Object> templateData) {
        String content;
        if (templateName != null) {
            content = renderTemplate(templateName, templateData);
        } else {
            content = message;
        }

        // Prepend subject if provided
        if (subject != null && !subject.isEmpty()) {
            content = subject + ": " + content;
        }

        for (String recipient : recipients) {
            if (!isPhoneNumberValid(recipient)) {
                logger.error("Invalid phone number format: {}", recipient);
                throw new NotificationException("Invalid phone number format: " + recipient);
            }

            try {
                Message twilioMessage = Message.creator(
                        new PhoneNumber(recipient),
                        new PhoneNumber(fromNumber),
                        content
                ).create();

                logger.info("SMS sent successfully to {}, SID: {}", recipient, twilioMessage.getSid());
            } catch (Exception e) {
                logger.error("Failed to send SMS to {}: {}", recipient, e.getMessage());
                throw new NotificationException("Failed to send SMS to " + recipient + ": " +
                        e.getLocalizedMessage());
            }
        }
    }

    private String renderTemplate(String templateName, Map<String, Object> templateData) {
        return templateData.entrySet().stream()
                .reduce(templateName, (msg, entry) ->
                                msg.replace("{{" + entry.getKey() + "}}",
                                        String.valueOf(entry.getValue())),
                        String::concat);
    }

    public static boolean isPhoneNumberValid(String phoneNumber) {
        if (phoneNumber == null || phoneNumber.trim().isEmpty()) {
            return false;
        }
        return phonePattern.matcher(phoneNumber).matches();
    }
}