package sample;

import gamelogic.RearrangeCharacterGame;
import gamelogic.RearrangeCharacterGameItem;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Point3D;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.Popup;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.*;

public class ChoiceQuizController implements Initializable {
    //universal Declarations
    private Stage primaryStage;
    Timer buttonFlipTimer = new Timer();
    public int buttonsSelected = 0;
    int iter = 0;
    ArrayList<Character> characters;
    ArrayList<Button> selectedButtons = new ArrayList<>();
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
    Button[] choicesArray = {getChoice1(),getChoice2(),getChoice3(),getChoice4(),getChoice5(),getChoice6(),getChoice7(),getChoice8()};

    //other element declaration
    public BorderPane skyClassChoiceQuiz;
    public HBox grassClassChoiceQuiz;

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

    public Button getChoice7() {
        return choice7;
    }

    public Button getChoice6() {
        return choice6;
    }

    public Button getChoice8() {
        return choice8;
    }
    public Button getInternalSkip() {
        return internalSkip;
    }
    public Button getInternalHome() {
        return internalHome;
    }
    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }
    public Button getBackToMe() {
        return backToMe;
    }

    public BorderPane getSkyClassChoiceQuiz() {
        return skyClassChoiceQuiz;
    }

    public HBox getGrassClassChoiceQuiz() {
        return grassClassChoiceQuiz;
    }

    public void blackOut(Button pair1,Button pair2){
        pair1.setDisable(true);
        pair2.setDisable(true);
        pair1.setOpacity(0.8);
        pair2.setOpacity(0.8);
    }

//    private void flipCharacterArrayItems (Character x , Character y) {
//        int indexOfX = characters.indexOf(x);
//        int indexOfY = characters.indexOf(y);
//
//        Collections.swap(characters, indexOfX, indexOfY);
//    }

    private void flipCharacterArrayItems( Character x, Character y, RearrangeCharacterGameItem gameItem ) {
        int indexOfX = characters.indexOf(x);
        int indexOfY = characters.indexOf(y);

        characters.remove(indexOfX);
        characters.add(indexOfY, x);
        flipButtonsStartingFromIndex(indexOfY, gameItem);
    }

    private void flipButtonsStartingFromIndex(int i, RearrangeCharacterGameItem gameItem) {
        ArrayList<Button> buttons = new ArrayList<>();
        for (int j = i; j < choicesArray.length; j++) buttons.add(choicesArray[j]);
        flipButton(buttons, gameItem);
    }

    public void flipButton(List<Button> buttons, RearrangeCharacterGameItem gameItem) {
        for (Button button : buttons) button.setRotationAxis(new Point3D(0, 1, 0));
        new Thread(() -> {
            for (iter = 0; iter < 180; iter++) {
                try {
                    Thread.sleep(2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Platform.runLater(() -> {
                    if (iter == 90){
                        // redraw characters into the buttons
                        characters.forEach(character ->choicesArray[characters.indexOf(character)].setText(character + ""));
                    }
                    for(Button button: buttons) button.setRotate(iter > 90 ? 180 - iter : iter);
                });
            }

            if (gameItem.isCorrectedArranged(characters)) {
                Controller.win = 1;
                Platform.runLater(() -> {
                    try {
                        toScores();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
            }
        }).start();
    }

    public void flipButton(Button buttonToFlip1, Button buttonToFlip2, RearrangeCharacterGameItem gameItem){
        //flipCharacterArrayItems(buttonToFlip1.getText().charAt(0), buttonToFlip2.getText().charAt(0));
        buttonToFlip1.setRotationAxis(new Point3D(0,1,0));
        buttonToFlip2.setRotationAxis(new Point3D(0,1,0));
        new Thread(() -> {
            for (iter = 0; iter <180 ; iter++) {
                try {
                    Thread.sleep(2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Platform.runLater(() -> {
                    if(iter == 90) {
                        String button1String = buttonToFlip1.getText();
                        String button2String = buttonToFlip2.getText();
                        buttonToFlip1.setText(button2String);
                        buttonToFlip2.setText(button1String);
                    }

                     buttonToFlip1.setRotate(iter >= 90 ? 180 - iter : iter);
                     buttonToFlip2.setRotate(iter >= 90 ? 180 - iter : iter);
                });
            }

            if (gameItem.isCorrectedArranged(characters)) {
//                makeLine(selectedButtons.get(0), selectedButtons.get(1));
//                disabledButtonCount++;
//                System.out.println(disabledButtonCount);
//                if(disabledButtonCount >= 4){
//                    try {
//                        Controller.win = 1;
//                        toScores();
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
//                }
            }
        }).start();
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

    private void setupButtonsArrayList() {
        choicesArray = new Button[]{
                getChoice1(), getChoice2(),
                getChoice3(), getChoice4(),
                getChoice5(), getChoice6(),
                getChoice7(), getChoice8(),
        };
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if(Controller.nightModeStatus == 1){
            new Controller().setNightMode(skyClassChoiceQuiz,grassClassChoiceQuiz,"CHOICEQUIZ");
            getInternalHome().setStyle("-fx-background-color: rgba(200, 200, 200,1)");
            getInternalSkip().setStyle("-fx-background-color: rgba(200, 200, 200,1)");
        }
        else{
            new Controller().setDayMode(skyClassChoiceQuiz,grassClassChoiceQuiz,"CHOICEQUIZ");
            getInternalSkip().setStyle("-fx-background-color: rgb(255, 244, 0)");
            getInternalHome().setStyle( "-fx-background-color: rgb(255, 244, 0)");
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
//        flipButton(getChoice1(),getChoice2());

        //GAME LOGIC =====
        RearrangeCharacterGame rearrangeCharacterGame = new RearrangeCharacterGame(ChoicesMenuController.difficulty);
        RearrangeCharacterGameItem rearrangeCharacterGameItem = rearrangeCharacterGame.getASingleGame(8);

        characters = rearrangeCharacterGameItem.getRandomizedCharacters();
        System.out.println((rearrangeCharacterGameItem.toString()));
        setupButtonsArrayList();

        characters.forEach(character -> choicesArray[characters.indexOf(character)].setText(character + ""));

//        ========================================
        for (Button button : choicesArray) {
            button.setOnAction(event -> {
                selectedButtons.add(buttonsSelected, button);
                if (buttonsSelected == 1) {
                    buttonsSelected = -1;
                    flipCharacterArrayItems(selectedButtons.get(0).getText().charAt(0), selectedButtons.get(1).getText().charAt(0), rearrangeCharacterGameItem);
                }
                buttonsSelected++;
            });
        }
    }
}
