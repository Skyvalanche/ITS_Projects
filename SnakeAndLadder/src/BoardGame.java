import java.util.Random;

public class BoardGame {

    private int playersNumber;

    private Snakes snakes;
    private Ladders ladders;

    private Random random;
    private Player[] players;

    public BoardGame(int playerNb, String[] playersName, boolean r, boolean log) {
        playersNumber = playerNb;
        random = new Random();
        players = new Player[playersNumber];
        snakes = new Snakes();
        ladders = new Ladders();

        if (r) {
            int[] attributed = new int[playersNumber];
            for (int i = 0; i < playersNumber; i++) {
                attributed[i] = -1;
            }
            for (int i = 0; i < playersNumber; i++) {
                int nb = random.nextInt(playersNumber);
                while(attributed[nb] != -1) {
                    nb = random.nextInt(playersNumber);
                }
                attributed[nb] = i;
                players[nb] = new Player(playersName[i], ladders, snakes, log);
            }
        }
        else {
            for (int i = 0; i < playersNumber; i++) {
                players[i] = new Player(playersName[i], ladders, snakes, log);
            }
        }
    }

    public void swap(int i, int playersLeft) {
        //0,1,2,3,4,5
        //i=2 < 5
        Player p = players[i];
        while (i < playersLeft) {
            players[i] = players[i+1];
            i++;
        }
        players[i] = p;
    }

    public void playGame() {
        int i = 0;
        //time to wait when a event happen
        String eventName = "None";
        String[] eventList = {"Zany", "Night", "Rain", "Heat Wave", "Tsunami", "Earth Quake"};
        int[] eventDurations = {5, 4, 3, 3, 1, 1};
        int playersLeft = playersNumber;

        int eventDuration = 0;
        while (playersLeft != 0) {
            while (!players[i].Play()) {
                i = (i + 1) % playersLeft;

                if (eventDuration == 0) {
                    //undo the event changes
                    switch (eventName) {
                        case "Zany":
                            System.out.println("Zany has ended");
                            snakes.Revert(false);
                            ladders.Revert(false);
                        case "Night":
                            System.out.println("The Sun rise and the snake with them");
                            snakes.SetActive(true);
                            break;
                        case "Rain":
                            System.out.println("Ladders are dry now you can use them");
                            ladders.SetActive(true);
                            break;
                        case "Heat Wave":
                            System.out.println("The Heat went out, you can move faster");
                            for (int k = 0; k < playersLeft; k++) {
                                players[k].HeatWave(false);
                            }
                            break;
                        case "Tsunami":
                            //nothing happen
                            break;
                        case "Earth Quake":
                            System.out.println("The Earth Stopped shaking");
                            ladders.SetActive(true);
                            snakes.SetActive(true);
                            break;
                    }
                    eventName = "";
                    eventDuration--;
                } else if (eventDuration > 0) {
                    eventDuration--;
                } else if (random.nextInt(10) == 0) {
                    int eventID = random.nextInt(6);
                    eventName = eventList[eventID];
                    eventDuration = playersLeft * eventDurations[eventID];


                    switch (eventName) {
                        case "Zany":
                            System.out.println("The Zany has begun !!");
                            snakes.Revert(true);
                            ladders.Revert(true);
                            break;
                        case "Night":
                            System.out.println("The night is up, Snakes sleeping for 5 rounds");
                            snakes.SetActive(false);
                            break;
                        case "Rain":
                            System.out.println("It's Rainy, Ladders now slip and can't be used");
                            ladders.SetActive(false);
                            break;
                        case "Heat Wave":
                            System.out.println("It's Hot, You now move slower because of the high temperature");
                            for (int k = 0; k < playersLeft; k++) {
                                players[k].HeatWave(true);
                            }
                            break;
                        case "Tsunami":
                            System.out.println("There a Tsunami, Whoever is not protected (on %5 tile) is taken back");
                            for (int k = 0; k < playersLeft; k++) {
                                players[k].Tsunami();
                            }
                            break;
                        case "Earth Quake":
                            System.out.println("There was an Earth Quake, Ladders are now broken and snakes went hiding");
                            ladders.SetActive(false);
                            snakes.SetActive(false);
                            break;
                    }
                }
            }
            if (playersNumber == playersLeft) System.out.println(players[i] + " has Won the Game !!\nWell done 👍");
            else System.out.println(players[i] + " has scored Place "+(playersNumber-playersLeft+1)+"\nGood Job 👍");
            playersLeft--;
            swap(i,playersLeft);
            if (playersLeft!=0) i = (i + 1) % playersLeft;
        }
        System.out.println("Everyone Ended The Game 👍");
    }
}
