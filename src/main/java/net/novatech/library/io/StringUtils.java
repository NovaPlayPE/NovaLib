package net.novatech.library.io;

import java.util.*;

import net.novatech.library.utils.SystemOS;

import java.io.*;

public class StringUtils {
	
	public static String getStackTrace(Throwable throwable) {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        throwable.printStackTrace(new PrintStream(stream));
        return stream.toString();
    }

    public static String getStackTrace(StackTraceElement[] elements) {
        StringBuilder builder = new StringBuilder();
        for (StackTraceElement element : elements)
            builder.append("\tat ").append(element).append(SystemOS.LINE_SEPARATOR);
        return builder.toString();
    }

    public static boolean isBlank(String str) {
        return str == null || str.trim().isEmpty();
    }

    public static boolean isNotBlank(String str) {
        return !isBlank(str);
    }

    public static String substringBeforeLast(String str, char delimiter) {
        return substringBeforeLast(str, delimiter, str);
    }

    public static String substringBeforeLast(String str, char delimiter, String missingDelimiterValue) {
        int index = str.lastIndexOf(delimiter);
        return index == -1 ? missingDelimiterValue : str.substring(0, index);
    }

    public static String substringBeforeLast(String str, String delimiter) {
        return substringBeforeLast(str, delimiter, str);
    }

    public static String substringBeforeLast(String str, String delimiter, String missingDelimiterValue) {
        int index = str.lastIndexOf(delimiter);
        return index == -1 ? missingDelimiterValue : str.substring(0, index);
    }

    public static String substringBefore(String str, char delimiter) {
        return substringBefore(str, delimiter, str);
    }

    public static String substringBefore(String str, char delimiter, String missingDelimiterValue) {
        int index = str.indexOf(delimiter);
        return index == -1 ? missingDelimiterValue : str.substring(0, index);
    }

    public static String substringBefore(String str, String delimiter) {
        return substringBefore(str, delimiter, str);
    }

    public static String substringBefore(String str, String delimiter, String missingDelimiterValue) {
        int index = str.indexOf(delimiter);
        return index == -1 ? missingDelimiterValue : str.substring(0, index);
    }

    public static String substringAfterLast(String str, char delimiter) {
        return substringAfterLast(str, delimiter, "");
    }

    public static String substringAfterLast(String str, char delimiter, String missingDelimiterValue) {
        int index = str.lastIndexOf(delimiter);
        return index == -1 ? missingDelimiterValue : str.substring(index + 1);
    }

    public static String substringAfterLast(String str, String delimiter) {
        return substringAfterLast(str, delimiter, "");
    }

    public static String substringAfterLast(String str, String delimiter, String missingDelimiterValue) {
        int index = str.lastIndexOf(delimiter);
        return index == -1 ? missingDelimiterValue : str.substring(index + delimiter.length());
    }

    public static String substringAfter(String str, char delimiter) {
        return substringAfter(str, delimiter, "");
    }

    public static String substringAfter(String str, char delimiter, String missingDelimiterValue) {
        int index = str.indexOf(delimiter);
        return index == -1 ? missingDelimiterValue : str.substring(index + 1);
    }

    public static String substringAfter(String str, String delimiter) {
        return substringAfter(str, delimiter, "");
    }

    public static String substringAfter(String str, String delimiter, String missingDelimiterValue) {
        int index = str.indexOf(delimiter);
        return index == -1 ? missingDelimiterValue : str.substring(index + delimiter.length());
    }

    public static boolean isSurrounded(String str, String prefix, String suffix) {
        return str.startsWith(prefix) && str.endsWith(suffix);
    }

    public static String removeSurrounding(String str, String delimiter) {
        return removeSurrounding(str, delimiter, delimiter);
    }

    public static String removeSurrounding(String str, String prefix, String suffix) {
        if ((str.length() >= prefix.length() + suffix.length()) && str.startsWith(prefix) && str.endsWith(suffix))
            return str.substring(prefix.length(), str.length() - suffix.length());
        else
            return str;
    }

    public static String addPrefix(String str, String prefix) {
        if (str.startsWith(prefix))
            return str;
        else
            return prefix + str;
    }

    public static String addSuffix(String str, String suffix) {
        if (str.endsWith(suffix))
            return str;
        else
            return str + suffix;
    }

    public static String removePrefix(String str, String... prefixes) {
        for (String prefix : prefixes)
            if (str.startsWith(prefix))
                return str.substring(prefix.length());
        return str;
    }

    /**
     * Remove one suffix of the suffixes of the string.
     */
    public static String removeSuffix(String str, String... suffixes) {
        for (String suffix : suffixes)
            if (str.endsWith(suffix))
                return str.substring(0, str.length() - suffix.length());
        return str;
    }

    public static boolean containsOne(Collection<String> patterns, String... targets) {
        for (String pattern : patterns)
            for (String target : targets)
                if (pattern.toLowerCase().contains(target.toLowerCase()))
                    return true;
        return false;
    }

    public static boolean containsOne(String pattern, String... targets) {
        for (String target : targets)
            if (pattern.toLowerCase().contains(target.toLowerCase()))
                return true;
        return false;
    }

    public static boolean containsOne(String pattern, char... targets) {
        for (char target : targets)
            if (pattern.toLowerCase().indexOf(Character.toLowerCase(target)) >= 0)
                return true;
        return false;
    }

    public static List<String> tokenize(String str) {
        if (str == null)
            return new LinkedList<>();
        else
            return tokenize(str, " \t\n\r\f");
    }

    public static List<String> tokenize(String str, String delim) {
        LinkedList<String> result = new LinkedList<>();
        StringTokenizer tokenizer = new StringTokenizer(str, delim);
        while (tokenizer.hasMoreTokens()) {
            delim = tokenizer.nextToken();
            result.add(delim);
        }

        return result;
    }

    public static String parseColorEscapes(String original) {
        return original.replaceAll("\u00A7\\d", "");
    }

    public static String parseEscapeSequence(String str) {
        StringBuilder builder = new StringBuilder();
        boolean inEscape = false;
        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            if (ch == '\033') {
                inEscape = true;
            }
            if (!inEscape) {
                builder.append(ch);
            }
            if (inEscape && ch == 'm') {
                inEscape = false;
            }
        }
        return builder.toString();
    }
	
}