import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.*;
import java.util.stream.LongStream;

import static java.lang.Math.toIntExact;
import static java.util.stream.Collectors.toCollection;

public class Jour22 {

    private static String[] input;

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
        Jour22 app = new Jour22();
        String fileName = "input";
        File file = app.getFileFromResource(fileName);
        getFileLine(file);
        System.out.println("------ Getting input : End ------");
    }

    private static void getFileLine(File file) {
        try {
            input = Files.readString(file.toPath(), StandardCharsets.UTF_8).split("\n\n");
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

        Deque<Long> p1 = getInput(0, input);
        Deque<Long> p2 = getInput(1, input);

        while(p1.size()>0 && p2.size() > 0){
            long l1 = p1.pop();
            long l2 = p2.pop();
            if(l1 > l2){
                p1.add(l1);
                p1.add(l2);
            } else {
                p2.add(l2);
                p2.add(l1);
            }
        }
        Deque<Long> winner = p1.size()>0 ? p1 : p2;
        long score = calcScore(winner);
        System.out.println("The winning player's score : " + score);
        System.out.println("------ Part1 : End ------");
    }

    private static ArrayDeque<Long> getInput(int i, String[] input) {
        return Arrays.stream(input[i].split("\n")).filter(e -> !e.startsWith("Player")).map(Long::parseLong).collect(toCollection(ArrayDeque::new));
    }

    private static long calcScore(Deque<Long> winner) {
        return LongStream.rangeClosed(1, winner.size()).boxed().sorted(Comparator.reverseOrder()).mapToLong(l -> winner.pop() * l).sum();
    }

    private static void solvePart2() {
        System.out.println("------ Part2 : Start ------");
        Deque<Long> p1 = getInput(0, input);
        Deque<Long> p2 = getInput(1, input);
        long score =  calcScore(playGame(p1, p2) == Player.P1 ? p1 : p2);
        System.out.println("The winning player's score : " + score);
        System.out.println("------ Part2 : End ------");
    }

    private static Player playGame(Deque<Long> p1, Deque<Long> p2){
        Set<List<Long>> playedGames = new HashSet<>();
        while(p1.size()>0 && p2.size() > 0){
            if(!playedGames.add(new ArrayList<>(p1))){
                return Player.P1;
            }

            long l1 = p1.pop();
            long l2 = p2.pop();
            if(p1.size() < l1 || p2.size() < l2){
                if(l1 > l2){
                    p1.add(l1);
                    p1.add(l2);
                } else {
                    p2.add(l2);
                    p2.add(l1);
                }
            } else {
                if(playGame(new ArrayDeque<>(new ArrayList<>(p1).subList(0, toIntExact(l1))), new ArrayDeque<>(new ArrayList<>(p2).subList(0, toIntExact(l2)))) == Player.P1){
                    p1.add(l1);
                    p1.add(l2);
                } else {
                    p2.add(l2);
                    p2.add(l1);
                }
            }
        }
        return p1.size()>0 ? Player.P1 : Player.P2;
    }
}
