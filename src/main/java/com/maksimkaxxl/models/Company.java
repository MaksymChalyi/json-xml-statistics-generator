package com.maksimkaxxl.models;

import java.util.List;



public record Company(String name,
                      List<Employee> employees) {

}
