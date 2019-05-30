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
import javafx.stage.StageStyle;


import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;

public class SplashController implements Initializable {
    private Stage primaryStage;
    //Universal Declarations
    Timer loopTheEarth = new Timer();

    public Button justForCodeButton;
    public BorderPane splashPane;
    public ImageView theEarth;

    public Button getJustForCodeButton() {
        return justForCodeButton;
    }

    public BorderPane getSplashPane() {
        return splashPane;
    }

    public ImageView getTheEarth() {
        return theEarth;
    }


    public void animations() {
        loopTheEarth.schedule(new TimerTask() {
            @Override
            public boolean cancel() {
                Platform.runLater(() -> {
                    try {
                        clickedToNextPage();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
                return super.cancel();
            }

            @Override
            public void run() {
                if(theEarth.getFitWidth()>=1000){
                    this.cancel();
                }
                Platform.runLater(() -> {
                    theEarth.setFitHeight(theEarth.getFitHeight()+10);
                    theEarth.setFitWidth(theEarth.getFitWidth()+10);
                    getSplashPane().setOpacity(getSplashPane().getOpacity()-0.01);
                });
            }
        }, 1000, 10);
    }

    public Stage getPrimaryStage() {
        return primaryStage;
    }

    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    public void clickedToNextPage() throws IOException{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("theClouds.fxml"));
        TheCloudsController controller = new TheCloudsController();
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
        animations();
    }
}
