import org.apache.commons.lang3.ArrayUtils;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.List;

import static java.util.Arrays.sort;


public class Jour10 {

    private static long[] inputs;

    public static void main(String[] args) throws URISyntaxException {
        System.out.println("------ Start ------");

        getFileInput();
        sortInput();

        solvePart1();
        solvePart2();

        System.out.println("------ End ------");
    }


    private static void getFileInput() throws URISyntaxException {
        System.out.println("------ Getting input : Start ------");
        Jour10 app = new Jour10();
        String fileName = "input";
        File file = app.getFileFromResource(fileName);
        getFileLine(file);
        System.out.println("------ Getting input : End ------");
    }

    private static void getFileLine(File file) {
        try {
            List<String> inputLines = Files.readAllLines(file.toPath(), StandardCharsets.UTF_8);
            inputs = inputLines.stream().mapToLong(Long::parseLong).toArray();
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

    private static void sortInput() {
        sort(inputs);
        inputs = ArrayUtils.add( inputs, inputs[inputs.length-1]+3);
        inputs = ArrayUtils.add(inputs, 0,0L);
    }

    private static void solvePart1() {
        System.out.println("------ Part1 : Start ------");

        MapDay10<Long> diffs = new MapDay10<>();
        for(int i = 1; i<inputs.length; i++) {
            diffs.increment(inputs[i] - inputs[i - 1]);
        }
        long number = diffs.get(1L) * diffs.get(3L);
        System.out.println("number of 1-jolt differences multiplied by the number of 3-jolt differences : " + number);

        System.out.println("------ Part1 : End ------");
    }

    private static void solvePart2() {
        System.out.println("------ Part2 : Start ------");

        MapDay10<Long> nRoutes = new MapDay10<>();
        nRoutes.increment(inputs[inputs.length - 1]);
        for (int i = inputs.length - 2; i >= 0; i--) {
            for (int j = i + 1; j < inputs.length && j <= i + 3; j++) {
                if (inputs[j] - inputs[i] <= 3) {
                    nRoutes.increment(inputs[i], nRoutes.get(inputs[j]));
                }
            }
        }
        long number = nRoutes.get(0L);
        System.out.println("Total number of distinct ways you can arrange the adapters :" + number);


        System.out.println("------ Part2 : End ------");
    }
}
