package gamelogic;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class RearrangeCharacterGameItem implements Serializable {

    private ArrayList<Character> arrangedCharacters;
    private int level;

    public RearrangeCharacterGameItem(ArrayList<Character> arrangedCharacters, int level) {
        this.arrangedCharacters = arrangedCharacters;
        this.level = level;
    }

    public int getLevel() {
        return level;
    }

    public int getCharacterPosition(Character ch) {
        return arrangedCharacters.indexOf(ch);
    }

    public boolean isCorrectedArranged(ArrayList<Character> compare) {
        boolean arranged = true;
        for (Character c : compare) {
            if (compare.indexOf(c) != arrangedCharacters.indexOf(c)) {
                arranged = false;
                break;
            }
        }
        return arranged;
    }

    public ArrayList<Character> getRandomizedCharacters() {
        ArrayList<Character> charactersClone = (ArrayList<Character>) arrangedCharacters.clone();
        Collections.shuffle(charactersClone);
        return charactersClone;
    }

    @Override
    public String toString() {
        return "[ Characters: " + Arrays.toString(arrangedCharacters.toArray(new Character[0])) + ", LEVEL: " + level +" ]";
    }
}
