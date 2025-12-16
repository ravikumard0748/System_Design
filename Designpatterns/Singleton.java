class Logger
{
    public void log(String msg)
    {
        System.out.println(msg);
    }
}

class LoggerSingleton
{
    private static final LoggerSingleton instance = new LoggerSingleton();
    
    private LoggerSingleton ()
    {
        System.out.println("Logger Singleton created");
    }

    public static LoggerSingleton getInstance()
    {
        return instance;
    }

    public void log(String msg)
    {
        System.out.println(msg);
    }

}

public class Singleton{
    public static void main(String[] args) {
        {
            LoggerSingleton l1 = LoggerSingleton.getInstance();
            LoggerSingleton l2 = LoggerSingleton.getInstance();
            System.out.println(l1==l2);
        }
    }
}