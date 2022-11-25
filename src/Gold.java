public class Gold implements Object {
    private static final String objectName = "Gold";

    public String releasePercept() {
        return "SPARKLE";
    }

    public boolean killPlayer() {
        return false;
    }
    
    public String getName() {
        return objectName;
    }
}
