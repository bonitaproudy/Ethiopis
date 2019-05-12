
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.ProgressBar;

import javafx.stage.Stage;
import javafx.scene.text.Text;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.paint.Color;





public class Main extends Application {
    @Override
    public void start(Stage stage) {
        //Creating a Text object
        Text menu  = new Text("====  MENU   ====");
        Text text1 = new Text("LEVEL 1");
        Text text2 = new Text("LEVEL 2");
        Text text3 = new Text("LEVEL 3");
        Text text4 = new Text("LEVEL 4");
        Text text5 = new Text("LEVEL 5");
        Text text6 = new Text("LEVEL 6");




//Setting the color
        menu.setFill(Color.WHITE);
        text1.setFill(Color.WHITE);
        text2.setFill(Color.WHITE);
        text3.setFill(Color.WHITE);
        text4.setFill(Color.WHITE);
        text5.setFill(Color.WHITE);
        text6.setFill(Color.WHITE);


        //Setting the Stroke
        menu.setStrokeWidth(1);
        text1.setStrokeWidth(1);
        text2.setStrokeWidth(1);
        text3.setStrokeWidth(1);
        text4.setStrokeWidth(1);
        text5.setStrokeWidth(1);
        text6.setStrokeWidth(1);


        // Setting the stroke color
        menu.setStroke(Color.DARKBLUE);
        text1.setStroke(Color.DARKBLUE);
        text2.setStroke(Color.DARKBLUE);
        text3.setStroke(Color.DARKBLUE);
        text4.setStroke(Color.DARKBLUE);
        text5.setStroke(Color.DARKBLUE);
        text6.setStroke(Color.DARKBLUE);


        // setting font
        menu.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 35));
        text1.setFont(Font.font("verdana", FontWeight.SEMI_BOLD, FontPosture.REGULAR, 20));
        text2.setFont(Font.font("verdana", FontWeight.SEMI_BOLD, FontPosture.REGULAR, 20));
        text3.setFont(Font.font("verdana", FontWeight.SEMI_BOLD, FontPosture.REGULAR, 20));
        text4.setFont(Font.font("verdana", FontWeight.SEMI_BOLD, FontPosture.REGULAR, 20));
        text5.setFont(Font.font("verdana", FontWeight.SEMI_BOLD, FontPosture.REGULAR, 20));
        text6.setFont(Font.font("verdana", FontWeight.SEMI_BOLD, FontPosture.REGULAR, 20));

        // adding progress bar
        ProgressBar p1 = new ProgressBar(20);
        ProgressBar p2 = new ProgressBar(20);
        ProgressBar p3 = new ProgressBar(20);
        ProgressBar p4 = new ProgressBar(20);
        ProgressBar p5 = new ProgressBar(20);
        ProgressBar p6 = new ProgressBar(20);



        //setting the position of the text
        menu.setX(75);
        menu.setY(50);

        text1.setX(20);
        text1.setY(150);

        p1.setLayoutX(20);
        p1.setLayoutY(175);
        p1.setProgress(0.25F);


        text2.setX(20);
        text2.setY(250);

        p2.setLayoutX(20);
        p2.setLayoutY(275);
        p2.setProgress(0.5F);

        text3.setX(20);
        text3.setY(350);

        p3.setLayoutX(20);
        p3.setLayoutY(375);
        p3.setProgress(0.75F);

        text4.setX(20);
        text4.setY(450);

        p4.setLayoutX(20);
        p4.setLayoutY(475);
        p4.setProgress(0.5F);

        text5.setX(20);
        text5.setY(550);

        p5.setLayoutX(20);
        p5.setLayoutY(575);
        p5.setProgress(0.5F);

        text6.setX(20);
        text6.setY(650);

        p6.setLayoutX(20);
        p6.setLayoutY(675);
        p6.setProgress(0.5F);



        //Creating a Group object
        Group root = new Group(menu, text1,p1, text2,p2, text3,p3, text4,p4, text5,p5, text6,p6);


        //Creating a scene object
        Scene scene = new Scene(root, 550, 750);

        //Setting title to the Stage
        stage.setTitle("MENU");

        //Adding scene to the stage
        stage.setScene(scene);

        //Displaying the contents of the stage
        stage.show();
    }
    public static void main(String args[]){
        launch(args);
    }
} 