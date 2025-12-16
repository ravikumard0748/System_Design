package behaviouralpattterns;
import java.util.*;

interface PaymentStrategy
{
    void pay(double amount );
}

class CardPaymentStrategies implements PaymentStrategy
{
    public void pay(double amount )
    {
        System.out.println("Paying using card");
    }
}

class UpiPaymentStrategies implements PaymentStrategy
{
    public void pay(double amount)
    {
        System.out.println("Paying using the upi");
    }
}
public class behaviour {
    Scanner sc = new Scanner(System.in);

    String query = sc.nextLine();
    if(query=="upi")
    {
        PaymentStrategy obj1 = new UpiPaymentStrategies();\
        obj1.pay(2000.0)
        
    }
    else

    
}
