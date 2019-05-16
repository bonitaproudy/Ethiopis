package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;


import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ChoicesMenuController implements Initializable {
    //buttons
    public Button backToMe;
    public Button diffMediumButton;
    public Button diffHardButton;
    public Button diffEasyButton;
    //other scene elements
//getters and setters
    public Button getBackToMe() {
        return backToMe;
    }

    public Button getDiffEasyButton() {
        return diffEasyButton;
    }

    public Button getDiffHardButton() {
        return diffHardButton;
    }

    public Button getDiffMediumButton() {
        return diffMediumButton;
    }
    public void onBackToMeClick(ActionEvent event) throws IOException{
        Parent backToSample =  FXMLLoader.load(getClass().getResource("sample.fxml"));
        Scene backToMeScene = new Scene(backToSample);
        Stage backToMeStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        backToMeStage.setScene(backToMeScene);
        backToMeStage.show();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
