package com.example.endassignment.controller;

import com.example.endassignment.LibaryApplication;
import com.example.endassignment.database.Database;
import com.example.endassignment.model.User;
import com.example.endassignment.service.LoginService;
import com.example.endassignment.service.UserService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable{
    private LoginService loginService;
    private UserService userService;
    private Database database;
    @FXML
    private Button loginbutton;
    @FXML
    private TextField usernameText;
    @FXML
    private PasswordField passwordText;
    @FXML
    private Label message;

    //Initialize
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        database = new Database();
        loginService = new LoginService(database);
        userService = new UserService(database);
    }
    @FXML
    protected void onLoginButtonClick(ActionEvent event) {
        //login logic
        String username = usernameText.getText();
        String password = passwordText.getText();

        if(!checkFields()){
            message.setText("Please fill out all fields");
            message.setVisible(true);
        }else{
            if(loginService.checkLogin(username, password)) {
                //get user
                User currentUser = userService.getUser(username);
                message.setText("login successfull yay");
                message.setVisible(true);
                try{
                    openMainWindow(currentUser);
                }catch(Exception e){
                    message.setText(e.getMessage());
                }
            }else{
                message.setText("Login data is wrong.");
                message.setVisible(true);
            }
        }
    }

    private boolean checkFields() {
        if (usernameText.getText().isEmpty() || passwordText.getText().isEmpty()) {
            return false;
        } else return true;
    }
    private void openMainWindow(User user) throws IOException {
        Stage stage = new Stage();

        FXMLLoader fxmlLoader = new FXMLLoader(LibaryApplication.class.getResource("main-view.fxml"));
        MainController mainController = new MainController(user, stage, database);
        fxmlLoader.setController(mainController);

        // Initialize scene and display dashboard
        Scene scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
        stage.setTitle("Library System");
        stage.sizeToScene();
        stage.show();
        stage.setMinWidth(stage.getWidth());
        stage.setMinHeight(stage.getHeight());

        // Close current window
        Stage currentStage = (Stage)loginbutton.getScene().getWindow();
        currentStage.close();
    }


}
