package gamelogic;

import java.util.Date;
import java.util.Random;

public class AmharicCharacters {

    public static final Character amCharacters[] = {
        'ሀ', 'ሁ', 'ሂ', 'ሃ', 'ሄ', 'ህ', 'ሆ',
        'ለ', 'ሉ', 'ሊ', 'ላ', 'ሌ', 'ል', 'ሎ',
        'መ', 'ሙ', 'ሚ', 'ማ', 'ሜ', 'ም', 'ሞ',
            'ሀ', 'ሁ', 'ሂ', 'ሃ', 'ሄ', 'ህ', 'ሆ',
            'ለ', 'ሉ', 'ሊ', 'ላ', 'ሌ', 'ል', 'ሎ',
            'መ', 'ሙ', 'ሚ', 'ማ', 'ሜ', 'ም', 'ሞ',
            'ሀ', 'ሁ', 'ሂ', 'ሃ', 'ሄ', 'ህ', 'ሆ',
            'ለ', 'ሉ', 'ሊ', 'ላ', 'ሌ', 'ል', 'ሎ',
            'መ', 'ሙ', 'ሚ', 'ማ', 'ሜ', 'ም', 'ሞ',
            'ሀ', 'ሁ', 'ሂ', 'ሃ', 'ሄ', 'ህ', 'ሆ',
            'ለ', 'ሉ', 'ሊ', 'ላ', 'ሌ', 'ል', 'ሎ',
            'መ', 'ሙ', 'ሚ', 'ማ', 'ሜ', 'ም', 'ሞ',
            'ሀ', 'ሁ', 'ሂ', 'ሃ', 'ሄ', 'ህ', 'ሆ',
            'ለ', 'ሉ', 'ሊ', 'ላ', 'ሌ', 'ል', 'ሎ',
            'መ', 'ሙ', 'ሚ', 'ማ', 'ሜ', 'ም', 'ሞ',
            'ሀ', 'ሁ', 'ሂ', 'ሃ', 'ሄ', 'ህ', 'ሆ',
            'ለ', 'ሉ', 'ሊ', 'ላ', 'ሌ', 'ል', 'ሎ',
            'መ', 'ሙ', 'ሚ', 'ማ', 'ሜ', 'ም', 'ሞ',
    };

    public static Character getARandomCharacter() {
        Random random = new Random();
        random.setSeed(new Date().getTime());
        return amCharacters[random.nextInt(amCharacters.length)];
    }

}
