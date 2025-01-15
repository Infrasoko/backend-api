package com.laurent.infrasoko.common.communication;

import java.util.List;
import java.util.Map;

public interface NotificationService {
    void send(String recipient, String subject, String message, String templateName, Map<String, Object> templateData);

    void send(List<String> recipients, String subject, String message, String templateName, Map<String, Object> templateData);
}
