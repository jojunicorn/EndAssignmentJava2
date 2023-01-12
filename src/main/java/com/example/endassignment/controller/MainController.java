package com.example.endassignment.controller;

import com.example.endassignment.LibaryApplication;
import com.example.endassignment.database.Database;
import com.example.endassignment.model.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable {
    private String name;
    private Stage stage;
    private Database database;

    final String MAIN_VIEW ="main-view";
    final String LENDING_RECEIVING_VIEW = "lending-receiving-view.fxml";
    final String COLLECTION_VIEW = "collection-view.fxml";
    final String MEMBER_VIEW = "member-view.fxml";
    @FXML
    VBox mainWindow;
    @FXML
    Button btnLendingReceiving;
    @FXML
    Button btnCollections;
    @FXML
    Button btnMembers;

    public MainController(User user, Stage stage, Database database){
        this.name = user.firstName;
        this.stage = stage;
        this.database = database;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        loadScene(LENDING_RECEIVING_VIEW, new LendingReceivingController(name, database));
    }

    private void loadScene(String fileName, Object controller){
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(LibaryApplication.class.getResource(fileName));
            fxmlLoader.setController(controller);
            Scene scene = new Scene(fxmlLoader.load());
            if (mainWindow.getChildren().size() > 1) {
                mainWindow.getChildren().remove(1);
            }

            mainWindow.getChildren().add(scene.getRoot());
        } catch (Exception e) {
            // write exception message
        }
    }
    @FXML
    protected void onLendingReceivingButtonClick(ActionEvent event){
        changeButtonColor(btnLendingReceiving, btnCollections, btnMembers);
        loadScene(LENDING_RECEIVING_VIEW, new LendingReceivingController(name, database));
    }
    @FXML
    protected void onCollectionButtonClick(ActionEvent event){
        changeButtonColor(btnCollections, btnLendingReceiving, btnMembers);
        loadScene(COLLECTION_VIEW, new CollectionViewController());
    }
    @FXML
    protected void onMembersButtonClick(ActionEvent event){
        changeButtonColor(btnMembers, btnLendingReceiving, btnCollections);
    }

    private void changeButtonColor(Button currentButton, Button otherButton1, Button otherButton2){
        //set button color to make it look like selected tab
        currentButton.setStyle("-fx-background-color: #D3D3D3; -fx-background-radius: 0;");
        otherButton1.setStyle("-fx-background-color: #63666A; -fx-background-radius: 0;");
        otherButton2.setStyle("-fx-background-color: #63666A; -fx-background-radius: 0;");
    }
}
