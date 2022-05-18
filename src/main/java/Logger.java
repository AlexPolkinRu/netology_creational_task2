import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Logger {

    private static final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss");

    private static int num = 1;

    private static final Logger logger = new Logger();

    private Logger() {}

    public static Logger getInstance() {
        return logger;
    }

    public void log(String msg) {
        System.out.println("[" + LocalDateTime.now().format(dtf) + " " + num++ + "] " + msg);
    }

}