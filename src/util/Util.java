package util;

import java.lang.reflect.Array;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

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

    /**
     *
     * @param time in 24hr format: "22:15"
     * @return time in 12hr format hh:mm a
     */
    public static String convert24to12format(String time){
        try {
            SimpleDateFormat _24HourSDF = new SimpleDateFormat("HH:mm");
            SimpleDateFormat _12HourSDF = new SimpleDateFormat("hh:mm a");
            Date date = _24HourSDF.parse(time);
           // System.out.println(date);
            System.out.println(_12HourSDF.format(date));
            return _12HourSDF.format(date);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     *
     * @param day integer extracted from date instance
     * @return the day, ie day = 1 means Monday
     */
    public static String getDay(int day){
        String[] days = {"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"};
        return days[day];
    }
}
