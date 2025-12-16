class User
{  public String name,add,email;
   public int age;

   private User(Builder bu)
   {
    this.age = bu.age;
    this.name = bu.name;
    this.email = bu.email;
    this.add = bu.add;
   }

   public static class Builder{
    private String name,add,email;
    private int age;

    public Builder setname(String name)
   {
    this.name = name;
    return this;

   }
   public Builder setage(int age)
   {
    this.age = age;
    return this;
   }
   public Builder setadd(String add)
   {
    this.add  = add;
    return this;
   }
   public Builder setemail(String email)
   {
    this.email = email;
    return this;
   }

   public User build(){
    return new User (this);
   }

}
}


public class BuilderPattern {

    public static void main(String[] args)
    {

  

    User us = new User.Builder().setadd("Deepamangalam")
    .setage(19)
    .setemail("rkumard777@gmail.com")
    .setname("Ravi").build();

    }


    
}
