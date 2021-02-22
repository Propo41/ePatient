package util;

import main.ui.database.ConnectMSSQL;

import java.sql.Connection;

/*Singleton class to hold local variables*/
public class Util {
    private static Util instance = null;

    private Connection connection;
    private ConnectMSSQL connectMSSQL;


    public static Util getInstance()
    {
        if (instance == null)
            instance = new Util();

        return instance;
    }

    public ConnectMSSQL getConnectMSSQL() {
        return connectMSSQL;
    }

    public void setConnectMSSQL(ConnectMSSQL connectMSSQL) {
        this.connectMSSQL = connectMSSQL;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public Connection getConnection() {
        return connection;
    }


}
