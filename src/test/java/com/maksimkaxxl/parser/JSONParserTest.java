package com.maksimkaxxl.parser;

import com.maksimkaxxl.models.Employee;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

class JSONParserTest {

    @Test
    void parseJsonFiles_ValidFolderPath_ReturnsNonEmptyList() {
        // Given
        String folderPath = "src/test/resources/json";

        // When
        List<Employee> employees = JSONParser.parseJsonFiles(folderPath);

        // Then
        Assertions.assertFalse(employees.isEmpty());
    }


    @Test
    void parseJsonFiles_InvalidFolderPath_ReturnsNullPointerException() {
        // Given
        String folderPath = "invalid/path";

        // When & Then
        Assertions.assertThrows(NullPointerException.class, () -> {
            JSONParser.parseJsonFiles(folderPath);
        });
    }

    @Test
    void parseJsonFiles_EmptyFolderPath_ReturnsNullPointerException() {
        // Given
        String folderPath = "";

        // When & Then
        Assertions.assertThrows(NullPointerException.class, () -> {
            JSONParser.parseJsonFiles(folderPath);
        });
    }

    @Test
    void parseJsonFiles_NullFolderPath_ReturnsNullPointerException() {
        // Given
        String folderPath = null;

        // When & Then
        Assertions.assertThrows(NullPointerException.class, () -> {
            JSONParser.parseJsonFiles(folderPath);
        });
    }

    @Test
    void parseJsonFile_FolderPathTOEmptyJson_ReturnsEmptyList() {
        // Given
        String folderPath = "src/test/resources/empty_json";

        // When
        List<Employee> employees = JSONParser.parseJsonFiles(folderPath);

        // Then
        Assertions.assertTrue(employees.isEmpty());
    }



}
