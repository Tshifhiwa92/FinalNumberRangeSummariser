package org.example;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class  NumberRangeSummarizerImpl
        implements NumberRangeSummarizer {

    @Override
    public Collection<Integer> collect(String input) {
        if (input == null || input.trim().isEmpty()) {
            return java.util.Collections.emptyList();
        }

        // Skip invalid tokens, collect valid integers only
        List<Integer> result = new ArrayList<>();
        for (String token : input.split(",")) {
            String t = token.trim();
            if (t.isEmpty()) continue;
            try {
                int value = Integer.parseInt(t);
                result.add(value);
            } catch (NumberFormatException e) {
                // Option 1: skip invalid token; Option 2: throw; here we choose to skip
                // You could log a warning here if needed
            }
        }
        // Sort and deduplicate
        return result.stream()
                .distinct()
                .sorted()
                .collect(Collectors.toList());
    }

    @Override
    public String summarizeCollection(Collection<Integer> input) {
        if (input == null || input.isEmpty()) {
            return "";
        }

        // Normalize once: distinct and sort (defensive)
        List<Integer> numbers = input.stream()
                .distinct()
                .sorted()
                .collect(Collectors.toList());

        List<NumberRange> ranges = createRanges(numbers);

        return ranges.stream()
                .map(NumberRange::toString)
                .collect(Collectors.joining(","));
    }

    private List<NumberRange> createRanges(List<Integer> numbers) {
        List<NumberRange> ranges = new ArrayList<>();
        if (numbers.isEmpty()) return ranges;

        int start = numbers.getFirst();
        int previous = start;

        for (int i = 1; i < numbers.size(); i++) {
            int current = numbers.get(i);
            if (current != previous + 1) {
                ranges.add(new NumberRange(start, previous));
                start = current;
            }
            previous = current;
        }

        // Add the final range
        ranges.add(new NumberRange(start, previous));
        return ranges;
    }

}
