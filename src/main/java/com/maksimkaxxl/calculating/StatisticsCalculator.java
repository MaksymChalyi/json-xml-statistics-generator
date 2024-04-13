package com.maksimkaxxl.calculating;

import com.maksimkaxxl.generator.XmlGenerator;
import com.maksimkaxxl.models.Employee;
import com.maksimkaxxl.parser.JSONParser;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Клас для обчислення та збереження статистики щодо певного атрибута у списку співробітників.
 */
public class StatisticsCalculator {
    private static final String FOLDER_PATH_TO_JSON = "src/main/resources/json";
    private final String attributeName;

    public StatisticsCalculator(String attributeName) {
        this.attributeName = attributeName;
    }

    /**
     * Обчислює та зберігає статистику щодо певного атрибута співробітників.
     */
    public void calculateAndSaveStatistics() {
        var employees = JSONParser.parseJsonFiles(FOLDER_PATH_TO_JSON);
        Map<String, Integer> attributeCountMap = calculateAttributeCount(employees);
        XmlGenerator.saveStatisticsToFile(attributeCountMap, attributeName);
    }

    /**
     * Обчислює кількість входжень кожного значення певного атрибута у списку співробітників.
     *
     * @param employees Список співробітників.
     * @return Відображення, де ключ - значення атрибута, а значення - кількість входжень цього значення.
     */
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
