package gamelogic;

import java.util.*;

import static gamelogic.AmharicCharacters.*;
import static gamelogic.Levels.LEVEL_EASY;
import static gamelogic.Levels.LEVEL_HARD;
import static gamelogic.Levels.LEVEL_MID;

public class MatchCharacterFamilyGame {

    private int level;

    public MatchCharacterFamilyGame(int level) {
        if (isLevelNotValid(level)) throw new IllegalArgumentException("invalid level: " + level);
        this.level = level;
    }

    private boolean isLevelNotValid(int level) {
        return level != LEVEL_EASY && level != LEVEL_MID && level != LEVEL_HARD;
    }

    private Character getRandomCharacterDependingOnLevel(int level) {
        switch (level) {
            case LEVEL_EASY:
            default:
                return getARandomCharacterFromABlock(0, 11*7);
            case LEVEL_MID:
                return getARandomCharacterFromABlock(12*7, 22*7);
            case LEVEL_HARD:
                return getARandomCharacterFromABlock(23*7, 231);

        }
    }

    public MatchCharacterFamilyGameItem getASingleGame(int size) throws IllegalArgumentException{
        Map<Character, Character> gameItemsPartI = new HashMap<>();
        Map<Character, Character> gameItemsPartII = new HashMap<>();

        // The size of the game board should be divisible by two.
        if (size % 2 != 0) throw new IllegalArgumentException("Size should be divisible by 2");

        // First we generate the first half of the character-family pair list determined from the size
        do {
            // When ever we generate these characters we should change
            // 1. The character is not present in the list previously
            // 2. The character is not the first element of a family. ex: 'ሀ', 'ለ', 'መ'
            // 3. The family of the character shouldn't be in the list previously

            Character character = getRandomCharacterDependingOnLevel(level);
            if (!gameItemsPartI.keySet().contains(character) &&
                    !isCharacterFirstLetterInFamily(character) &&
                    !gameItemsPartI.values().contains(getCharacterFamily(character))) {
                // all the above checks passed so push it
                gameItemsPartI.put(character, getCharacterFamily(character));
            }
        } while (gameItemsPartI.values().size() < size / 2);

        // fill the rest half of the pair list with something random from each of the family on the previous half
        for (int i = 0; i < size / 2; i++) {
            // get all the member characters of the specific family
            List members = getListOfAllMembersOfFamily(gameItemsPartI.get(gameItemsPartI.keySet().toArray(new Character[0])[i]));
            do {
                // choose one character randomly from the list of members and repeat if it's already present in the list
                int memberChoosed = new Random().nextInt(members.size());
                if (!gameItemsPartI.keySet().contains(members.get(memberChoosed)) && !isCharacterFirstLetterInFamily((Character) members.get(memberChoosed))) {
                    gameItemsPartII.put((Character) members.get(memberChoosed) , gameItemsPartI.get(gameItemsPartI.keySet().toArray(new Character[0])[i]));
                    break;
                }
            } while (true);
        }

        return new MatchCharacterFamilyGameItem(combineMaps(gameItemsPartI, gameItemsPartII), level);
    }

    @SafeVarargs
    private final Map<Character, Character> combineMaps(Map<Character, Character>... characterMaps) {
        Map<Character, Character> returnable = new HashMap<>();
        for (Map<Character, Character> map : characterMaps) {
            returnable.putAll(map);
        }
        //shuffle the map list
        return returnable;
    }

}
