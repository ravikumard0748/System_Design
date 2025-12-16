abstract class display
{
    abstract void print();
}

class car extends display{
    @Override
    void print()
    {
        System.out.println("Hello world");
    }


}

public class main

{
    public static void main(String[] args)
    {
        car ca = new car();
        ca.print();
    }
}
