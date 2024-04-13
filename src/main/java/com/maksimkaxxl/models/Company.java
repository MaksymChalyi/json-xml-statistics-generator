package com.maksimkaxxl.models;

import java.util.List;

/**
 * Клас, що представляє компанію зі списком співробітників.
 * (В даному завданні не використовується)
 */
public record Company(String name,
                      List<Employee> employees) {

}
