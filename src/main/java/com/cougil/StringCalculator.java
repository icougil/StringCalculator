package com.cougil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class StringCalculator {

    private static final String PREFIX = "//";
    private static final String DEFAULT_DELIMITER = ",";

    public static int add(String text) {
        String delimiter = calculateDelimiter(text);
        if (!delimiter.equals(DEFAULT_DELIMITER)) {
            text = text.substring(text.indexOf("\n") +1);
        }
        final String[] numbers = text.split("[\\s"+delimiter+"]+");
        List<Integer> negatives = new ArrayList<>();
        final int result = calculateResult(numbers, negatives);
        checkNegatives(negatives);
        return result;
    }

    private static void checkNegatives(List<Integer> negatives) {
        final String invalidNumbers = negatives.stream()
                .map(Object::toString)
                .collect(Collectors.joining(","));
        if (invalidNumbers.length() > 0) throw new NumberFormatException("Negatives are not allowed. Found: "+invalidNumbers);
    }

    private static int calculateResult(String[] numbers, List<Integer> negatives) {
        return Arrays.stream(numbers)
                .filter(string -> string.length() > 0)
                .mapToInt(Integer::parseInt)
                .peek(number -> {
                    if (number < 0) negatives.add(number);
                })
                .filter( number -> number <= 1000 )
                .sum();
    }

    private static String calculateDelimiter(String text) {
        String delimiter = DEFAULT_DELIMITER;
        int pos = text.indexOf("\n");
        if (pos > -1 && text.startsWith(PREFIX)) {
            delimiter = text.substring(PREFIX.length(), pos);
            if (delimiter.startsWith("[") && delimiter.endsWith("]")) {
                delimiter = delimiter.replaceAll("\\[", "");
                delimiter = delimiter.replaceAll("]","");
            }
        }
        return delimiter;
    }
}
