import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Jour1 {

    private static List<String> input;

    public static void main(String[] args) throws URISyntaxException, FileNotFoundException {
        System.out.println("------ Start ------");

        Jour1 app = new Jour1();
        String fileName = "frequencies";
        File file = app.getFileFromResource(fileName);
        getFileLine(file);

        solvePart1(input);
        solvePart2(file);

        System.out.println("------ End ------");
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

    private static void getFileLine(File file) {
        try {
            input = Files.readAllLines(file.toPath(), StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void solvePart1(List<String> input) {
        System.out.println("------ Part 1 : Start ------");
        int frequency = 0;
        for (String line: input) {
            char operande = line.substring(0,1).charAt(0);
            int change = Integer.valueOf(line.substring(1));
            switch (operande){
                case '+' : {
                    frequency = frequency + change;
                    break;
                }
                case '-': {
                    frequency = frequency - change;
                    break;
                }
            }
        }
        System.out.println("final frequency : " + frequency);
        System.out.println("------ Part 1 : End ------");
    }

    private static void solvePart2(File file) throws FileNotFoundException {
        System.out.println("------ Part 2 : Start ------");
        int frequency = 0;
        ArrayList<Integer> freqList = new ArrayList<>();
        boolean frequencieNotFound = true;

        while (frequencieNotFound) {
            Scanner infile = new Scanner(new FileReader(file));
            while (infile.hasNext()) {
                frequency = frequency + infile.nextInt();

                if (freqList.contains(frequency)){
                    System.out.println("First repeated frequency = " + frequency);
                    System.out.println("------ Part 2 : End ------");
                    frequencieNotFound = false;
                    break;
                }

                freqList.add(frequency);
            }
        }
    }
}
