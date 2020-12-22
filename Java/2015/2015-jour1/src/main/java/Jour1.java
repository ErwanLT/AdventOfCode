import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;

public class Jour1 {

    private static String input;
    private static char[] inputArray;
    private static int floor = 0;

    public static void main(String[] args) throws URISyntaxException {
        System.out.println("------ Start ------");

        getFileInput();

        solvePart1();
        solvePart2();

        System.out.println("------ End ------");
    }

    private static void getFileInput() throws URISyntaxException {
        System.out.println("------ Getting input : Start ------");
        Jour1 app = new Jour1();
        String fileName = "input";
        File file = app.getFileFromResource(fileName);
        getFileLine(file);
        System.out.println("------ Getting input : End ------");
    }

    private static void getFileLine(File file) {
        try {
            input = Files.readString(file.toPath(), StandardCharsets.UTF_8);
            inputArray = input.toCharArray();
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

        for (char c : inputArray) {
            if(c == '('){
                floor++;
            } else {
                floor--;
            }
        }
        System.out.println("The instruction take Santa to floor : " + floor);

        System.out.println("------ Part1 : End ------");
    }


    private static void solvePart2() {
        System.out.println("------ Part2 : Start ------");

        floor=0;
        int position = 0;
        for (int i = 0; i<inputArray.length; i++){
            if(inputArray[i]=='('){
                floor++;
            } else {
                floor--;
            }
            if(floor == -1){
                System.out.println("Position of the character that causes Santa to first enter the basement : " + (i+1));
                break;
            }
        }

        System.out.println("------ Part2 : End ------");
    }
}
