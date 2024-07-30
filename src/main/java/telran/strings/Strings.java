package telran.strings;

import java.util.Arrays;

public class Strings {
    static final String keywords[] = { "abstract", "assert", "boolean",
                "break", "byte", "case", "catch", "char", "class", "const",
                "continue", "default", "do", "double", "else", "enum", "extends", "false",
                "final", "finally", "float", "for", "goto", "if", "implements",
                "import", "instanceof", "int", "interface", "long", "native",
                "new", "null", "package", "private", "protected", "public",
                "return", "short", "static", "strictfp", "super", "switch",
                "synchronized", "this", "throw", "throws", "transient", "true",
                "try", "void", "volatile", "while" };

    public static String firstName() {
        return "([A-Z][a-z]{4,})";      
    }

    public static String javaVariable() {
        //return "[a-zA-Z$]|[a-zA-Z_$][\\w$]+";
        return "((?!_$)[a-zA-Z_$][\\w$]*)";
    }

    public static String number0_300() {
        return "0|[1-9]\\d?|[1-2]\\d\\d|300";
    }

    public static String ipV4Octet() {
        return "([0-1]?\\d{1,2}|2([0-4]\\d|5[0-5]))";
    }

    public static String ipV4Address() {
        String octetExpr = ipV4Octet();
        return String.format("%s(\\.%s){3}", octetExpr, octetExpr);
    }

    public static String stringWithJavaNames(String names) {
        String[] tokens = names.split("\\s+");
        String res = "";
        for (String name : tokens) {
            if (isJavaName(name)) {
                res += name + " ";
            }
        }
        return res.length() > 0 ? res.substring(0, res.length() - 1) : "";
    }

    private static boolean isJavaName(String str) {
        return str.matches(javaVariable()) && !isKeyword(str) ? true : false;
    }

    private static boolean isKeyword(String str) {
        return Arrays.binarySearch(keywords, str) >= 0 ? true : false;
    }

    public static boolean isArithmeticExpression(String expr) {
        //TODO
        return false;

    }
}
