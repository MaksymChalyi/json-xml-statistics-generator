package com.maksimkaxxl.generator;

import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * Клас, що відповідає за генерацію XML-файлів для статистики.
 */
public class XmlGenerator {
    /**
     * Зберігає статистику у файл XML.
     *
     * @param attributeCountMap мапа, що містить значення атрибутів та їх кількість
     * @param attributeName     ім'я атрибуту
     */
    public static void saveStatisticsToFile(Map<String, Integer> attributeCountMap, String attributeName, String folderPathToXml) {
        try {
            String fileName = "statistics_by_" + attributeName + ".xml";

            XmlMapper xmlMapper = new XmlMapper();
            xmlMapper.enable(SerializationFeature.INDENT_OUTPUT);

            List<Map.Entry<String, Integer>> sortedEntries = attributeCountMap.entrySet().stream()
                    .sorted(Collections.reverseOrder(Map.Entry.comparingByValue()))
                    .toList();

            StatisticsXml statisticsXml = new StatisticsXml();
            for (Map.Entry<String, Integer> entry : sortedEntries) {
                statisticsXml.addItem(new StatisticsXml.Item(entry.getKey(), entry.getValue()));
            }

            xmlMapper.writeValue(new File(folderPathToXml + fileName), statisticsXml);
            System.out.println("Statistics saved to " + fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}