public class Jour7A {

    private static final String input = "3,8,1001,8,10,8,105,1,0,0,21,34,47,72,81,94,175,256,337,418,99999,3,9,102,3,9,9,1001,9,3,9,4,9,99,3,9,101,4,9,9,1002,9,5,9,4,9,99,3,9,1001,9,5,9,1002,9,5,9,1001,9,2,9,1002,9,5,9,101,5,9,9,4,9,99,3,9,102,2,9,9,4,9,99,3,9,1001,9,4,9,102,4,9,9,4,9,99,3,9,102,2,9,9,4,9,3,9,101,2,9,9,4,9,3,9,1002,9,2,9,4,9,3,9,102,2,9,9,4,9,3,9,1001,9,2,9,4,9,3,9,101,2,9,9,4,9,3,9,101,2,9,9,4,9,3,9,102,2,9,9,4,9,3,9,101,2,9,9,4,9,3,9,1001,9,1,9,4,9,99,3,9,102,2,9,9,4,9,3,9,1001,9,2,9,4,9,3,9,101,1,9,9,4,9,3,9,1001,9,2,9,4,9,3,9,102,2,9,9,4,9,3,9,101,2,9,9,4,9,3,9,1001,9,1,9,4,9,3,9,1001,9,2,9,4,9,3,9,1001,9,2,9,4,9,3,9,1002,9,2,9,4,9,99,3,9,101,2,9,9,4,9,3,9,101,2,9,9,4,9,3,9,101,2,9,9,4,9,3,9,1001,9,1,9,4,9,3,9,1002,9,2,9,4,9,3,9,101,1,9,9,4,9,3,9,102,2,9,9,4,9,3,9,101,1,9,9,4,9,3,9,1001,9,2,9,4,9,3,9,102,2,9,9,4,9,99,3,9,1001,9,1,9,4,9,3,9,102,2,9,9,4,9,3,9,101,2,9,9,4,9,3,9,102,2,9,9,4,9,3,9,101,1,9,9,4,9,3,9,102,2,9,9,4,9,3,9,1001,9,2,9,4,9,3,9,101,2,9,9,4,9,3,9,1002,9,2,9,4,9,3,9,101,2,9,9,4,9,99,3,9,102,2,9,9,4,9,3,9,1001,9,2,9,4,9,3,9,1002,9,2,9,4,9,3,9,101,2,9,9,4,9,3,9,101,2,9,9,4,9,3,9,1002,9,2,9,4,9,3,9,1001,9,1,9,4,9,3,9,101,2,9,9,4,9,3,9,1001,9,1,9,4,9,3,9,1002,9,2,9,4,9,99";

    private static int min = 0;
    private static int max = 5;

    public static void main(String[] args) {
        int maxOutput = 0;
        for (int a = min; a < max; a++) {
            for (int b = min; b < max; b++) {
                for (int c = min; c < max; c++) {
                    for (int d = min; d < max; d++) {
                        for (int e = min; e < max; e++) {
                            if (validInput(a, b, c, d, e)) {
                                int temp = getMax(a, b, c, d, e);
                                if (temp > maxOutput) {
                                    maxOutput = temp;
                                }
                            }
                        }
                    }
                }
            }
        }
        System.out.println("Max: " + maxOutput);
    }

    private static boolean validInput(int a, int b, int c, int d, int e) {
        if (a == b || a == c || a == d || a == e || b == c || b == d || b == e || c == d || c == e || d == e)
            return false;
        return true;
    }

    private static int getMax(int a, int b, int c, int d, int e) {
        Computer computer = new Computer(input, true, false, true);
        computer.setInputNumber(a);
        computer.compute();
        computer.setInputNumber(0);
        computer.compute();
        int output = (int) computer.getOutput();
        computer = new Computer(input, true, false, true);
        computer.setInputNumber(b);
        computer.compute();
        computer.setInputNumber(output);
        computer.compute();
        output = (int) computer.getOutput();
        computer = new Computer(input, true, false, true);
        computer.setInputNumber(c);
        computer.compute();
        computer.setInputNumber(output);
        computer.compute();
        output = (int) computer.getOutput();
        computer = new Computer(input, true, false, true);
        computer.setInputNumber(d);
        computer.compute();
        computer.setInputNumber(output);
        computer.compute();
        output = (int) computer.getOutput();
        computer = new Computer(input, true, false, true);
        computer.setInputNumber(e);
        computer.compute();
        computer.setInputNumber(output);
        computer.compute();
        return (int) computer.getOutput();
    }
}
