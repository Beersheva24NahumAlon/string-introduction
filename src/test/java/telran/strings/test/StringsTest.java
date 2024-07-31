package telran.strings.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static telran.strings.Strings.*;

import org.junit.jupiter.api.Test;

public class StringsTest {

    @Test
    void firstNameTest() {
        assertTrue("Nahum".matches(firstName()));

        assertFalse("Nah".matches(firstName()));
        assertFalse("NAhum".matches(firstName()));
        assertFalse("NahuM".matches(firstName()));
    }

    @Test
    void javaVariableTest() {
        assertTrue("j".matches(javaVariable()));
        assertTrue("$".matches(javaVariable()));
        assertTrue("javaVariable".matches(javaVariable()));
        assertTrue("_Java$Variable$".matches(javaVariable()));
        assertTrue("$JavaVariable_".matches(javaVariable()));
        assertTrue("a1".matches(javaVariable()));

        assertFalse("1java_Variable$".matches(javaVariable()));
        assertFalse("java%Variable$".matches(javaVariable()));
        assertFalse("_".matches(javaVariable()));
    }

    @Test
    void number0_300TrueTest() {
        String regexp = number0_300();
        assertTrue("123".matches(regexp));
        assertTrue("0".matches(regexp));
        assertTrue("300".matches(regexp));
        assertTrue("12".matches(regexp));
        assertTrue("25".matches(regexp));
        assertTrue("299".matches(regexp));
        assertTrue("1".matches(regexp));
    }

    @Test
    void number0_300FalseTest() {
        String regexp = number0_300();
        assertFalse("00".matches(regexp));
        assertFalse("301".matches(regexp));
        assertFalse("01".matches(regexp));
        assertFalse("00".matches(regexp));
        assertFalse("030".matches(regexp));
        assertFalse("1(".matches(regexp));
        assertFalse("1000".matches(regexp));
        assertFalse(" 20".matches(regexp));
        assertFalse("1001".matches(regexp));
    }

    @Test
    void ipV4OctetTrueTest() {
        String regexp = ipV4Octet();
        assertTrue("0".matches(regexp));
        assertTrue("00".matches(regexp));
        assertTrue("000".matches(regexp));
        assertTrue("10".matches(regexp));
        assertTrue("100".matches(regexp));
        assertTrue("255".matches(regexp));
        assertTrue("199".matches(regexp));
        assertTrue("249".matches(regexp));
        assertTrue("192".matches(regexp));  
    }

    @Test
    void ipV4OctetFalseTest() {
        String regexp = ipV4Octet();
        assertFalse("0000".matches(regexp));
        assertFalse("t".matches(regexp));
        assertFalse("-1".matches(regexp));
        assertFalse("1111".matches(regexp));
        assertFalse("0001".matches(regexp));
        assertFalse("256".matches(regexp));
        assertFalse("300".matches(regexp));
        assertFalse("*".matches(regexp));
        assertFalse("1 ".matches(regexp));
    }

    @Test
    void ipV4AddressTrueTest() {
        String regexp = ipV4Address();
        assertTrue("0.0.0.0".matches(regexp));
        assertTrue("192.168.0.1".matches(regexp));
        assertTrue("255.255.255.255".matches(regexp));
    }

    @Test
    void ipV4AddressFalseTest() {
        String regexp = ipV4Address();
        assertFalse("0.0.0".matches(regexp));
        assertFalse("0.0.0+0".matches(regexp));
        assertFalse("0".matches(regexp));
        assertFalse("0.-".matches(regexp));
        assertFalse("0.0.0 0".matches(regexp));
    }

    @Test
    void stringWithJavaNamesTest() {
        String names1 = "123 1a _ abs int enum null lmn";
        assertEquals("abs lmn", stringWithJavaNames(names1));
        String names2 = "123 1a _ int enum null lmn";
        assertEquals("lmn", stringWithJavaNames(names2));
        String names3 = "123 1a _ int enum null";
        assertEquals("", stringWithJavaNames(names3));
    }

    @Test
    void isArithmeticExpressionTest() {
        assertTrue(isArithmeticExpression("1.23+ 0.01-3 /4-g "));
        assertTrue(isArithmeticExpression("1 +i "));
        assertTrue(isArithmeticExpression("1 + (1 + 1) - (1 + t)"));

        assertFalse(isArithmeticExpression("1,23+0.01"));
        assertFalse(isArithmeticExpression("1.23 +int"));
        assertFalse(isArithmeticExpression("(1+(1+1)-(1+t)"));
        assertFalse(isArithmeticExpression(")1 + (1 + 1) - (1 + t)"));
    }

    @Test
    void isBracketsRightTest() {
        assertTrue(isBracketsRight("1+(1+1)-(1+1)"));
        assertTrue(isBracketsRight("((1+1)-1)+(1-(1+1))+1"));
    }

    @Test
    void isKeyWordsInExpressionTest() {
        assertFalse(isKeyWordsInExpression("1+(1+1)-(1+1)"));
        assertTrue(isKeyWordsInExpression("1+1+int+4"));
        assertTrue(isKeyWordsInExpression("1+(double+1)-(1+int)"));
    }
}