package gamelogic;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class MatchCharacterFamilyGameItem implements Serializable {

    private Map<Character, Character> gameCharacters;
    private int level;

    public MatchCharacterFamilyGameItem(Map<Character, Character> gameCharacters, int level) {
        this.gameCharacters = gameCharacters;
        this.level = level;
    }

    public List<Character> getItems() {
        List<Character> returnable = Arrays.asList(gameCharacters.keySet().toArray(new Character[0]));
        Collections.shuffle(returnable);
        return returnable;
    }

    public boolean isCharactersInSameFamily(Character check1, Character check2) {
        return gameCharacters.get(check1).equals(gameCharacters.get(check2));
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    @Override
    public String toString() {
        return "[[GAME_CHARACTERS: " + gameCharacters.toString() + ", LEVEL: " + level + "]]";
    }
}
