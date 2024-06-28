import java.util.Random;

public class Player {
    private String playerName;
    private int position;
    private static Random random;
    private Ladders ladders;
    private Snakes snakes;
    private boolean heat;
    private static boolean log;
    private int[] penaltyTiles;

    private int points; // Added field to keep track of points
    private boolean diceDuplicated; // Added field to keep track of dice duplication

    public Player(String name, Ladders lddrs, Snakes snks, boolean clog, int[] penaltyTiles) {
        playerName = name;
        random = new Random();
        position = 0;
        ladders = lddrs;
        snakes = snks;
        heat = false;
        log = clog;
        points = 0; // Initialize points
        diceDuplicated = false; // Initialize diceDuplicated
        this.penaltyTiles = penaltyTiles;
    }

    private boolean hasWon() {
        return position == 100;
    }

    private int rollADice() {
        if (diceDuplicated) {
            // Roll two dice if dice are duplicated
            return (random.nextInt(heat ? 3 : 6) + 1) * 2;
        } else {
            // Roll one die otherwise
            return random.nextInt(heat ? 3 : 6) + 1;
        }
    }

    private void walk(int foots) {
        if (log) System.out.print(this + "(🎲 " + foots + ") ");
        if ((position + foots) > 100) {
            if (log) System.out.print(position + " -> ");
            position = 100 - (position + foots) % 100;
            if (log) System.out.print(position);
        } else {
            if (log) System.out.print(position + " -> ");
            position += foots;
            if (log) System.out.print(position);
        }
    }

    private void checkPenalty() {
        for (int tile : penaltyTiles) {
            if (position == tile) {
                if (log) System.out.println(playerName + " landed on a penalty tile!\nMoving back 3 tiles.");
                position -= 3;
                if (position < 0) {
                    position = 0;
                }
                break;
            }
        }
    }

    public boolean Play() {
        walk(rollADice());

        points++; // Increment points
        if (points == 3) {
            points = 0; // Reset points
            diceDuplicated = true; // Duplicate the dice
            if (log) System.out.println(" ✖\uFE0F 2\uFE0F⃣");
        }

        int ladder = ladders.isOnLadder(position);
        int snake;
        if (ladder != -1) {
            position = ladder;
            if (log) System.out.print(" 🪜 -> " + position);
        }
        if ((snake = snakes.isOnSnake(position)) != -1) {
            position = snake;
            if (log) System.out.print(" 🐍 -> " + position);
        }

        checkPenalty(); // Check for penalty tile after moving

        if (log) System.out.println();
        return hasWon();
    }

    /*EVENTS*/

    public void HeatWave(boolean b) {
        heat = b;
    }

    public void Tsunami() {
        if (position % 5 != 0) {
            if (log) System.out.print(this + " " + position + " 🌊 -> ");
            position = position < 15 ? 0 : position - 15;
            if (log) System.out.println(position);
        } else if (log) System.out.println(this + " Is Safe !");
    }

    public String toString() {
        return playerName;
    }
}
