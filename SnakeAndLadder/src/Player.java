import java.util.Random;

public class Player {
    private final String playerName;
    private int position;
    private static Random random;
    private Ladders ladders;
    private Snakes snakes;

    private boolean heat;
    private static boolean log;

    public Player(String name, Ladders lddrs, Snakes snks, boolean clog) {
        playerName = name;
        random = new Random();
        position = 0;
        ladders = lddrs;
        snakes = snks;
        heat = false;
        log = clog;
    }

    private boolean hasWon() {
        return position == 100;
    }

    private int rollADice() {
        return random.nextInt(heat?3:6) + 1;
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

        int ladder = ladders.isOnLadder(position);
        int snake;
        if (ladder != -1) {
            position = ladder;
            if (log) System.out.print(" 🪜 -> " + position);
        } if ((snake = snakes.isOnSnake(position)) != -1) {
            position = snake;
            if (log) System.out.print(" 🐍 -> " + position);
        }
        if (log) System.out.println();
        return hasWon();
    }

    /*EVENTS*/

    public void HeatWave(boolean b) {
        heat = b;
    }

    public void Tsunami() {
        if (position%5 != 0) {
            if (log) System.out.print(this + " " + position + " 🌊 -> ");
            position = position<15?0:position-15;
            if (log) System.out.println(position);
        }
        else if (log) System.out.println(this + " Is Safe !");
    }

    public String toString() {
        return playerName;
    }
}
