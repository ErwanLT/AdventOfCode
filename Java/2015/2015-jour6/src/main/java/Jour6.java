import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Jour6 {

    private static List<String> input;

    public static void main(String[] args) throws URISyntaxException {
        System.out.println("------ Start ------");

        getFileInput();

        solvePart1();
        solvePart2();

        System.out.println("------ End ------");
    }

    private static void getFileInput() throws URISyntaxException {
        System.out.println("------ Getting input : Start ------");
        Jour6 app = new Jour6();
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


    private static void solvePart1() {
        System.out.println("------ Part1 : Start ------");

        boolean[][] lit = new boolean[1000][1000];

        for (String line : input) {
            Matcher m = Pattern.compile("([e|n ([n|f])]) (\\d+),(\\d+) through (\\d+),(\\d+)").matcher(line);
            if(m.find()) {
                String instr = m.group(1);
                int x_s = Integer.parseInt(m.group(2));
                int y_s = Integer.parseInt(m.group(3));
                int x_e = Integer.parseInt(m.group(4));
                int y_e = Integer.parseInt(m.group(5));
                if(instr.equals("n")) {        // on
                    for(int x = x_s; x <= x_e; x++)
                        for(int y = y_s; y <= y_e; y++) {
                            lit[x][y] = true;
                        }
                } else if(instr.equals("f")) { // off
                    for(int x = x_s; x <= x_e; x++)
                        for(int y = y_s; y <= y_e; y++) {
                            lit[x][y] = false;
                        }
                } else if(instr.equals("e")) { // toggle
                    for(int x = x_s; x <= x_e; x++) {
                        for (int y = y_s; y <= y_e; y++) {
                            lit[x][y] = !lit[x][y];
                        }
                    }
                }
            }
        }

        int lit_count = 0;
        for(int x = 0; x < 1000; x++) {
            for (int y = 0; y < 1000; y++) {
                lit_count += lit[x][y] ? 1 : 0;
            }
        }
        System.out.println("Light lit : " + lit_count);
        System.out.println("------ Part1 : End ------");
    }


    private static void solvePart2() {
        System.out.println("------ Part2 : Start ------");

        int[][] brightness = new int[1000][1000];

        for (String line : input) {
            Matcher m = Pattern.compile("([e|n ([n|f])]) (\\d+),(\\d+) through (\\d+),(\\d+)").matcher(line);
            if (m.find()) {
                String instr = m.group(1);
                int x_s = Integer.parseInt(m.group(2));
                int y_s = Integer.parseInt(m.group(3));
                int x_e = Integer.parseInt(m.group(4));
                int y_e = Integer.parseInt(m.group(5));
                if (instr.equals("n")) {        // on
                    for (int x = x_s; x <= x_e; x++)
                        for (int y = y_s; y <= y_e; y++) {
                            brightness[x][y] += 1;
                        }
                } else if (instr.equals("f")) { // off
                    for (int x = x_s; x <= x_e; x++)
                        for (int y = y_s; y <= y_e; y++) {
                            brightness[x][y] -= (brightness[x][y] > 0 ? 1 : 0);
                        }
                } else if (instr.equals("e")) { // toggle
                    for (int x = x_s; x <= x_e; x++) {
                        for (int y = y_s; y <= y_e; y++) {
                            brightness[x][y] += 2;
                        }
                    }
                }
            }
        }

        int luminosity = 0;
        for (int x = 0; x < 1000; x++){
            for (int y = 0; y < 1000; y++) {
                luminosity += brightness[x][y];
            }
        }
        System.out.println("total brightness : " + luminosity);
        System.out.println("------ Part2 : End ------");
    }
}
