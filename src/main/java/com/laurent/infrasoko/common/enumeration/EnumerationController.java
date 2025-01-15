package com.laurent.infrasoko.common.enumeration;

import com.laurent.infrasoko.common.GenericResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@Tag(name = "System Enumerations")
public class EnumerationController {

    private final EnumConfig enumConfig;

    @Autowired
    public EnumerationController(EnumConfig enumConfig) {
        this.enumConfig = enumConfig;
    }

    @GetMapping("/api/v1/core/options")
    @Operation(summary = "Get All System Enumerations")
    public GenericResponse<?> getAllEnumValues(@RequestParam(value = "type", required = false) String optionType) {

        Map<String, Class<? extends BaseEnum>> enumMap = enumConfig.enumMap();

        if (optionType != null && enumMap.containsKey(optionType)) {
            enumMap = Map.of(optionType, enumMap.get(optionType));
        }

        Map<String, List<Map<String, Object>>> result = EnumUtils.getAllEnumValues(enumMap);

        return new GenericResponse.Builder<Map<String, List<Map<String, Object>>>>()
                .status(HttpStatus.OK)
                .message("Enumerations were successfully retrieved")
                .data(result)
                .build();
    }
}
