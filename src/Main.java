import static gamelogic.AmharicCharacters.getCharacterFamily;

public class Main {

    private static void runFor(Runnable runnable, int times) {
        for (int i = 0; i < times; i++) {
            runnable.run();
        }
    }

    public static void main(String args[]){
//        runFor(() -> {
//            ImageQuizGameItem aGame = new ImageQuizGame(LEVEL_EASY).giveMeAQuestion();
//            System.out.println(aGame.toString());
//        }, 10);
//
//        System.out.println(" ----------------------------------------------------------------------------------------------------------- ");
//
//        runFor(() -> {
//            ImageQuizGameItem aGame = new ImageQuizGame(LEVEL_MID).giveMeAQuestion();
//            System.out.println(aGame.toString());
//        }, 10);
//
//        System.out.println(" ----------------------------------------------------------------------------------------------------------- ");
//
//        runFor(() -> {
//            ImageQuizGameItem aGame = new ImageQuizGame(LEVEL_HARD).giveMeAQuestion();
//            System.out.println(aGame.toString());
//        }, 10);

        runFor(() -> {
            System.out.println("Family: " + getCharacterFamily('ቩ'));
        }, 10);

//        runFor(() -> {
//            System.out.println("Game: " + new MatchCharacterFamilyGame(LEVEL_EASY).getASingleGame(8));
//            System.out.println("match: " + (amCharacters.length/7) *7);
//        }, 10);
//
//        runFor(() -> {
//            System.out.println("Game: " + new RearrangeCharacterGame(LEVEL_HARD).getASingleGame(8));
////            System.out.println("match: " + (amCharacters.length/7) *7);
//        }, 10);

    }
} 