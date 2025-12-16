class synLazySingleton
{   private static  synLazySingleton instances;
    private synLazySingleton()
    {
        System.out.println("Lazy singleton synchronized code created");
    }
    public static synchronized synLazySingleton getInstance()
    {
        if(instances==null)
        {
            instances = new synLazySingleton();
        }
        return instances;
    }
    public void log(String msg)
    {
        System.out.println("Hello World");
    }
}

public class LazySynchronizedSingleton {
    public static void main (String[] args)
    {
        synLazySingleton l1 = synLazySingleton.getInstance();
        synLazySingleton l2 = synLazySingleton.getInstance();
        System.out.println(l1==l2);
    }

    
}
