import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.HashSet;
import java.util.Set;

public class Jour3 {

    private static char[] input;

    public static void main(String[] args) throws URISyntaxException {
        System.out.println("------ Start ------");

        getFileInput();

        solvePart1();
        solvePart2();

        System.out.println("------ End ------");
    }

    private static void getFileInput() throws URISyntaxException {
        System.out.println("------ Getting input : Start ------");
        Jour3 app = new Jour3();
        String fileName = "input";
        File file = app.getFileFromResource(fileName);
        getFileLine(file);
        System.out.println("------ Getting input : End ------");
    }

    private static void getFileLine(File file) {
        try {
            input = Files.readString(file.toPath(), StandardCharsets.UTF_8).toCharArray();
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

        Coordinate origine = new Coordinate(0, 0);
        Set<Coordinate> housesVisited = new HashSet<>();
        housesVisited.add(origine);

        int x = 0;
        int y = 0;
        for (int i = 0; i < input.length; i++){
            switch (input[i]) {
                case '^' :
                    y++;
                    break;
                case '>' :
                    x++;
                    break;
                case '<' :
                    x--;
                    break;
                case 'v' :
                    y--;
                    break;
            }
            Coordinate coordinate = new Coordinate(x, y);
            housesVisited.add(coordinate);
        }
        System.out.println("Visited house : " + housesVisited.size());

        System.out.println("------ Part1 : End ------");
    }


    private static void solvePart2() {
        System.out.println("------ Part2 : Start ------");

        Coordinate origine = new Coordinate(0, 0);
        Set<Coordinate> housesVisited = new HashSet<>();
        housesVisited.add(origine);

        int santaX = 0;
        int santaY = 0;
        int robotSantaX = 0;
        int robotSantaY = 0;
        boolean santaTurn = true;

        for (int i = 0; i < input.length; i++){
            if(santaTurn){
                santaTurn = false;
                switch (input[i]) {
                    case '^' :
                        santaY++;
                        break;
                    case '>' :
                        santaX++;
                        break;
                    case '<' :
                        santaX--;
                        break;
                    case 'v' :
                        santaY--;
                        break;
                }
                Coordinate coordinate = new Coordinate(santaX, santaY);
                housesVisited.add(coordinate);
            } else {
                santaTurn = true;
                switch (input[i]) {
                    case '^' :
                        robotSantaY++;
                        break;
                    case '>' :
                        robotSantaX++;
                        break;
                    case '<' :
                        robotSantaX--;
                        break;
                    case 'v' :
                        robotSantaY--;
                        break;
                }
                Coordinate coordinate = new Coordinate(robotSantaX, robotSantaY);
                housesVisited.add(coordinate);
            }
        }

        System.out.println("Visited house : " + housesVisited.size());
        System.out.println("------ Part2 : End ------");
    }
}
