package com.asos.carrental.utils;

import java.util.EnumSet;

public class TypeFetcher<E> {

    public static <E extends Enum<E>> E getType(Class<E> clazz, String typeName) {

        for (E type : EnumSet.allOf(clazz)) {
            if (typeName.equalsIgnoreCase(type.name())) {
                return type;
            }
        }
        return null;

    }
}
