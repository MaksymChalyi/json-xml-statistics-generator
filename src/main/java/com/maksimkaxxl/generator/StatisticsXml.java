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

    private List<Item> items;

    public StatisticsXml() {
        this.items = new ArrayList<>();
    }

    @JacksonXmlElementWrapper(useWrapping = false)
    @JacksonXmlProperty(localName = "item")
    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public void addItem(Item item) {
        this.items.add(item);
    }

/**
 * Внутрішній клас, що представляє окремий елемент статистики.
 * Кожен елемент має значення та кількість входжень.
 */
    public static class Item {
        private String value;
        private int count;

        public Item() {
        }

        public Item(String value, int count) {
            this.value = value;
            this.count = count;
        }

        @JacksonXmlProperty(localName = "value")
        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

        @JacksonXmlProperty(localName = "count")
        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
        }
    }
}
