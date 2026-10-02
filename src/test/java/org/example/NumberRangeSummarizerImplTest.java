package org.example;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Collection;
import java.util.List;

class NumberRangeSummarizerImplTest {

    private final NumberRangeSummarizer summarizer = new NumberRangeSummarizerImpl();

    @Test
    void collect_emptyInput_returnsEmptyList() {
        Collection<Integer> result = summarizer.collect("");
        assertTrue(result.isEmpty(), "Expected empty collection for empty input");
    }

    @Test
    void collect_nullInput_returnsEmptyList() {
        Collection<Integer> result = summarizer.collect(null);
        assertTrue(result.isEmpty(), "Expected empty collection for null input");
    }

    @Test
    void collect_validInput_parsesAndSortsDistinct() {
        Collection<Integer> result = summarizer.collect("1, 3, 2, 2, 5, 3");
        // Expect distinct and sorted
        assertEquals(List.of(1, 2, 3, 5), result.stream().sorted().toList(),
                "Should parse, deduplicate, and sort values");
    }

    @Test
    void summarizeCollection_emptyInput_returnsEmptyString() {
        String result = summarizer.summarizeCollection(List.of());
        assertEquals("", result, "Expected empty string for empty input");
    }

    @Test
    void summarizeCollection_singleRange_and_singleNumbers() {
        Collection<Integer> input = List.of(1, 2, 3, 5, 6, 8);
        String result = summarizer.summarizeCollection(input);
        // Expected ranges: 1-3,5-6,8
        assertEquals("1-3,5-6,8", result);
    }

    @Test
    void summarizeCollection_multipleRanges() {
        Collection<Integer> input = List.of(1, 3, 4, 5, 7, 8, 9, 11);
        String result = summarizer.summarizeCollection(input);
        // Ranges: 1,3-5,7-9,11
        assertEquals("1,3-5,7-9,11", result);
    }

    @Test
    void endToEnd_example_fromApplication() {
        String input = "1,3,6,7,8,12,13,14,15,21,22,23,24,31";
        Collection<Integer> numbers = summarizer.collect(input);
        String result = summarizer.summarizeCollection(numbers);
        assertEquals("1,3,6-8,12-15,21-24,31", result);
    }

}