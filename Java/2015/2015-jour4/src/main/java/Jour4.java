import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Jour4 {

    public static String input = "bgvyzdsv";

    public static void main(String[] args) throws NoSuchAlgorithmException {
        System.out.println("------ Start ------");

        solvePart1();
        solvePart2();

        System.out.println("------ End ------");
    }

    private static void solvePart1() throws NoSuchAlgorithmException {
        System.out.println("------ Part1 : Start ------");

        long index = getMD5Hash("00000");
        System.out.println(String.format("%d", index-1));
        System.out.println("------ Part1 : End ------");
    }

    private static void solvePart2() throws NoSuchAlgorithmException {
        System.out.println("------ Part2 : Start ------");
        long index = getMD5Hash("000000");
        System.out.println(String.format("%d", index-1));
        System.out.println("------ Part2 : End ------");
    }

    private static long getMD5Hash(String s) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("MD5");
        long index = 0L;
        while (true) {
            String key = String.format("%s%d", input, index++);
            byte[] digest = md.digest(key.getBytes());
            if (byteToHexString(digest).startsWith(s)) {
                break;
            }
        }
        return index;
    }

    private static String byteToHexString(byte[] digest) {
        String result = "";
        for (byte b : digest) {
            result += Integer.toString((b & 0xff) + 0x100, 16).substring(1);
        }
        return result;
    }
}
