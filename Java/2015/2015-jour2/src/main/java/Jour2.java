
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

public class Jour2 {

    private static List<String> input;

    private static List<Box> boxes;

    public static void main(String[] args) throws URISyntaxException {
        System.out.println("------ Start ------");

        getFileInput();

        parseInput();

        solvePart1();
        solvePart2();

        System.out.println("------ End ------");
    }

    private static void getFileInput() throws URISyntaxException {
        System.out.println("------ Getting input : Start ------");
        Jour2 app = new Jour2();
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

    private static void parseInput() {
        boxes = new ArrayList<>();

        for (String s : input) {
            String[] values = s.split("x");
            Box box = new Box(Integer.parseInt(values[0]),Integer.parseInt(values[1]),Integer.parseInt(values[2]));
            boxes.add(box);
        }
    }

    private static void solvePart1() {
        System.out.println("------ Part1 : Start ------");

        int squareFeetOfWrappingPaper = 0;
        for (Box b: boxes) {
            squareFeetOfWrappingPaper += b.getRequiredWrappingPaper();
        }
        System.out.println("Total square feet of wrapping paper : " + squareFeetOfWrappingPaper);

        System.out.println("------ Part1 : End ------");
    }


    private static void solvePart2() {
        System.out.println("------ Part2 : Start ------");

        int ribbonLength = 0;
        for (Box b: boxes) {
            ribbonLength += b.getRibbonLength();
        }
        System.out.println("Total feet of ribbon : " + ribbonLength);

        System.out.println("------ Part2 : End ------");
    }
}
