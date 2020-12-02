import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

public class Jour10A {

    private static String input =   ".###.#...#.#.##.#.####..\n" +
                                    ".#....#####...#.######..\n" +
                                    "#.#.###.###.#.....#.####\n" +
                                    "##.###..##..####.#.####.\n" +
                                    "###########.#######.##.#\n" +
                                    "##########.#########.##.\n" +
                                    ".#.##.########.##...###.\n" +
                                    "###.#.##.#####.#.###.###\n" +
                                    "##.#####.##..###.#.##.#.\n" +
                                    ".#.#.#####.####.#..#####\n" +
                                    ".###.#####.#..#..##.#.##\n" +
                                    "########.##.#...########\n" +
                                    ".####..##..#.###.###.#.#\n" +
                                    "....######.##.#.######.#\n" +
                                    "###.####.######.#....###\n" +
                                    "############.#.#.##.####\n" +
                                    "##...##..####.####.#..##\n" +
                                    ".###.#########.###..#.##\n" +
                                    "#.##.#.#...##...#####..#\n" +
                                    "##.#..###############.##\n" +
                                    "##.###.#####.##.######..\n" +
                                    "##.#####.#.#.##..#######\n" +
                                    "...#######.######...####\n" +
                                    "#....#.#.#.####.#.#.#.##";

    private static List<Position> asteroids= new ArrayList<>();

    private static int maxCount = 0;

    private static Position bestPosition = new Position(0,0);
    private static int maxX;
    private static int maxY;

    public static void main(String[] args) {
        System.out.println("------ Start ------");
        String[] lines = input.split("\n");
        maxX = lines[0].length();
        maxY = lines.length;

        populateAsteroidsPosition(lines, maxX, maxY);

        findBestPosition(maxX, maxY);

        System.out.println(maxCount);
        System.out.println("------ End ------");
    }

    private static void populateAsteroidsPosition(String[] lines, int maxX, int maxY) {
        System.out.println("------ Populating Asteroids Position : Start ------");
        for (int y = 0; y < maxY; y++) {
            for (int x = 0; x < maxX; x++) {
                if (lines[y].toCharArray()[x] == '#') {
                    asteroids.add(new Position(x, y));
                }
            }
        }
        System.out.println("------ Populating Asteroids Position : End ------");
    }

    private static void findBestPosition(int maxX, int maxY) {
        System.out.println("------ Finding Best Position : Start ------");
        for (int y = 0; y < maxY; y++) {
            for (int x = 0; x < maxX; x++) {
                int count = 0;
                for (int y2 = 0; y2 < maxY; y2++) {
                    for (int x2 = 0; x2 < maxX; x2++) {
                        if (checkLineOfSight(x, y, x2, y2)) count++;
                    }
                }
                if (count > maxCount) {
                    maxCount = count;
                    bestPosition = new Position(x, y);
                }
            }
        }
        System.out.println("------ Finding Best Position : End ------");
    }

    private static boolean checkLineOfSight(int startX, int startY, int stopX, int stopY) {
        Position start = new Position(startX, startY);
        Position stop = new Position(stopX, stopY);
        if (!asteroids.contains(start)) return false;
        if (!asteroids.contains(stop)) return false;
        if (start.equals(stop)) return false;
        int dy = stopY - startY;
        int dx = stopX - startX;
        if (!(dx == dy && dy == 0)) {
            int gcd = getGCD(dy, dx);
            dy /= gcd;
            dx /= gcd;
        }
        int x = startX + dx;
        int y = startY + dy;
        while (y < maxY && y >= 0 && x < maxX && x >= 0) {
            Position pos = new Position(x, y);
            if (asteroids.contains(pos)) {
                if (y == stopY && x == stopX) {
                    return true;
                } else {
                    return false;
                }
            }
            x += dx;
            y += dy;
        }
        return false;
    }

    private static int getGCD(int a, int b) {
        a = Math.abs(a);
        b = Math.abs(b);
        while (b != 0) {
            int t = b;
            b = a % b;
            a = t;
        }
        return a;
    }

    @Getter
    @Setter
    @Data
    @AllArgsConstructor
    private static class Position {

        private int x = 0;

        private int y = 0;

    }
}
