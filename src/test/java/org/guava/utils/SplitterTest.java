package org.guava.utils;

import com.google.common.base.CharMatcher;
import com.google.common.base.Predicate;
import com.google.common.base.Splitter;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class SplitterTest {
    @Test
    public void SplitOnSplit() {
        List<String> result = Splitter.on("|").splitToList("hello|world");
        assertNotNull(result);
        assertEquals(result.size(), 2);
        assertEquals(result.get(0), "hello");
        assertEquals(result.get(1), "world");
    }

    @Test
    public void testSplitOmitEmpty() {
        List<String> result = Splitter.on("|").omitEmptyStrings().splitToList("hello | world|||");
        assertNotNull(result);
        assertEquals(result.size(), 2);
        assertEquals(result.get(0), "hello ");
        assertEquals(result.get(1), " world");
    }

    @Test
    public void testSplitOmitEmptyTrim() {
        List<String> resultTrim = Splitter.on("|").trimResults().omitEmptyStrings().splitToList("hello  | world|||");
        assertNotNull(resultTrim);
        assertEquals(resultTrim.get(0), "hello");
        assertEquals(resultTrim.get(1), "world");
    }

    @Test
    public void testSplitFixLength() {
        List<String> result = Splitter.fixedLength(4).splitToList("aaaabbbbcc");
        assertNotNull(result);
        assertEquals(result.get(0), "aaaa");
        assertEquals(result.get(1), "bbbb");
        assertEquals(result.get(2), "cc");

    }

    @Test
    public void testSplitLimit() {
        List<String> result = Splitter.on("#")
                .limit(3)
                .splitToList("hello#java#gogo#test#");
        assertNotNull(result);
        assertEquals(result.size(), 3);
        assertEquals(result.get(0), "hello");
        assertEquals(result.get(1), "java");
        assertEquals(result.get(2), "gogo#test#");
    }

    @Test
    public void testSplitPattern() {
        List<String> result = Splitter.onPattern("\\|")
                .trimResults(CharMatcher.anyOf("?"))
                .omitEmptyStrings()
                .splitToList("??hello|java?|");
        assertNotNull(result);
        assertEquals(result.size(), 2);
        assertEquals(result.get(0), "hello");
        assertEquals(result.get(1), "java");
    }

    @Test
    public void testSplitToMap() {
        Map<String, String> split = Splitter.onPattern("\\|").trimResults()
                .omitEmptyStrings().withKeyValueSeparator("=").split("hello=Word|java=JDK8");
        assertNotNull(split);
        assertEquals(split.size(), 2);
        assertEquals(split.get("hello"), "Word");
        assertEquals(split.get("java"), "JDK8");
    }
}
