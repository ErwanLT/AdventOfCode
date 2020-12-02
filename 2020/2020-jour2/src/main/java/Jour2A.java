import org.apache.commons.lang.StringUtils;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

public class Jour2A {

    private static List<String> correctPassword = new ArrayList<>();

    public static void main(String[] args) throws URISyntaxException {
        Jour2A app = new Jour2A();

        String fileName = "passwords";
        File file = app.getFileFromResource(fileName);

        findCorrectPassword(file);

        System.out.println("Correct password : " + correctPassword.size());

    }

    private File getFileFromResource(String fileName) throws URISyntaxException {

        ClassLoader classLoader = getClass().getClassLoader();
        URL resource = classLoader.getResource(fileName);
        if (resource == null) {
            throw new IllegalArgumentException("file not found! " + fileName);
        } else {
            return new File(resource.toURI());
        }
    }

    private static void findCorrectPassword(File file) {
        List<String> lines;
        try {
            lines = Files.readAllLines(file.toPath(), StandardCharsets.UTF_8);
            for (String line: lines) {
                String minMax = line.substring(0, line.lastIndexOf(':') -1).trim();
                int min = Integer.parseInt(minMax.substring(0, minMax.lastIndexOf('-')));
                int max = Integer.parseInt(minMax.substring(minMax.lastIndexOf('-')+1));
                String letter = line.substring(line.lastIndexOf(':')-1, line.lastIndexOf(':')).trim();
                String password = line.substring(line.lastIndexOf(':') + 1).trim();

                int matches = StringUtils.countMatches(password, letter);
                if(matches>= min && matches<=max){
                    System.out.println(password);
                    correctPassword.add(password);
                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
