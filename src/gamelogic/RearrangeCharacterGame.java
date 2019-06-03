package gamelogic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

import static gamelogic.AmharicCharacters.amCharacters;
import static gamelogic.AmharicCharacters.getARandomCharacterFromABlock;
import static gamelogic.Levels.LEVEL_EASY;
import static gamelogic.Levels.LEVEL_HARD;
import static gamelogic.Levels.LEVEL_MID;

public class RearrangeCharacterGame {

    private int level;

    private class RGameItem {
        Character character;
        Integer position;

        public RGameItem(Character character, Integer position) {
            this.character = character;
            this.position = position;
        }
    }

    public RearrangeCharacterGame(int level) {
        if (isLevelNotValid(level)) throw new IllegalArgumentException("invalid level: " + level);
        this.level = level;
    }

    private boolean isLevelNotValid(int level) {
        return level != LEVEL_EASY && level != LEVEL_MID && level != LEVEL_HARD;
    }

    private Character getRandomCharacterBasedOnLevel (int level) {
        switch (level) {
            case LEVEL_EASY:
            default:
                return getARandomCharacterFromABlock(0 , 12*7);
            case LEVEL_MID:
                return getARandomCharacterFromABlock(0, 23*7);
            case LEVEL_HARD:
                return getARandomCharacterFromABlock(0, amCharacters.length);
        }
    }

    private boolean doesListContainACharacter(ArrayList<RGameItem> items, Character ch) {
        for (RGameItem item : items) {
            if (item.character.equals(ch)) return true;
        }
        return false;
    }

    private ArrayList<Character> splitOnlyCharacters(ArrayList<RGameItem> fromPreList) {
        ArrayList<Character> characters = new ArrayList<>();
        fromPreList.forEach(item -> characters.add(item.character));
        return characters;
    }

    public RearrangeCharacterGameItem getASingleGame(int size) {
        ArrayList<RGameItem> characterList = new ArrayList<>();
        do {
            Character character = getRandomCharacterBasedOnLevel(level);
            RGameItem item = new RGameItem(character, Arrays.asList(amCharacters).indexOf(character));
            if (!doesListContainACharacter(characterList, character)) characterList.add(item);
        } while (characterList.size() < size);

        characterList.sort((o1, o2) -> (o1.position > o2.position) ? 0 : -1);
        ArrayList<Character> arrangedCharacters = splitOnlyCharacters(characterList);
        ArrayList<Character> charactersClone = (ArrayList<Character>) arrangedCharacters.clone();
        Collections.shuffle(charactersClone);

        return new RearrangeCharacterGameItem(splitOnlyCharacters(characterList), charactersClone, level);
    }
}
