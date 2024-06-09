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
        return switch (position) {
            case 29 -> 9;
            case 38 -> 15;
            case 47 -> 5;
            case 53 -> 33;
            case 62 -> 37;
            case 86 -> 54;
            case 92 -> 70;
            case 97 -> 25;
            default -> -1;
        };
    }

    private int isOnLadder() {
        return switch (position) {
            case 2 -> 18;
            case 8 -> 34;
            case 20 -> 77;
            case 32 -> 68;
            case 41 -> 79;
            case 74 -> 88;
            case 82 -> 100;
            case 85 -> 95;
            default -> -1;
        };
    }

    private boolean hasWon() {
        return position == 100;
    }

    private int rollADice() {
        return random.nextInt(1,7);
    }

    private void walk (int foots) {
        if (log) System.out.print(this + "(\uD83C\uDFB2 " + foots + ") ");
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
            if (log) System.out.print(" \uD83E\uDE9C -> " + position);
        } else if ((snake = isOnSnake()) != -1) {
            position = snake;
            if (log) System.out.print(" \uD83D\uDC0D -> " + position);
        }
        System.out.println();
        return hasWon();
    }

    public String toString() {
        return playerName;
    }
}
