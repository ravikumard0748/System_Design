package Whatsapp;

interface introduce
{
    void aboutme();
}

class bio
{
    public String name,description;
    public Boolean privacy;

    bio(String nm,String des,Boolean priv)
    {
        this.description = des;
        this.privacy = priv;
        this.name = nm;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public void setDescription(String des)
    {
        this.description = des;
    }
    

    public void setPrivacy(Boolean set)
    {
        this.privacy = set;
    } 

    public String getName()
    {
        return this.name;
    }

    public String getDescription()
    {
        return this.description;
    }


}

public class profile 



