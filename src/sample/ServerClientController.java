package sample;

import gamelogic.RearrangeCharacterGame;
import gamelogic.RearrangeCharacterGameItem;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Point3D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import networking.MultiplayerClient;
import networking.MultiplayerServer;

import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;

import static gamelogic.Levels.LEVEL_EASY;

public class ServerClientController implements Initializable {
    //universal declarations
    private Stage primaryStage;
    Timer loadingTimer = new Timer();
    RearrangeCharacterGameItem gameItem;
    //buttons declaration
    public Button createServerButton;
    public Button joinServerButton;
    public BorderPane skyClassClientServer;
    public HBox grassClassClientServer;
    public Label waitingLabel;
    public ImageView loadingAnimation;
    public ImageView sheepAnim;
    public Button backToMe;

    private MultiplayerServer server;
    private MultiplayerClient client;

    public ImageView getLoadingAnimation() {
        return loadingAnimation;
    }

    public ImageView getSheepAnim() {
        return sheepAnim;
    }

    public Label getWaitingLabel() {
        return waitingLabel;
    }

    public BorderPane getSkyClassClientServer() {
        return skyClassClientServer;
    }

    public HBox getGrassClassClientServer() {
        return grassClassClientServer;
    }

    public Button getBackToMe() {
        return backToMe;
    }

    public Button getCreateServerButton() {
        return createServerButton;
    }

    public Button getJoinServerButton() {
        return joinServerButton;
    }
    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }
    public void startRearrangeGame(RearrangeCharacterGameItem gameItem, MultiplayerClient client) throws IOException{
        Platform.runLater(() -> {
            GameModesListController.gameMode = 3;
            FXMLLoader loader = new FXMLLoader(getClass().getResource("rearrangeQuiz.fxml"));
            RearrangeQuizController controller = new RearrangeQuizController(gameItem, client);
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
            //primaryStage.setResizable(false);
            primaryStage.show();
        });
    }

    public void startRearrangeGame(RearrangeCharacterGameItem gameItem, MultiplayerServer server) throws IOException{
        Platform.runLater(() -> {
            GameModesListController.gameMode = 3;
            FXMLLoader loader = new FXMLLoader(getClass().getResource("rearrangeQuiz.fxml"));
            RearrangeQuizController controller = new RearrangeQuizController(gameItem, server);
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
            //primaryStage.setResizable(false);
            primaryStage.show();
        });
    }

    private void toScores() throws IOException {
        Platform.runLater(() -> {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("scores.fxml"));
            ScoresController controller = new ScoresController();
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
    public void onBackToMeClick() throws IOException {
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
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if(Controller.nightModeStatus == 1){
            new Controller().setNightMode(getSkyClassClientServer(),getGrassClassClientServer(),"CLIENTSERVER");
            getCreateServerButton().setStyle("-fx-background-color: rgba(200, 200, 200,1)");
            getJoinServerButton().setStyle("-fx-background-color: rgba(200, 200, 200,1)");

        }
        else{
            new Controller().setDayMode(getSkyClassClientServer(),getGrassClassClientServer(),"CLIENTSERVER");
            getCreateServerButton().setStyle("-fx-background-color: rgb(255, 244, 0)");
            getJoinServerButton().setStyle( "-fx-background-color: rgb(255, 244, 0)");
        }
        getBackToMe().setOnAction(event -> {
            if (getCreateServerButton().isVisible() == false){
                getLoadingAnimation().setVisible(false);
                getWaitingLabel().setVisible(false);
                getJoinServerButton().setVisible(true);
                getCreateServerButton().setVisible(true);
                if (server != null) {
                    try {
                        server.stopServer();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
            else{
                try {
                    onBackToMeClick();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        gameItem = new RearrangeCharacterGame(LEVEL_EASY).getASingleGame(8);

        createServerButton.setOnAction(event -> {
            getLoadingAnimation().setVisible(true);
            getWaitingLabel().setVisible(true);
            getJoinServerButton().setVisible(false);
            getCreateServerButton().setVisible(false);
            loadingTimer.schedule(new TimerTask() {
                @Override
                public void run() {
                    Platform.runLater(() -> {
                        getLoadingAnimation().setRotationAxis(new Point3D(0,0,1));
                        getLoadingAnimation().setRotate(getLoadingAnimation().getRotate() + 1);
                    });
                }
            }, 0, 7);
            try {
                server = new MultiplayerServer(gameItem, new MultiplayerServer.OnGameStarted() {
                    @Override
                    public void startYourEngine(RearrangeCharacterGameItem game) throws IOException {
                        startRearrangeGame(game, server);
                    }

                    @Override
                    public void tekedemk(Date time) {
                        try {
                            Controller.win = 0;
                            toScores();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                });
                server.startServer();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        getJoinServerButton().setOnAction(event -> {
            client = new MultiplayerClient(new MultiplayerServer.OnGameStarted() {
                @Override
                public void startYourEngine(RearrangeCharacterGameItem game) throws IOException {
                    startRearrangeGame(game, client);
                }

                @Override
                public void tekedemk(Date time) {
                    try {
                        toScores();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });
            try {
                client.startSniffer();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

}
