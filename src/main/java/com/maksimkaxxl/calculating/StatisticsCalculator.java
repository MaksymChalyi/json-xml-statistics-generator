package com.maksimkaxxl.calculating;

import com.maksimkaxxl.generator.XmlGenerator;
import com.maksimkaxxl.models.Employee;
import com.maksimkaxxl.parser.JSONParser;
import com.maksimkaxxl.utils.Constants;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.maksimkaxxl.utils.Constants.AttributeConstants.*;
import static com.maksimkaxxl.utils.Constants.ErrorMessages.INVALID_ATTRIBUTE_NAME;


/**
 * Клас для обчислення та збереження статистики щодо певного атрибута у списку співробітників.
 */
public class StatisticsCalculator {
    private final String attributeName;
    private final String folderPathToJson;

    public StatisticsCalculator(String attributeName, String folderPathToJson) {
        this.attributeName = attributeName;
        this.folderPathToJson = folderPathToJson;
    }

    /**
     * Обчислює та зберігає статистику щодо певного атрибута співробітників.
     */
    public void calculateAndSaveStatistics(String folderPathToXml) {
        var employees = JSONParser.parseJsonFiles(folderPathToJson);
        Map<String, Integer> attributeCountMap = calculateAttributeCount(employees);
        XmlGenerator.saveStatisticsToFile(attributeCountMap, attributeName, folderPathToXml);
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
            if (attributeName.equals(Constants.AttributeConstants.INTERESTS)) {
                for (String interest : employee.interests()) {
                    attributeCountMap.merge(interest, 1, Integer::sum);
                }
            } else {
                String attributeValue = switch (attributeName) {
                    case NAME -> employee.name();
                    case POSITION -> employee.position();
                    case AGE -> String.valueOf(employee.age());
                    case EXPERIENCE_YEARS -> String.valueOf(employee.experienceYears());
                    case COMPANY -> employee.company().name();
                    default -> throw new IllegalArgumentException(INVALID_ATTRIBUTE_NAME);
                };
                attributeCountMap.merge(attributeValue, 1, Integer::sum);
            }
        }
        return attributeCountMap;
    }
}