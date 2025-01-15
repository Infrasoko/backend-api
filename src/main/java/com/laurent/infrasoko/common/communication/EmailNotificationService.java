package com.laurent.infrasoko.common.communication;

import com.laurent.infrasoko.common.exception.NotificationException;
import com.laurent.infrasoko.core.property.MailProperties;
import jakarta.mail.internet.MimeMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

@Service
public class EmailNotificationService implements NotificationService {
    private final JavaMailSender mailSender;
    private final TemplateEngine templateEngine;
    private final String fromEmail;

    // RFC 5322 regular expression for email validation
    static String emailRegexPattern = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$";

    public EmailNotificationService(JavaMailSender mailSender, TemplateEngine templateEngine,
                                    MailProperties mailProperties) {
        this.mailSender = mailSender;
        this.templateEngine = templateEngine;
        this.fromEmail = mailProperties.getUsername();
    }

    @Override
    public void send(String recipient, String subject, String message, String templateName,
                     Map<String, Object> templateData) {
        send(List.of(recipient), subject, message, templateName, templateData);
    }

    @Override
    public void send(List<String> recipients, String subject, String message, String templateName,
                     Map<String, Object> templateData) {
        String body;
        if (templateName != null) {
            Context context = new Context();
            context.setVariables(templateData);
            body = templateEngine.process(templateName, context);
        } else {
            body = message;
        }

        try {
            MimeMessage msg = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(msg);

            helper.setFrom(fromEmail, "Infrasoko Notification");
            helper.setTo(recipients.toArray(new String[0]));
            helper.setSubject(subject);
            helper.setText(body, true);

            mailSender.send(msg);
        } catch (Exception e) {
            throw new NotificationException("Sending email failed. " + e.getLocalizedMessage());
        }
    }

    public static boolean isEmailValid(String emailAddress) {
        return Pattern.compile(emailRegexPattern).matcher(emailAddress).matches();
    }
}