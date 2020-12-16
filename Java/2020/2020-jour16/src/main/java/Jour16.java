import com.google.common.collect.Multimap;
import com.google.common.collect.MultimapBuilder;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.*;

import static java.util.Arrays.stream;
import static java.util.stream.Collectors.toList;
import static org.apache.commons.lang3.ArrayUtils.subarray;

public class Jour16 {

    private static String inputs;

    private static Rules[] rules;
    private static long[] myTicket;
    private static List<List<Long>> tickets;

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
        String[] input = inputs.split("\n\n");
        rules = stream(input[0].split("\n")).map(s -> Utils.readString(s, "%s: %n-%n or %n-%n", Rules.class)).toArray(Rules[]::new);
        myTicket = stream(input[1].split("\n")[1].split(",")).mapToLong(Long::parseLong).toArray();
        String[] ticketStrings = input[2].split("\n");
        tickets = stream(subarray(ticketStrings, 1, ticketStrings.length)).map(s -> stream(s.split(",")).map(Long::parseLong).collect(toList())).collect(toList());
    }

    private static void getFileInput() throws URISyntaxException {
        System.out.println("------ Getting input : Start ------");
        Jour16 app = new Jour16();
        String fileName = "input";
        File file = app.getFileFromResource(fileName);
        getFileLine(file);
        System.out.println("------ Getting input : End ------");
    }

    private static void getFileLine(File file) {
        try {
            inputs = Files.readString(file.toPath(), StandardCharsets.UTF_8);
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
        long ticketScanningErrorRate = tickets.stream().flatMapToLong(t -> t.stream().filter(n -> stream(rules).noneMatch(r -> r.check(n))).mapToLong(e -> e)).sum();
        System.out.println("Ticket scanning error rate : " + ticketScanningErrorRate);
        System.out.println("------ Part1 : End ------");
    }

    private static void solvePart2() {
        System.out.println("------ Part2 : Start ------");

        List<List<Long>> valid = tickets.stream().filter(t -> t.stream().allMatch(n -> stream(rules).anyMatch(r -> r.check(n)))).collect(toList());

        Multimap<Integer, Rules> ruleIndex = MultimapBuilder.hashKeys().arrayListValues().build();
        for (Rules r : rules) {
            for (int j = 0; j < valid.get(0).size(); j++) {
                int finalJ = j;
                if (valid.stream().allMatch(t -> r.check(t.get(finalJ)))) {
                    ruleIndex.put(j, r);
                }
            }
        }

        Optional<Map.Entry<Integer, Collection<Rules>>> rs;
        Set<Integer> indices = new HashSet<>();
        while ((rs = ruleIndex.asMap().entrySet().stream().filter(e -> e.getValue().size() == 1 && !indices.contains(e.getKey())).findAny()).isPresent()) {
            Map.Entry<Integer, Collection<Rules>> r = rs.get();
            int index = r.getKey();
            Rules rule = ((List<Rules>) r.getValue()).get(0);
            for (int i = 0; i < rules.length; i++) {
                Map.Entry<Integer, Collection<Rules>> t = new ArrayList<>(ruleIndex.asMap().entrySet()).get(i);
                if (t.getKey() != index) {
                    t.getValue().remove(rule);
                }
            }
            indices.add(index);
        }

        long ticketScanningErrorRate = ruleIndex.asMap().entrySet().stream().filter(e -> e.getValue().stream().anyMatch(Rules::isDeparture)).mapToLong(e -> myTicket[e.getKey()]).reduce((a, b) -> a * b).getAsLong();
        System.out.println("Ticket scanning error rate : " + ticketScanningErrorRate);

        System.out.println("------ Part2 : End ------");
    }
}
