interface Coffe
{
    double cost();
    String description();
}

class basicCoffe implements Coffe{
    public double cost() { return 60;}
    public String description() { return "This is basic coffer";}
}

class milkCoffe implements Coffe{
    private Coffe co;
    milkCoffe(Coffe cof)
    {
        this.co = cof;
    }
    public double cost() {return co.cost()+20;}

    public String description() { return co.description() + "and this is the best coffe";}
}

public class DecoratorPattern {
public static void main(String[] args)
{
Coffe c = new basicCoffe();
c = new milkCoffe(c);
System.out.println(c.description() + " costs" + c.cost());
}
}
