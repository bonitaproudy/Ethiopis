package sample;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;

public class Controller implements Initializable {
    //Global declarations
    Timer iterateClouds = new Timer();
    double counter=0;
    //buttons
    public Button playbutton;
    public Button externalsettings;
    public Button externalmute;
    public Button mainexit;
    //clouds and other floaters
    public ImageView cloud1;
    public ImageView cloud2;
    public ImageView cloud3;
    public ImageView sunshine;
    public ImageView birds;
    public ImageView tree;
    public ImageView sheep;

    //getters
    public ImageView getCloud1() {
        return cloud1;
    }

    public ImageView getCloud2() {
        return cloud2;
    }

    public ImageView getCloud3() {
        return cloud3;
    }

    public ImageView getBirds() {
        return birds;
    }

    public ImageView getSheep() {
        return sheep;
    }

    public ImageView getSunshine() {
        return sunshine;
    }

    public ImageView getTree() {
        return tree;
    }

    public Button getMainexit() {
        return mainexit;
    }

    public Button getExternalmute() {
        return externalmute;
    }

    public Button getExternalsettings() {
        return externalsettings;
    }

    public Button getPlaybutton() {
        return playbutton;
    }

    //makes the playme button (when clicked) take one to the next page
    public void onPlayButtonClick (ActionEvent event) throws IOException {



        Parent toChoicesMenuPage =  FXMLLoader.load(getClass().getResource("choicesMenu.fxml"));
        Scene choicesMenu = new Scene(toChoicesMenuPage);
        Stage choicesMenuStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        choicesMenuStage.setScene(choicesMenu);
        choicesMenuStage.show();


    }
    public void onExitButtonClick(){
     getMainexit().setOnAction(event -> System.exit(0));
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //this is all me  trying to make an animation

        iterateClouds.schedule(new TimerTask() {
                @Override
                public void run() {
                    Platform.runLater(() -> {
                        counter= getCloud3().getLayoutX();
                        if(getCloud1().getLayoutX()>=290){
                            getCloud1().setLayoutX(-110);
                        }
                        else if ( getCloud2().getLayoutX()>=400){
                            getCloud2().setLayoutX(-110);
                        }
                        else if (getCloud3().getLayoutX()>=327){
                            getCloud3().setLayoutX(-110);
                        }

                        getCloud1().setTranslateX((getCloud1().getLayoutX()) + 1);
                        getCloud1().setLayoutX((getCloud1().getLayoutX()) + 1);
                        getCloud2().setTranslateX((getCloud2().getLayoutX()) + 1);
                        getCloud2().setLayoutX((getCloud2().getLayoutX()) + 1);
                        getCloud3().setTranslateX((getCloud3().getLayoutX()) + 0.6);
                        getCloud3().setLayoutX((getCloud3().getLayoutX()) + 0.6);
                        System.out.println(getCloud1().getLayoutX());
                    });
                }
            }, 100, 30);

    }
}
