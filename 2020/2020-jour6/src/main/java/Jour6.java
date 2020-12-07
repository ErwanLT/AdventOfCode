import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.google.common.primitives.Ints.asList;
import static java.util.stream.IntStream.range;

public class Jour6 {

    private static String inputs = "";

    public static void main(String[] args) throws URISyntaxException {
        System.out.println("------ Start ------");

        getFileInput();

        solvePart1();
        solvePart2();

        System.out.println("------ End ------");
    }

    private static void solvePart1() {
        System.out.println("------ Part1 : Start ------");

        Long sumOfCount = Arrays.stream(inputs.split("\n\n")).map(s -> s.replace("\n", ""))
                .mapToLong(s -> s.chars().distinct().count()).sum();
        System.out.println(sumOfCount);

        System.out.println("------ Part1 : End ------");
    }

    private static void solvePart2() {
        System.out.println("------ Part2 : Start ------");

        int x = Arrays.stream(inputs.split("\n\n")).mapToInt(group -> {
            String[] people = group.split("\n");
            List<Integer> c = new ArrayList<>(asList(people[0].chars().toArray()));
            range(1, people.length).forEach(i -> c.retainAll(asList(people[i].chars().toArray())));
            return c.size();
        }).sum();

        System.out.println(x);

        System.out.println("------ Part2 : End ------");
    }

    private static void getFileInput() throws URISyntaxException {
        System.out.println("------ Getting input : Start ------");
        Jour6 app = new Jour6();
        String fileName = "input";
        File file = app.getFileFromResource(fileName);
        inputs = getFileLine(file);
        System.out.println("------ Getting input : End ------");
    }

    private static String getFileLine(File file) {
        try {
            return Files.readString(file.toPath(), StandardCharsets.UTF_8);
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
}
