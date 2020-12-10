public class Jour7B {

    private static final String input = "3,8,1001,8,10,8,105,1,0,0,21,34,47,72,81,94,175,256,337,418,99999,3,9,102,3,9,9,1001,9,3,9,4,9,99,3,9,101,4,9,9,1002,9,5,9,4,9,99,3,9,1001,9,5,9,1002,9,5,9,1001,9,2,9,1002,9,5,9,101,5,9,9,4,9,99,3,9,102,2,9,9,4,9,99,3,9,1001,9,4,9,102,4,9,9,4,9,99,3,9,102,2,9,9,4,9,3,9,101,2,9,9,4,9,3,9,1002,9,2,9,4,9,3,9,102,2,9,9,4,9,3,9,1001,9,2,9,4,9,3,9,101,2,9,9,4,9,3,9,101,2,9,9,4,9,3,9,102,2,9,9,4,9,3,9,101,2,9,9,4,9,3,9,1001,9,1,9,4,9,99,3,9,102,2,9,9,4,9,3,9,1001,9,2,9,4,9,3,9,101,1,9,9,4,9,3,9,1001,9,2,9,4,9,3,9,102,2,9,9,4,9,3,9,101,2,9,9,4,9,3,9,1001,9,1,9,4,9,3,9,1001,9,2,9,4,9,3,9,1001,9,2,9,4,9,3,9,1002,9,2,9,4,9,99,3,9,101,2,9,9,4,9,3,9,101,2,9,9,4,9,3,9,101,2,9,9,4,9,3,9,1001,9,1,9,4,9,3,9,1002,9,2,9,4,9,3,9,101,1,9,9,4,9,3,9,102,2,9,9,4,9,3,9,101,1,9,9,4,9,3,9,1001,9,2,9,4,9,3,9,102,2,9,9,4,9,99,3,9,1001,9,1,9,4,9,3,9,102,2,9,9,4,9,3,9,101,2,9,9,4,9,3,9,102,2,9,9,4,9,3,9,101,1,9,9,4,9,3,9,102,2,9,9,4,9,3,9,1001,9,2,9,4,9,3,9,101,2,9,9,4,9,3,9,1002,9,2,9,4,9,3,9,101,2,9,9,4,9,99,3,9,102,2,9,9,4,9,3,9,1001,9,2,9,4,9,3,9,1002,9,2,9,4,9,3,9,101,2,9,9,4,9,3,9,101,2,9,9,4,9,3,9,1002,9,2,9,4,9,3,9,1001,9,1,9,4,9,3,9,101,2,9,9,4,9,3,9,1001,9,1,9,4,9,3,9,1002,9,2,9,4,9,99";

    private static int min = 5;
    private static int max = 10;

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
        int number = 0;
        int max = 0;
        Computer computer = new Computer(input, true, false);
        computer.setInputNumber(a);
        computer.compute();
        computer.setInputNumber(0);
        computer.compute();
        int output = (int) computer.getOutput();
        Computer computer2 = new Computer(input, true, false);
        computer2.setInputNumber(b);
        computer2.compute();
        computer2.setInputNumber(output);
        computer2.compute();
        output = (int) computer2.getOutput();
        Computer computer3 = new Computer(input, true, false);
        computer3.setInputNumber(c);
        computer3.compute();
        computer3.setInputNumber(output);
        computer3.compute();
        output = (int) computer3.getOutput();
        Computer computer4 = new Computer(input, true, false);
        computer4.setInputNumber(d);
        computer4.compute();
        computer4.setInputNumber(output);
        computer4.compute();
        output = (int) computer4.getOutput();
        Computer computer5 = new Computer(input, true, false);
        computer5.setInputNumber(e);
        computer5.compute();
        computer5.setInputNumber(output);
        computer5.compute();
        number = (int) computer5.getOutput();
        computer.setInputNumber(number);
        while (computer5.isRunning()) {
            computer.compute();
            number = (int) computer.getOutput();
            computer2.setInputNumber(number);
            computer2.compute();
            number = (int) computer2.getOutput();
            computer3.setInputNumber(number);
            computer3.compute();
            number = (int) computer3.getOutput();
            computer4.setInputNumber(number);
            computer4.compute();
            number = (int) computer4.getOutput();
            computer5.setInputNumber(number);
            computer5.compute();
            number = (int) computer5.getOutput();
            computer.setInputNumber(number);
            if (number > max) {
                max = number;
            }
        }
        return max;
    }
}
