package gamelogic;

import java.util.ArrayList;
import java.util.Random;
import static gamelogic.Levels.*;

import static java.util.Arrays.asList;

public class ImageQuizGame {

    private int level;

    private ArrayList<ImageQuizGameItem> sampleGameItems = new ArrayList<>(asList(
            new ImageQuizGameItem(new ArrayList<>(asList('ህ', 'ብ', 'ር')) , "resources/unity_32px.png", LEVEL_EASY),
            new ImageQuizGameItem(new ArrayList<>(asList('ህ', 'ብ', 'ረ', 'ተ', 'ሰ', 'ብ')) , "resources/community_50px.png", LEVEL_EASY),
            new ImageQuizGameItem(new ArrayList<>(asList('ህ', 'ይ', 'ወ', 'ት')) , "resources/heart_with_pulse_48px.png", LEVEL_EASY),
            new ImageQuizGameItem(new ArrayList<>(asList('ላ', 'ም')) , "resources/cow_48px.png", LEVEL_EASY),
            new ImageQuizGameItem(new ArrayList<>(asList('በ', 'ር', 'ጩ', 'ማ')) , "resources/chair_48px.png", LEVEL_EASY)
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
        ImageQuizGameItem actualGame = new ImageQuizGameItem(game.getAnswerText(), game.getImageResourceName(), level);
        return actualGame;
    }



}
