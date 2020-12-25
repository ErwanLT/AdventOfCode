public class Jour25 {

    private static long cardPublicKey = 6930903L;
    private static long doorPublicKey = 19716708L;

    public static void main(String[] args) {
        System.setOut(new MyPrinter(System.out));
        System.out.println("------ Start ------");

        solvePart1();
        solvePart2();

        System.out.println("------ End ------");
    }

    private static void solvePart1() {
        System.out.println("------ Part1 : Start ------");
        long encryptionKey;
        long value = 1;
        for(int loopSize = 1; true; loopSize++) {
            value = transform(value, loopSize-1, loopSize, 7);
            if(value == cardPublicKey) {
               encryptionKey = transform(1, 0, loopSize, doorPublicKey);
               break;
            }
        }
        System.out.println("The encryption key is : " + encryptionKey);
        System.out.println("------ Part1 : End ------");
    }

    private static long transform (long value, int start, int loopSize, long subjectNumber){
        for (int i = start; i < loopSize; i++) {
            value *= subjectNumber;
            value %= 20201227;
        }
        return value;
    }

    private static void solvePart2() {
        System.out.println("------ Part2 : Start ------");
        System.out.println("Nothing here ! Merry Christmass !");
        System.out.println("------ Part2 : End ------");
    }
}
