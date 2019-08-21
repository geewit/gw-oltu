package io.geewit.oltu.oauth2.utils.test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

/**
 *
 */
public class FileUtils {
    private FileUtils() {
    }

    public static String readTextFileAsString(String fileName) throws IOException {
        URL inputStream = ClassLoader.getSystemResource(fileName);
        BufferedReader br = new BufferedReader(new InputStreamReader(inputStream.openStream()));

        String line;
        StringBuilder buffer = new StringBuilder();
        while ((line = br.readLine()) != null) {
            buffer.append(line);
        }
        return buffer.toString();
    }
}
