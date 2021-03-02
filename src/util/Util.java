package util;

/*Singleton class to hold local variables*/
public class Util {
    private static Util instance = null;

    private String userId;
    private String userType;

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
