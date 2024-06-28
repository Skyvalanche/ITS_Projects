public class Snakes {

    private boolean active;
    private boolean revert;
    public Snakes() {
        active = true;
        revert = false;
    }

    public void SetActive(boolean a) {
        active = a;
    }

    public void Revert(boolean r) {
        revert = r;

    }
    public int isOnSnake(int position) {

        //If the Snakes are not activated
        if (!active) return -1;

        if (revert) {
            switch (position) {
                case 9:
                    return 29;
                case 15:
                    return 38;
                case 5:
                    return 47;
                case 33:
                    return 53;
                case 37:
                    return 62;
                case 54:
                    return 70;
                case 70:
                    return 92;
                case 25:
                    return 97;
                default:
                    return -1;
            }
        }
        switch (position) {
            case 29:
                return 9;
            case 38:
                return 15;
            case 47:
                return 5;
            case 53:
                return 33;
            case 62:
                return 37;
            case 86:
                return  54;
            case 92:
                return 70;
            case 97:
                return  25;
            default:
                return -1;
        }
    }
}
