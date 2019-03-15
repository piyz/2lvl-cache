package by.matrosov.cacheappl.tasks;

import java.util.Arrays;
import java.util.Optional;

public enum Options {
    ADD(0), GET(1), REMOVE(2);

    int value;

    Options(int value) {
        this.value = value;
    }

    public static Optional<Options> valueOf(int value) {
        return Arrays.stream(values())
                .filter(o -> o.value == value)
                .findFirst();
    }
}
