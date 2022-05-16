package org.guava.io;

import com.google.common.base.Charsets;
import com.google.common.collect.FluentIterable;
import com.google.common.hash.HashCode;
import com.google.common.hash.Hashing;
import com.google.common.io.CharSink;
import com.google.common.io.FileWriteMode;
import com.google.common.io.Files;
import com.google.common.io.LineProcessor;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class FilesTest {
    public final static String FILE_PATH = "/Users/linyukai/Desktop/idea/Guava/src/test/resources/File.txt";
    public final static String SOURCE_PATH = "/Users/linyukai/Desktop/idea/Guava/src/test/resources/Source.txt";

    @Test
    public void testCopyFileWithGuava() throws IOException {
        File targetFile = new File(FILE_PATH);
        Files.copy(new File(SOURCE_PATH),targetFile);
        assertTrue(targetFile.exists());
        HashCode sourceHashCode = Files.asByteSource(new File(SOURCE_PATH)).hash(Hashing.sha256());
        HashCode fileHashCode = Files.asByteSource(new File(FILE_PATH)).hash(Hashing.sha256());
        assertEquals(fileHashCode,sourceHashCode);
    }
    @Test
    public void testCopyFileWithJDKNio() throws IOException {
        Path copy = java.nio.file.Files.copy(
                Paths.get(SOURCE_PATH),
                Paths.get(FILE_PATH),
                StandardCopyOption.REPLACE_EXISTING
        );
        assertTrue(copy.toFile().exists());
    }
    @Test
    public void testMove() throws IOException {
        try{
            Files.move(new File(SOURCE_PATH),new File(FILE_PATH));
            assertTrue(new File(FILE_PATH).exists());
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            Files.move(new File(FILE_PATH),new File(SOURCE_PATH));
        }
    }

    @Test
    public void testRead() throws IOException {
        List<String> strings = Files.readLines(new File(SOURCE_PATH), StandardCharsets.UTF_8);
        strings.forEach(System.out::println);
    }
    //[65, 77, 71, 29]

    /**
     * 讀取到希望讀取的地方則停止
     */
    @Test
    public void testToProcessString() throws IOException {

        LineProcessor<List<Integer>> lineProcessor = new LineProcessor<List<Integer>>() {
            private final List<Integer> lengthList = new ArrayList<>();
            @Override
            public boolean processLine(String line) throws IOException {
                if(line.length()==77) return false;
                lengthList.add(line.length());
                return true;
            }

            @Override
            public List<Integer> getResult() {
                return lengthList;
            }
        };
        List<Integer> result = Files.asCharSource(new File(SOURCE_PATH), StandardCharsets.UTF_8).readLines(lineProcessor);
        System.out.println(result);
    }
    @Test
    public void testFileSha256() throws IOException {
        File file = new File(SOURCE_PATH);
        HashCode hash = Files.asByteSource(file).hash(Hashing.sha256());
        System.out.println(hash.toString());
    }

    /**
     * write將會覆蓋掉原檔案
     * @throws IOException
     */
    @Test
    public void testFileWrite() throws IOException {
        final String write_path = "/Users/linyukai/Desktop/idea/Guava/src/test/resources/write.txt";
        File file = new File(write_path);
        file.deleteOnExit();
        String context = "test\nguava";
        Files.asCharSink(file, Charsets.UTF_8).write(context);
        String read = Files.asCharSource(file,StandardCharsets.UTF_8).read();
        assertEquals(read,"test\nguava");
    }
    @Test
    public void testFileAppend() throws IOException {
        File file = new File(FILE_PATH);
        try(BufferedWriter bufferedWriter = Files.newWriter(file,Charsets.UTF_8)){
            bufferedWriter.write("哈哈哈");
        } catch (Exception e) {
           e.printStackTrace();
        }
        CharSink charSink = Files.asCharSink(file,Charsets.UTF_8,FileWriteMode.APPEND);
        charSink.write(",嘿嘿嘿");
        String read = Files.asCharSource(file, Charsets.UTF_8).read();
        assertEquals(read,"哈哈哈,嘿嘿嘿");

    }

    /**
     * 過濾掉名稱為Main.java檔案
     */
    @Test
    public void testTreeFilesPreOrderTraversal(){
        File root = new File("/Users/linyukai/Desktop/idea/Guava/src/main");
        Iterable<File> files = Files.fileTraverser().breadthFirst(root);
        FluentIterable.concat(files)
                .filter(e->!e.getName().equals("Main.java"))
                .forEach(System.out::println);
    }

    @AfterEach
    public void tearDown(){
        File copyFile = new File(FILE_PATH);
        if(copyFile.exists()) {
            System.out.println(copyFile.delete()?"刪除成功":"復原失敗");
        }
    }


}
