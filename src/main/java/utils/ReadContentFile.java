package utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class ReadContentFile {
    public static String readUsingFiles(String fileName) {
        byte[] fileBytes = null;
        try {
            fileBytes = Files.readAllBytes(Paths.get(fileName));
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        return new String(fileBytes);
    }
}
