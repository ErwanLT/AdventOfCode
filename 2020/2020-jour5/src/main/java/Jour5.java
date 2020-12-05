import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Jour5 {

    private static List<String> inputs = new ArrayList<>();

    public static void main(String[] args) throws URISyntaxException {
        System.out.println("------ Start ------");

        getFileInput();

        solvePart1();
        solvePart2();

        System.out.println("------ End ------");
    }

    private static void getFileInput() throws URISyntaxException {
        System.out.println("------ Getting input : Start ------");
        Jour5 app = new Jour5();
        String fileName = "input";
        File file = app.getFileFromResource(fileName);
        inputs = getFileLine(file);
        System.out.println("------ Getting input : End ------");
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

    private static void solvePart1() {
        System.out.println("------ Part1 : Start ------");

        int seatId = seatID().stream().mapToInt(i->i).max().getAsInt();
        System.out.println("Highest seat ID : " + seatId);

        System.out.println("------ Part1 : End ------");
    }

    private static void solvePart2() {
        System.out.println("------ Part2 : Start ------");

        Set<Integer> l = seatID();
        int mySeatID = l.stream().mapToInt(e -> e).filter(n -> l.contains(n) && l.contains(n+2) && !l.contains(n+1)).sum() + 1;
        System.out.println("My seat id is : " + mySeatID);

        System.out.println("------ Part2 : End ------");
    }

    private static Set<Integer> seatID() {
        Set<Integer> l = new HashSet<>();
        for(String s : inputs){
            int minRow = 0;
            int maxRow = 127;
            int minColumn = 0;
            int maxColumn = 7;
            for(char c : s.toCharArray()){
                if(c == 'F'){
                    maxRow -= (maxRow-minRow+1)/2;
                } else if(c == 'B'){
                    minRow += (maxRow-minRow+1)/2;
                } else if(c == 'L'){
                    maxColumn -= (maxColumn-minColumn+1)/2;
                } else if(c == 'R'){
                    minColumn += (maxColumn-minColumn+1)/2;
                }
            }
            l.add(maxRow * 8 + maxColumn);
        }
        return l;
    }
}
