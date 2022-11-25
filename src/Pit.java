public class Pit implements Object {
    private static final String objectName = "Pit";

    public String releasePercept()
    {
        return "Breeze";
    }

    public boolean killPlayer()
    {
        return false;
    }

    @Override
    public String getName() {
        return objectName;
    }
}
