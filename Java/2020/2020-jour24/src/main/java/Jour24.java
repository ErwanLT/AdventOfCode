import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.*;
import java.util.List;

import static java.util.stream.Collectors.toList;

public class Jour24 {

    private static String[] inputLines;

    private static Set<Point> visitedTiles = new HashSet<>();

    public static void main(String[] args) throws URISyntaxException {
        System.setOut(new MyPrinter(System.out));
        System.out.println("------ Start ------");

        getFileInput();

        solvePart1();
        solvePart2();

        System.out.println("------ End ------");
    }

    private static void getFileInput() throws URISyntaxException {
        System.out.println("------ Getting input : Start ------");
        Jour24 app = new Jour24();
        String fileName = "input";
        File file = app.getFileFromResource(fileName);
        getFileLine(file);
        System.out.println("------ Getting input : End ------");
    }

    private static void getFileLine(File file) {
        try {
            inputLines = Files.readAllLines(file.toPath(), StandardCharsets.UTF_8).toArray(String[]::new);
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

        List<List<HexDirection>> input = Arrays.stream(inputLines).map(s -> read(s)).collect(toList());
        for(List<HexDirection> dirs : input){
            Point pos = new Point(0,0);
            for(HexDirection dir : dirs){
                pos = dir.move(pos);
            }
            if(!visitedTiles.add(pos)){
                visitedTiles.remove(pos);
            }
        }
        System.out.println("Tiles left with the black side up : " + visitedTiles.size());

        System.out.println("------ Part1 : End ------");
    }

    private static List<HexDirection> read(String dirs){
        List<HexDirection> res = new ArrayList<>(dirs.length());
        while(dirs.length()>0){
            Optional<HexDirection> direction;
            if(dirs.length()>1 && (direction = HexDirection.get(dirs.substring(0,2))).isPresent()){
                res.add(direction.get());
                dirs = dirs.substring(2);
            } else if ((direction = HexDirection.get(dirs.substring(0,1))).isPresent()){
                res.add(direction.get());
                dirs = dirs.substring(1);
            }
        }
        return res;
    }

    private static void solvePart2() {
        System.out.println("------ Part2 : Start ------");

        for(int i = 0; i<100; i++){
            Set<Point> newPos = new HashSet<>();
            visitedTiles.forEach(p -> addNeighbors(visitedTiles, newPos, new HashSet<>(), p, true));
            visitedTiles = newPos;
        }
        System.out.println("Number of tiles that would be black after 100 days : " + visitedTiles.size());

        System.out.println("------ Part2 : End ------");
    }

    private static void addNeighbors(Set<Point> pos, Set<Point> newPos, Set<Point> checkedPos, Point p, boolean active) {
        if (!checkedPos.contains(p)) {
            int neighbours = 0;
            checkedPos.add(p);
            for (HexDirection dir : HexDirection.values()) {
                Point x = dir.move(p);
                if (pos.contains(x)) {
                    neighbours++;
                } else if (active) {
                    addNeighbors(pos, newPos, checkedPos, x, false);
                }
            }
            if ((active && (neighbours == 1 || neighbours == 2)) ||
                    (!active && neighbours == 2)) {
                newPos.add(p);
            }
        }
    }
}
