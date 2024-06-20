public class Ladders {

    private boolean active;
    private boolean revert;
    public Ladders() {
        active = true;
        revert = false;
    }

    public void SetActive(boolean a) {
        active = a;
    }

    public void Revert(boolean r) {
        revert = r;

    }
    public int isOnLadder(int position) {
        //If the Ladder is not activated
        if (!active) return -1;
        if (revert) {
            switch (position) {
                case 18:
                    return 2;
                case 34:
                    return 8;
                case 77:
                    return 20;
                case 68:
                    return 32;
                case 79:
                    return 41;
                case 88:
                    return 74;
                case 100:
                    return 82;
                case 95:
                    return 85;
                default:
                    return -1;
            }
        }
        else {
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
    }
}
