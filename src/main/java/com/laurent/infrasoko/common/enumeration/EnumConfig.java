package com.laurent.infrasoko.common.enumeration;

import com.laurent.infrasoko.common.model.*;
import org.springframework.stereotype.Component;
import java.util.HashMap;
import java.util.Map;

/**
 * Configuration class for managing enum mappings.
 */
@Component
public class EnumConfig {
    /**
     * Provides a map of enum type unique keys to their corresponding enum classes.
     *
     * @return a map where the key is the enum type name and the value is the enum
     * class
     */
    public Map<String, Class<? extends BaseEnum>> enumMap() {
        HashMap<String, Class<? extends BaseEnum>> enumMap = new HashMap<>();

        enumMap.put("projectStatus", ProjectStatus.class);
        enumMap.put("projectType", ProjectType.class);
        enumMap.put("projectPriority", ProjectPriority.class);
        enumMap.put("userRoleType", UserRoleType.class);
        enumMap.put("materialRequestStatus", MaterialRequestStatus.class);
        enumMap.put("bidStatus", BidStatus.class);
        enumMap.put("maintenanceCategory", MaintenanceCategory.class);
        enumMap.put("requestPriority", RequestPriority.class);
        enumMap.put("contractStatus", ContractStatus.class);
        enumMap.put("guaranteeType", GuaranteeType.class);
        enumMap.put("projectStage", ProjectStage.class);
        enumMap.put("invoiceStatus", InvoiceStatus.class);
        enumMap.put("documentType", DocumentType.class);
        enumMap.put("budgetCategory", BudgetCategory.class);
        enumMap.put("paymentTerms", PaymentTerms.class);
        enumMap.put("qualityCheckStatus", QualityCheckStatus.class);
        enumMap.put("accessLevel", AccessLevel.class);
        enumMap.put("documentSecurityLevel", DocumentSecurityLevel.class);
        enumMap.put("notificationType", NotificationType.class);
        enumMap.put("timePeriod", TimePeriod.class);
        enumMap.put("defectStatus", DefectStatus.class);

        return enumMap;
    }
}
