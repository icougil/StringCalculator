package com.cougil;

import java.util.Arrays;

public class StringCalculator {

    private static final String PREFIX = "//";

    public static int add(String text) {
        String delimiter = ",";
        int pos = text.indexOf("\n");
        if (pos > -1 && text.startsWith(PREFIX)) {
            delimiter = text.substring(PREFIX.length(), pos);
            text = text.substring(pos+1);
        }
        final String[] numbers = text.split("[\\s"+delimiter+"]+");
        return Arrays.stream(numbers).filter(string -> string.length() > 0).mapToInt(Integer::parseInt).sum();
    }
}
