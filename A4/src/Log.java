import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Log
{
    private ListADT<LogLine> logList;
    private static Log instance;
    private static Lock lock = new ReentrantLock();

    private Log()
    {
        logList = new ArrayList<>();
    }

    public static Log getInstance()
    {
        if(instance == null)
        {
            synchronized (lock)
            {
                if(instance == null)
                    instance = new Log();
            }
        }
        return instance;
    }

    public synchronized void add(String logEntry)
    {
        if(logEntry == null || "".equals(logEntry))
            return;

        LogLine toAdd = new LogLine(logEntry);
        logList.add(toAdd);
        System.out.println(toAdd);
    }

    public ListADT<LogLine> getAll()
    {
        return logList;
    }
}
