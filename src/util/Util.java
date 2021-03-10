package util;

import java.lang.reflect.Array;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

/*Singleton class to hold local variables*/
public class Util {
    public static final String TYPE_ADMIN = "admin";
    public static final String TYPE_DOCTOR = "doctor";

    public static final String TYPE_RECEPTIONIST = "receptionist";

    private static Util instance = null;

    private String userId;
    private String userType;
    public static final int SCREEN_WIDTH = 1200;
    public static final int SCREEN_HEIGHT = 857;

    public static final int DIALOG_SCREEN_WIDTH = 1000;
    public static final int DIALOG_SCREEN_HEIGHT = 800;

    public static Util getInstance() {
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
     * @param time in 24hr format: "22:15"
     * @return time in 12hr format hh:mm a
     */
    public static String convert24to12format(String time) {
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

    public static String formatDate(Date date) {
        String pattern = "dd MMMM yyyy";
        SimpleDateFormat simpleDateFormat =
                new SimpleDateFormat(pattern, Locale.getDefault());

        String a = simpleDateFormat.format(date);
        System.out.println(a);
        return a;


    }

    public static String formatDate(LocalDate date) {
        DateTimeFormatter formatters = DateTimeFormatter.ofPattern("dd MMMM yyyy");
        String text = date.format(formatters);
        LocalDate parsedDate = LocalDate.parse(text, formatters);
        return parsedDate.format(formatters);

    }

    /**
     * @param day integer extracted from date instance
     * @return the day, ie day = 1 means Monday
     */
    public static String getDay(int day) {
        String[] days = {"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"};
        return days[day];
    }

    private static String getMonth(int month) {
        String[] days = {"January", "February", "March", "April", "May", "June",
                "July", "Aug", "September", "October", "November", "December"};
        return days[month];
    }

    /**
     * str: in the form of "sdasd, asd, asd"
     * returns: list of strings with a the comma as a separator
     */
    public static String[] splitString(String str) {
        return str.split(",");
    }
}
