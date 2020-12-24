import com.google.common.collect.Streams;

import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static java.lang.Long.parseLong;
import static java.util.Arrays.stream;

public class Jour23 {

    private static int[] input = new int[]{5,8,3,9,7,6,2,4,1};

    public static void main(String[] args) {
        System.setOut(new MyPrinter(System.out));
        System.out.println("------ Start ------");

        solvePart1();

        solvePart2();

        System.out.println("------ End ------");
    }


    private static void solvePart1() {
        System.out.println("------ Part1 : Start ------");

        long label = getSolution(true);
        System.out.println("The labels on the cups after cup 1 is : " + label);

        System.out.println("------ Part1 : End ------");
    }

    private static void solvePart2() {
        System.out.println("------ Part2 : Start ------");

        long product = getSolution(false);

        System.out.println("The product of the label is : " + product);
        System.out.println("------ Part2 : End ------");
    }

    private static long getSolution(boolean part1) {
        CircularLinkedList cups = new CircularLinkedList(
                Streams.concat(stream(input), part1 ? IntStream.empty() : IntStream.rangeClosed(10, 1000000)
                ).toArray());
        for(int i = 0; i<(part1 ? 100 : 10000000); i++){
            int current = cups.current();
            int j;
            Node next = cups.currentNode().next;
            Node last = next.next.next;
            for(j = current - 2 + cups.size(); j>0; j--){
                int n = j % cups.size() + 1;
                if(next.value != n && next.next.value != n && last.value != n){
                    break;
                }
            }
            int d = j % cups.size() + 1;
            cups.insertAfter(next, last, d);
            cups.next();
        }
        cups.setCurrent(1);
        if(part1) return parseLong(stream(cups.next(8)).mapToObj(Integer::toString).collect(Collectors.joining()));
        int[] next = cups.next(2);
        return (long)next[0] * next[1];
    }
}
