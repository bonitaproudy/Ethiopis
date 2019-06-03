package sample;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Point3D;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;


import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;

import static gamelogic.Levels.LEVEL_EASY;
import static gamelogic.Levels.LEVEL_HARD;
import static gamelogic.Levels.LEVEL_MID;

public class ChoicesMenuController implements Initializable {
    //Universal declarations
    static public int difficulty=0;
    Timer iterateClouds = new Timer();
    double counter = 0;
    int nightModeStatus = 0;
    private Stage primaryStage;
    //buttons
    public Button backToMe;
    public Button diffMediumButton;
    public Button diffHardButton;
    public Button diffEasyButton;
    //other scene elements
    public ImageView topCloud;
    public ImageView bottomCloud;
    public Label difficultyLabelHard;
    public Label difficultyLabelEasy;
    public Label difficultyLabelMedium;
    public Label difficultyLabelSelect;
    //containers
    public BorderPane skyClassChoices;
    public HBox grassClassChoices;
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

    public ImageView getTopCloud() {
        return topCloud;
    }

    public ImageView getBottomCloud() {
        return bottomCloud;
    }

    public BorderPane getSkyClassChoices() {
        return skyClassChoices;
    }

    public HBox getGrassClassChoices() {
        return grassClassChoices;
    }

    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    public void onBackToMeClick() throws IOException{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("sample.fxml"));
        Controller controller = new Controller();
        loader.setController(controller);
        Parent root = loader.load();
        Scene scene = new Scene(root, 600, 400);
        controller.setPrimaryStage(primaryStage);
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }
    public void setDifficulty(int difficulty){
        difficulty = this.difficulty;
    }
    public int getDifficulty(){
        return difficulty;
    }
    public void onButtonClickForward(ActionEvent event) throws IOException{
        if(event.getSource() == getDiffEasyButton()){
            difficulty = LEVEL_EASY;
        }
        else if(event.getSource() == getDiffMediumButton()){
            difficulty = LEVEL_MID;
        }
        else if(event.getSource() == getDiffHardButton()){
            difficulty = LEVEL_HARD;
        }
        System.out.println(difficulty);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("gameModesList.fxml"));
        GameModesListController controller = new GameModesListController();
        loader.setController(controller);
        Parent root = loader.load();
        Scene scene = new Scene(root, 600, 400);
        controller.setPrimaryStage(primaryStage);
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }
    public void cloudsAnimation(){
        iterateClouds.schedule(new TimerTask() {
            @Override
            public void run() {
                Platform.runLater(() -> {
                    counter= getTopCloud().getLayoutX();
                    if(getTopCloud().getLayoutX()>=290){
                        getTopCloud().setLayoutX(-100);
                    }
                    else if ( getBottomCloud().getLayoutX()>=285){
                        getBottomCloud().setLayoutX(-80);
                    }

                    getTopCloud().setTranslateX((getTopCloud().getLayoutX()) + 1);
                    getTopCloud().setLayoutX((getTopCloud().getLayoutX())+ 1);
                    getBottomCloud().setTranslateX((getBottomCloud().getLayoutX()) + 0.6);
                    getBottomCloud().setLayoutX((getBottomCloud().getLayoutX()) + 0.6);
                });
            }
        }, 100, 30);
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if(Controller.nightModeStatus == 1){
            new Controller().setNightMode(skyClassChoices,grassClassChoices,"CHOICES");
            difficultyLabelEasy.setTextFill(new Color(1,1,1,1));
            difficultyLabelHard.setTextFill(new Color(1,1,1,1));
            difficultyLabelMedium.setTextFill(new Color(1,1,1,1));
            difficultyLabelSelect.setTextFill(new Color(1,1,1,1));
            getDiffEasyButton().setStyle("-fx-background-color: rgba(200, 200, 200,1)");
            getDiffHardButton().setStyle("-fx-background-color: rgba(200, 200, 200,1)");
            getDiffMediumButton().setStyle("-fx-background-color: rgba(200, 200, 200,1)");
        }
        else{
            new Controller().setDayMode(skyClassChoices,grassClassChoices,"CHOICES");
            difficultyLabelEasy.setTextFill(new Color(0,0,0,1));
            difficultyLabelHard.setTextFill(new Color(0,0,0,1));
            difficultyLabelMedium.setTextFill(new Color(0,0,0,1));
            difficultyLabelSelect.setTextFill(new Color(0,0,0,1));
            getDiffEasyButton().setStyle("-fx-background-color: rgb(255, 244, 0)");
            getDiffHardButton().setStyle( "-fx-background-color: rgb(255, 244, 0)");
            getDiffMediumButton().setStyle("-fx-background-color: #fff400");
        }

        cloudsAnimation();

        getDiffEasyButton().setOnAction(event -> {
            try {
                onButtonClickForward(event);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        getDiffHardButton().setOnAction(event -> {
            try {
                onButtonClickForward(event);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        getDiffMediumButton().setOnAction(event -> {
            try {
                onButtonClickForward(event);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        getBackToMe().setOnAction(event -> {
            try {
                onBackToMeClick();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

    }
}
