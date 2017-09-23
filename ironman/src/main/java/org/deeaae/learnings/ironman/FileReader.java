package org.deeaae.learnings.ironman;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class FileReader {
    public String filepath;
    Stream<String> stream;
    public void resetStream() {
        if(stream!=null) {
            stream.close();
        }
        try {
            stream = Files.lines(Paths.get(filepath));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Stream getStream() {
        if(stream == null) {
            resetStream();
        }
        return stream;

    }
}
