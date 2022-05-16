package org.guava.io;

import com.google.common.collect.ImmutableList;
import com.google.common.io.Files;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class FilesTest {
    public final static String FILE_PATH = "/Users/linyukai/DeskTop/Guava/src/test/resources/Source.txt";
    public final static String SOURCE_PATH = "/Users/linyukai/DeskTop/Guava/src/test/resources/Source.txt";

    @Test
    public void testCopyFileWithGuava() throws IOException {
        File targetFile = new File(FILE_PATH);
        Files.copy(new File(SOURCE_PATH),targetFile);
        assertTrue(targetFile.exists());
    }

}
