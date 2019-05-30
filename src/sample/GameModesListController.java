package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class GameModesListController implements Initializable {
    //universal Declarations
    private Stage primaryStage;
    static int gameMode = 0;
    //button declarations
    public Button diffEasyButton;
    public Button diffHardButton;
    public Button diffMediumButton;
    public Button imageQuizButton;
    public Button matchingQuizButton;
    public Button choiceQuizButton;
    public Button backToMe;
    //other element declaration
    public BorderPane skyClassGML;
    public HBox grassClassGML;
    public Label difficultyLabelEasy;
    public Label difficultyLabelMedium;
    public Label difficultyLabelHard;
    public Label difficultyLabelSelect;

    //getters and setters

    public Button getImageQuizButton() {
        return imageQuizButton;
    }

    public Button getDiffMediumButton() {
        return diffMediumButton;
    }

    public Button getDiffHardButton() {
        return diffHardButton;
    }

    public Button getDiffEasyButton() {
        return diffEasyButton;
    }

    public BorderPane getSkyClassGML() {
        return skyClassGML;
    }

    public Button getChoiceQuizButton() {
        return choiceQuizButton;
    }

    public Button getMatchingQuizButton() {
        return matchingQuizButton;
    }

    public Button getBackToMe() {
        return backToMe;
    }

    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    public HBox getGrassClassGML() {
        return grassClassGML;
    }
    public void onBackToMeClick() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("choicesMenu.fxml"));
        ChoicesMenuController controller = new ChoicesMenuController();
        loader.setController(controller);
        Parent root = loader.load();
        Scene scene = new Scene(root, 600, 400);
        controller.setPrimaryStage(primaryStage);
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    public void onImageQuizButtonClick() throws IOException {
        gameMode = 1;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("imageQuiz.fxml"));
        ImageQuizController controller = new ImageQuizController();
        loader.setController(controller);
        Parent root = loader.load();
        Scene scene = new Scene(root, 600, 400);
        controller.setPrimaryStage(primaryStage);
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }
    public void onChoiceQuizButtonClick() throws IOException{
        gameMode = 3;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("choiceQuiz.fxml"));
        ChoiceQuizController controller = new ChoiceQuizController();
        loader.setController(controller);
        Parent root = loader.load();
        Scene scene = new Scene(root, 600, 400);
        controller.setPrimaryStage(primaryStage);
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }
    public void onMatchingQuizButtonClick()throws IOException{
        gameMode = 2;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("matchingQuiz.fxml"));
        MatchingQuizController controller = new MatchingQuizController();
        loader.setController(controller);
        Parent root = loader.load();
        Scene scene = new Scene(root, 600, 400);
        controller.setPrimaryStage(primaryStage);
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if(Controller.nightModeStatus == 1){
            new Controller().setNightMode(skyClassGML,grassClassGML,"GML");
            getChoiceQuizButton().setStyle("-fx-background-color: rgba(200, 200, 200,1)");
            getImageQuizButton().setStyle("-fx-background-color: rgba(200, 200, 200,1)");
            getMatchingQuizButton().setStyle("-fx-background-color: rgba(200, 200, 200,1)");
            getDiffEasyButton().setStyle("-fx-background-color: rgba(200, 200, 200,1)");
            getDiffMediumButton().setStyle("-fx-background-color: rgba(200, 200, 200,1)");
            getDiffHardButton().setStyle("-fx-background-color: rgba(200, 200, 200,1)");
            difficultyLabelEasy.setTextFill(new Color(1,1,1,1));
            difficultyLabelHard.setTextFill(new Color(1,1,1,1));
            difficultyLabelMedium.setTextFill(new Color(1,1,1,1));
            difficultyLabelSelect.setTextFill(new Color(1,1,1,1));
        }
        else{
            new Controller().setDayMode(skyClassGML,grassClassGML,"GML");
            difficultyLabelEasy.setTextFill(new Color(0,0,0,1));
            difficultyLabelHard.setTextFill(new Color(0,0,0,1));
            difficultyLabelMedium.setTextFill(new Color(0,0,0,1));
            difficultyLabelSelect.setTextFill(new Color(0,0,0,1));
        }
        getImageQuizButton().setOnAction(event -> {
            try {
                onImageQuizButtonClick();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        getMatchingQuizButton().setOnAction(event -> {
            try {
                onMatchingQuizButtonClick();
            } catch (IOException | NullPointerException e) {
                e.printStackTrace();
            }
        });
        getChoiceQuizButton().setOnAction(event -> {
            try {
                onChoiceQuizButtonClick();
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
