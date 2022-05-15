package org.guava.utils;

import com.google.common.base.Splitter;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class SplitterTest {
    @Test
    public void SplitOnSplit(){
        List<String> result = Splitter.on("|").splitToList("hello|world");
        assertNotNull(result);
        assertEquals(result.size(),2);
        assertEquals(result.get(0),"hello");
        assertEquals(result.get(1),"world");
    }
}
