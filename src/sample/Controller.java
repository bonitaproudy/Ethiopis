package sample;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Point3D;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Popup;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;

public class Controller implements Initializable {
    //Global declarations
    private Stage primaryStage;
    Timer settingsPlayTimer = new Timer();
    Timer iterateClouds = new Timer();
    double counter=0;
    public int muteStatus = 0;
    static int nightModeStatus = 0;
    public static int win = 0;
    double clickedNumber = 4;
    Image muted = new Image("resources/unmute-toggle.png",30,30,false,true);
    Image notMuted = new Image("resources/mute-toggle.png",30,30,false,true);
    final ContextMenu popup = new ContextMenu();
    MenuItem help = new MenuItem("help");
    MenuItem about = new MenuItem("about");
    //buttons
    public Button playbutton;
    public Button externalsettings;
    public Button externalmute;
    public Button mainexit;
    public Button nightMode;
    //clouds and other floaters
    public ImageView cloud1;
    public ImageView cloud2;
    public ImageView cloud3;
    public ImageView sunshine;
    public ImageView birds;
    public ImageView tree;
    public ImageView sheep;
    //containers
    public BorderPane skyClassSample;
    public HBox grassClassSample;

    //getters
    public Stage getPrimaryStage(){return primaryStage;}

    public MenuItem getHelp() {
        return help;
    }

    public MenuItem getAbout() {
        return about;
    }

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

    public Button getNightMode() {
        return nightMode;
    }

    public BorderPane getSkyClassSample() {
        return skyClassSample;
    }

    public HBox getGrassClassSample() {
        return grassClassSample;
    }

    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }
    //setup initial looks of elements

    public void onExternalSettingsClicked(double clicked){
        Popup setting = new Popup();
        setting.setX(400);
        setting.setY(350);
        setting.setHeight(150);
        setting.setWidth(150);
        VBox settingsPane = new VBox(20);
        settingsPane.setPrefHeight(100);
        settingsPane.setPrefWidth(100);
        if(nightModeStatus == 0){
            settingsPane.setStyle("-fx-background-color: #22ff1a;"  + "-fx-border-radius: 10;" + "-fx-border:10 solid #aaa;");
        }
        else if(nightModeStatus == 1){
            settingsPane.setStyle("-fx-background-color: rgba(15,84,16,0.9);"  + "-fx-border-radius: 10;" + "-fx-border:10 solid #aaa;");
        }
        settingsPane.setAlignment(Pos.CENTER);
        Button aboutButton = new Button("ስለኛ");
        aboutButton.setStyle(" -fx-text-align:center;" + "-fx-text-decoration:bold;" + "-fx-border:1.5px solid #aaa;" + "-fx-border-radius:30px;" + "-fx-background-color: rgb(255, 244, 0);" + "-fx-overflow:hidden;" + "-fx-font-family: \"Nyala\";" + "-fx-font-size: 20;" + "-fx-font-style: Bold;");
        Label versionLabel = new Label("ethiopis Version 1.0.4");
        settingsPane.getChildren().addAll(aboutButton,versionLabel);
        setting.getContent().addAll(settingsPane);
        about.setOnAction(event -> {
            Popup aboutPopup = new Popup();
            aboutPopup.setX(400);
            aboutPopup.setY(350);
            aboutButton.setPrefHeight(150);
            aboutButton.setPrefWidth(150);
            VBox aboutPopupPane = new VBox(20);
            Label aboutUs = new Label("ይሄ መተግበሪያ የተሰራው በ: \n\t\t\t-ብሩክ መዝገቡ \n\t\t\t-በረከት ተረፈ \n\t\t\t-አብርሃም ትርፌ \n\t\t\t-ቦንቱ ግርማ \n\t\t\t-ብስራት በትረ  \n\t\t\t-ብሩክ ጌታቸው ");
            aboutPopupPane.setStyle("-fx-background-color: rgba(15,84,16,0.9);"  + "-fx-border-radius: 10;" + "-fx-border:10 solid #aaa;");
            aboutPopupPane.getChildren().addAll(aboutUs);
            aboutPopup.getContent().addAll(aboutPopupPane);
            aboutPopup.show(primaryStage);
            aboutPopup.setAutoHide(true);
        });
        setting.show(primaryStage);
        if (clicked%2 == 0){
            System.out.println("nothidden");
        }
        else{
            settingsPlayTimer.schedule(new TimerTask() {
                @Override
                public void run() {
                    Platform.runLater(() -> setting.hide());
                }
            }, 1000);
            System.out.println("hidden");
        }
    }
    //makes the playme button (when clicked) take one to the next page
    public void onPlayButtonClick () throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("sampleTransition.fxml"));
        SampleTransitionController controller = new SampleTransitionController();
        loader.setController(controller);
        Parent root = loader.load();
        Scene scene = new Scene(root, 600, 400);
        controller.setPrimaryStage(primaryStage);
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }
    public void onMuteButtonClick(){
        if(muteStatus == 0){
            externalmute.setGraphic(new ImageView(muted));
            muteStatus =1;
        }
        else if(muteStatus == 1){
            externalmute.setGraphic(new ImageView(notMuted));
            muteStatus = 0;
        }
    }

    public void cloudsAnimation(){
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
                });
            }
        }, 100, 30);

    }
    public int setNightMode(Pane skyClass, Pane grassClass,String page){
        skyClass.setStyle("-fx-background-color: rgba(37,36,122,1)");
        grassClass.setStyle("-fx-background-color: rgba(15,84,16,0.9)");
       if(page == "SAMPLE"){
           getExternalmute().setStyle("-fx-background-color: rgba(200, 200, 200,1)");
           getExternalsettings().setStyle("-fx-background-color: rgba(200, 200, 200,1)");
           getMainexit().setStyle("-fx-background-color: rgba(200, 200, 200,1)");
           getPlaybutton().setStyle("-fx-background-color: rgba(200, 200, 200,1)");
           getSunshine().setImage(new Image("/resources/full_moon_100px.png"));
           getSunshine().setFitWidth(150);
           getSunshine().setFitHeight(150);
           getSunshine().setTranslateY(30);
           getSunshine().setTranslateX(30);
           getSunshine().setStyle("-fx-background-size: cover");
       }
        nightModeStatus = 1;
       return nightModeStatus;
    }

    public int setDayMode(Pane skyClass, Pane grassClass, String page){
        skyClass.setStyle("-fx-background-color: rgba(0,241,255,0.6)");
        grassClass.setStyle("-fx-background-color: #22ff1a");
        if(page =="SAMPLE"){
            getExternalmute().setStyle("-fx-background-color: rgb(255, 244, 0)");
            getExternalsettings().setStyle("-fx-background-color: rgb(255, 244, 0)");
            getMainexit().setStyle( "-fx-background-color: rgb(255, 244, 0)");
            getPlaybutton().setStyle("-fx-background-color: #fff400");
            getSunshine().setImage(new Image("/resources/sunshine-icon-32866.png"));
            getSunshine().setFitWidth(198);
            getSunshine().setFitHeight(200);
            getSunshine().setTranslateY(0);
            getSunshine().setTranslateX(0);
            getSunshine().setStyle("-fx-background-size: cover");
        }
        nightModeStatus = 0;
        return  nightModeStatus;
    }
    public void onNightModeClick(){
        if(nightModeStatus == 0){
            setNightMode(getSkyClassSample(),getGrassClassSample(),"SAMPLE");
        }
        else if(nightModeStatus == 1){
            setDayMode(getSkyClassSample(),getGrassClassSample(),"SAMPLE");
        }

    }
    public void onExitButtonClick(){
     getMainexit().setOnAction(event -> System.exit(0));
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        /*try {
            MusicClass.getInstance();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }*/
        getExternalsettings().setOnAction(event -> {
            onExternalSettingsClicked(clickedNumber);
            clickedNumber++;
        } );

        getExternalmute().setOnAction(event -> onMuteButtonClick());
        getPlaybutton().setOnAction(event -> {
            try {
                onPlayButtonClick();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        getMainexit().setOnAction(event -> onExitButtonClick());
        getNightMode().setOnAction(event -> onNightModeClick());
        Tooltip exitTwice = new Tooltip("ለመውጣት ደግምው ይጫኑ...");
        exitTwice.setShowDelay(new Duration(10));
        exitTwice.setFont(new Font("Nyala",12));
        if(nightModeStatus == 0){
            setDayMode(getSkyClassSample(),getGrassClassSample(),"SAMPLE");
        }
        else{
            setNightMode(getSkyClassSample(),getGrassClassSample(),"SAMPLE");
        }

        mainexit.setTooltip(exitTwice);
        getExternalmute().setGraphic(new ImageView(notMuted));
        cloudsAnimation();
    }

}
