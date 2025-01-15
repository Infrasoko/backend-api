package com.laurent.infrasoko.core.property;

public class InfrasokoProperties {
    /**
     * Checks if a given property value indicates an unset environment variable.
     * Unset environment variables are often represented in the form
     * "${some-environment-variable}"
     *
     * @param propertyValue The String value of a property to check
     * @return true if the propertyValue likely references an unset environment
     *         variable, false otherwise
     */
    static boolean isValidProperty(String propertyValue) {
        if (propertyValue == null) { // Handle null values
            return false;
        }
        return !(propertyValue.startsWith("${") && propertyValue.endsWith("}"));
    }
}
