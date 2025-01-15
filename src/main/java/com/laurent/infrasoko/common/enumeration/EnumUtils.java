package com.laurent.infrasoko.common.enumeration;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Utility class for handling operations related to enums implementing the
 * BaseEnum interface.
 */
public class EnumUtils {
    /**
     * Converts the constants of the specified enum class into a list of maps.
     * Each map contains the enum constant's name (key) and its display value
     * (display).
     *
     * @param <T>       the type of the enum that extends Enum<T> and implements
     *                  BaseEnum
     * @param enumClass the class object of the enum type
     * @return a list of maps, where each map contains the enum constant's name and
     *         display value
     */
    public static <T extends Enum<T> & BaseEnum> List<Map<String, Object>> getEnumValuesAsList(
            Class<? extends BaseEnum> enumClass) {

        Object[] constants = enumClass.getEnumConstants();

        List<Map<String, Object>> valuesAsList = new ArrayList<>();

        try {
            Method getDisplayMethod = enumClass.getMethod("getDisplay");
            for (Object obj : constants) {
                try {
                    valuesAsList.add(Map.of("key", obj.toString(), "display", getDisplayMethod.invoke(obj)));
                } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        } catch (NoSuchMethodException | SecurityException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return valuesAsList;
    }

    /**
     * Converts the constants of multiple enums into a map of lists.
     * Each entry in the resulting map contains the enum type name (key) and a list
     * of maps,
     * where each map contains the enum constant's name and display value.
     *
     * @param enumMap a map where each key is the name of the enum type and the
     *                value is the class object of the enum type
     * @return a map where each key is the name of the enum type and the value is a
     *         list of maps containing the enum constants' names and display values
     */
    public static Map<String, List<Map<String, Object>>> getAllEnumValues(
            Map<String, Class<? extends BaseEnum>> enumMap) {

        Map<String, List<Map<String, Object>>> result = new HashMap<>();

        for (Map.Entry<String, Class<? extends BaseEnum>> entry : enumMap.entrySet()) {
            result.put(entry.getKey(), getEnumValuesAsList(entry.getValue()));
        }
        return result;
    }
}
