package main.ui.login;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import main.ui.database.ConnectMSSQL;

public class LogInController {

    @FXML
    private TextField userId;

    @FXML
    private TextField userPassword;

    private String loginType;

    @FXML
    void loginClicked(ActionEvent event) {
        System.out.println("Login Type " + loginType);
        ConnectMSSQL connectMSSQL = new ConnectMSSQL();
        System.out.println(userId.getText() + " " +userPassword.getText());
        connectMSSQL.logInForm(userId.getText(),loginType ,userPassword.getText());
    }

    public void setLoginType(String loginType){
        this.loginType = loginType;
    }


}
