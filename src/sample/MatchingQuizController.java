package sample;

import gamelogic.MatchCharacterFamilyGame;
import gamelogic.MatchCharacterFamilyGameItem;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.StrokeLineCap;
import javafx.scene.shape.StrokeType;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Popup;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.util.*;

public class MatchingQuizController implements Initializable {
    //universal Declarations
    Timer errorTimer = new Timer();
    private Stage primaryStage;
    public int buttonsSelected = 0;
    int disabledButtonCount = 0;
    int i = 0;
    //button declaration
    public Button backToMe;
    public Button internalSkip;
    public Button internalHome;
    public Button choice1;
    public Button choice2;
    public Button choice3;
    public Button choice4;
    public Button choice5;
    public Button choice6;
    public Button choice7;
    public Button choice8;

    ArrayList<Button> selectedButtons = new ArrayList<>();
    Button[] buttons = new Button[8];

    //other elements declaration
    public Label gameExplainLabel;
    public BorderPane skyClassMatchingQuiz;
    public HBox grassClassMatchingQuiz;
    public AnchorPane canvasToLines;

    //GAME LOGIC -----------------------------------------------------------------------------------

    MatchCharacterFamilyGame matchCharacterFamilyGame = new MatchCharacterFamilyGame(ChoicesMenuController.difficulty);

    public Button getChoice1() {
        return choice1;
    }

    public Button getChoice2() {
        return choice2;
    }

    public Button getChoice3() {
        return choice3;
    }

    public Button getChoice4() {
        return choice4;
    }

    public Button getChoice5() {
        return choice5;
    }

    public Button getChoice6() {
        return choice6;
    }

    public Button getChoice7() {
        return choice7;
    }
    public Button getChoice8() {
        return choice8;
    }

    public AnchorPane getCanvasToLines() {
        return canvasToLines;
    }

    public Button getInternalSkip() {
        return internalSkip;
    }
    public Button getInternalHome() {
        return internalHome;
    }
    public Button getBackToMe() {
        return backToMe;
    }

    public BorderPane getSkyClassMatchingQuiz() {
        return skyClassMatchingQuiz;
    }

    public HBox getGrassClassMatchingQuiz() {
        return grassClassMatchingQuiz;
    }

    public Label getGameExplainLabel() {
        return gameExplainLabel;
    }

    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    private void setupButtonsArrayList() {
        buttons = new Button[]{
                getChoice1(), getChoice2(),
                getChoice3(), getChoice4(),
                getChoice5(), getChoice6(),
                getChoice7(), getChoice8(),
        };
    }

    private void toScores() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("scores.fxml"));
        ScoresController controller = new ScoresController();
        loader.setController(controller);
        Parent root = loader.load();
        Scene scene = new Scene(root, 600, 400);
        controller.setPrimaryStage(primaryStage);
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    public void onBackToMeClick() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("gameModesList.fxml"));
        GameModesListController controller = new GameModesListController();
        loader.setController(controller);
        Parent root = loader.load();
        Scene scene = new Scene(root, 600, 400);
        controller.setPrimaryStage(primaryStage);
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }
    public void onHomeClick() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("choicesmenu.fxml"));
        ChoicesMenuController controller = new ChoicesMenuController();
        loader.setController(controller);
        Parent root = loader.load();
        Scene scene = new Scene(root, 600, 400);
        controller.setPrimaryStage(primaryStage);
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }
    public void onInterenalSkipClick() throws IOException {
        Controller.win = 0;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("scores.fxml"));
        ScoresController controller = new ScoresController();
        loader.setController(controller);
        Parent root = loader.load();
        Scene scene = new Scene(root, 600, 400);
        controller.setPrimaryStage(primaryStage);
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }
    public void makeLine(Button pair1, Button pair2){
        Line line = new Line(pair1.getLayoutX()+60,pair1.getLayoutY()+60,pair2.getLayoutX()+40,pair2.getLayoutY()+40);
        line.setStrokeWidth(5);
        line.setStroke(Color.GREEN);
        line.setStrokeLineCap(StrokeLineCap.ROUND);
        getCanvasToLines().getChildren().add(line);
    }
    /*public void getWorkingPairs(MatchCharacterFamilyGameItem game){

        buttons.forEach( button -> {
            if( game.isCharactersInSameFamily(button.getText().charAt(0),buttons.get(buttons.indexOf(button)+1).getText().charAt(0)) == true){
                makeLine(button,buttons.get(buttons.indexOf(button)+1));
            }
            else{
                Image errorImg = new Image("resources/close_window_48px.png");
                Popup wrong = new Popup();
                wrong.setX(500);
                wrong.setY(500);
                Button errorButton = new Button();
                errorButton.setGraphic(new ImageView(errorImg));
                errorButton.setDisable(true);
                wrong.getContent().addAll(errorButton);
                wrong.show(primaryStage);
            }
        });
    }*/
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        gameExplainLabel.setFont(new Font(32));
        if(Controller.nightModeStatus == 1){
            new Controller().setNightMode(getSkyClassMatchingQuiz(),getGrassClassMatchingQuiz(),"MATCHINGQUIZ");
            getInternalHome().setStyle("-fx-background-color: rgba(200, 200, 200,1)");
            getInternalSkip().setStyle("-fx-background-color: rgba(200, 200, 200,1)");
            getChoice1().setStyle("-fx-background-color: rgba(200, 200, 200,1)");
            getChoice2().setStyle("-fx-background-color: rgba(200, 200, 200,1)");
            getChoice3().setStyle("-fx-background-color: rgba(200, 200, 200,1)");
            getChoice4().setStyle("-fx-background-color: rgba(200, 200, 200,1)");
            getChoice5().setStyle("-fx-background-color: rgba(200, 200, 200,1)");
            getChoice6().setStyle("-fx-background-color: rgba(200, 200, 200,1)");
            getChoice7().setStyle("-fx-background-color: rgba(200, 200, 200,1)");
            getChoice8().setStyle("-fx-background-color: rgba(200, 200, 200,1)");
            getGameExplainLabel().setStyle("-fx-text-fill: rgb(255,255,255)");
        }
        else{
            new Controller().setDayMode(getSkyClassMatchingQuiz(),getGrassClassMatchingQuiz(),"MATCHINGQUIZ");
            getInternalSkip().setStyle("-fx-background-color: rgb(255, 244, 0)");
            getInternalHome().setStyle( "-fx-background-color: rgb(255, 244, 0)");
            getChoice1().setStyle("-fx-background-color: rgb(255, 244, 0)");
            getChoice2().setStyle( "-fx-background-color: rgb(255, 244, 0)");
            getChoice3().setStyle("-fx-background-color: rgb(255, 244, 0)");
            getChoice4().setStyle( "-fx-background-color: rgb(255, 244, 0)");
            getChoice5().setStyle("-fx-background-color: rgb(255, 244, 0)");
            getChoice6().setStyle( "-fx-background-color: rgb(255, 244, 0)");
            getChoice7().setStyle("-fx-background-color: rgb(255, 244, 0)");
            getChoice8().setStyle( "-fx-background-color: rgb(255, 244, 0)");
            getGameExplainLabel().setStyle("-fx-text-fill: rgb(0,0,0)");
        }

        getBackToMe().setOnAction(event -> {
            try {
                onBackToMeClick();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        getInternalHome().setOnAction(event -> {
            try {
                onHomeClick();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        getInternalSkip().setOnAction(event -> {
            try {
                onInterenalSkipClick();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        setupButtonsArrayList();

        //GAME LOGIC -----------------------------------------------------------------------------
        MatchCharacterFamilyGameItem gameItem = matchCharacterFamilyGame.getASingleGame(8);
        System.out.println(gameItem.toString());

        List<Character> characters = gameItem.getItems();

        for (int j = 0; j < buttons.length; j++) {
            buttons[j].setText("" + characters.get(j));
        }

        //buttons.forEach(button -> button.setText("" + gameItem.getItems().get(buttons.indexOf(button))));
        for (Button button : buttons) {
            button.setOnAction(event -> {
                selectedButtons.add(buttonsSelected, button);
                button.setDisable(true);
                if (buttonsSelected == 1) {
                    buttonsSelected = -1;
                    if (gameItem.isCharactersInSameFamily(selectedButtons.get(0).getText().charAt(0), selectedButtons.get(1).getText().charAt(0))) {
                        makeLine(selectedButtons.get(0), selectedButtons.get(1));
                        disabledButtonCount++;
                        System.out.println(disabledButtonCount);
                        if(disabledButtonCount >= 4){
                            try {
                                Controller.win = 1;
                                toScores();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    } else {

                        Image errorImg = new Image("resources/cancel_48px.png");
                        ImageView errorImageView = new ImageView(errorImg);
                        errorImageView.setFitHeight(150);
                        errorImageView.setFitWidth(150);
                        Popup wrong = new Popup();
                        wrong.setX(600);
                        wrong.setY(200);
                        wrong.setHeight(100);
                        wrong.setWidth(100);
                        Button errorButton = new Button();
                        errorButton.setStyle("-fx-background-color: Transparent");
                        errorButton.setGraphic(errorImageView);
                        errorButton.setDisable(false);
                        wrong.getContent().addAll(errorButton);
                        wrong.show(primaryStage);
                        errorTimer.schedule(new TimerTask() {
                            @Override
                            public void run() {
                                Platform.runLater(() -> wrong.hide());
                            }
                        }, 1000);
                        selectedButtons.get(0).setDisable(false);
                        selectedButtons.get(1).setDisable(false);
                    }
                }
                buttonsSelected++;
            });
            }
        }
}



