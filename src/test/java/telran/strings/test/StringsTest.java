package telran.strings.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static telran.strings.Strings.*;

import org.junit.jupiter.api.Test;

public class StringsTest {

    @Test
    void firstNameTest() {
        String str;
        str = "Nahum";
        assertEquals(true, str.matches(firstName()));
        str = "Nah";
        assertEquals(false, str.matches(firstName()));
        str = "NAhum";
        assertEquals(false, str.matches(firstName()));
        str = "NahuM";
        assertEquals(false, str.matches(firstName()));
    }

    @Test
    void javaVariableTest() {
        String str;
        str = "j";
        assertEquals(true, str.matches(javaVariable()));
        str = "javaVariable";
        assertEquals(true, str.matches(javaVariable()));
        str = "Java_Variable$";
        assertEquals(true, str.matches(javaVariable()));
        str = "1java_Variable$";
        assertEquals(false, str.matches(javaVariable()));
        str = "java%Variable$";
        assertEquals(false, str.matches(javaVariable()));
        str = "0";
        assertEquals(false, str.matches(javaVariable()));
    }
}
