


import java.awt.*;
import java.awt.TextField;
import java.io.FileNotFoundException;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class game_page extends Application {
    @Override
    public void start(Stage stage) throws FileNotFoundException {
        //Creating an image
        Image image = new Image("test.jpg");

        //Setting the image view
        ImageView imageView = new ImageView(image);

        //Setting the position of the image
        imageView.setX(60);
        imageView.setY(25);

        //setting the fit height and width of the image view
        imageView.setFitHeight(355);
        imageView.setFitWidth(400);

        //Setting the preserve ratio of the image view
        imageView.setPreserveRatio(true);




        Label answer = new Label("Name:");
        TextField answer_box = new TextField ();
        HBox hb = new HBox();



/*
 // it wont let me add the text field


        hb.getChildren().addAll(answer, answer_box);
        hb.setSpacing(10);



        TextField answer_space =new TextField();
        answer_space.setBounds(75,250,50,20);

         // input the answer for the image
        Text answer=new Text("ENTER YOUR ANSWER HERE  ");
        answer.setFont(Font.font("verdana", FontWeight.SEMI_BOLD, FontPosture.REGULAR, 20));
        answer.setFill(Color.BLACK);
        answer.setStrokeWidth(1);
        answer.setX(100);
        answer.setY(400);

*/

        //Creating a Group object
        Group root = new Group(imageView);



        //Creating a scene object
        Scene scene = new Scene(root, 550, 750);

        //Setting title to the Stage
        stage.setTitle("Loading an image");

        //Adding scene to the stage
        stage.setScene(scene);

        //Displaying the contents of the stage
        stage.show();
    }
    public static void main(String args[]) {
        launch(args);
    }
}