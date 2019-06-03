package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ScoresController implements Initializable {
    //buttons declaration
    private Stage primaryStage;
    public Button playOn;
    public Button backToMenu;
    //other elements declaration
    public BorderPane skyClassScores;
    public HBox grassClassScores;
    public Label winOrLose;
    public ImageView winLoseImage;

    public Button getBackToMenu() {
        return backToMenu;
    }

    public Button getPlayOn() {
        return playOn;
    }

    public BorderPane getSkyClassScores() {
        return skyClassScores;
    }

    public Label getWinOrLose() {
        return winOrLose;
    }

    public HBox getGrassClassScores() {
        return grassClassScores;
    }

    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    public ImageView getWinLoseImage() {
        return winLoseImage;
    }
    public void ifWin(){
        getWinOrLose().setText("አሸነፍክ...");
        getWinLoseImage().setImage(new Image("resources/win_48px.png"));
    }
    public void iflose(){
        getWinOrLose().setText("ተሸነፍክ...");
        getWinLoseImage().setImage(new Image("resources/sad_48px.png"));
    }
    public void onPlayOnClick() throws IOException {
        if(GameModesListController.gameMode == 1){
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
        else if(GameModesListController.gameMode == 2){
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
        else if(GameModesListController.gameMode == 3){
            FXMLLoader loader = new FXMLLoader(getClass().getResource("rearrangeQuiz.fxml"));
            RearrangeQuizController controller = new RearrangeQuizController();
            loader.setController(controller);
            Parent root = null;
            root = loader.load();
            Scene scene = new Scene(root, 600, 400);
            controller.setPrimaryStage(primaryStage);
            primaryStage.setScene(scene);
            primaryStage.setResizable(false);
            primaryStage.show();
        }
    }
    public void onBackToMenuClick() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("choicesmenu.fxml"));
        ChoicesMenuController controller = new ChoicesMenuController();
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
        getWinOrLose().setStyle("-fx-font-size: 35");
        if(Controller.nightModeStatus == 1){
            new Controller().setNightMode(skyClassScores,grassClassScores,"IMAGE");
            getBackToMenu().setStyle("-fx-background-color: rgba(200, 200, 200,1)");
            getPlayOn().setStyle("-fx-background-color: rgba(200, 200, 200,1)");
            getWinOrLose().setTextFill(new Color(1,1,1,1));
        }
        else{
            new Controller().setDayMode(skyClassScores,grassClassScores,"IMAGE");
            getPlayOn().setStyle("-fx-background-color: rgb(255, 244, 0)");
            getBackToMenu().setStyle( "-fx-background-color: rgb(255, 244, 0)");
        }
        if(Controller.win == 0){
            iflose();
        }
        else if (Controller.win == 1){
            ifWin();
        }
        getBackToMenu().setOnAction(event -> {
            try {
                onBackToMenuClick();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        getPlayOn().setOnAction(event -> {
            try {
                onPlayOnClick();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
}
