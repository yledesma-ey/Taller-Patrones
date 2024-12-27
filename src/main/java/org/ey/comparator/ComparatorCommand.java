package org.ey.comparator;

public interface ComparatorCommand {
    boolean execute(String value, String compareToValue);
}
