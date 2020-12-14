import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Long.parseLong;
import static java.util.Arrays.stream;
import static java.util.stream.IntStream.range;

public class Jour13 {

    private static List<String> input;

    public static void main(String[] args) throws URISyntaxException {
        System.setOut(new MyPrinter(System.out));
        System.out.println("------ Start ------");

        getFileInput();

        solvePart1();
        solvePart2();

        System.out.println("------ End ------");
    }

    private static void solvePart1() {
        System.out.println("------ Part1 : Start ------");

        int timestamp = Integer.parseInt(input.get(0));
        String[] stimes = input.get(1).split(",");
        List<Integer> t = new ArrayList<>();
        for(String time : stimes){
            if(!time.equals("x")){
                t.add(Integer.valueOf(time));
            }
        }
        int[] times = t.stream().mapToInt(i->i).toArray();

        loop1:for(int i = timestamp; true; i++){
            for(int j : times){
                if(i%j == 0){
                    System.out.println("response : " + j * (i - timestamp));
                    break loop1;
                }
            }
        }
        System.out.println("------ Part1 : End ------");
    }

    private static void solvePart2() {
        System.out.println("------ Part2 : Start ------");

        String[] s = input.get(1).split(",");
        long[][] nums = range(0, s.length).filter(i -> !s[i].equals("x"))
                .mapToObj(i -> new long[]{parseLong(s[i]), i})
                .toArray(long[][]::new);
        long product = stream(nums).mapToLong(a -> a[0]).reduce((a, b) -> a * b).getAsLong();
        long sum = stream(nums).mapToLong(a -> a[1] * (product/a[0]) * inverseModulo(product/a[0], a[0])).sum();
        long result = product - sum % product;

        System.out.println("response : " + result);

        System.out.println("------ Part2 : End ------");
    }


    private static long inverseModulo(long x, long y){
        if(x!=0){
            long modulo = y % x;
            return modulo == 0 ? 1 : y - inverseModulo(modulo, x) * y / x;
        }
        return 0;
    }

    private static void getFileInput() throws URISyntaxException {
        System.out.println("------ Getting input : Start ------");
        Jour13 app = new Jour13();
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

}
