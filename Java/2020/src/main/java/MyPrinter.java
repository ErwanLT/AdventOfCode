import java.io.OutputStream;
import java.io.PrintStream;
import java.time.Instant;
import java.time.temporal.ChronoUnit;

public class MyPrinter extends PrintStream {

    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_CYAN = "\u001B[36m";

    public MyPrinter(OutputStream out) {
        super(out);
    }

    @Override
    public void println(String string) {
        super.println(ANSI_CYAN + "[" + Instant.now().truncatedTo(ChronoUnit.MILLIS).toString().replaceAll("[TZ]", " ") + "] " + ANSI_RESET + string);
    }

    public void printlnRed(String string){
        println(ANSI_RED + string + ANSI_RESET);
    }

    public void printlnGreen(String string){
        println(ANSI_GREEN + string + ANSI_RESET );
    }

    public void printlnBlue(String string){
        println(ANSI_BLUE + string + ANSI_RESET);
    }
}
