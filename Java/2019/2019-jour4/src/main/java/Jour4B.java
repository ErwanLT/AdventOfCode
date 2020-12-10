import java.util.ArrayList;
import java.util.List;

public class Jour4B {

    private static int min = 231832;
    private static int max = 767346;

    public static void main(String[] args) {
        List<Integer> found = new ArrayList<>();

        for (int i = min; i <= max; i++) {
            String digit = i + "";
            boolean doubles = false, decrease = false;
            char[] chars = digit.toCharArray();
            for (int j = 1; j < chars.length; j++) {
                if (chars[j] < chars[j - 1]) {
                    decrease = true;
                }
            }
            int positon = 0;
            boolean valid = false;
            while (positon < 6) {
                int amount = inARow(i, positon);
                if (amount == 2) valid = true;
                positon += amount;
            }
            if (valid) {
                doubles = true;
            }
            if (doubles && !decrease) {
                found.add(i);
            }
        }
        System.out.println("Filtered Output: " + found.size());
    }

    private static int inARow(int digit, int position) {
        char[] test = (digit + "").toCharArray();
        char searched = test[position];
        int row = 1;
        for (int i = position + 1; i < test.length; i++) {
            if (test[i] == searched) {
                row++;
            } else {
                return row;
            }
        }
        return row;
    }
}
