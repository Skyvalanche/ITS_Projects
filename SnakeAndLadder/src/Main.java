import java.util.Scanner;

public class Main {
    public Main() {
    }

    public static void main(String[] args) {
        System.out.println("Hey let's play Snake and Ladder game !!");
        System.out.println("How many player are they ?");
        Scanner sc = new Scanner(System.in);
        int playerNumber = sc.nextInt();
        sc.nextLine();
        while (playerNumber <= 0) {
            System.out.println("You must enter a positive number");
            playerNumber = sc.nextInt();
            sc.nextLine();
        }
        String[] playersName = new String[playerNumber];
        for (int i = 1; i <= playerNumber; i++) {
            System.out.print("Player " + i + " name : ");
            String currentPLayerName = sc.nextLine();
            playersName[i-1] = currentPLayerName.isEmpty() ? "Player " + i : currentPLayerName;
        }
        System.out.println("Do you want to see the logs ? (yes/no)");
        String line = sc.nextLine();
        BoardGame game = new BoardGame(playerNumber, playersName, line.equals("yes"));
        game.playGame();
    }
}
