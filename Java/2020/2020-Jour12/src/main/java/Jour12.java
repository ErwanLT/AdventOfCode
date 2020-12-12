import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.List;

import static java.util.stream.Collectors.toList;


public class Jour12 {

    private static List<Flight> inputFlight;

    public static void main(String[] args) throws URISyntaxException {
        System.out.println("------ Start ------");

        getFileInput();

        solvePart1();
        solvePart2();

        System.out.println("------ End ------");
    }


    private static void getFileInput() throws URISyntaxException {
        System.out.println("------ Getting input : Start ------");
        Jour12 app = new Jour12();
        String fileName = "input";
        File file = app.getFileFromResource(fileName);
        getFileLine(file);
        System.out.println("------ Getting input : End ------");
    }

    private static void getFileLine(File file) {
        try {
            List<String> input = Files.readAllLines(file.toPath(), StandardCharsets.UTF_8);
            inputFlight = input.stream().map(e -> new Flight(e.charAt(0), Integer.parseInt(e.substring(1)))).collect(toList());
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

        Direction face = Direction.EAST;
        Point location = new Point(0, 0);
        for(Flight f : inputFlight){
            switch(f.dir){
                case 'L':case 'R':
                {
                    int num = f.distance;
                    while(num>0){
                        face = face.turn(f.dir == 'R');
                        num-=90;
                    }
                    break;
                }
                case 'F': {
                    location = face.move(location, f.distance);
                    break;
                }
                default: {
                    location = Direction.getByDir(f.dir).move(location, f.distance);
                    break;
                }
            }
        }
        System.out.println(Math.abs(location.x) + Math.abs(location.y));

        System.out.println("------ Part1 : End ------");
    }

    private static void solvePart2() {
        System.out.println("------ Part2 : Start ------");

        Point waypoint = new Point(10, -1);
        Point location = new Point(0, 0);
        for(Flight f : inputFlight){
            switch(f.dir){
                case 'L':case 'R':
                {
                    int num = f.distance;
                    while(num>0){
                        waypoint = turn(waypoint, f.dir == 'R');
                        num-=90;
                    }
                    break;
                }
                case 'F': {
                    location = new Point(location.x+(waypoint.x*f.distance), location.y+(waypoint.y*f.distance));
                    break;
                }
                default: {
                    waypoint = Direction.getByDir(f.dir).move(waypoint, f.distance);
                    break;
                }
            }
        }
        System.out.println(Math.abs(location.x) + Math.abs(location.y));

        System.out.println("------ Part2 : End ------");
    }

    private static Point turn(Point w, boolean b) {
        return b ? new Point(-w.y, w.x) : new Point(w.y, -w.x);
    }
}
