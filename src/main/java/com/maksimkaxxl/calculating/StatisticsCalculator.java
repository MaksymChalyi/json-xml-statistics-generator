package com.maksimkaxxl.calculating;

import com.maksimkaxxl.generator.XmlGenerator;
import com.maksimkaxxl.models.Employee;
import com.maksimkaxxl.parser.JSONParser;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class StatisticsCalculator {
    private static final String FOLDER_PATH_TO_JSON = "src/main/resources/json";
    private final String attributeName;

    public StatisticsCalculator(String attributeName) {
        this.attributeName = attributeName;
    }

    public void calculateAndSaveStatistics() {
        var employees = JSONParser.parseJsonFiles(FOLDER_PATH_TO_JSON);
        Map<String, Integer> attributeCountMap = calculateAttributeCount(employees);
        XmlGenerator.saveStatisticsToFile(attributeCountMap, attributeName);
    }

    private Map<String, Integer> calculateAttributeCount(List<Employee> employees) {
        Map<String, Integer> attributeCountMap = new HashMap<>();
        for (Employee employee : employees) {
            if (attributeName.equals("interests")) {
                for (String interest : employee.interests()) {
                    attributeCountMap.merge(interest, 1, Integer::sum);
                }
            } else {
                String attributeValue = switch (attributeName) {
                    case "name" -> employee.name();
                    case "position" -> employee.position();
                    case "age" -> String.valueOf(employee.age());
                    case "experienceYears" -> String.valueOf(employee.experienceYears());
                    default -> throw new IllegalArgumentException("Invalid attribute name");
                };
                attributeCountMap.merge(attributeValue, 1, Integer::sum);
            }
        }
        return attributeCountMap;
    }
}
