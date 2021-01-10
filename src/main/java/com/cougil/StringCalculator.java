package com.cougil;

import java.util.Arrays;

public class StringCalculator {
    public static int add(String text) {
        final String[] numbers = text.split("[\\s,]+");
        return Arrays.stream(numbers).filter(string -> string.length() > 0).mapToInt(Integer::parseInt).sum();
    }
}
