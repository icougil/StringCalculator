package com.cougil;

public class StringCalculator {
    public static int add(String text) {
        if (text.length() > 0) {
            final int pos = text.indexOf(",");
            if (pos > 0) {
                int first = Integer.parseInt(text.substring(0, pos));
                int second = Integer.parseInt(text.substring(pos+1));
                return first+second;
            } else {
                return Integer.parseInt(text);
            }
        }
        return 0;
    }
}
