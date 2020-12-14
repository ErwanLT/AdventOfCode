import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.*;

import static java.lang.Long.parseLong;
import static java.lang.Long.toBinaryString;
import static java.util.stream.IntStream.range;


public class Jour14 {

    private static List<String> inputs;

    private static Instruction[] instructions;

    public static void main(String[] args) throws URISyntaxException {
        System.out.println("------ Start ------");

        getFileInput();

        getInstructionFromInput();

        solvePart1();
        solvePart2();

        System.out.println("------ End ------");
    }

    private static void getInstructionFromInput() {
        instructions = inputs.stream().map(s -> Utils.readString(s, "%s = %s", Instruction.class)).toArray(Instruction[]::new);
    }

    private static void getFileInput() throws URISyntaxException {
        System.out.println("------ Getting input : Start ------");
        Jour14 app = new Jour14();
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

    private static void solvePart1() {
        System.out.println("------ Part1 : Start ------");

        Map<Long, Long> memory = new HashMap<>();
        String currentMask = "";
        for(Instruction i : instructions){
            Optional<Memory> mem = i.getMem();
            if(mem.isPresent()){
                StringBuilder bin = binWithLength(mem.get().value, currentMask.length());
                String finalCurrentMask = currentMask;
                range(0, bin.length())
                        .filter(j -> finalCurrentMask.charAt(j) != 'X')
                        .forEach(j -> bin.setCharAt(j, finalCurrentMask.charAt(j)));
                memory.put(mem.get().index, parseLong(bin.toString(), 2));
            } else currentMask = i.value;
        }
        long sum = memory.values().stream().mapToLong(e -> e).sum();
        System.out.println("sum of all values left in memory : " + sum);
        System.out.println("------ Part1 : End ------");
    }

    private static StringBuilder binWithLength(long val, int s) {
        StringBuilder bin = new StringBuilder(toBinaryString(val));
        while (bin.length() < s) {
            bin.insert(0, '0');
        }
        return bin;
    }

    private static void solvePart2() {
        System.out.println("------ Part2 : Start ------");

        Map<Long, Long> memory = new HashMap<>();
        String currentMask = "";
        for(Instruction i : instructions){
            Optional<Memory> mem = i.getMem();
            if(mem.isPresent()){
                StringBuilder bin = binWithLength(mem.get().index, currentMask.length());
                List<Integer> floaters = applyMask(currentMask, bin);
                fillMemory(memory, mem, bin, floaters);
            } else {
                currentMask = i.value;
            }
        }
        long sum = memory.values().stream().mapToLong(e -> e).sum();
        System.out.println("sum of all values left in memory : " + sum);
        System.out.println("------ Part2 : End ------");
    }

    private static void fillMemory(Map<Long, Long> memory, Optional<Memory> mem, StringBuilder bin, List<Integer> floaters) {
        StringBuilder binary;
        for(long j = 0; (binary = binWithLength(j, floaters.size())).length() == floaters.size(); j++){
            for(int k = 0; k< floaters.size(); k++){
                bin.setCharAt(floaters.get(k), binary.charAt(k));
            }
            memory.put(parseLong(bin.toString(), 2), mem.get().value);
        }
    }

    private static List<Integer> applyMask(String currentMask, StringBuilder bin) {
        List<Integer> floaters = new ArrayList<>();
        for(int j = 0; j< bin.length(); j++){
            char c = currentMask.charAt(j);
            if(c=='X') floaters.add(j);
            else if(c == '1') bin.setCharAt(j, c);
        }
        return floaters;
    }
}
