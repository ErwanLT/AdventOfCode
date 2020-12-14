import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.Arrays;

public class Jour3 {

    private static char[][] grid;

    public static void main(String[] args) throws URISyntaxException {
        System.out.println("------ Start ------");

        Jour3 app = new Jour3();

        File file = app.getFileFromResource();

        getFileLine(file);

        solvePart1();
        solvePart2();

        System.out.println("------ End ------");
    }

    private static void getFileLine(File file) {
        try {
            String s = Files.readString(file.toPath(), StandardCharsets.UTF_8);
            grid = Arrays.stream(s.split(System.lineSeparator())).map(String::toCharArray).toArray(char[][]::new);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private File getFileFromResource() throws URISyntaxException {
        ClassLoader classLoader = getClass().getClassLoader();
        String fileName = "input";
        URL resource = classLoader.getResource(fileName);
        if (resource == null) {
            throw new IllegalArgumentException("file not found! " + fileName);
        } else {
            return new File(resource.toURI());
        }
    }

    private static int findEncounteredTree(int x, int y) {
        System.out.println("------ Counting Encoutered Tree : Start ------");

        int trees = 0;
        for (int i = 0; i * y < grid.length; i++) {
            if (grid[i * y][i * x % grid[0].length] == '#') {
                trees++;
            }
        }
        System.out.println("------ Counting Encoutered Tree : End ------");
        return trees;
    }

    private static void solvePart1(){
        System.out.println("------ Part1 : Start ------");
        int trees = findEncounteredTree(3, 1);
        System.out.println("Encounterd trees : " + trees);
        System.out.println("------ Part1 : End ------");
    }

    private static void solvePart2(){
        System.out.println("------ Part2 : Start ------");
        int trees = findEncounteredTree(1, 1) * findEncounteredTree(3, 1) * findEncounteredTree(5, 1) * findEncounteredTree(7, 1) * findEncounteredTree(1, 2);
        System.out.println("Encounterd trees : " + trees);
        System.out.println("------ Part2 : End ------");
    }
}
