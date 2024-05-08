package com.example.basic.utils;

import lombok.experimental.UtilityClass;

import java.util.Collection;


@UtilityClass
public class Utils {

    public static boolean isNullOrEmpty(Object object) {
        if (object == null) return true;
        return object instanceof String && ((String) object).isBlank();
    }

    public static boolean isNullOrEmpty(Collection collection) {
        return collection == null || collection.isEmpty();
    }
}
