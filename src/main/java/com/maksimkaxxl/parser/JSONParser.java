package com.maksimkaxxl.parser;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.maksimkaxxl.models.Employee;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class JSONParser {

    private static final ObjectMapper objectMapper;

    static {
        ObjectMapper mapper = new ObjectMapper();
        // Не створювати exception, якщо json має додаткові поля
        // без серіалізації. Це корисно, коли ви хочете використовувати pojo
        // для десеріалізації та дбає лише про частину json
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        // Ігноруйте нульові значення під час запису json.
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        objectMapper = mapper;
    }

    public static List<Employee> parseJsonFiles(String folderPath) {
        List<Employee> employees = new ArrayList<>();
        try {
            List<File> files = List.of(Objects.requireNonNull(new File(folderPath).listFiles()));
            ExecutorService executor = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
            List<Future<List<Employee>>> futures = new ArrayList<>();
            for (File file : files) {
                futures.add(executor.submit(() -> parseJsonFile(file)));
            }
            for (Future<List<Employee>> future : futures) {
                employees.addAll(future.get());
            }
            executor.shutdown();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        return employees;
    }

    private static List<Employee> parseJsonFile(File file) {
        List<Employee> employees = new ArrayList<>();
        try {
            List<Employee> parsedEmployees = objectMapper.readValue(file, new TypeReference<>() {
            });
            employees.addAll(parsedEmployees);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return employees;
    }
}
