package com.maksimkaxxl.models;

import java.util.List;

public record Employee(String name,
                       Integer age,
                       String position,
                       Integer experienceYears,
                       List<String> interests) {
}
