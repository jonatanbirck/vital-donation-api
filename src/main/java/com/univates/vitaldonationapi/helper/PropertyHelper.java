package com.univates.vitaldonationapi.helper;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

import java.beans.PropertyDescriptor;
import java.util.HashSet;
import java.util.Set;

public class PropertyHelper {

    private PropertyHelper() {
        throw new IllegalStateException("Utility class");
    }

    public static void copy(Object source, Object target) {
        BeanUtils.copyProperties(source, target, getNullPropertiesString(source));
    }

    private static String[] getNullPropertiesString(Object source) {
        Set<String> emptyNames = getNullProperties(source);
        String[] result = new String[emptyNames.size()];

        return emptyNames.toArray(result);
    }

    private static Set<String> getNullProperties(Object source) {
        final BeanWrapper src = new BeanWrapperImpl(source);
        PropertyDescriptor[] pds = src.getPropertyDescriptors();

        Set<String> emptyNames = new HashSet<>();
        for (PropertyDescriptor pd : pds) {
            Object srcValue = src.getPropertyValue(pd.getName());
            if (srcValue == null)
                emptyNames.add(pd.getName());
        }
        return emptyNames;
    }

}
