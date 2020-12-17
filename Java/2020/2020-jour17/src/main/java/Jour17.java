import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class Jour17 {

    private static List<String> inputLines;
    private static Set<Coordinates> initialState;

    public static void main(String[] args) throws URISyntaxException {
        System.setOut(new MyPrinter(System.out));
        System.out.println("------ Start ------");

        getFileInput();
        parse();

        solvePart1();
        solvePart2();

        System.out.println("------ End ------");
    }

    private static void getFileInput() throws URISyntaxException {
        System.out.println("------ Getting input : Start ------");
        Jour17 app = new Jour17();
        String fileName = "input";
        File file = app.getFileFromResource(fileName);
        getFileLine(file);
        System.out.println("------ Getting input : End ------");
    }

    private static void getFileLine(File file) {
        try {
            inputLines = Files.readAllLines(file.toPath(), StandardCharsets.UTF_8);
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
        int activeCube = runSixCycles(false);
        System.out.println("Cubes left in the active state : " + activeCube);
        System.out.println("------ Part1 : End ------");
    }


    private static void solvePart2() {
        System.out.println("------ Part2 : Start ------");
        int activeCube = runSixCycles(true);
        System.out.println("Cubes left in the active state : " + activeCube);
        System.out.println("------ Part2 : End ------");
    }

    private static int runSixCycles(boolean useFourthDim) {
        System.out.println("------ Running cycles : Start ------");
        Set<Coordinates> set = new HashSet<>(initialState);

        for (int cycle = 0; cycle < 6; cycle++) {
            set = runCycle(set, useFourthDim);
        }
        System.out.println("------ Running cycles : End ------");
        return set.size();

    }

    private static Set<Coordinates> runCycle(Set<Coordinates> set, boolean useFourthDim) {
        Set<Coordinates> next = new HashSet<>();
        Set<Coordinates> leftToCheck = new HashSet<>();

        for (Coordinates active : set) {
            int neighbors = getActiveNeighbors(set, active, leftToCheck, useFourthDim);
            if (neighbors == 2 || neighbors == 3)
                next.add(active);
        }
        for (Coordinates inactive : leftToCheck) {
            // If the inactive coord is in the set of active,
            // it isn't inactive and we already checked it.
            if (set.contains(inactive))
                continue;
            int neighbors = getActiveNeighbors(set, inactive, null, useFourthDim);
            if (neighbors == 3)
                next.add(inactive);
        }

        return next;
    }

    private static int getActiveNeighbors(Set<Coordinates> set, Coordinates coord, Set<Coordinates> leftToCheck, boolean useFourthDim) {
        int active = 0;
        int startW = useFourthDim ? -1 : 0;
        for (int z = -1; z <= 1; z++) {
            for (int y = -1; y <= 1; y++) {
                for (int x = -1; x <= 1; x++) {
                    // If no fourth dimension, just use 0 for w
                    for (int w = startW; useFourthDim ? w <= 1 : w == startW; w++) {
                        if (x == 0 && y == 0 && z == 0 && w == 0)
                            continue;
                        Coordinates neighbor = coord.resolve(x, y, z, w);
                        if (set.contains(neighbor))
                            active++;
                        if (leftToCheck != null)
                            leftToCheck.add(neighbor);
                    }
                }
            }
        }
        return active;
    }

    private static void parse() {
        initialState = new HashSet<>();
        for (int y = 0; y < inputLines.size(); y++) {
            String line = inputLines.get(y);
            for (int x = 0; x < line.length(); x++) {
                if (line.charAt(x) == '#')
                    initialState.add(Coordinates.of(x, y, 0, 0));
            }
        }
    }
}
