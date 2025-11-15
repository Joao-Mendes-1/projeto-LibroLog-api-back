package com.joaoMendes.catalogolivro.util;

public class StringUtils {

    private StringUtils() {
    }

    public static String cleanInput(String input) {
        if (input == null) return null;
        return input.trim().replace("\"", "");
    }
}
