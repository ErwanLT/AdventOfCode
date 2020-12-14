import org.apache.commons.lang.StringUtils;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.Map;
import java.util.function.Predicate;
import java.util.regex.Pattern;

import static com.google.common.collect.ImmutableSet.toImmutableSet;

public class Jour4 {

    private static String[][] passports;

    private static final Map<String, String> expected = Map.of("byr", "^(200[0-2]|19[2-9][0-9])$",
            "iyr", "^(2020|201[0-9])$",
            "eyr", "^(2030|202[0-9])$",
            "hgt", "^((1([5-8][0-9]|9[0-3])cm)|((59|6[0-9]|7[0-6])in))$",
            "hcl", "^(#[0-9a-f]{6})$",
            "ecl", "^(amb|blu|brn|gry|grn|hzl|oth)$",
            "pid", "^[0-9]{9}$");

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
        Jour4 app = new Jour4();
        String fileName = "batchFile";
        File file = app.getFileFromResource(fileName);
        getFileLine(file);
        System.out.println("------ Getting input : End ------");
    }

    private static void getFileLine(File file) {
        try {
            String inputs = Files.readString(file.toPath(), StandardCharsets.UTF_8);
            passports = Arrays.stream(inputs.split("\n\n")).map(str -> str.replace("\n", " ")).map(str -> str.split(" ")).toArray(String[][]::new);
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

        long validPasseport = verifyPassport(Jour4::valid1);
        System.out.println("Valid passports : " + validPasseport);

        System.out.println("------ Part1 : End ------");
    }

    private static long verifyPassport(Predicate<String[]> verifyFunction) {
        return Arrays.stream(passports).filter(verifyFunction).count();
    }

    public static boolean valid1(String[] passport) {
        return Arrays.stream(passport).map(s -> s.substring(0, 3)).collect(toImmutableSet()).containsAll(expected.keySet());
    }

    private static void solvePart2() {
        System.out.println("------ Part2 : Start ------");

        long validPasseport = verifyPassport(Jour4::valid2);
        System.out.println("Valid passports : " + validPasseport);

        System.out.println("------ Part2 : End ------");
    }

    public static boolean valid2(String[] passport) {
        return valid1(passport) && Arrays.stream(passport).map(s -> s.split(":")).allMatch(s -> matchesRegex(s[0], s[1]));
    }

    public static boolean matchesRegex(String key, String validate) {
        if (!expected.containsKey(key)) return true;
        final Pattern pattern = Pattern.compile(expected.get(key));
        return pattern.matcher(validate).matches();
    }
}
