import java.util.Random;

public class BoardGame {

    private int playersNumber;
    private Player[] players;

    public BoardGame(int playerNb, String[] playersName, boolean r, boolean log) {
        playersNumber = playerNb;

        players = new Player[playersNumber];

        if (r) {
            int[] attributed = new int[playersNumber];
            for (int i = 0; i < playersNumber; i++) {
                attributed[i] = -1;
            }
            Random rdm = new Random();
            for (int i = 0; i < playersNumber; i++) {
                int nb = rdm.nextInt(playersNumber);
                while(attributed[nb] != -1) {
                    nb = rdm.nextInt(playersNumber);
                }
                attributed[nb] = i;
                players[nb] = new Player(playersName[i], log);
            }
        }
        else {
            for (int i = 0; i < playersNumber; i++) {
                players[i] = new Player(playersName[i], log);
            }
        }
    }

    public void playGame() {
        int i = 0;

        while(!players[i].Play()) {
            i = (i+1)%playersNumber;
        }
        System.out.println(players[i] + " has Won the Game !!\nWell done 👍");
    }
}
