package com.tam.StudentManagement.Util;

import java.text.Normalizer;
import java.util.Locale;
import java.util.regex.Pattern;

public class SlugUtil {
    private static final Pattern NONLATIN = Pattern.compile("[^\\w-]");
    private static final Pattern WHITESPACE = Pattern.compile("[\\s]");

    /**
     * Convert a string to a URL-friendly slug
     * Example: "Hello World!" -> "hello-world"
     * 
     * @param input The string to convert
     * @return The generated slug
     */
    public static String toSlug(String input) {
        if (input == null) {
            return "";
        }

        // Convert to lowercase and normalize unicode characters
        String noWhitespace = WHITESPACE.matcher(input).replaceAll("-");
        String normalized = Normalizer.normalize(noWhitespace, Normalizer.Form.NFD);
        String slug = NONLATIN.matcher(normalized).replaceAll("");

        // Replace multiple hyphens with single hyphen
        slug = slug.replaceAll("-+", "-");

        // Remove leading and trailing hyphens
        slug = slug.replaceAll("^-+|-+$", "");

        return slug.toLowerCase(Locale.ENGLISH);
    }

    /**
     * Generate a unique slug by appending a number if the slug already exists
     * Example: If "hello-world" exists, it will return "hello-world-1"
     * 
     * @param baseSlug The base slug to check
     * @param exists   Function to check if a slug exists
     * @return A unique slug
     */
    public static String generateUniqueSlug(String baseSlug, java.util.function.Predicate<String> exists) {
        String slug = toSlug(baseSlug);
        String uniqueSlug = slug;
        int counter = 1;

        while (exists.test(uniqueSlug)) {
            uniqueSlug = slug + "-" + counter;
            counter++;
        }

        return uniqueSlug;
    }
}