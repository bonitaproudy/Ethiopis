package sample;

import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
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
        itemsTransition.schedule(new TimerTask() {
            @Override
            public void run() {
                Platform.runLater(() -> {
                    if (getTree().getLayoutX() >= 22){
                        getCloud1().setTranslateX((getCloud1().getLayoutX()) - 20);
                        getCloud1().setLayoutX((getCloud1().getLayoutX()) - 20);
                        getCloud2().setTranslateX(getCloud2().getLayoutX()-20);
                        getCloud2().setLayoutX(getCloud2().getLayoutX()-20);
                        getCloud3().setTranslateX(getCloud3().getLayoutX()-20);
                        getCloud3().setLayoutX(getCloud3().getLayoutX()-20);
                        getSheep().setTranslateX(getSheep().getLayoutX()-20);
                        getSheep().setLayoutX(getSheep().getLayoutX()-20);
                        getBirds().setTranslateX(getBirds().getLayoutX()-20);
                        getBirds().setLayoutX(getBirds().getLayoutX()-20);
                        getTree().setTranslateX(getTree().getLayoutX()-20);
                        getTree().setLayoutX(getTree().getLayoutX()-20);
                        getGrass1().setTranslateX(getGrass1().getLayoutX()-20);
                        getGrass1().setLayoutX(getGrass1().getLayoutX()-20);
                        getGrass2().setTranslateX(getGrass2().getLayoutX()-20);
                        getGrass2().setLayoutX(getGrass2().getLayoutX()-20);
                        getGrass3().setTranslateX(getGrass3().getLayoutX()-20);
                        getGrass3().setLayoutX(getGrass3().getLayoutX()-20);
                        getGrass4().setTranslateX(getGrass4().getLayoutX()-20);
                        getGrass4().setLayoutX(getGrass4().getLayoutX()-20);
                        getGrass5().setTranslateX(getGrass5().getLayoutX()-20);
                        getGrass5().setLayoutX(getGrass5().getLayoutX()-20);
                        getGrass6().setTranslateX(getGrass6().getLayoutX()-20);
                        getGrass6().setLayoutX(getGrass6().getLayoutX()-20);
                        getSunShine().setTranslateX(getSunShine().getLayoutX()-20);
                        getSunShine().setLayoutX(getSunShine().getLayoutX()-20);
                        transitionCounter++;
                    }
                    else{
                        this.cancel();
                    }
                });
            }
        }, 0, 50);
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
        },750);
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        itemsTransiton();
        //getCloud2().setTranslateY(450);
        transitionTimer();
    }
}
