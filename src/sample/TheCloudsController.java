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
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;

public class TheCloudsController implements Initializable {
    Timer translateClouds = new Timer();
    Timer translateAnykey = new Timer();
    double counter = 0;

    public Button anyKey = new Button();

    public ImageView motionCloud1;
    public ImageView motionCloud2;
    public ImageView motionCloud3;
    public ImageView motionCloud4;

    public Button getAnyKey() {
        return anyKey;
    }

    public ImageView getMotionCloud1() {
        return motionCloud1;
    }

    public ImageView getMotionCloud2() {
        return motionCloud2;
    }

    public ImageView getMotionCloud3() {
        return motionCloud3;
    }

    public ImageView getMotionCloud4() {
        return motionCloud4;
    }



    public void anyButtonClicked(Button forClick) throws IOException {
        Parent toMainfromSplash = FXMLLoader.load(getClass().getResource("sample.fxml"));
        Scene splashScene = new Scene(toMainfromSplash);
        Stage splashStage = (Stage) ((forClick)).getScene().getWindow();
        splashStage.setScene(splashScene);
        splashStage.show();
    }

    public void cloudSplit(){
        translateClouds.schedule(new TimerTask() {
            @Override
            public void run() {
                Platform.runLater(() -> {
                    counter= getMotionCloud1().getLayoutX();
                    if(getMotionCloud1().getLayoutX()>=290){
                        this.cancel();
                    }
                    else if ( getMotionCloud2().getLayoutX()<=-490){
                        this.cancel();
                    }
                    else if (getMotionCloud3().getLayoutX()>=327){
                        this.cancel();
                    }
                    else if (getMotionCloud3().getLayoutX()>=327){
                        this.cancel();
                    }

                    getMotionCloud1().setTranslateX((getMotionCloud1().getLayoutX()) - 1);
                    getMotionCloud1().setLayoutX((getMotionCloud1().getLayoutX()) - 1);
                    getMotionCloud2().setTranslateX((getMotionCloud2().getLayoutX()) - 0.6);
                    getMotionCloud2().setLayoutX((getMotionCloud2().getLayoutX()) - 0.6);
                    getMotionCloud3().setTranslateX((getMotionCloud3().getLayoutX()) + 0.6);
                    getMotionCloud3().setLayoutX((getMotionCloud3().getLayoutX()) + 0.6);
                    getMotionCloud4().setTranslateX((getMotionCloud3().getLayoutX()) + 1);
                    getMotionCloud4().setLayoutX((getMotionCloud3().getLayoutX()) + 1);

                    System.out.println(getMotionCloud1().getLayoutX());
                });
            }
        }, 100, 30);

    }

    public void buttonTranform(){

        translateAnykey.schedule(new TimerTask() {
            @Override
            public void run() {
                Platform.runLater(() -> {
                    if(getAnyKey().getFont().getSize() <=30)
                        getAnyKey().setFont(Font.font(getAnyKey().getFont().getSize()+0.3));
//                    if(getAnyKey().getPrefHeight()<100)
//                        getAnyKey().setPrefHeight(getAnyKey().getPrefHeight()+1);
//                    if(getAnyKey().getPrefWidth()<400)
//                        getAnyKey().setPrefWidth(getAnyKey().getPrefWidth()+1);
                    getAnyKey().setOpacity(getAnyKey().getOpacity()+0.01);
                });
            }
        }, 500, 10);

    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {

        //getAnyKey().setPrefSize(100,25);
        getAnyKey().setFont(Font.font(1.5));
        getAnyKey().setOpacity(0.1);
        cloudSplit();
        buttonTranform();
        try {
//            getAnyKey().fire();
            anyButtonClicked(getAnyKey());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
