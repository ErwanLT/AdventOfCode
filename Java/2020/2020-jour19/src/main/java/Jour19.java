import helper.Helper;
import helper.ParseUtils;
import helper.RegexHelper;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

public class Jour19 {

    private static List<String> inputLines;

    private static Map<Integer, Rule> rules;
    private static List<String> messages;

    public static void main(String[] args) throws URISyntaxException {
        System.setOut(new MyPrinter(System.out));
        System.out.println("------ Start ------");

        getFileInput();
        parseInput();

        solvePart1();
        solvePart2();

        System.out.println("------ End ------");
    }

    private static void getFileInput() throws URISyntaxException {
        System.out.println("------ Getting input : Start ------");
        Jour19 app = new Jour19();
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

    private static void parseInput() {
        List<List<String>> input = Helper.split(inputLines, String::isBlank);
        rules = new HashMap<>();
        messages = input.get(1);

        for (String ruleStr : input.get(0)) {
            RegexHelper match = ParseUtils.parseMatch("(\\d+): (.+)", ruleStr);
            int id = match.groupInt(1);
            String raw = match.group(2);
            Rule rule = rules.computeIfAbsent(id, Rule::new);

            if (raw.startsWith("\"")) {
                rule.setRaw(Character.toString(raw.charAt(1)));
                rule.setLiteral(true);
            } else {
                rule.setLiteral(false);

                for (String groupStr : raw.split("\\|")) {
                    String[] split = groupStr.trim().split(" ");
                    List<Rule> group = new ArrayList<>(split.length);
                    for (String subId : split) {
                        group.add(rules.computeIfAbsent(Integer.parseInt(subId), Rule::new));
                    }
                    rule.children.add(group);
                }
            }
        }
    }

    private static void solvePart1() {
        System.out.println("------ Part1 : Start ------");
        int matchCount = matchCount(false);
        System.out.println("Message matching rule 0 : " + matchCount);
        System.out.println("------ Part1 : End ------");
    }


    private static void solvePart2() {
        System.out.println("------ Part2 : Start ------");
        int matchCount = matchCount(true);
        System.out.println("Message matching rule 0 : " + matchCount);
        System.out.println("------ Part2 : End ------");
    }

    private static int matchCount(boolean recursive) {
        String regex = rules.get(0).genRegex(recursive);
        Pattern pattern = Pattern.compile(regex);
        int count = 0;

        for (String message : messages) {
            if (pattern.matcher(message).matches())
                count++;
        }

        return count;
    }
}
