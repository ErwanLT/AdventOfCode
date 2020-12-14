import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static java.util.Arrays.stream;

public class Jour8 {

    private static List<String> inputs;

    private static int accumulator;

    private static List<Integer> alreadyVisitedIndex = new ArrayList<>();

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

        Instruction[] input = inputs.stream().map(s -> s.replace("+", "")).map(s -> s.split(" ")).map(s -> new Instruction(s[0], Long.parseLong(s[1]))).toArray(Instruction[]::new);
        Set<Integer> alreadyVisitedIndex = new HashSet<>();
        int op = 0;
        while(alreadyVisitedIndex.add(op)){
            String operation = input[op].operation;
            long number = input[op].number;
            switch(operation){
                case "acc":
                    accumulator+=number;
                    op++;
                    break;
                case "jmp":
                    op+=number;
                    break;
                case "nop":
                    op++;
                    break;
                default: throw new IllegalStateException();
            }
        }
        System.out.println(accumulator);

        System.out.println("------ Part1 : End ------");
    }

    private static void solvePart2() {
        System.out.println("------ Part2 : Start ------");

         System.out.println(compute());

        System.out.println("------ Part2 : End ------");
    }

    private static Object compute() {
        for(int i = 0; i<223; i++) {
            Instruction[] instructions = inputs.stream().map(s -> s.replace("+", "")).map(s -> s.split(" ")).map(s -> new Instruction(s[0], Long.parseLong(s[1]))).toArray(Instruction[]::new);
            replace(instructions, "jmp", "nop", i);
            long acc = 0L;
            Set<Integer> visited = new HashSet<>();
            int op = 0;
            while (visited.add(op)) {
                String operation = instructions[op].operation;
                long number = instructions[op].number;
                switch (operation) {
                    case "acc":
                        acc += number;
                        op++;
                        break;
                    case "jmp":
                        op += number;
                        break;
                    case "nop":
                        op++;
                        break;
                    default:
                        throw new IllegalStateException();
                }
                if(op == instructions.length) {
                    return acc;
                }
                if(op > instructions.length) {
                    break;
                }
            }

        }
        return "FAIL";
    }

    private static void getFileInput() throws URISyntaxException {
        System.out.println("------ Getting input : Start ------");
        Jour8 app = new Jour8();
        String fileName = "input";
        File file = app.getFileFromResource(fileName);
        getFileLine(file);
        System.out.println("------ Getting input : End ------");
    }

    private static void getFileLine(File file) {
        try {
            inputs = Files.readAllLines(file.toPath(), StandardCharsets.UTF_8);
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

    public static void replace(Instruction[] input, String instruction, String replacement, int occurrence){
        Instruction[] those = stream(input).filter(e -> e.operation.equals(instruction)).toArray(Instruction[]::new);
        those[occurrence].operation = replacement;
    }
}
