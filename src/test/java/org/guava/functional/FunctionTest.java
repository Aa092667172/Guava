package org.guava.functional;

import com.google.common.base.Function;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * google版本  function 現今為相容有繼承java8的function
 */
public class FunctionTest {
    @Test
    public void Function(){
        Function<String,Integer> size = new Function<String, Integer>() {
            @Override
            public Integer apply(String input) {
                return input.length();
            }
        };
        assertEquals(2,size.apply("你好"));
    }
}
