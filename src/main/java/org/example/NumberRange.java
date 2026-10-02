package org.example;

public final class NumberRange {

    private final int start;
    private final int end;

    // Creates a NumberRange with the specified start and end values, ensuring the start is not greater than the end.
    public NumberRange(int start, int end) {
        if (start > end) {
            throw new IllegalArgumentException("Start not > than end");
        }
        this.start = start;
        this.end = end;
    }

    // Returns the starting value of the number range.
    public int getStart() { return start; }

    // Returns the ending value of the number range.
    public int getEnd() { return end; }

    // Determines whether the range represents a single number rather than a sequence.
    public boolean isSingleNumber() { return start == end; }

    // Compares this NumberRange with another object based on their start and end values.
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof NumberRange)) return false;
        NumberRange that = (NumberRange) o;
        return start == that.start && end == that.end;
    }

    // Generates a hash code based on the start and end values of the range.
    @Override
    public int hashCode() {
        int result = Integer.hashCode(start);
        result = 31 * result + Integer.hashCode(end);
        return result;
    }

    // Returns a string representation of the range, displaying either a single number or a start-end range.
    @Override
    public String toString() {
        return isSingleNumber() ? String.valueOf(start) : start + "-" + end;
    }
}
