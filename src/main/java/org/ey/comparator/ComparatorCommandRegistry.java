package org.ey.comparator;

import java.util.HashMap;
import java.util.Map;

public class ComparatorCommandRegistry {
    private static final Map<String, ComparatorCommand> COMMANDS = new HashMap<>();

    static {
        COMMANDS.put("greater_than", new GreaterThanCommand());
        COMMANDS.put("equals", new EqualsCommand());
        COMMANDS.put("equal", new EqualsCommand());
        COMMANDS.put("less_or_equal", new LessOrEqualCommand());
        COMMANDS.put("greater_equal", new GreaterEqualCommand());
        COMMANDS.put("greater_or_equal", new GreaterEqualCommand());
    }

    public static ComparatorCommand getCommand(String comparator) {
        ComparatorCommand command = COMMANDS.get(comparator);
        if (command == null) {
            throw new IllegalArgumentException("Comparador no soportado: " + comparator);
        }
        return command;
    }
}

