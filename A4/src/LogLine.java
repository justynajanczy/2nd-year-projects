import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class LogLine
{
    private String log;
    private LocalDateTime time;

    public LogLine(String logText)
    {
        log = logText;
        time = LocalDateTime.now();
    }

    public String getLogEntry()
    {
        return log;
    }

    public String toString()
    {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM/dd HH:mm:ss");
        return dtf.format(time) + " " + log;
    }
}
