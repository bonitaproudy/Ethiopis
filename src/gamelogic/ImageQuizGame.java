package gamelogic;

import java.util.ArrayList;
import java.util.Random;
import static gamelogic.Levels.*;

import static java.util.Arrays.asList;

public class ImageQuizGame {

    private int level;

    private ArrayList<ImageQuizGameItem> sampleGameItems = new ArrayList<>(asList(
            new ImageQuizGameItem(new ArrayList<>(asList('ህ', 'ብ', 'ር')) , "base64imagetextalphatestone", LEVEL_EASY),
            new ImageQuizGameItem(new ArrayList<>(asList('ህ', 'ብ', 'ረ', 'ተ', 'ሰ', 'ብ')) , "base64imagetextalphatesttwo", LEVEL_EASY),
            new ImageQuizGameItem(new ArrayList<>(asList('ህ', 'ይ', 'ወ', 'ት')) , "base64imagetextalphatestthree", LEVEL_EASY),
            new ImageQuizGameItem(new ArrayList<>(asList('ላ', 'ም')) , "base64imagetextalphatestfour", LEVEL_EASY),
            new ImageQuizGameItem(new ArrayList<>(asList('በ', 'ር', 'ጩ', 'ማ')) , "base64imagetextalphatestfive", LEVEL_EASY)
    ));

    public ImageQuizGame(int level) throws IllegalArgumentException{
        if (isLevelNotValid(level)) throw new IllegalArgumentException("Invalid level type: " + level);
        this.level = level;
    }

    private boolean isLevelNotValid(int level) {
        return level != LEVEL_EASY && level != LEVEL_MID && level != LEVEL_HARD;
    }


    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public ImageQuizGameItem giveMeAQuestion() {
        if (isLevelNotValid(level)) throw new IllegalArgumentException("Invalid level type: " + level);
        ImageQuizGameItem game = sampleGameItems.get(new Random().nextInt(sampleGameItems.size()));
        ImageQuizGameItem actualGame = new ImageQuizGameItem(game.getAnswerText(), game.getImagebase64(), level);
        return actualGame;
    }



}
