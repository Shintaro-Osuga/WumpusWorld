public class Wumpus implements Object {
    private static final String objectName = "Wumpus";
    private boolean isAlive = true;

    public String releasePercept()
    {
        return "Stench";
    }

    public boolean killPlayer()
    {
        return false;
    }

    public boolean hit()
    {
        isAlive = false;
        return true;
    }

    public String getName()
    {
        return objectName;
    }
}
