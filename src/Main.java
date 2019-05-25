import gamelogic.ImageQuizGame;
import gamelogic.ImageQuizGameItem;

public class Main {

    private static void runFor(Runnable runnable, int times) {
        for (int i = 0; i < times; i++) {
            runnable.run();
        }
    }

    public static void main(String args[]){
        runFor(() -> {
            ImageQuizGameItem aGame = new ImageQuizGame(ImageQuizGame.LEVEL_EASY).giveMeAQuestion();
            System.out.println(aGame.toString());
        }, 10);

        System.out.println(" ----------------------------------------------------------------------------------------------------------- ");

        runFor(() -> {
            ImageQuizGameItem aGame = new ImageQuizGame(ImageQuizGame.LEVEL_MID).giveMeAQuestion();
            System.out.println(aGame.toString());
        }, 10);

        System.out.println(" ----------------------------------------------------------------------------------------------------------- ");

        runFor(() -> {
            ImageQuizGameItem aGame = new ImageQuizGame(ImageQuizGame.LEVEL_HARD).giveMeAQuestion();
            System.out.println(aGame.toString());
        }, 10);
    }
} 