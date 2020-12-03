import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Jour3A {

    private static Set<Position> treePosition = new HashSet<>();

    private static int maxX = 0;
    private static int maxY = 0;

    private static int treeEncouter = 0;


    public static void main(String[] args) throws URISyntaxException {
        System.out.println("------ Start ------");

        Jour3A app = new Jour3A();
        String fileName = "map";

        /**
         * duplicate the files column to symbolize arboreal genetics and biome stability
         */
        File file = app.getFileFromResource(fileName);

        List<String> lines = getFileLine(file);
        maxX = lines.get(0).length();
        maxY = lines.size();

        populateTreePosition(lines);
        
        findEncounteredTree(lines);

        System.out.println("Tree encoutered : "+ treeEncouter);
    }

    private static List<String> getFileLine(File file) {
        try {
            return Files.readAllLines(file.toPath(), StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
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

    private static void populateTreePosition(List<String> lines) {
        for (int y = 0; y < maxY; y++) {
            for (int x = 0; x < maxX; x++) {
                String line = lines.get(y);
                if (line.toCharArray()[x] == '#') {
                    treePosition.add(new Position(x, y));
                }
            }
        }
    }

    private static void findEncounteredTree(List<String> lines) {
        System.out.println("------ Counting Encoutered Tree : Start ------");

        int index = 3;

        for (int y = 1; y < maxY; y++) {
            String line = lines.get(y);

            if(line.charAt(index) == '#'){
                Position p = new Position(index, y);
                System.out.println("Tree encountered at pos : " + p);
                treeEncouter++;
                System.out.println(treeEncouter);
            }
            index = index+3;
        }
        System.out.println("------ Counting Encoutered Tree : End ------");
    }
}
