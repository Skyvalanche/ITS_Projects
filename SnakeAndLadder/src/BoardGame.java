import java.util.Random;

public class BoardGame {

    private int playersNumber;
    private Snakes snakes;
    private Ladders ladders;
    private Random random;
    private Player[] players;
    private boolean logs;

    //Constructor
    public BoardGame(int playerNb, String[] playersName, boolean r, boolean log) {
        playersNumber = playerNb;
        random = new Random();
        players = new Player[playersNumber];
        snakes = new Snakes();
        ladders = new Ladders();
        logs = log;

        //if we randomize
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
        } else { //otherwise we just insert players in the given name order
            for (int i = 0; i < playersNumber; i++) {
                players[i] = new Player(playersName[i], ladders, snakes, log);
            }
        }
    }

    public void swap(int position, int playersLeft) {
        //move the wining player behind the players still in the game
        Player p = players[position];
        while (position < playersLeft) {
            players[position] = players[position+1];
            position++;
        }
        players[position] = p;
    }

    public void playGame() {
        int i = 0;
        String eventName = "None";
        String[] eventList = {"Zany", "Night", "Rain", "Heat Wave", "Tsunami", "Earth Quake"};
        int[] eventDurations = {6, 5, 4, 4, 1, 3};
        int playersLeft = playersNumber;
        int eventDuration = 0;

        //Loop till they are no player left
        while (playersLeft != 0) {
            //Same loop, used twice to permit everyone to finish the game
            while (!players[i].Play()) {

                //Manage Events
                if (eventDuration == 0) { //if the event just ended
                    //undo the event changes
                    switch (eventName) {
                        case "Zany":
                            if (logs) System.out.println("Zany has ended");
                            snakes.Revert(false);
                            ladders.Revert(false);
                        case "Night":
                            if (logs) System.out.println("The Sun rise and the snake with them");
                            snakes.SetActive(true);
                            break;
                        case "Rain":
                            if (logs) System.out.println("Ladders are dry now you can use them");
                            ladders.SetActive(true);
                            break;
                        case "Heat Wave":
                            if (logs) System.out.println("The Heat went out, you can move faster");
                            for (int k = 0; k < playersLeft; k++) {
                                players[k].HeatWave(false);
                            }
                            break;
                        case "Tsunami":
                            //nothing happen
                            break;
                        case "Earth Quake":
                            if (logs) System.out.println("The Earth Stopped shaking");
                            ladders.SetActive(true);
                            snakes.SetActive(true);
                            break;
                    }
                    //reset event
                    eventName = "";
                    eventDuration--;
                } else if (eventDuration > 0) {
                    eventDuration--;
                } else if (random.nextInt(10) == 0) { //if there is no event and with 10% chance
                    //We pick a new event randomly
                    int eventID = random.nextInt(6);
                    eventName = eventList[eventID];
                    eventDuration = playersLeft * eventDurations[eventID];

                    switch (eventName) {
                        case "Zany": //the snake make you go up and ladder go down
                            if (logs) System.out.println("The Zany has begun !!");
                            snakes.Revert(true);
                            ladders.Revert(true);
                            break;
                        case "Night": //no snakes
                            if (logs) System.out.println("The night is up, Snakes sleeping for 5 rounds");
                            snakes.SetActive(false);
                            break;
                        case "Rain": //no ladders
                            if (logs) System.out.println("It's Rainy, Ladders now slip and can't be used");
                            ladders.SetActive(false);
                            break;
                        case "Heat Wave": //you move slowly (dice between 1 and 3)
                            if (logs) System.out.println("It's Hot, You now move slower because of the high temperature");
                            for (int k = 0; k < playersLeft; k++) {
                                players[k].HeatWave(true);
                            }
                            break;
                        case "Tsunami": //if you're not in a shelter (%5 tiles), you're being pushed back from 15 tiles
                            if (logs) System.out.println("There a Tsunami, Whoever is not protected (on %5 tile) is taken back");
                            for (int k = 0; k < playersLeft; k++) {
                                players[k].Tsunami();
                            }
                            break;
                        case "Earth Quake": //no ladder nor snakes during this event
                            if (logs) System.out.println("There was an Earth Quake, Ladders are now broken and snakes went hiding");
                            ladders.SetActive(false);
                            snakes.SetActive(false);
                            break;
                    }
                }

                //next turn
                i = (i + 1) % playersLeft;
            }
            if (playersNumber == playersLeft) System.out.println(players[i] + " has Won the Game !!\nWell done 👍");
            else System.out.println(players[i] + " has scored in "+(playersNumber-playersLeft+1)+ " position\nGood Job 👍");
            playersLeft--;
            swap(i,playersLeft);
            if (playersLeft!=0) i = (i + 1) % playersLeft;
        }
        System.out.println("Everyone Ended The Game 👍");
    }
}
