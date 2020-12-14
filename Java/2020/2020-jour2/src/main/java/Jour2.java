import org.apache.commons.lang.StringUtils;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

public class Jour2 {

    private static List<String> inputs;

    public static void main(String[] args) throws URISyntaxException {
        System.out.println("------ Start ------");

        getFileInput();

        solvePart1();
        solvePart2();

        System.out.println("------ End ------");
    }


    private static void solvePart1() {
        System.out.println("------ Part1 : Start ------");

        List<String> correctPassword = new ArrayList<>();

        for (String line: inputs) {
            String minMax = line.substring(0, line.lastIndexOf(':') -1).trim();
            int min = Integer.parseInt(minMax.substring(0, minMax.lastIndexOf('-')));
            int max = Integer.parseInt(minMax.substring(minMax.lastIndexOf('-')+1));
            String letter = line.substring(line.lastIndexOf(':')-1, line.lastIndexOf(':')).trim();
            String password = line.substring(line.lastIndexOf(':') + 1).trim();

            int matches = StringUtils.countMatches(password, letter);
            if(matches>= min && matches<=max){
                correctPassword.add(password);
            }

        }
        System.out.println("Correct passwords : " + correctPassword.size());
        System.out.println("------ Part1 : End ------");
    }


    private static void solvePart2() {
        System.out.println("------ Part2 : Start ------");

        List<String> correctPassword = new ArrayList<>();
        for (String line: inputs) {
            String minMax = line.substring(0, line.lastIndexOf(':') -1).trim();
            int position1 = Integer.parseInt(minMax.substring(0, minMax.lastIndexOf('-')));
            int position2 = Integer.parseInt(minMax.substring(minMax.lastIndexOf('-')+1));
            char letter = line.substring(line.lastIndexOf(':')-2, line.lastIndexOf(':')).trim().charAt(0);
            String password = line.substring(line.lastIndexOf(':') + 1).trim();

            char[] passwordArray = password.toCharArray();
            if (passwordArray[position1-1] == letter ^ passwordArray[position2-1] == letter){
                correctPassword.add(password);
            }

        }
        System.out.println("Correct passwords : " + correctPassword.size());
        System.out.println("------ Part2 : End ------");
    }

    private static void getFileInput() throws URISyntaxException {
        System.out.println("------ Getting input : Start ------");
        Jour2 app = new Jour2();
        String fileName = "passwords";
        File file = app.getFileFromResource(fileName);
        getFileLine(file);
        System.out.println("------ Getting input : End ------");
    }

    private static void getFileLine(File file) {
        try {
            inputs = Files.readAllLines(file.toPath(), StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
        }
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
}
