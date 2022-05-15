package org.guava.utils;

import com.google.common.base.Joiner;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class JoinerTest {
    private final List<String> stringList = Arrays.asList(
            "Google", "Guava", "Java", "Scala", "Kafka"
    );
    private final List<String> stringListWithNullValue = Arrays.asList(
            "Google", "Guava", "Java", "Scala", "Kafka", null
    );

    private final Map<String, String> stringMap = ImmutableMap.of("Hello", "Guava", "Java", "jdk8");

    @Test
    public void testJoinOnJoin() {
        String result = Joiner.on("#").join(stringList);
        assertEquals(result, "Google#Guava#Java#Scala#Kafka");
    }

    @Test
    public void testJoinOnJoinWithNullValue() {
        NullPointerException nullPointerException = assertThrows(NullPointerException.class, () -> {
            String result = Joiner.on("#").join(stringListWithNullValue);
        });
        assertNull(nullPointerException.getMessage());
    }

    @Test
    public void testJoinOnJoinWithNullValue_UseDefaultValue() {
        String result = Joiner.on("#").useForNull("Default").join(stringListWithNullValue);
        assertEquals(result, "Google#Guava#Java#Scala#Kafka#Default");
    }

    @Test
    public void testJoinOnJAppendToStringBuilder() {
        final StringBuilder builder = new StringBuilder();
        StringBuilder result = Joiner.on("#").useForNull("Default").appendTo(builder, stringListWithNullValue);
        assertInstanceOf(StringBuilder.class, result);
        assertEquals(result.toString(), "Google#Guava#Java#Scala#Kafka#Default");

    }

    @Test
    public void getFile() {
        List<String> context = ImmutableList.of("Test", "Haha");
        String targetFileName = "/Users/linyukai/DeskTop/test1.txt";
        Path path = Paths.get(targetFileName);
        try {
            Path write = Files.write(path, context);
            String textContext = String.join("#", Files.readAllLines(write));
            assertEquals(textContext, "Test#Haha");
        } catch (IOException e) {
            fail(e);
        }
    }

    @Test
    public void testJoinOnMap() {
        String result = Joiner.on("#").withKeyValueSeparator("=").join(stringMap);
        assertEquals(result, "Hello=Guava#Java=jdk8");
    }
}