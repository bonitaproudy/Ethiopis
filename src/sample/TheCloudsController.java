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
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;

public class TheCloudsController implements Initializable {
    //universal declarations
    private Stage primaryStage;
    Timer translateClouds = new Timer();
    Timer translateAnykey = new Timer();
    double counter = 0;
    double sizeOfAnykey;

    public Button anyKey = new Button();

    public ImageView motionCloud1;
    public ImageView motionCloud2;
    public ImageView motionCloud3;
    public ImageView motionCloud4;
    public AnchorPane splashPane;

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

    public AnchorPane getSplashPane(){return splashPane;}



    public void anyButtonClicked() throws IOException {
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

    public void cloudSplit(){
        translateClouds.schedule(new TimerTask() {
            @Override
            public void run() {
                Platform.runLater(() -> {
                    counter= getMotionCloud1().getLayoutX();
                    if(getMotionCloud1().getLayoutX()>=290 ){
                        try {
                            this.cancel();
                            anyButtonClicked();

                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                    }
                    else if ( getMotionCloud2().getLayoutX()<=-490){
                        try {
                            this.cancel();
                            anyButtonClicked();

                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    else if (getMotionCloud3().getLayoutX()>=327){
                        try {
                            this.cancel();
                            anyButtonClicked();

                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    else if (getMotionCloud4().getLayoutX()>=400){
                        try {
                            this.cancel();
                            anyButtonClicked();

                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    getSplashPane().setOpacity(getSplashPane().getOpacity()+0.1);
                    getMotionCloud1().setTranslateX((getMotionCloud1().getLayoutX()) - 2);
                    getMotionCloud1().setLayoutX((getMotionCloud1().getLayoutX()) - 2);
                    getMotionCloud2().setTranslateX((getMotionCloud2().getLayoutX()) - 1);
                    getMotionCloud2().setLayoutX((getMotionCloud2().getLayoutX()) - 1);
                    getMotionCloud3().setTranslateX((getMotionCloud3().getLayoutX()) + 1);
                    getMotionCloud3().setLayoutX((getMotionCloud3().getLayoutX()) + 1);
                    getMotionCloud4().setTranslateX((getMotionCloud3().getLayoutX()-125) + 2);
                    getMotionCloud4().setLayoutX((getMotionCloud3().getLayoutX()-125) + 2);
                });
            }
        }, 100, 30);

    }

    public void buttonTranform(){
        translateAnykey.schedule(new TimerTask() {
            @Override
            public void run() {
                Platform.runLater(() -> {
                    getAnyKey().setOpacity(getAnyKey().getOpacity()+0.01);
                });
            }
        }, 500, 10);

    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        getAnyKey().setFont(Font.font(1.5));
        getAnyKey().setOpacity(0.1);
        cloudSplit();
        buttonTranform();
    }

    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }
}
