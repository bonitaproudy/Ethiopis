import gamelogic.RearrangeCharacterGame;
import gamelogic.RearrangeCharacterGameItem;
import networking.MultiplayerClient;
import networking.MultiplayerServer;

import java.io.IOException;
import java.net.Inet4Address;
import java.util.Date;

import static gamelogic.AmharicCharacters.getCharacterFamily;
import static gamelogic.Levels.LEVEL_EASY;

public class Main {

    private static void runFor(Runnable runnable, int times) {
        for (int i = 0; i < times; i++) {
            runnable.run();
        }
    }

    public static void main(String args[]) throws IOException {
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

//        runFor(() -> {
//            System.out.println("Family: " + getCharacterFamily('ቩ'));
//        }, 10);

//        runFor(() -> {
//            System.out.println("Game: " + new MatchCharacterFamilyGame(LEVEL_EASY).getASingleGame(8));
//            System.out.println("match: " + (amCharacters.length/7) *7);
//        }, 10);
//
//        runFor(() -> {
//            System.out.println("Game: " + new RearrangeCharacterGame(LEVEL_HARD).getASingleGame(8));
////            System.out.println("match: " + (amCharacters.length/7) *7);
//        }, 10);

//        MultiplayerServer server = new MultiplayerServer(
//                new RearrangeCharacterGame(LEVEL_EASY).getASingleGame(8),
//                new MultiplayerServer.OnGameStarted() {
//                    @Override
//                    public void startYourEngine(RearrangeCharacterGameItem game) {
//                        // go to the game ....
//                    }
//
//                    @Override
//                    public void tekedemk(Date time) {
//                        // stop the game
//                    }
//                });
//        server.startServer();
//

        MultiplayerClient client = new MultiplayerClient(new MultiplayerServer.OnGameStarted() {
            @Override
            public void startYourEngine(RearrangeCharacterGameItem game) {

            }

            @Override
            public void tekedemk(Date time) {

            }
        });

        try {
          client.startSniffer();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
} 