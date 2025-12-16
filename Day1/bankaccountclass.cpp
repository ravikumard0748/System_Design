#include<bits/stdc++.h>
using namespace std;
class bankaccount
{
   
    
    private:
    float balance;

    public:
    int accountnumber;
    
    float balance(int accountnumber)
    { 
        if(accountnumber==this->accountnumber) 
        {
        return this->balance;
        }
        return 0.00;
    }
    void deposit(int accountnumber , float amount ) 
    {
        if(accountnumber==this->accountnumber) 
        {
            this->balance += amount;
            cout<<"Money deposited";
        }
        
    }

    float withdrawl(int accountnumber,float amount)
    {
        if(accountnumber==this->accountnumber)
        {
            this->balance -= amount;
            cout<<"Money witdrwaed";
            
        }
    }

    bankaccount(int acc)
    {
        this->accountnumber = acc;
    }

};

int main()
{
     bankaccount ba(1234);
     ba.deposit(1234,50000);

}