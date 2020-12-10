public class Jour4A {

    private static int min = 231832;
    private static int max = 767346;

    private static int number = 0;

    public static void main(String[] args) {
        for (int i = min; i < max; i++) {
            String digit = i + "";

            boolean doubles = false, decrease = false;
            char[] chars = digit.toCharArray();
            for (int j = 1; j < chars.length; j++) {
                if (chars[j] < chars[j - 1]) {
                    decrease = true;
                }
                if (chars[j] == chars[j - 1]) {
                    doubles = true;
                }
            }
            if (doubles && !decrease) {
                number++;
            }
        }
        System.out.println("Number of possible passwords : " + number);
    }
}
