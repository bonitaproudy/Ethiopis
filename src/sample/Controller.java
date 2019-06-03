package sample;

import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.text.Font;
import javafx.stage.Popup;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.File;
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
    Timer playButtonMotion = new Timer();
    double counter=0;
    public int muteStatus = 0;
    static int nightModeStatus = 0;
    public static int win = 0;
    double clickedNumber = 4;
    Image muted = new Image("resources/unmute-toggle.png",30,30,false,true);
    Image notMuted = new Image("resources/mute-toggle.png",30,30,false,true);
    Timer aboutUsTimer = new Timer();
    static boolean isPlaying;
    static MediaPlayer player;

    //buttons
    public Button playbutton;
    public Button externalmute;
    public Button mainexit;
    public Button nightMode;
    public Button singlePlayer;
    public Button multiPlayer;
    public MenuButton externalsettings;
    public MenuItem aboutUsButton;
    public MenuItem aboutUsLabel;

    //clouds and other floaters
    public ImageView cloud1;
    public ImageView cloud2;
    public ImageView cloud3;
    public ImageView sunshine;
    public ImageView birds;
    public ImageView tree;
    public ImageView sheep;

    //containers
    public BorderPane fullCanvas;
    public BorderPane skyClassSample;
    public HBox grassClassSample;

    //getters
    public Stage getPrimaryStage(){return primaryStage;}

    public MenuItem getAboutUsButton() {
        return aboutUsButton;
    }

    public MenuItem getAboutUsLabel() {
        return aboutUsLabel;
    }

    public MenuButton getExternalsettings() {
        return externalsettings;
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

    public Button getPlaybutton() {
        return playbutton;
    }

    public Button getNightMode() {
        return nightMode;
    }

    public Button getSinglePlayer () { return singlePlayer;}

    public Button getMultiPlayer() {
        return multiPlayer;
    }

    public BorderPane getFullCanvas() {
        return fullCanvas;
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
    //makes the playme button (when clicked) take one to the next page
    public void onPlayButtonClick (int waywards) throws IOException {
        if (waywards == 1){
            iterateClouds.schedule(new TimerTask() {
                @Override
                public void run() {
                    Platform.runLater(() -> {
                        if (getPlaybutton().getTranslateY() >= -20){
                            getPlaybutton().setTranslateY((getPlaybutton().getTranslateY()) - 1);
                            getPlaybutton().setLayoutY((getPlaybutton().getLayoutY()) - 1);
                        }
                        else{
                            this.cancel();
                        }
                    });
                }
            }, 0, 10);
            getSinglePlayer().setVisible(true);
            getMultiPlayer().setVisible(true);
        }
        else if(waywards == 0){
            getSinglePlayer().setVisible(false);
            getMultiPlayer().setVisible(false);
            iterateClouds.schedule(new TimerTask() {
                @Override
                public void run() {
                    Platform.runLater(() -> {
                        if (getPlaybutton().getTranslateY() <= 0){
                            getPlaybutton().setTranslateY((getPlaybutton().getTranslateY()) + 1);
                            getPlaybutton().setLayoutY((getPlaybutton().getLayoutY()) + 1);
                        }
                        else{
                            this.cancel();
                        }
                    });
                }
            }, 0, 10);
        }
    }
    public void onSinglePlayerClicked () throws IOException {
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
    public void onMultiplayerClicked () throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("serverClient.fxml"));
        ServerClientController controller = new ServerClientController();
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
        if (isPlaying) {
            isPlaying = false;
            player.stop();
        }
        else {
            isPlaying = true;
            player.play();

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
           getSinglePlayer().setStyle("-fx-background-color: rgba(200, 200, 200,1)");
           getMultiPlayer().setStyle("-fx-background-color: rgba(200, 200, 200,1)");
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
            getSinglePlayer().setStyle("-fx-background-color: #fff400");
            getMultiPlayer().setStyle("-fx-background-color: #fff400");
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

    public void musicPlay(){

    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Thread musicThread = new Thread(() ->{
            try {
                String uriString = new File("C:\\Users\\rav3n\\Documents\\GitHub\\Ethiopis\\src\\resources\\02-title-theme.mp3").toURI().toURL().toString();
                player= new MediaPlayer(new Media(uriString));
                if (!isPlaying){
                    player.play();
                    isPlaying = true;
                }
                System.out.println("finished play... apearantly.");
                Thread.sleep(10);
            } catch (MalformedURLException | InterruptedException e) {
                e.printStackTrace();
                player.stop();
            }
        });
        musicThread.start();
        getExternalmute().setOnAction(event ->{
        });
//        try {
//            AudioStream audioStream = new AudioStream(new FileInputStream("../resources/02-title-theme.mp3"));
//            AudioPlayer.player.start(audioStream);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

        aboutUsButton.setStyle(" -fx-text-align:center;" + "-fx-text-decoration:bold;" + "-fx-border:1.5px solid #aaa;" + "-fx-border-radius:30px;"  + "-fx-overflow:hidden;" + "-fx-font-family: \"Nyala\";" + "-fx-font-size: 20;" + "-fx-font-style: Bold;");
        // MenuItem aboutUsItem = new MenuItem("ስለኛ" );
//        aboutUsItem.setStyle(" -fx-text-align:center;" + "-fx-text-decoration:bold;" + "-fx-border:1.5px solid #aaa;" + "-fx-border-radius:30px;" + "-fx-background-color: rgb(255, 244, 0);" + "-fx-overflow:hidden;" + "-fx-font-family: \"Nyala\";" + "-fx-font-size: 20;" + "-fx-font-style: Bold;");
//        MenuItem aboutUsLabelItem = new MenuItem("ethiopis version 1.0.4");
//        aboutUsLabelItem.setStyle("-fx-font-family: Nyala;" + "-fx-fill: rgb(0,0,0,1);");
//        getExternalsettings().getItems().addAll(aboutUsItem,aboutUsLabelItem);
//        VBox aboutPopupPane = new VBox(5);
        Label aboutUs = new Label("ይሄ መተግበሪያ የተሰራው በ: \n\t\t\t-ብሩክ መዝገቡ \n\t\t\t-በረከት ተረፈ \n\t\t\t-አብርሃም ትርፌ \n\t\t\t-ቦንቱ ግርማ \n\t\t\t-ብስራት በትረ  \n\t\t\t-ብሩክ ጌታሁን ");
        aboutUs.setStyle("-fx-font-family: Nyala;" + "-fx-fill: rgb(0,0,0,1); " + "-fx-font-size: 25;");
        aboutUsButton.setOnAction(event -> {
            Popup aboutPopup = new Popup();
            aboutPopup.setX(400);
            aboutPopup.setY(350);
//                aboutButton.setPrefHeight(150);
//                aboutButton.setPrefWidth(150);
            aboutPopup.getContent().addAll(aboutUs);
            aboutPopup.show(primaryStage);
            aboutPopup.setAutoHide(true);
            aboutUsTimer.schedule(new TimerTask() {
                @Override
                public void run() {
                    Platform.runLater(() -> aboutPopup.hide());
                }
            }, 5000);
        });
//        URL resource = getClass().getResource("file:C:\\Users\\rav3n\\Documents\\GitHub\\Ethiopis\\src\\resources\\02-title-theme.mp3");
//        Media marioSong = new Media(resource.toString());
//        MediaPlayer player = new MediaPlayer(marioSong);
//        player.play();

//        try {
//            MusicClass.getInstance().music();
//        } catch (MalformedURLException e) {
//            e.printStackTrace();
//        }

//        getExternalsettings().setOnAction(event -> {
//            onExternalSettingsClicked(1);
//        } );
        getSinglePlayer().setOnAction(event -> {
            try {
                onSinglePlayerClicked();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        getMultiPlayer().setOnAction(event -> {
            try {
                onMultiplayerClicked();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        getExternalmute().setOnAction(event -> onMuteButtonClick());
        getPlaybutton().setOnAction(event -> {
            try {
                onPlayButtonClick(1);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        getSkyClassSample().setOnMouseClicked(event -> {
            try {
                onPlayButtonClick(0);
            } catch (IOException e) {
                e.printStackTrace();
            }
//            onExternalSettingsClicked(0);
        });
        getMainexit().setOnAction(event -> onExitButtonClick());
        getNightMode().setOnAction(event -> onNightModeClick());
        Tooltip exitTwice = new Tooltip("ለመውጣት ደግምው ይጫኑ...");
        //exitTwice.setShowDelay(new Duration(10));
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
