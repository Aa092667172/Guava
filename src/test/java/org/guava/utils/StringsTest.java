package org.guava.utils;

import com.google.common.base.CharMatcher;
import com.google.common.base.Strings;
import org.junit.jupiter.api.Test;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.*;

public class StringsTest {
    @Test
    public void testStringMethod(){
        assertNull(Strings.emptyToNull(""));
        assertEquals(Strings.emptyToNull("hello"),"hello");
        assertEquals(Strings.commonPrefix("Hello","Hit"),"H");
        assertEquals(Strings.commonPrefix("Hello","Xit"),"");
        assertEquals(Strings.commonSuffix("Hello","Echo"),"o");
        assertEquals(Strings.repeat("Alex",3),"AlexAlexAlex");
        assertTrue(Strings.isNullOrEmpty(""));
        assertEquals(Strings.padStart("Alex",3,'H'),"Alex");
        assertEquals(Strings.padStart("Alex",5,'H'),"HAlex");
        assertEquals(Strings.padEnd("Alex",5,'H'),"AlexH");
    }
    @Test
    public void testCharMatcher(){
        assertEquals(CharMatcher.is('A').countIn("Alex test haha"),1);
        assertEquals(CharMatcher.breakingWhitespace().collapseFrom(" Hello Guava   ",'*'),"*Hello*Guava*");
    }
}
