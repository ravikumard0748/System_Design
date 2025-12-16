package others;
import java.util.*;
import java.util.Random;

interface position {
    int getposition();
}

interface snake {
    int checkSnake(int pos);
}

interface ladder {
    int checkLadder(int pos);
}

class Board implements ladder, snake {
    public Map<Integer, Integer> ladder = Map.of(
            3, 22, 5, 8, 11, 26, 20, 29, 27, 56, 36, 44, 51, 67, 71, 91, 80, 99
    );
    public Map<Integer, Integer> snake = Map.of(
            17, 4, 19, 7, 21, 9, 27, 1, 62, 18, 64, 60, 87, 24, 93, 73, 95, 75, 99, 78
    );
    public int end = 100;

    Board(int end) {
        this.end = end;
    }

    public int checkLadder(int pos) {
        return ladder.getOrDefault(pos, 0);
    }

    public int checkSnake(int pos) {
        return snake.getOrDefault(pos, 0);
    }
}

interface introduce {
    void aboutMe();
}

class player implements introduce {
    public String name, address;
    int playerId, age;

    player(String name, String address, int playerId, int age) {
        this.name = name;
        this.address = address;
        this.age = age;
        this.playerId = playerId;
    }

    public void aboutMe() {
        System.out.println("I am " + this.name + " and my age is " + age + " my address is " + this.address + " Player id-" + this.playerId);
    }
}

interface rolldice {
    int roll();
}

class NormalDice implements rolldice {
    private final Random r = new Random();
    public int roll() {
        int rand = r.nextInt(6) + 1;
        System.out.println("You got " + rand + " in dice");
        return rand;
    }
}

class DiceFactory {
    public static rolldice getDice() {
        return new NormalDice();
    }
}

interface positionupdate {
    void updateposition();
}

interface check {
    int getresult();
}

class play implements positionupdate, check {
    private int curPos = 0;
    private final Board board = new Board(100);
    public player user = new player("Ravi", "172 pillayar kovil st,Deepmanagalam,Aaandikuppam ", 1, 18);
    private final rolldice dice = DiceFactory.getDice();

    public void updateposition() {
        int toAdd = dice.roll();
        int tentative = this.curPos + toAdd;

        if (tentative > board.end) {
            System.out.println("Need exact number to reach 100!");
            System.out.println("Current Position: " + this.curPos);
            return;
        }

        this.curPos = tentative;

        int ladderDest = board.checkLadder(this.curPos);
        if (ladderDest != 0) {
            this.curPos = ladderDest;
            System.out.println("🔥 Ladder Climb! New Position: " + this.curPos);
        }

        int snakeDest = board.checkSnake(this.curPos);
        if (snakeDest != 0) {
            this.curPos = snakeDest;
            System.out.println("🐍 Snake Bite! New Position: " + this.curPos);
        }

        System.out.println("Current Position: " + this.curPos);
    }

    public int getresult() {
        if (this.curPos == board.end) {
            System.out.println("🏆 Congratulations " + user.name + "! You have won the Game!");
            return 1;
        }
        System.out.println("You have to move " + (board.end - this.curPos) + " positions");
        return 0;
    }
}

public class game {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        play pl = new play();

        System.out.println("🎯 Game Started!");
        System.out.println("Type Roll to roll the dice");

        while (pl.getresult() != 1) {
            String st = sc.nextLine();
            if (st.equalsIgnoreCase("Roll")) {
                pl.updateposition();
                if (pl.getresult() == 1) break;
                System.out.println("Type Roll to roll the dice");
            }
        }
    }
}
