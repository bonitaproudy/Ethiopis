package sample;

import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;

public class SampleTransitionController implements Initializable {
    //universal declarations
    private Stage primaryStage = new Stage();
    Timer transitiontime = new Timer();
    Timer itemsTransition = new Timer();
    int transitionCounter = 0;
    //elements declaration
    public ImageView tree;
    public ImageView birds;
    public ImageView cloud1;
    public ImageView cloud2;
    public ImageView cloud3;
    public ImageView sunShine;
    public ImageView sheep;
    public ImageView grass1;
    public ImageView grass2;
    public ImageView grass3;
    public ImageView grass4;
    public ImageView grass5;
    public ImageView grass6;
    public ImageView rankPodium;
    public Button diffMediumButton;
    public Button diffHardButton;
    public Button diffEasyButton;
    public Label difficultyLabelHard;
    public Label difficultyLabelEasy;
    public Label difficultyLabelMedium;
    public Label difficultyLabelSelect;
    public BorderPane skyClassTransition;
    public HBox grassClassTransition;

    public ImageView getRankPodium() {
        return rankPodium;
    }

    public BorderPane getSkyClassTransition() {
        return skyClassTransition;
    }

    public HBox getGrassClassTransition() {
        return grassClassTransition;
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

    public Label getDifficultyLabelEasy() {
        return difficultyLabelEasy;
    }

    public Label getDifficultyLabelHard() {
        return difficultyLabelHard;
    }

    public Label getDifficultyLabelMedium() {
        return difficultyLabelMedium;
    }

    public Label getDifficultyLabelSelect() {
        return difficultyLabelSelect;
    }

    public ImageView getTree() {
        return tree;
    }

    public ImageView getSheep() {
        return sheep;
    }

    public ImageView getBirds() {
        return birds;
    }

    public ImageView getCloud3() {
        return cloud3;
    }

    public ImageView getCloud2() {
        return cloud2;
    }

    public ImageView getCloud1() {
        return cloud1;
    }

    public ImageView getGrass1() {
        return grass1;
    }

    public Stage getPrimaryStage() {
        return primaryStage;
    }

    public ImageView getGrass2() {
        return grass2;
    }

    public ImageView getGrass3() {
        return grass3;
    }

    public ImageView getGrass4() {
        return grass4;
    }

    public ImageView getGrass5() {
        return grass5;
    }

    public ImageView getGrass6() {
        return grass6;
    }

    public ImageView getSunShine() {
        return sunShine;
    }

    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }
    public void itemsTransiton(){
        System.out.println(getTree().getLayoutX());
        itemsTransition.schedule(new TimerTask() {
            @Override
            public void run() {
                Platform.runLater(() -> {
                    if (getTree().getLayoutX()>= 302){
                        getCloud1().setTranslateX((getCloud1().getTranslateX()) - 20);
                        getCloud1().setLayoutX((getCloud1().getLayoutX()) - 20);
                        getCloud2().setTranslateX(getCloud2().getTranslateX()-20);
                        getCloud2().setLayoutX(getCloud2().getLayoutX()-20);
                        getCloud3().setTranslateX(getCloud3().getTranslateX()-20);
                        getCloud3().setLayoutX(getCloud3().getLayoutX()-20);
                        getSheep().setTranslateX(getSheep().getTranslateX()-20);
                        getSheep().setLayoutX(getSheep().getLayoutX()-20);
                        getBirds().setTranslateX(getBirds().getTranslateX()-20);
                        getBirds().setLayoutX(getBirds().getLayoutX()-20);
                        getTree().setTranslateX(getTree().getTranslateX()-20);
                        getTree().setLayoutX(getTree().getLayoutX()-20);
                        System.out.println(getTree().getLayoutX());
                        getGrass1().setTranslateX(getGrass1().getTranslateX()-20);
                        getGrass1().setLayoutX(getGrass1().getLayoutX()-20);
                        getGrass2().setTranslateX(getGrass2().getTranslateX()-20);
                        getGrass2().setLayoutX(getGrass2().getLayoutX()-20);
                        getGrass3().setTranslateX(getGrass3().getTranslateX()-20);
                        getGrass3().setLayoutX(getGrass3().getLayoutX()-20);
                        getGrass4().setTranslateX(getGrass4().getTranslateX()-20);
                        getGrass4().setLayoutX(getGrass4().getLayoutX()-20);
                        getGrass5().setTranslateX(getGrass5().getTranslateX()-20);
                        getGrass5().setLayoutX(getGrass5().getLayoutX()-20);
                        getGrass6().setTranslateX(getGrass6().getTranslateX()-20);
                        getGrass6().setLayoutX(getGrass6().getLayoutX()-20);
                        getDiffEasyButton().setTranslateX(getDiffEasyButton().getTranslateX()-20);
                        getDiffEasyButton().setLayoutX(getDiffEasyButton().getLayoutX()-20);
                        getDiffHardButton().setTranslateX(getDiffHardButton().getTranslateX()-20);
                        getDiffHardButton().setLayoutX(getDiffHardButton().getLayoutX()-20);
                        getDiffMediumButton().setTranslateX(getDiffMediumButton().getTranslateX()-20);
                        getDiffMediumButton().setLayoutX(getDiffMediumButton().getLayoutX()-20);
                        getDifficultyLabelEasy().setTranslateX(getDifficultyLabelEasy().getTranslateX()-20);
                        getDifficultyLabelEasy().setLayoutX(getDifficultyLabelEasy().getLayoutX()-20);
                        getDifficultyLabelMedium().setTranslateX(getDifficultyLabelMedium().getTranslateX()-20);
                        getDifficultyLabelMedium().setLayoutX(getDifficultyLabelMedium().getLayoutX()-20);
                        getDifficultyLabelHard().setTranslateX(getDifficultyLabelHard().getTranslateX()-20);
                        getDifficultyLabelHard().setLayoutX(getDifficultyLabelHard().getLayoutX()-20);
                        getDifficultyLabelSelect().setTranslateX(getDifficultyLabelSelect().getTranslateX()-20);
                        getDifficultyLabelSelect().setLayoutX(getDifficultyLabelSelect().getLayoutX()-20);
                        getRankPodium().setTranslateX(getRankPodium().getTranslateX()-20);
                        getRankPodium().setLayoutX(getRankPodium().getLayoutX()-20);
                        getSunShine().setTranslateX(getSunShine().getTranslateX()-20);
                        getSunShine().setLayoutX(getSunShine().getLayoutX()-20);
                        transitionCounter++;
                    }
                    else{
                        this.cancel();
                    }
                });
            }
        }, 0, 100);
    }
    public void transitionTimer(){
        transitiontime.schedule(new TimerTask() {
            @Override
            public void run() {
                Platform.runLater(()->{

                    FXMLLoader loader = new FXMLLoader(getClass().getResource("choicesMenu.fxml"));
                    ChoicesMenuController controller = new ChoicesMenuController();
                    loader.setController(controller);
                    Parent root = null;
                    try {
                        root = loader.load();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    Scene scene = new Scene(root, 600, 400);
                    controller.setPrimaryStage(primaryStage);
                    primaryStage.setScene(scene);
                    primaryStage.setResizable(false);
                    primaryStage.show();
                });
            }
        },600);
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if(Controller.nightModeStatus == 1){
            new Controller().setNightMode(getSkyClassTransition(),getGrassClassTransition(),"TRANSITION");
            difficultyLabelEasy.setTextFill(new Color(1,1,1,1));
            difficultyLabelHard.setTextFill(new Color(1,1,1,1));
            difficultyLabelMedium.setTextFill(new Color(1,1,1,1));
            difficultyLabelSelect.setTextFill(new Color(1,1,1,1));
            getDiffEasyButton().setStyle("-fx-background-color: rgba(200, 200, 200,1)");
            getDiffHardButton().setStyle("-fx-background-color: rgba(200, 200, 200,1)");
            getDiffMediumButton().setStyle("-fx-background-color: rgba(200, 200, 200,1)");
            getSunShine().setImage(new Image("/resources/full_moon_100px.png"));
            getSunShine().setFitWidth(150);
            getSunShine().setFitHeight(150);
            getSunShine().setTranslateY(30);
            getSunShine().setTranslateX(30);
            getSunShine().setStyle("-fx-background-size: cover");
        }
        else{
            new Controller().setDayMode(getSkyClassTransition(),getGrassClassTransition(),"TRANSITION");
            difficultyLabelEasy.setTextFill(new Color(0,0,0,1));
            difficultyLabelHard.setTextFill(new Color(0,0,0,1));
            difficultyLabelMedium.setTextFill(new Color(0,0,0,1));
            difficultyLabelSelect.setTextFill(new Color(0,0,0,1));
            getDiffEasyButton().setStyle("-fx-background-color: rgb(255, 244, 0)");
            getDiffHardButton().setStyle( "-fx-background-color: rgb(255, 244, 0)");
            getDiffMediumButton().setStyle("-fx-background-color: #fff400");
            getSunShine().setImage(new Image("/resources/sunshine-icon-32866.png"));
            getSunShine().setFitWidth(198);
            getSunShine().setFitHeight(200);
            getSunShine().setTranslateY(0);
            getSunShine().setTranslateX(0);
            getSunShine().setStyle("-fx-background-size: cover");
        }
        itemsTransiton();
        //getCloud2().setTranslateY(450);
        transitionTimer();
    }
}
