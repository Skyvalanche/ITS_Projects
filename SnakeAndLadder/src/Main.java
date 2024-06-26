import java.util.Scanner;

public class Main {
    public Main() {
    }

    public static void main(String[] args) {

        //Intro
        System.out.println("Hey let's play Snake and Ladder game !!");
        System.out.print("How many player are they : ");
        Scanner sc = new Scanner(System.in);

        //Player Number
        int playerNumber = sc.nextInt();
        sc.nextLine();
        while (playerNumber <= 0) {
            System.out.println("You must enter a positive number");
            playerNumber = sc.nextInt();
            sc.nextLine();
        }

        //Player Names
        String[] playersName = new String[playerNumber];
        System.out.println("Enter Players Name (if nothing it will just be the number of player)");
        for (int i = 1; i <= playerNumber; i++) {
            System.out.print("Player " + i + " : ");
            String currentPLayerName = sc.nextLine();
            playersName[i-1] = currentPLayerName.isEmpty() ? "Player " + i : currentPLayerName;
        }

        String line;

        //Questions
        System.out.println("Do you want to randomize the playing order ? (y/n)");
        line = sc.nextLine();
        boolean random = line.equals("y");
        System.out.println("Do you want to see the logs ? (y/n)");
        line = sc.nextLine();
        boolean logs = !line.equals("n");

        //Start Game
        BoardGame game = new BoardGame(playerNumber, playersName, random, logs);
        game.playGame();
    }
}
