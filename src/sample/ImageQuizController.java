package sample;

import gamelogic.ImageQuizGame;
import gamelogic.ImageQuizGameItem;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.Popup;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.util.*;

public class ImageQuizController implements Initializable {
    //universal declarations
    Timer errorTimer = new Timer();
    ImageQuizGame imageQuizGame = new ImageQuizGame(ChoicesMenuController.difficulty);
    Timer messages = new Timer();
    int x = 0;
    int positionHidden;
    private Stage primaryStage;
    Button[] buttons = new Button[8];
    //button declarations
    public Button choice1;
    public Button choice2;
    public Button choice3;
    public Button choice4;
    public Button choice5;
    public Button choice6;
    public Button choice7;
    public Button choice8;
    //other element declaration
    public BorderPane skyClassImageQuiz;
    public HBox grassClassImageQuiz;
    public ImageView quizPic;
    public TextField answerTextField;
    public Button backToMe;
    public Button internalSkip;
    public Button internalHome;
    public Button[] choicesArray = {choice1,choice2,choice3,choice4,choice5,choice6,choice7,choice8};

    public Button getBackToMe() {
        return backToMe;
    }

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

    public Button getInternalHome() {
        return internalHome;
    }

    public Button getInternalSkip() {
        return internalSkip;
    }

    public BorderPane getSkyClassImageQuiz() {
        return skyClassImageQuiz;
    }

    public HBox getGrassClassImageQuiz() {
        return grassClassImageQuiz;
    }

    public TextField getAnswerTextField() {
        return answerTextField;
    }

    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }
    public void setQuizPic(ImageView quizPic) {
        this.quizPic = quizPic;
    }

    public ImageView getQuizPic() {
        return quizPic;
    }
    public void imageload(Image selected){
        ImageView imageProspect = new ImageView(selected);
        quizPic = imageProspect;
    }
    public void textsLoad(String[] choices){
        for (int i = 0; i < 8; i++) {
            choicesArray[i].setText(choices[i]);
        }
    }


    public void quizBackEnd(){
        if (new ChoicesMenuController().getDifficulty() == 0){

        }
        else if (new ChoicesMenuController().getDifficulty() == 1){

        }
        else if (new ChoicesMenuController().getDifficulty() == 2){

        }


    }
    private String generateTextFromCharacters(List<Character> characters) {
        StringBuilder builder = new StringBuilder();
        for (Character character : characters) builder.append(character).append(' ');
        return builder.toString();
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
    public void toScores() throws IOException {
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
        buttons = new Button[]{
                getChoice1(), getChoice2(),
                getChoice3(), getChoice4(),
                getChoice5(), getChoice6(),
                getChoice7(), getChoice8(),
        };
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if(Controller.nightModeStatus == 1){
            new Controller().setNightMode(getSkyClassImageQuiz(),getGrassClassImageQuiz(),"IMAGEQUIZ");
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
            getAnswerTextField().setStyle("-fx-text-fill: rgb(255,255,255)");
        }
        else{
            new Controller().setDayMode(getSkyClassImageQuiz(),getGrassClassImageQuiz(),"IMAGEQUIZ");
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
            getAnswerTextField().setStyle("-fx-text-fill: rgb(0,0,0)");
        }

        getInternalSkip().setOnAction((event) -> {
            try {
                onInterenalSkipClick();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        getInternalHome().setOnAction((event) -> {
            try {
                onHomeClick();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        getBackToMe().setOnAction(event -> {
            try {
                onBackToMeClick();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        //GAME
        setupButtonsArrayList();
        ImageQuizGameItem gameItem = imageQuizGame.giveMeAQuestion();
        for (int j = 0; j < buttons.length; j++) {
            buttons[j].setText("" + gameItem.getPossibleChoices().get(j));
        }
        StringBuilder answerWithBlanks = new StringBuilder();

        List<Character> characters = (List<Character>) gameItem.getAnswerText().clone();

//        for (int i = 0; i < gameItem.getAnswerText().size(); i++) {
//            for (int j = 0; j < gameItem.getPositionToHide().size(); j++) {
//                if(i == gameItem.getPositionToHide().get(j)){
//                    answerWithBlanks.append("_");
//                } else {
//                    answerWithBlanks.append(gameItem.getAnswerText().get(i));
//                }
//            }
//        }
        System.out.println(gameItem.getAnswerText().toString());

        for (int positionToHide : gameItem.getPositionToHide()) {
            characters.remove(positionToHide);
            characters.add(positionToHide, '_');
        }

        answerTextField.setText(generateTextFromCharacters(characters));
        //positionHidden = gameItem.getPositionToHide().get(x);

        for (Button button:buttons) { button.setOnAction(event -> {
            positionHidden = characters.indexOf('_');
            if (gameItem.isUserChoiceCorrect(positionHidden, button.getText().charAt(0))) {
                button.setDisable(true);
                characters.remove(positionHidden);
                characters.add(positionHidden,button.getText().charAt(0));
                answerTextField.setText(generateTextFromCharacters(characters));
                x++;
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
            }

            if(!characters.contains('_')){
                Controller.win = 1;
                try {
                    toScores();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        }

        quizPic.setImage(new Image(gameItem.getImageResourceName()));
        System.out.println(gameItem.getAnswerText());
        System.out.println(gameItem.getPositionToHide());
        System.out.println(gameItem.getPossibleChoices());

     }

}
