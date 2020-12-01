import java.util.Arrays;

public class Computer {

    private long[] parts = new long[10000];
    private int position = 0;
    private int relBase = 0;

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
