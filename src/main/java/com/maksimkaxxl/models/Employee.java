package com.maksimkaxxl.models;

import java.util.List;

/**
 * Клас, що представляє співробітника з основними характеристиками.
 */
public record Employee(String name,
                       Integer age,
                       String position,
                       Integer experienceYears,
                       List<String> interests) {
}
