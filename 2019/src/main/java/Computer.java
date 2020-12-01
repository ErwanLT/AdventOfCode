import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Computer {

    private long[] parts = new long[10000];
    private int position = 0;
    private int relBase = 0;
    private final boolean pauseOnInput = false;
    private final boolean pauseOnOutput = false;
    private int inputNumber = 0;
    private List<Long> outputs = new ArrayList<>();
    private long output;

    public Computer(String input) {
        String[] temp = input.split(",");
        Arrays.fill(parts, 0);
        for (int i = 0; i < temp.length; i++) {
            parts[i] = Long.parseLong(temp[i]);
        }
    }

    public long[] getParts() {
        return parts;
    }

    public void compute() {
        String opcode = parts[position] + "";
        while (!opcode.equalsIgnoreCase("99")){
            int mode1 = parseNumber(opcode, opcode.length() - 3);
            int mode2 = parseNumber(opcode, opcode.length() - 4);
            int mode3 = parseNumber(opcode, opcode.length() - 5);

            switch (opcode.toCharArray()[opcode.length() - 1]) {
                case '1': {
                    long numb1 = getValue(mode1, position + 1);
                    long numb2 = getValue(mode2, position + 2);
                    int output = getAddress(mode3, position + 3);
                    parts[output] = numb2 + numb1;
                    position += 4;
                        System.out.printf("Addition : %d + %d = %d\n", numb1, numb2, numb1 + numb2);
                    break;
                }
                case '2': {
                    long numb1 = getValue(mode1, position + 1);
                    long numb2 = getValue(mode2, position + 2);
                    int output = getAddress(mode3, position + 3);
                    parts[output] = numb2 * numb1;
                    position += 4;
                    System.out.printf("Multiplication : %d * %d = %d \n", numb1, numb2, numb1 * numb2);
                    break;
                }
                case '3': {
                    if (pauseOnInput && inputNumber == -100) {
                        return;
                    }
                    int output = getAddress(mode1, position + 1);
                    if (!pauseOnInput) {
                        System.out.println("Enter a number: ");
                        Scanner in = new Scanner(System.in);
                        long value = Long.parseLong(in.next());
                        parts[output] = value;
                    } else {
                        parts[output] = inputNumber;
                        inputNumber = -100;
                    }
                    position += 2;
                    System.out.printf("Input [%d]: Changed %d to %d \n", mode1, output, parts[output]);
                    break;
                }
                case '4': {
                    long output = getValue(mode1, position + 1);
                    outputs.add(output);
                    System.out.printf("Output [%d]: %d \n", mode1, output);
                    position += 2;
                    this.output = output;
                    if (pauseOnOutput) {
                        return;
                    }
                    break;
                }
                case '5': {
                    long condition = getValue(mode1, position + 1);
                    int output = (int) getValue(mode2, position + 2);
                    if (condition != 0L) {
                        position = output;
                    } else {
                        position += 3;
                    }
                    break;
                }
                case '6': {
                    long condition = getValue(mode1, position + 1);
                    int output = (int) getValue(mode2, position + 2);
                    if (condition == 0L) {
                        position = output;
                    } else {
                        position += 3;
                    }
                    break;
                }
                case '7': {
                    long numb1 = getValue(mode1, position + 1);
                    long numb2 = getValue(mode2, position + 2);
                    int output = getAddress(mode3, position + 3);
                    if (numb1 < numb2) {
                        parts[output] = 1;
                    } else {
                        parts[output] = 0;
                    }
                    position += 4;
                    break;
                }
                case '8': {
                    long numb1 = getValue(mode1, position + 1);
                    long numb2 = getValue(mode2, position + 2);
                    int output = getAddress(mode3, position + 3);
                    if (numb1 == numb2) {
                        parts[output] = 1;
                    } else {
                        parts[output] = 0;
                    }
                    position += 4;
                    break;
                }
            }
            opcode = parts[position] + "";
        }
    }

    private int parseNumber(String number, int position) {
        if (position < 0) {
            return 0;
        }
        return Integer.parseInt((number.charAt(position) + ""));
    }

    private long getValue(int mode, int position) {
        if (mode == 0) {
            return parts[Integer.parseInt(parts[position] + "")];
        } else if (mode == 1) {
            return parts[position];
        } else {
            return parts[Integer.parseInt(parts[position] + relBase + "")];
        }
    }

    private int getAddress(int mode, int position) {
        if (mode == 0) {
            return (int) parts[position];
        } else {
            return (int) parts[position] + relBase;
        }
    }
}
