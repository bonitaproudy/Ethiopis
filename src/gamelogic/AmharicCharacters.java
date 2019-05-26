package gamelogic;

import java.util.*;

public class AmharicCharacters {

    public static final Character amCharacters[] = {
            'ሀ','ሁ','ሂ','ሃ','ሄ','ህ','ሆ',
            'ለ','ሉ','ሊ','ላ','ሌ','ል','ሎ',
            'ሐ','ሑ','ሒ','ሓ','ሔ','ሕ','ሖ',
            'መ','ሙ','ሚ','ማ','ሜ','ም','ሞ',
            'ሠ','ሡ','ሢ','ሣ','ሤ','ሥ','ሦ',
            'ረ','ሩ','ሪ','ራ','ሬ','ር','ሮ',
            'ሰ','ሱ','ሲ','ሳ','ሴ','ስ','ሶ',
            'ሸ','ሹ','ሺ','ሻ','ሼ','ሽ','ሾ',
            'ቀ','ቁ','ቂ','ቃ','ቄ','ቅ','ቆ',
            'በ','ቡ','ቢ','ባ','ቤ','ብ','ቦ',
            'ተ','ቱ','ቲ','ታ','ቴ','ት','ቶ',
            'ቸ','ቹ','ቺ','ቻ','ቼ','ች','ቾ',
            'ኅ','ኁ','ኂ','ኃ','ኄ','ኅ','ኆ',
            'ነ','ኑ','ኒ','ና','ኔ','ን','ኖ',
            'ኘ','ኙ','ኚ','ኛ','ኜ','ኝ','ኞ',
            'አ','ኡ','ኢ','አ','ኤ','እ','ኦ',
            'ከ','ኩ','ኪ','ካ','ኬ','ክ','ኮ',
            'ኸ','ኹ','ኺ','ኻ','ኼ','ኽ','ኾ',
            'ወ','ዉ','ዊ','ዋ','ዌ','ው','ዎ',
            'ዐ','ዑ','ዒ','ዓ','ዔ','ዕ','ዖ',
            'ዘ','ዙ','ዚ','ዛ','ዜ','ዝ','ዞ',
            'ዠ','ዡ','ዢ','ዣ','ዤ','ዥ','ዦ',
            'የ','ዩ','ዪ','ያ','ዬ','ይ','ዮ',
            'ደ','ዱ','ዲ','ዳ','ዴ','ድ','ዶ',
            'ጀ','ጁ','ጂ','ጃ','ጄ','ጅ','ጆ',
            'ገ','ጉ','ጊ','ጋ','ጌ','ግ','ጎ',
            'ጠ','ጡ','ጢ','ጣ','ጤ','ጥ','ጦ',
            'ጨ','ጩ','ጪ','ጫ','ጬ','ጭ','ጮ',
            'ጰ','ጱ','ጲ','ጳ','ጴ','ጵ','ጶ',
            'ጸ','ጹ','ጺ','ጻ','ጼ','ጽ','ጾ',
            'ፀ','ፁ','ፂ','ፃ','ፄ','ፅ','ፆ',
            'ፈ','ፉ','ፊ','ፋ','ፌ','ፍ','ፎ',
            'ፐ','ፑ','ፒ','ፓ','ፔ','ፕ','ፖ',
            'ቨ','ቩ','ቪ','ቫ','ቬ','ቭ','ቮ',
    };

    public static Character getARandomCharacter() {
        Random random = new Random();
        return amCharacters[random.nextInt(amCharacters.length)];
    }

    public static Character getARandomCharacterFromABlock(int from, int to) {
        Random random = new Random();
        List<Character> sublist = Arrays.asList(amCharacters).subList(from, to);
        return sublist.get(random.nextInt(sublist.size()));
    }

    public static Character getCharacterFamily(Character character) {
        int position = Arrays.asList(amCharacters).indexOf(character);
        int familyFirstCharacterPosition = (position / 7) * 7;
        return amCharacters[familyFirstCharacterPosition];
    }

    public static int getCharacterFamilyFirstCharacterPosition(Character character) {
        return Arrays.asList(amCharacters).indexOf(getCharacterFamily(character));
    }

    public static boolean isCharacterFirstLetterInFamily(Character character) {
        List<Character> list = Arrays.asList(amCharacters);
        return list.indexOf(character) == getCharacterFamilyFirstCharacterPosition(character);
    }

    public static List<Character> subListFamilies(int from, int to) {
        return Arrays.asList(amCharacters).subList(from*7, to*7);
    }

    public static List<Character> getListOfAllMembersOfFamily(Character familyName) {
        return subListFamilies(getCharacterFamilyFirstCharacterPosition(familyName)/7, (getCharacterFamilyFirstCharacterPosition(familyName)/7) +1);
    }
}
