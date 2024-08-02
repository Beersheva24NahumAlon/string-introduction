package telran.strings;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Strings {
    static Pattern pattern;
    static {
        pattern = Pattern.compile(regexpArithmeticExpression());
    }
    static final String keywords[] = { "abstract", "assert", "boolean",
            "break", "byte", "case", "catch", "char", "class", "const",
            "continue", "default", "do", "double", "else", "enum", "extends", "false",
            "final", "finally", "float", "for", "goto", "if", "implements",
            "import", "instanceof", "int", "interface", "long", "native",
            "new", "null", "package", "private", "protected", "public",
            "return", "short", "static", "strictfp", "super", "switch",
            "synchronized", "this", "throw", "throws", "transient", "true",
            "try", "void", "volatile", "while" };

    public static String regexpFirstName() {
        return "([A-Z][a-z]{4,})";
    }

    public static String regexpJavaVariable() {
        // return "[a-zA-Z$]|[a-zA-Z_$][\\w$]+";
        return "((?!_$)[a-zA-Z_$][\\w$]*)";
    }

    public static String regexpNumber0_300() {
        return "0|[1-9]\\d?|[1-2]\\d\\d|300";
    }

    public static String regexpIpV4Octet() {
        return "([0-1]?\\d{1,2}|2([0-4]\\d|5[0-5]))";
    }

    public static String regexpIpV4Address() {
        String octetExpr = regexpIpV4Octet();
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
        return str.matches(regexpJavaVariable()) && !isKeyword(str) ? true : false;
    }

    private static boolean isKeyword(String str) {
        return Arrays.binarySearch(keywords, str) >= 0 ? true : false;
    }

    public static boolean isArithmeticExpression(String expr) {
        Matcher matcher = pattern.matcher(expr);
        return matcher.matches() &&
                isBracketsRight(expr) &&
                !isKeyWordsInExpression(expr);
    }

    private static String regexpArithmeticExpression() {
        String operand = regexpOperand();
        String operator = regexpOperator();
        return String.format("%s(%s%s)+", operand, operator, operand);
    }

    private static String regexpOperator() {
        return "([-+*/])";
    }

    private static String regexpSepator() {
        return String.format("(%s|[\\s()]+)", regexpOperator());
    }

    private static String regexpNumber() {
        return "(\\d+|\\d+\\.\\d+)";
    }

    private static String regexpOperand() {
        return String.format("([(\\s]*(%s|%s)[\\s)]*)", regexpNumber(), regexpJavaVariable());
    }

    public static boolean isBracketsRight(String expr) {
        int counter = 0;
        int i = 0;
        char[] array = expr.toCharArray();
        while (counter >= 0 && i < array.length) {
            if (array[i] == '(') {
                counter++;
            }
            if (array[i] == ')') {
                counter--;
            }
            i++;
        }
        return counter == 0;
    }

    public static boolean isKeyWordsInExpression(String expr) {
        String[] tokens = expr.split(regexpSepator());
        int i = 0;
        while (i < tokens.length && !isKeyword(tokens[i])) {
            i++;
        }
        return i == tokens.length ? false : true;
    }
}
