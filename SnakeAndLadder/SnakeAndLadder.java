package SnakeAndLadder;
import java.util.*;
import java.util.Random;

import javax.swing.text.PlainDocument;

// board interfaces

interface position
{  
    int getposition();
}

interface snake
{
    int checkSnake(int pos);
}

interface ladder
{
    int checkLadder(int pos);
}

class Board implements ladder,snake{
public Map<Integer, Integer> ladder = Map.of(3, 22,5, 8,11, 26,20, 29,27, 56,36, 44,51, 67,71, 91,80, 99);
public Map<Integer, Integer> snake = Map.of(17, 4,19, 7,21, 9,27, 1,62, 18,64, 60,87, 24,93, 73,95, 75,99, 78);
public int end = 100;
    Board(int end)
    {
        this.end = end;
    }
    public int checkLadder(int pos)
    {
        if(ladder.containsKey(pos))
        {
            return ladder.get(pos);
        }
        return 0;

    }

    public int checkSnake(int pos)
    {
        if(snake.containsKey(pos))
        {
            return pos;
        }
        return 0;
    }

}
// -----------------------------------------------------------------------------------------------



//player interfaces

interface introduce
{
   void  aboutMe();
}

class player implements introduce{
    public String name,address;
    int playerId,age;
    player(String name,String address,int playerId,int age)
    {
        this.name = name;
        this.address = address;
        this.age = age;
        this.playerId = playerId;
    }


    public void aboutMe()
    {
        System.out.println("I am "+this.name + "and my age is "+age+"my address is "+ this.address + "Player id-"+this.playerId);
    }
}


// -----------------------------------------------------------------------------------------------

//playing interface

interface rolldice
{
    int roll();
}

interface positionupdate
{
    void updateposition();
}

interface check
{
    int getresult();
}

class play implements rolldice,positionupdate,check
{   private int curPos = 0;
    private Board board;
    public player user = new player("Ravi", "172 pillayar kovil st,Deepmanagalam,Aaandikuppam ", 001, 18);
    public int roll()
    {   Random r = new Random();
        int rand = r.nextInt(7);
        System.out.println("You got "+rand+"in dice");
        return rand;
    }
    public void updateposition()
    {
        int toAdd = this.roll();
        this.curPos +=toAdd;
        if(board==null)
        {
            board = new Board(10);
        }
        if(board.checkLadder(curPos)!=0)
        {
            this.curPos = board.checkLadder(curPos);
            System.out.println("Hurrah You got the ladder and your new posistion is "+this.curPos);
        }
        else if(board.checkSnake(curPos)!=0)
        {
            this.curPos  = board.checkSnake(curPos);
            System.out.println("Oh no you got bite by snake and you new position is "+this.curPos);
        }
    }
    public int getresult()
    {
        if(this.curPos == board.end)
        {
            System.out.println("Hurray Congratulations" +user.name + "  You have won the Game");
            return 1;
        }
        else{
            System.out.println("You have to move " + (board.end-this.curPos) + " Positions");
            return 0;
        }
    }
}
// -----------------------------------------------------------------------------------------------

//public class

public class game
{
    public static void main(String[] args)
    {    Scanner sc = new Scanner(System.in);
        play pl = new play();
        while(pl.getresult()!=1)
        {   System.out.println("Type Roll to Roll the dice");
            String st = sc.nextLine();
            if(st.equalsIgnoreCase("Roll"))
            {
                pl.roll();
            }
            pl.getresult();

        }
    }
}



