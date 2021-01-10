package com.cougil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class StringCalculator {

    private static final String PREFIX = "//";

    public static int add(String text) {
        List<Integer> negatives = new ArrayList<>();

        String delimiter = ",";
        int pos = text.indexOf("\n");
        if (pos > -1 && text.startsWith(PREFIX)) {
            delimiter = text.substring(PREFIX.length(), pos);
            text = text.substring(pos+1);
        }
        final String[] numbers = text.split("[\\s"+delimiter+"]+");
        final int result = Arrays.stream(numbers)
                .filter(string -> string.length() > 0)
                .mapToInt(Integer::parseInt)
                .peek(number -> {
                    if (number < 0) negatives.add(number);
                })
                .sum();
        final String invalidNumbers = negatives.stream().map(Object::toString).collect(Collectors.joining(","));
        if (invalidNumbers.length() > 0) throw new NumberFormatException("Negatives are not allowed. Found: "+invalidNumbers);
        return result;
    }
}
