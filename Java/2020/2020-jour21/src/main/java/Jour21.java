import Utils.MultiMap;
import Utils.Parser;
import Utils.Processor;
import Utils.Wrapper;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.*;

public class Jour21 {

    private static List<String> lines;

    private static Map<List<String>, Set<String>> foods;
    private static Set<String> allIngredients;
    private static MultiMap<String, String> allergenCandidates;

    public static void main(String[] args) throws URISyntaxException {
        System.setOut(new MyPrinter(System.out));
        System.out.println("------ Start ------");

        getFileInput();

        parseInput();

        solvePart1();
        solvePart2();

        System.out.println("------ End ------");
    }

    private static void parseInput() {
        foods = new HashMap<>();
        allIngredients = new HashSet<>();

        for (String line : lines) {
            Wrapper match = Parser.parseMatch("(.+) \\(contains (.+)\\)", line);

            List<String> ingredients = Arrays.asList(match.group(1).split(" "));
            Set<String> allergens = new HashSet<>(Arrays.asList(match.group(2).split(", ")));
            foods.put(ingredients, allergens);
            allIngredients.addAll(ingredients);
        }
    }

    private static void getFileInput() throws URISyntaxException {
        System.out.println("------ Getting input : Start ------");
        Jour21 app = new Jour21();
        String fileName = "input";
        File file = app.getFileFromResource(fileName);
        getFileLine(file);
        System.out.println("------ Getting input : End ------");
    }

    private static void getFileLine(File file) {
        try {
            lines = Files.readAllLines(file.toPath(), StandardCharsets.UTF_8);
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

        allergenCandidates = new MultiMap<>();
        for (Map.Entry<List<String>, Set<String>> food : foods.entrySet()) {
            List<String> ingredients = food.getKey();
            Set<String> allergens = food.getValue();

            for (String allergen : allergens) {
                allergenCandidates.put(allergen, Processor.intersection(allergenCandidates.get(allergen), ingredients));
            }
        }

        Set<String> impossible = new HashSet<>(allIngredients);
        for (Set<String> possible : allergenCandidates.values()) {
            impossible.removeAll(possible);
        }

        long count = 0;
        for (List<String> ingredients : foods.keySet()) {
            for (String ingredient : ingredients) {
                if (impossible.contains(ingredient))
                    count++;
            }
        }

        System.out.println("Times any of those ingredients appear : " + count);

        System.out.println("------ Part1 : End ------");
    }


    private static void solvePart2() {
        System.out.println("------ Part2 : Start ------");

        Map<String, String> found = new TreeMap<>(Comparator.naturalOrder());

        // Similar to Day 16 part 2
        while (!allergenCandidates.isEmpty()) {
            for (Map.Entry<String, Set<String>> entry : allergenCandidates.entrySet()) {
                Set<String> set = entry.getValue();
                if (set.isEmpty())
                    continue;
                set.removeAll(found.values());
                if (set.size() == 1) {
                    found.put(entry.getKey(), set.iterator().next());
                }
            }
            allergenCandidates.entrySet().removeIf(e -> e.getValue().isEmpty());
        }

        String list = String.join(",", found.values());
        System.out.println("Canonical dangerous ingredient list : " + list);

        System.out.println("------ Part2 : End ------");
    }

}
