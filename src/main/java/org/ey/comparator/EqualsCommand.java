package org.ey.comparator;

public class EqualsCommand implements ComparatorCommand {
    @Override
    public boolean execute(String value, String compareToValue) {
        return value.equals(compareToValue);
    }
}
