package org.ey.comparator;

public class GreaterThanCommand implements ComparatorCommand {
    @Override
    public boolean execute(String value, String compareToValue) {
        return Double.parseDouble(value) > Double.parseDouble(compareToValue);
    }
}