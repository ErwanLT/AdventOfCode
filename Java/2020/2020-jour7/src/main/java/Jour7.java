import lombok.Getter;
import lombok.Setter;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.*;
import java.util.stream.IntStream;

import static java.lang.StrictMath.toIntExact;
import static java.util.Arrays.stream;
import static java.util.Collections.singletonList;

public class Jour7 {

    private static List<String> inputs = new ArrayList<>();

    private static Trade[] trades;

    public static void main(String[] args) throws URISyntaxException {
        System.setOut(new MyPrinter(System.out));
        System.out.println("------ Start ------");

        getFileInput();

        trades = inputs.stream().filter(s -> !s.contains("no other bags")).map(Trade::new).toArray(Trade[]::new);

        solvePart1();
        solvePart2();

        System.out.println("------ End ------");
    }

    private static void solvePart1() {
        System.out.println("------ Part1 : Start ------");

        int bagType = findBagTypes(new Item(1, "shiny gold"), new HashSet<>()).size() - 1;
        System.out.println(bagType);

        System.out.println("------ Part1 : End ------");
    }

    private static void solvePart2() {
        System.out.println("------ Part2 : Start ------");

        int numberOfBags = (int) (findNumberOfBags(new LinkedList<>(singletonList(new Item(1, "shiny gold")))) - 1);
        System.out.println(numberOfBags);

        System.out.println("------ Part2 : End ------");
    }

    private static void getFileInput() throws URISyntaxException {
        System.out.println("------ Getting input : Start ------");
        Jour7 app = new Jour7();
        String fileName = "input";
        File file = app.getFileFromResource(fileName);
        inputs.addAll(getFileLine(file));
        System.out.println(inputs);
        System.out.println("------ Getting input : End ------");
    }

    private static List<String> getFileLine(File file) {
        try {
            return Files.readAllLines(file.toPath(), StandardCharsets.UTF_8);
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

    private static Set<String> findBagTypes(Item buyingItem, Set<String> visitedColors) {
        visitedColors.add(buyingItem.item);
        Trade[] possibleTrades = getTrades(buyingItem);
        stream(possibleTrades).forEach(t -> findBagTypes(t.input, visitedColors));
        return visitedColors;
    }

    private static Trade[] getTrades(Item i) {
        return stream(trades).filter(e -> stream(e.output).anyMatch(t -> t.item.equals(i.item) && t.amount >= i.amount)).toArray(Trade[]::new);
    }

    private static Optional<Trade> getTrade(Item i) {
        return stream(trades).filter(e -> e.input.item.equals(i.item)).findAny();
    }

    private static long findNumberOfBags(Deque<Item> leftOver) {
        long total = 0;
        while (!leftOver.isEmpty()) total += findBagsInside(leftOver, leftOver.pop());
        return total;
    }

    private static long findBagsInside(Deque<Item> leftOver, Item buyingItem) {
        Optional<Trade> fuelTrade = getTrade(buyingItem);
        fuelTrade.ifPresent(trade -> stream(trade.output).flatMap(i -> IntStream.rangeClosed(1, toIntExact(i.amount)).mapToObj(e -> new Item(1, i.item))).forEach(leftOver::add));
        return buyingItem.amount;
    }
}
