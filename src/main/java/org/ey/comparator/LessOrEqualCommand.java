package org.ey.comparator;

public class LessOrEqualCommand implements ComparatorCommand {
    @Override
    public boolean execute(String value, String compareToValue) {
        return Double.parseDouble(value) <= Double.parseDouble(compareToValue);
    }
}