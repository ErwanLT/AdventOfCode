import java.io.OutputStream;
import java.io.PrintStream;
import java.time.Instant;
import java.time.temporal.ChronoUnit;

public class MyPrinter extends PrintStream {


    public MyPrinter(OutputStream out) {
        super(out);
    }

    @Override
    public void println(String string) {
        super.println("[" + Instant.now().truncatedTo(ChronoUnit.MILLIS).toString().replaceAll("[TZ]", " ") + "] " + string);
    }
}
