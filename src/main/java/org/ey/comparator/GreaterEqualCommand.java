package org.ey.comparator;

public class GreaterEqualCommand implements ComparatorCommand {
    @Override
    public boolean execute(String value, String compareToValue) {
        return Double.parseDouble(value) >= Double.parseDouble(compareToValue);
    }
}
