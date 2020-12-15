import java.util.HashMap;
import java.util.Map;

import static java.util.stream.IntStream.range;

public class Jour15 {

    private static final long[] input = new long[]{8,0,17,4,1,12};

    public static void main(String[] args) {
        System.setOut(new MyPrinter(System.out));
        System.out.println("------ Start ------");

        solvePart1();
        solvePart2();

        System.out.println("------ End ------");
    }

    private static void solvePart1() {
        System.out.println("------ Part1 : Start ------");

        System.out.println("The 2020th number is : " + getNumber(2020L));

        System.out.println("------ Part1 : End ------");
    }

    private static void solvePart2() {
        System.out.println("------ Part2 : Start ------");

        System.out.println("The 30000000th number is : " + getNumber(30000000L));

        System.out.println("------ Part2 : End ------");
    }

    private static long getNumber(long position){
        Map<Long, Long> turnNumbers = new HashMap<>();
        range(0, input.length-1).forEach(i -> turnNumbers.put(input[i], (long)i));
        long lastNumber = input[input.length-1];
        for(long turnNumber = turnNumbers.size(); turnNumber <= position -2L; turnNumber++){
            long newLastNumber = turnNumbers.containsKey(lastNumber) ? turnNumber - turnNumbers.get(lastNumber) : 0;
            turnNumbers.put(lastNumber, turnNumber);
            lastNumber = newLastNumber;
        }
        return lastNumber;
    }
}
