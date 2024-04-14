package com.maksimkaxxl.calculating;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
class StatisticsCalculatorTest {

    @Mock
    private StatisticsCalculator statisticsCalculator;

    @Test
    void calculateAndSaveStatistics_ValidateForEmptyAttribute_ThrowAIllegalArgumentException() {
        // Given
        String attribute = "";
        statisticsCalculator = new StatisticsCalculator(attribute, "src/test/resources/json");

        // When & Then
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> statisticsCalculator.calculateAndSaveStatistics("src/test/resources/xml/"));
        assertEquals("Invalid attribute name", exception.getMessage());
    }

    @Test
    void calculateAndSaveStatistics_ValidateForNonEmptyFiles_ReturnNonEmptyFile() {
        // Given
        String attribute = "name";
        statisticsCalculator = new StatisticsCalculator(attribute, "src/test/resources/json");

        // When
        statisticsCalculator.calculateAndSaveStatistics("src/test/resources/xml/actual_non_empty/");

        // Then
        String expectedFilePath = "src/test/resources/xml/expected_non_empty/statistics_by_name.xml";
        String actualFilePath = "src/test/resources/xml/actual_non_empty/statistics_by_name.xml";
        assertFileContentsEqual(expectedFilePath, actualFilePath);
    }


    @Test
    void calculateAndSaveStatistics_ValidateForEmptyFiles_ReturnEmptyFile() {
        // Given
        String attribute = "position";
        statisticsCalculator = new StatisticsCalculator(attribute, "src/test/resources/empty_json");

        // When
        statisticsCalculator.calculateAndSaveStatistics("src/test/resources/xml/actual_empty/");

        // Then
        String expectedFilePath = "src/test/resources/xml/expected_empty/statistics_by_position.xml";
        String actualFilePath = "src/test/resources/xml/actual_empty/statistics_by_position.xml";
        assertFileContentsEqual(expectedFilePath, actualFilePath);
    }


    @Test
    void calculateAndSaveStatistics_ValidateForNonExistingFolder_ThrowNullPointerException() {
        // Given
        String attribute = "position";
        statisticsCalculator = new StatisticsCalculator(attribute, "non_existing_folder/");

        // When & Then
        assertThrows(NullPointerException.class, () -> {
            statisticsCalculator.calculateAndSaveStatistics("src/test/resources/xml/");
        });
    }

    @Test
    void calculateAndSaveStatistics_ValidateForNullAttribute_ThrowNullPointerException() {
        // Given
        String attribute = null;
        statisticsCalculator = new StatisticsCalculator(attribute, "src/test/resources/json/");

        // When & Then
        assertThrows(NullPointerException.class, () -> {
            statisticsCalculator.calculateAndSaveStatistics("src/test/resources/xml/");
        });
    }

    private void assertFileContentsEqual(String expectedFilePath, String actualFilePath) {
        try {
            List<String> expectedLines = Files.readAllLines(Paths.get(expectedFilePath));
            List<String> actualLines = Files.readAllLines(Paths.get(actualFilePath));

            // Перевіряємо, чи вміст обох файлів ідентичний
            assertEquals(expectedLines, actualLines);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
