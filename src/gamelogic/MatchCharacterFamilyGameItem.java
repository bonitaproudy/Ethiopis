package gamelogic;

import java.io.Serializable;
import java.util.*;

public class MatchCharacterFamilyGameItem implements Serializable {

    private Map<Character, Character> gameCharacters;
    private int level;

    public MatchCharacterFamilyGameItem(Map<Character, Character> gameCharacters, int level) {
        this.gameCharacters = gameCharacters;
        this.level = level;
    }

    public List<Character> getItems() {
        List<Character> returnable = Arrays.asList(gameCharacters.keySet().toArray(new Character[0]));
        // split them in two parts ( to shuffle them easily)
        List<Character> partOne = new ArrayList<>();
        List<Character> partTwo = new ArrayList<>();

        for (Character character : returnable) {
            if (!doesFamilyExistInList(partOne, character)) partOne.add(character);
            else partTwo.add(character);
        }

        Collections.shuffle(partOne);
        Collections.shuffle(partTwo);

        partOne.addAll(partTwo);
        return partOne;
    }

    private boolean doesFamilyExistInList(List<Character> characters, Character item) {
        for (Character c : AmharicCharacters.getListOfAllMembersOfFamily(item))
            if (characters.contains(c)) return true;
        return false;
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
