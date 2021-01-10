package com.cougil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class StringCalculator {

    private static final String PREFIX = "//";
    private static final String DEFAULT_DELIMITER = ",";

    public static int add(String text) {
        List<Integer> negatives = new ArrayList<>();

        String delimiter = DEFAULT_DELIMITER;
        int pos = text.indexOf("\n");
        if (pos > -1 && text.startsWith(PREFIX)) {
            delimiter = text.substring(PREFIX.length(), pos);
            if (delimiter.startsWith("[") && delimiter.endsWith("]")) {
                delimiter = delimiter.substring(1,delimiter.length()-1);
            }
            text = text.substring(pos+1);
        }
        final String[] numbers = text.split("[\\s"+delimiter+"]+");
        final int result = Arrays.stream(numbers)
                .filter(string -> string.length() > 0)
                .mapToInt(Integer::parseInt)
                .peek(number -> {
                    if (number < 0) negatives.add(number);
                })
                .filter( number -> number <= 1000 )
                .sum();
        final String invalidNumbers = negatives.stream()
                .map(Object::toString)
                .collect(Collectors.joining(","));
        if (invalidNumbers.length() > 0) throw new NumberFormatException("Negatives are not allowed. Found: "+invalidNumbers);
        return result;
    }
}
