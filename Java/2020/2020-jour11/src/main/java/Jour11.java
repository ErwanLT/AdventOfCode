import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.List;

import static java.util.Arrays.stream;

public class Jour11 {

    private static char[][] inputs;

    public static void main(String[] args) throws URISyntaxException {
        System.out.println("------ Start ------");

        getFileInput();

        solvePart1();
        solvePart2();

        System.out.println("------ End ------");
    }


    private static void getFileInput() throws URISyntaxException {
        System.out.println("------ Getting input : Start ------");
        Jour11 app = new Jour11();
        String fileName = "input";
        File file = app.getFileFromResource(fileName);
        getFileLine(file);
        System.out.println("------ Getting input : End ------");
    }

    private static void getFileLine(File file) {
        try {
            List<String> inputLines = Files.readAllLines(file.toPath(), StandardCharsets.UTF_8);
            inputs = inputLines.stream().map(String::toCharArray).toArray(char[][]::new);
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

        char[][] part1 = inputs.clone();

        int changes;
        do{
            changes = 0;
            char[][] newGrid = new char[part1.length][part1[0].length];
            for(int i = 0; i<part1.length; i++){
                for(int j = 0; j<part1[0].length; j++){
                    Point p = new Point(i, j);
                    char[][] finalInput = part1;
                    long occupied = stream(Direction.values()).filter(d ->
                            d.getInGrid(finalInput, d.move(p)) == '#'
                    ).count();
                    changes = getChanges(part1, changes, newGrid, i, j, occupied, 4L);
                }
            }
            part1 = newGrid;
        } while(changes > 0);
        long count = getCount(part1, '#');
        System.out.println("Seats occupied : " + count);

        System.out.println("------ Part1 : End ------");
    }

    private static void solvePart2() {
        System.out.println("------ Part2 : Start ------");

        char[][] part2 = inputs.clone();

        int changes;
        do{
            changes = 0;
            char[][] newGrid = new char[part2.length][part2[0].length];
            for(int i = 0; i<part2.length; i++){
                for(int j = 0; j<part2[0].length; j++){
                    int occupied = countOccupied(part2, i, j);
                    changes = getChanges(part2, changes, newGrid, i, j, occupied, 5L);
                }
            }
            part2 = newGrid;
        } while (changes > 0);
        long count =  getCount(part2, '#');
        System.out.println("Seats occupied : " + count);

        System.out.println("------ Part2 : End ------");
    }

    private static int getChanges(char[][] input, int changes, char[][] newGrid, int i, int j, long occupied, long numOccupied) {
        if(occupied == 0L && input[i][j] == 'L'){
            newGrid[i][j] = '#';
            changes++;
        } else if(occupied >= numOccupied && input[i][j] == '#'){
            newGrid[i][j] = 'L';
            changes++;
        } else {
            newGrid[i][j] = input[i][j];
        }
        return changes;
    }

    private static long getCount(char[][] input, char ch) {
        return stream(input).flatMapToInt(c -> new String(c).chars()).filter(c -> c == ch).count();
    }

    private static int countOccupied(char[][] input, int i, int j) {
        int occupied = 0;
        Direction[] dirs = Direction.values();
        for(Direction dir : dirs){
            Point p = new Point(i, j);
            p = dir.move(p, 1);
            while(p.x>=0 && p.x< input.length && p.y>=0 && p.y< input[0].length && input[p.x][p.y] != 'L'){
                if(input[p.x][p.y] == '#'){
                    occupied++;
                    break;
                }
                p = dir.move(p, 1);
            }
        }
        return occupied;
    }
}
