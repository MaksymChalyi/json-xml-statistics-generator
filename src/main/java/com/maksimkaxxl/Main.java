package com.maksimkaxxl;

import com.maksimkaxxl.calculating.StatisticsCalculator;

import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static final String FOLDER_PATH_TO_JSON = "src/main/resources/json";
    private static final String FOLDER_PATH_OUT_OF_XML = "src/main/resources/xml/";

    /**
     * Головний метод програми, який зчитує назву атрибута від користувача
     * та викликає метод обчислення та збереження статистики для цього атрибута.
     */
    public static void main(String[] args) {
        System.out.print("Введіть назву атрибута, по якому формуватиметься статистика " +
                "(Наприклад: name, age, position, experienceYears, interests): ");
        String attribute = scanner.nextLine();

        StatisticsCalculator calculator = new StatisticsCalculator(attribute, FOLDER_PATH_TO_JSON, FOLDER_PATH_OUT_OF_XML);
        calculator.calculateAndSaveStatistics(FOLDER_PATH_OUT_OF_XML);
    }

}