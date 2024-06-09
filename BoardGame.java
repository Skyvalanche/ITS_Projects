public class BoardGame {

    private static int playersNumber;
    private static Player[] players;

    public BoardGame(int playerNb, String[] playersName, boolean log) {
        playersNumber = playerNb;
        players = new Player[playersNumber];
        for (int i = 0; i < playersNumber; i++) {
            players[i] = new Player(playersName[i], log);
        }
    }

    public void playGame() {
        int i = 0;

        while(!players[i].Play()) {
            i = (i+1)%playersNumber;
        }
        System.out.println(players[i] + " has Won the Game !!\nWell done \uD83D\uDC4D");
    }
}
