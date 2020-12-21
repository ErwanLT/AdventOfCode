import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.*;

public class Jour20 {

    private static List<String> input;

    private static List<Pane> panes;

    private static String[] monster = {"                  # ", "#    ##    ##    ###", " #  #  #  #  #  #   "};

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
        Jour20 app = new Jour20();
        String fileName = "input";
        File file = app.getFileFromResource(fileName);
        getFileLine(file);
        System.out.println("------ Getting input : End ------");
    }

    private static void getFileLine(File file) {
        try {
            input = Files.readAllLines(file.toPath(), StandardCharsets.UTF_8);
            panes = parsePanes(input);
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
        Map<Pane, List<String[]>> corners = getCorners(panes);
        long product = corners.keySet().stream().mapToLong(Pane::getId).reduce(1, (a, b) -> a * b);
        System.out.println("The product of the IDs of the four corner tiles is : " + product);
        System.out.println("------ Part1 : End ------");
    }

    private static Map<Pane, List<String[]>> getCorners(List<Pane> panes) {
        Map<Pane, List<String[]>> corners = new HashMap<>();
        for (Pane pane : panes) {
            List<String[]> commonBorders = new ArrayList<>();
            List<String[]> borders = pane.getAllBorders();
            for (Pane otherPane : panes) {
                if (otherPane != pane) {
                    List<String[]> otherPaneBorders = otherPane.getAllBordersWithFlips();
                    commonBorders.addAll(getCommonBorders(borders, otherPaneBorders));
                }
            }
            if (commonBorders.size() == 2) {
                corners.put(pane, commonBorders);
            }
        }
        return corners;
    }

    private static List<String[]> getCommonBorders(List<String[]> a, List<String[]> b) {
        List<String[]> commonBorders = new ArrayList<>();
        for (String[] borderA : a) {
            for (String[] borderB : b) {
                if (Arrays.compare(borderA, borderB) == 0) {
                    commonBorders.add(borderA);
                }
            }
        }
        return commonBorders;
    }

    private static List<Pane> parsePanes(List<String> lines) {
        String[][] area = new String[10][];
        int currentId = 0;
        int currentTileLineIndex = 0;
        List<Pane> panes = new ArrayList<>();
        for (String line : lines) {
            if (line.startsWith("Tile")) {
                String idStr = line.split(" ")[1];
                currentId = Integer.parseInt(idStr.substring(0, idStr.length() - 1));
                area = new String[10][];
                currentTileLineIndex = 0;
                continue;
            }
            if (!line.trim().isBlank()) {
                area[currentTileLineIndex++] = line.split("");
            } else {
                panes.add(new Pane(currentId, area));
            }
        }
        panes.add(new Pane(currentId, area));
        return panes;
    }


    private static void solvePart2() {
        System.out.println("------ Part2 : Start ------");

        List<Pane> panes = parsePanes(input);
        Map<Pane, List<String[]>> corners = getCorners(panes);
        int n = (int) Math.floor(Math.sqrt(panes.size()));
        Pane[][] panesMap = new Pane[n][n];
        Set<Integer> usedPanes = new HashSet<>();
        Pane firstCorner = new ArrayList<>(corners.keySet()).get(0);
        List<String[]> commonBorders = corners.get(firstCorner);

        alignToRightBorder(firstCorner, commonBorders.get(0))
                .flatMap(pane -> alignToBottomBorder(pane, commonBorders.get(1)))
                .ifPresent(pane -> panesMap[0][0] = pane);
        usedPanes.add(firstCorner.getId());

        for (int i = 1; i < n; i++) {
            for (Pane pane : panes) {
                if (!usedPanes.contains(pane.getId())) {
                    final int j = i;
                    alignToLeftBorder(pane, panesMap[0][i - 1].getRightBorder())
                            .ifPresent(alignedPane -> {
                                panesMap[0][j] = alignedPane;
                                usedPanes.add(alignedPane.getId());
                            });
                }
            }
        }

        for (int i = 1; i < n; i++) {
            for (int j = 0; j < n; j++) {
                for (Pane pane : panes) {
                    if (!usedPanes.contains(pane.getId())) {
                        Optional<Pane> result = alignToTopBorder(pane, panesMap[i - 1][j].getBottomBorder());
                        if (result.isPresent()) {
                            panesMap[i][j] = result.get();
                            usedPanes.add(result.get().getId());
                            break;
                        }
                    }
                }
            }
        }

        String[][] monsterArea = new String[monster.length][];
        for (int i = 0; i < monster.length; i++) {
            monsterArea[i] = monster[i].split("");
        }

        long solution = waterRoughness(createBigMapPane(panesMap), monsterArea);
        System.out.println(solution);

        System.out.println("------ Part2 : End ------");
    }

    private static Optional<Pane> alignToRightBorder(Pane pane, String[] border) {
        for (int i = 0; i < 4; i++) {
            pane = pane.rotateRight();
            if (Arrays.equals(border, pane.getRightBorder())) {
                return Optional.of(pane);
            }
        }

        pane = pane.flipHorizontally();
        for (int i = 0; i < 4; i++) {
            pane = pane.rotateRight();
            if (Arrays.equals(border, pane.getRightBorder())) {
                return Optional.of(pane);
            }
        }

        return Optional.empty();
    }

    private static Optional<Pane> alignToLeftBorder(Pane pane, String[] border) {
        for (int i = 0; i < 4; i++) {
            pane = pane.rotateRight();
            if (Arrays.equals(border, pane.getLeftBorder())) {
                return Optional.of(pane);
            }
        }

        pane = pane.flipHorizontally();
        for (int i = 0; i < 4; i++) {
            pane = pane.rotateRight();
            if (Arrays.equals(border, pane.getLeftBorder())) {
                return Optional.of(pane);
            }
        }

        return Optional.empty();
    }

    private static Optional<Pane> alignToBottomBorder(Pane pane, String[] border) {
        for (int i = 0; i < 4; i++) {
            pane = pane.rotateRight();
            if (Arrays.equals(border, pane.getBottomBorder())) {
                return Optional.of(pane);
            }
        }

        pane = pane.flipHorizontally();
        for (int i = 0; i < 4; i++) {
            pane = pane.rotateRight();
            if (Arrays.equals(border, pane.getBottomBorder())) {
                return Optional.of(pane);
            }
        }

        return Optional.empty();
    }

    private static Optional<Pane> alignToTopBorder(Pane pane, String[] border) {
        for (int i = 0; i < 4; i++) {
            pane = pane.rotateRight();
            if (Arrays.equals(border, pane.getTopBorder())) {
                return Optional.of(pane);
            }
        }

        pane = pane.flipHorizontally();
        for (int i = 0; i < 4; i++) {
            pane = pane.rotateRight();
            if (Arrays.equals(border, pane.getTopBorder())) {
                return Optional.of(pane);
            }
        }

        return Optional.empty();
    }

    private static Pane createBigMapPane(Pane[][] panesMap) {
        int n = panesMap.length;
        String[][] bigMap = new String[n * 8][n * 8];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                String[][] area = panesMap[i][j].getArea();
                for (int x = 1; x < area.length - 1; x++) {
                    for (int y = 1; y < area.length - 1; y++) {
                        bigMap[i * (area.length - 2) + (x - 1)][j * (area.length - 2) + (y - 1)] = area[x][y];
                    }
                }
            }
        }

        return new Pane(0, bigMap);
    }

    private static int waterRoughness(Pane pane, String[][] monster) {
        int monsterHash = 0;

        for (int i = 0; i < monster.length; i++) {
            for (int j = 0; j < monster[i].length; j++) {
                if (monster[i][j].equals("#")) {
                    monsterHash++;
                }
            }
        }

        for (int i = 0; i < 4; i++) {
            pane = pane.rotateRight();
            int nrOfMonsters = countMonsters(pane, monster);
            if (nrOfMonsters != 0) {
                return pane.countHash() - nrOfMonsters * monsterHash;
            }
        }

        pane = pane.flipHorizontally();
        for (int i = 0; i < 4; i++) {
            pane = pane.rotateRight();
            int nrOfMonsters = countMonsters(pane, monster);
            if (nrOfMonsters != 0) {
                return pane.countHash() - nrOfMonsters * monsterHash;
            }
        }
        return 0;
    }

    private static int countMonsters(Pane pane, String[][] monster) {
        int monsterCounter = 0;
        String[][] bigPane = pane.getArea();

        for (int i = 0; i < bigPane.length - monster.length; i++) {
            for (int j = 0; j < bigPane[0].length - monster[0].length; j++) {
                boolean allMatch = true;
                label:
                for (int x = 0; x < monster.length; x++) {
                    for (int y = 0; y < monster[0].length; y++) {
                        if (monster[x][y].equals("#")) {
                            if (!bigPane[i + x][j + y].equals("#")) {
                                allMatch = false;
                                break label;
                            }
                        }
                    }
                }
                if (allMatch) {
                    monsterCounter++;
                }
            }
        }
        return monsterCounter;
    }
}
