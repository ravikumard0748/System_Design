interface displayMovie
{
    public void screening();
}

interface verifyYourIdentity
{
    public void verifying();
}

class user implements verifyYourIdentity
{
    public int user_id;
    public String name,address;
     user(int uid,String name , String add)
    {
        this.user_id = uid;
        this.name = name;
        this.address = add;
    }
    public void verifying()
    {
        System.out.println("My name is "+name+"and my user id is"+ user_id + "i am living in "+address);
    }
}

class movie implements displayMovie
{
    public String name;
    public int movie_id;
      movie(String nm,int m_id)
    {
        this.name = nm;
        this.movie_id = m_id;

    }
    public void screening()
    {
        System.out.println(name + "Screening...............");

    }
}

class booking
{
   user us = new user(100,"Ravi","Tiruvarur");
   movie mv = new movie("Jananayagan",1);

   public int book_id = mv.movie_id;
   public String m_name = mv.name,user_name = us.name;

   public void display()
   {
    System.out.println(user_name + "is booked the movie "+m_name);
   }


}
public class online_boooking {
    public static void main(String[] args)
    {
      booking bk = new booking();
      bk.display();
    }
}
