package org.guava.utils;

import com.google.common.base.Stopwatch;
import org.junit.jupiter.api.Test;

import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StopWatchTest {
    @Test
    public void TimeLogger() throws InterruptedException {
        Stopwatch started = Stopwatch.createStarted();
        TimeUnit.SECONDS.sleep(1);
        //耗時一秒
        assertEquals(1, started.stop().elapsed(TimeUnit.SECONDS));
    }

}
