package telran.strings.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static telran.strings.Strings.*;

import org.junit.jupiter.api.Test;

public class StringsTest {

    @Test
    void firstNameTest() {
        String[] strPass = { "Nahum" };
        String[] strFail = { "Nah", "NAhum", "NahuM" };
        for (String str : strPass) {
            assertEquals(true, str.matches(firstName()));
        }
        for (String str : strFail) {
            assertEquals(false, str.matches(firstName()));
        }
    }

    @Test
    void javaVariableTest() {
        String[] strPass = { "j", "$" ,"javaVariable", "_Java$Variable$", "$JavaVariable_", "a1"};
        String[] strFail = { "1java_Variable$", "java%Variable$", "_"};
        for (String str : strPass) {
            assertEquals(true, str.matches(javaVariable()));
        }
        for (String str : strFail) {
            assertEquals(false, str.matches(javaVariable()));
        }
    }
}
