package util;

/*Singleton class to hold local variables*/
public class Util {
    private static Util instance = null;

    private String userId;
    private String userType;
    public static  final int SCREEN_WIDTH = 1200;
    public static  final int SCREEN_HEIGHT = 857;

    public static  final int DIALOG_SCREEN_WIDTH = 1000;
    public static  final int DIALOG_SCREEN_HEIGHT = 800;

    public static Util getInstance()
    {
        if (instance == null)
            instance = new Util();

        return instance;
    }

    public static void setInstance(Util instance) {
        Util.instance = instance;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }
}
