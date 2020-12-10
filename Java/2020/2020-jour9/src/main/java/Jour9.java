import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static java.util.Arrays.copyOfRange;
import static java.util.Arrays.stream;

public class Jour9 {

    private static long[] inputs;

    private static long invalidNumber = 0L;

    public static void main(String[] args) throws URISyntaxException {
        System.out.println("------ Start ------");

        getFileInput();

        solvePart1();
        solvePart2();

        System.out.println("------ End ------");
    }

    private static void solvePart1() {
        System.out.println("------ Part1 : Start ------");

        invalidNumber = findNumber();
        System.out.println("The first number to be a sum of the first 25 is : " + invalidNumber);

        System.out.println("------ Part1 : End ------");
    }

    private static long findNumber() {
        for (int k = 0; k < inputs.length - 25; k++) {
            Set<Long> sums = new HashSet<>();
            for (int i = k; i < k + 25; i++) {
                for (int j = i + 1; j < k + 25; j++) {
                    sums.add(inputs[i] + inputs[j]);
                }
            }
            if (!sums.contains(inputs[k + 25])) {
                return inputs[k + 25];
            }
        }
        return 0;
    }

    private static void solvePart2() {
        System.out.println("------ Part2 : Start ------");

        long encryptionWeakness = findWeakness();
        System.out.println("The encryption weakness is : " + encryptionWeakness);

        System.out.println("------ Part2 : End ------");
    }

    private static long findWeakness() {
        for (int i = 2; i < inputs.length; i++) {
            for (int j = 0; j <= inputs.length - i; j++) {
                if (stream(inputs, j, j + i).sum() == invalidNumber) {
                    long[] window = copyOfRange(inputs, j, j + i + 1);
                    return stream(window).max().getAsLong() + stream(window).min().getAsLong();
                }
            }
        }
        return 0;
    }

    private static void getFileInput() throws URISyntaxException {
        System.out.println("------ Getting input : Start ------");
        Jour9 app = new Jour9();
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
}
