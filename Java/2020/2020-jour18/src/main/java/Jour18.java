import org.apache.commons.lang3.tuple.Pair;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.List;

public class Jour18 {

    private static List<String> inputLines;

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
        Jour18 app = new Jour18();
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

    private static void solvePart1() {
        System.out.println("------ Part1 : Start ------");
        long solution = getSolution(true);
        System.out.println("The sum of the resulting values is : " + solution);
        System.out.println("------ Part1 : End ------");
    }


    private static void solvePart2() {
        System.out.println("------ Part2 : Start ------");
        long solution = getSolution(false);
        System.out.println("The sum of the resulting values is : " + solution);
        System.out.println("------ Part2 : End ------");
    }

    public static long resolveExpression(StringBuilder s, boolean simpleMath){
        var a = solve(s, simpleMath);
        return getSolution(a.getRight(), s, a.getLeft(), simpleMath);
    }

    private static long getSolution(boolean simpleMath){
        return inputLines.stream().mapToLong(i -> resolveExpression(new StringBuilder(i), simpleMath)).sum();
    }

    private static Pair<Long, Integer> solve(StringBuilder s, boolean part1){
        long leftHand;
        int i = s.length()-2;
        if(s.charAt(s.length()-1) == ')'){
            for(int nBrackets = 1; nBrackets>0; i--){
                if(s.charAt(i) == '(') nBrackets--;
                else if(s.charAt(i) == ')') nBrackets++;
            }
            i++;
            leftHand = resolveExpression(new StringBuilder(s.substring(i+1, s.length()-1)), part1);
        } else {
            leftHand = Long.parseLong(s.substring(s.length()-1, s.length()));
            i = s.length()-1;
        }
        return Pair.of(leftHand, i);
    }

    private static long getSolution(int i, StringBuilder s, long leftHand, boolean simpleMath){
        if(i>0) {
            char operator = s.charAt(i-2);
            StringBuilder leftSide = new StringBuilder(s.substring(0, i-3));
            if (operator == '*'){
                return resolveExpression(new StringBuilder(s.substring(0, i-3)), simpleMath) * leftHand;
            }
            else if (operator == '+'){
                if(simpleMath) {
                    return resolveExpression(new StringBuilder(s.substring(0, i-3)), true) + leftHand;
                } else {
                    var sol = solve(leftSide, false);
                    return getSolution(sol.getRight(), leftSide, sol.getKey() + leftHand, false);
                }
            }
        } else if(i == 0){
            return leftHand;
        }
        throw new IllegalStateException();
    }
}
