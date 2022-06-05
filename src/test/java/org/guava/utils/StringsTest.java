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
        //找出共同開頭
        assertEquals(Strings.commonPrefix("Hello","Hit"),"H");
        assertEquals(Strings.commonPrefix("Hello","Xit"),"");
        //找出共同字尾
        assertEquals(Strings.commonSuffix("Hello","llo"),"llo");
        //重複字串
        assertEquals(Strings.repeat("Alex",3),"AlexAlexAlex");
        assertTrue(Strings.isNullOrEmpty(""));
        //不夠長度將補充char
        assertEquals(Strings.padStart("Alex",3,'H'),"Alex");
        assertEquals(Strings.padStart("Alex",5,'H'),"HAlex");
        assertEquals(Strings.padEnd("Alex",5,'H'),"AlexH");
    }
    @Test
    public void testCharMatcher(){
        //返回sequence中匹配到的字元計數
        assertEquals(CharMatcher.is('t').or(CharMatcher.is('a')).countIn("Alex test haha"),4);
        //判斷是否為一個中斷的空白 連續空格視為一個
        assertEquals(CharMatcher.breakingWhitespace().collapseFrom("        Hello Guava   ",'*'),"*Hello*Guava*");
        //返回符合anyOf中任意字串的最先索引
        assertEquals(CharMatcher.anyOf("as").indexIn("我測s試a"),2);
        //將字串範圍內的字元刪除
        assertEquals(CharMatcher.inRange('a','d').removeFrom("abcdefg我字串拉"),"efg我字串拉");
        //處理unicode空白
        assertEquals(CharMatcher.whitespace().replaceFrom('\u00A0'+"嘿嘿嘿","換掉"),"換掉嘿嘿嘿");


    }
}
