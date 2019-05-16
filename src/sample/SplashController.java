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
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;


import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;

public class SplashController implements Initializable {
    //Universal Declarations
    Timer loopTheEarth = new Timer();


    public BorderPane splashPane;
    public ImageView theEarth;
    public Button pressKey;

    public Button getPressKey() {
        return pressKey;
    }

    public BorderPane getSplashPane() {
        return splashPane;
    }

    public ImageView getTheEarth() {
        return theEarth;
    }

//    public  void toTheClouds() throws IOException{
//        Parent toCloudsPage =  FXMLLoader.load(getClass().getResource("theClouds.fxml"));
//        Scene theClouds = new Scene(toCloudsPage);
//        Stage theCloudsStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
//        theCloudsStage.setScene(theClouds);
//        theCloudseStage.show();
//    }
    public void animations() {
        loopTheEarth.schedule(new TimerTask() {
            @Override
            public void run() {
                if(theEarth.getFitWidth()>=1200){
                    getPressKey().setVisible(true);
                    this.notifyAll();
                    this.cancel();
                }
                Platform.runLater(() -> {
                    theEarth.setFitHeight(theEarth.getFitHeight()+10);
                    System.out.println(theEarth.getFitHeight());
                    theEarth.setFitWidth(theEarth.getFitWidth()+10);
                    System.out.println(theEarth.getFitWidth());
                    getSplashPane().setOpacity(getSplashPane().getOpacity()-0.01);
                });
            }
        }, 1500, 30);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        animations();


    }
}
