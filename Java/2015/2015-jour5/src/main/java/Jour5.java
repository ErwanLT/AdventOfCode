import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.List;
import java.util.regex.Pattern;

public class Jour5 {

    private static List<String> input;

    private static final String VOWELS_REGEX = ".*(.*[aeiou]){3}.*";
    private static final String SAME_LETTER_ROW_TWICE = "(.)\\1";
    private static final String BAD_STRING = "ab|cd|pq|xy";

    public static void main(String[] args) throws URISyntaxException {
        System.out.println("------ Start ------");

        getFileInput();

        solvePart1();
        solvePart2();

        System.out.println("------ End ------");
    }

    private static void getFileInput() throws URISyntaxException {
        System.out.println("------ Getting input : Start ------");
        Jour5 app = new Jour5();
        String fileName = "input";
        File file = app.getFileFromResource(fileName);
        getFileLine(file);
        System.out.println("------ Getting input : End ------");
    }

    private static void getFileLine(File file) {
        try {
            input = Files.readAllLines(file.toPath(), StandardCharsets.UTF_8);
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


    private static void solvePart1() {
        System.out.println("------ Part1 : Start ------");

        Pattern badString = Pattern.compile(BAD_STRING);
        Pattern vowel = Pattern.compile(VOWELS_REGEX);
        Pattern doubleLetter = Pattern.compile(SAME_LETTER_ROW_TWICE);

        int count = 0;
        for (String s: input) {
            if(vowel.matcher(s).find() &&
                doubleLetter.matcher(s).find() &&
                    !badString.matcher(s).find()){
                count++;
            }
        }
        System.out.println("Nice string : " + count);
        System.out.println("------ Part1 : End ------");
    }


    private static void solvePart2() {
        System.out.println("------ Part2 : Start ------");

        int count = 0;
        Pattern p2_p1 = Pattern.compile("(..).*\\1");
        Pattern p2_p2 = Pattern.compile("(.).\\1");

        for (String s: input) {
            if(p2_p1.matcher(s).find() &&
                    p2_p2.matcher(s).find()) {
                count++;
            }
        }
        System.out.println("Nice string : " + count);
        System.out.println("------ Part2 : End ------");
    }
}
