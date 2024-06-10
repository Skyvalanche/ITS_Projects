import java.util.Random;

public class Player {
    private final String playerName;
    private int position;
    private static Random random;
    private static boolean log;

    public Player(String name, boolean clog) {
        playerName = name;
        random = new Random();
        position = 0;
        log = clog;
    }

    private int isOnSnake() {
        switch (position) {
            case 29:
                return 9;
            case 38:
                return 15;
            case 47:
                return 5;
            case 53:
                return 33;
            case 62:
                return 37;
            case 86:
                return  54;
            case 92:
                return 70;
            case 97:
                return  25;
            default:
                return -1;
        }
    }

    private int isOnLadder() {
        switch (position) {
            case 2:
                return 18;
            case 8:
                return 34;
            case 20:
                return 77;
            case 32:
                return 68;
            case 41:
                return 79;
            case 74:
                return 88;
            case 82:
                return 100;
            case 85:
                return 95;
            default:
                return -1;
        }
    }

    private boolean hasWon() {
        return position == 100;
    }

    private int rollADice() {
        return random.nextInt(6) + 1;
    }

    private void walk (int foots) {
        if (log) System.out.print(this + "(🎲 " + foots + ") ");
        if ((position + foots) > 100) {
            if (log)  System.out.print(position  + " -> ");
            position = 100 - (position + foots)%100;
            if (log) System.out.print(position);
        }
        else {
            if (log) System.out.print(position  + " -> ");
            position += foots;
            if (log) System.out.print(position);

        }
    }

    public boolean Play() {

        walk(rollADice());

        int ladder;
        int snake;
        if ((ladder= isOnLadder()) != -1) {
            position = ladder;
            if (log) System.out.print(" 🪜 -> " + position);
        } else if ((snake = isOnSnake()) != -1) {
            position = snake;
            if (log) System.out.print(" 🐍 -> " + position);
        }
        if (log) System.out.println();
        return hasWon();
    }

    public String toString() {
        return playerName;
    }
}
