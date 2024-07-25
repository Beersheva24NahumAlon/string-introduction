package telran.strings;

public class Strings {

    public static String firstName() {
        return "[A-Z][a-z]{4,}";
    }

    public static String javaVariable() {
        return "([a-z]|[A-Z]|_|\\$)([a-z]|[A-Z]|[0-9]|_|\\$)*";
    }
}
