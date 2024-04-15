package com.maksimkaxxl.generator;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import java.util.ArrayList;
import java.util.List;

/**
 * Клас, що відповідає за створення структури XML для представлення статистики.
 * Використовується для генерації XML-файлів зі статистичною інформацією.
 */
@JacksonXmlRootElement(localName = "statistics")
public class StatisticsXml {

    private final List<Item> items;

    public StatisticsXml() {
        this.items = new ArrayList<>();
    }

    @JacksonXmlElementWrapper(useWrapping = false)
    @JacksonXmlProperty(localName = "item")
    public List<Item> getItems() {
        return items;
    }

    public void addItem(Item item) {
        this.items.add(item);
    }

    /**
     * Внутрішній клас(record), що представляє окремий елемент статистики.
     * Кожен елемент має значення та кількість входжень.
     */
    public record Item(String value,
                       int count) {

    }
}
