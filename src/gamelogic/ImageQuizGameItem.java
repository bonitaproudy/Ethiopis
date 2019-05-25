package gamelogic;

import java.util.*;

public class ImageQuizGameItem {

    private ArrayList<Character> answerText;
    private ArrayList<Character> possibleChoices;
    private String imagebase64;
    private int gameLevel;
    private ArrayList<Integer> positionToHide;

    public ImageQuizGameItem(ArrayList<Character> answerText, String imagebase64, int gameLevel) {
        this.answerText = answerText;
        this.imagebase64 = imagebase64;
        this.gameLevel = gameLevel;
        Random random = new Random();
        random.setSeed(new Date().getTime());

        int numberOfCharactersToHide = getNumberOfAnswersToHide(gameLevel, answerText.size());
        this.possibleChoices = getListOfRandomAmCharacters(Math.max(4 , numberOfCharactersToHide));
        this.positionToHide = getListOfPositionsToHide(answerText.size(), numberOfCharactersToHide);

        for (int i = 0; i < numberOfCharactersToHide; i++) {
            this.possibleChoices.remove(random.nextInt(this.possibleChoices.size()));
        }

        for (int i = 0; i < numberOfCharactersToHide; i++) {
            this.possibleChoices.add(answerText.get(positionToHide.get(i)));   // add that hidden character to the possible choices array so that the user can see it
        }

        Collections.shuffle(this.possibleChoices);  // shuffle the array in order to not make the game guessable
    }

    public ArrayList<Character> getAnswerText() {
        return answerText;
    }

    public void setAnswerText(ArrayList<Character> answerText) {
        this.answerText = answerText;
    }

    public ArrayList<Character> getPossibleChoices() {
        return possibleChoices;
    }

    public void setPossibleChoices(ArrayList<Character> possibleChoices) {
        this.possibleChoices = possibleChoices;
    }

    public String getImagebase64() {
        return imagebase64;
    }

    public void setImagebase64(String imagebase64) {
        this.imagebase64 = imagebase64;
    }

    public int getGameLevel() {
        return gameLevel;
    }

    public void setGameLevel(int gameLevel) {
        this.gameLevel = gameLevel;
    }

    public boolean isUserChoiceCorrect (int position , Character choice) {
        return answerText.get(position).equals(choice);
    }

    public Character getAnswerCharacterAtPosition (int position) {
        return answerText.get(position);
    }

    public ArrayList<Integer> getPositionToHide() {
        return positionToHide;
    }

    public void setPositionToHide(ArrayList<Integer> positionToHide) {
        this.positionToHide = positionToHide;
    }

    private int getNumberOfAnswersToHide(int level , int answerSize) {
        switch (level) {
            case ImageQuizGame.LEVEL_EASY:
                //only 1 answer should be hidden on easy level
                return 1;
            case ImageQuizGame.LEVEL_MID:
                //50% of the answer should be hidden on medium level
                return (int) Math.round(answerSize * 0.5);
            case ImageQuizGame.LEVEL_HARD:
                //all the answers should be hidden on hard level
                return answerSize;
            default:
                return 1;
        }
    }

    private ArrayList<Character> getListOfRandomAmCharacters (int ofSize) {
        ArrayList<Character> returnable = new ArrayList<>();
        for (int i = 0; i < ofSize; i++) returnable.add(AmharicCharacters.getARandomCharacter());

        while (checkIfGeneratedListIsNotValid(returnable, ofSize))
            for (int j = returnable.size(); j < ofSize; j++)
                returnable.add(AmharicCharacters.getARandomCharacter());

        return returnable;
    }

    private ArrayList<Integer> getListOfPositionsToHide (int arraySize, int requiredToHide) {
        ArrayList<Integer> returnable = new ArrayList<>();
        Random random = new Random();
        random.setSeed(new Date().getTime());

        for (int i = 0; i < requiredToHide; i++) returnable.add(random.nextInt(arraySize));

        while (checkIfGeneratedListIsNotValid(returnable, requiredToHide))
            for (int j = returnable.size(); j < requiredToHide; j++)
                returnable.add(random.nextInt(arraySize));

        return returnable;
    }

    private <T> boolean checkIfGeneratedListIsNotValid(ArrayList<T> items, int ofSize) {
        //remove any duplicates and check the size if it matches the requested size
        ArrayList<Integer> possibleDuplicates = new ArrayList<>();
        ArrayList<T> nonDuplicatedItems = new ArrayList();

        for (int i = 0; i < items.size(); i++)
            for (int j = i+1; j < items.size(); j++)
                if (items.get(i).equals(items.get(j)))
                    possibleDuplicates.add(j);


        for (int i = 0; i < ofSize; i++) if (!possibleDuplicates.contains(i)) nonDuplicatedItems.add(items.get(i));
        items.clear();
        items.addAll(nonDuplicatedItems);

        return nonDuplicatedItems.size() != ofSize;
    }

    @Override
    public String toString() {
        return "[" + Arrays.toString(answerText.toArray(new Character[0]))
                + "] , [" + Arrays.toString(possibleChoices.toArray(new Character[0]))
                + "] , [" + imagebase64
                + "] , [" + Arrays.toString(positionToHide.toArray(new Integer[0]))
                + "] , [" + gameLevel + "]";

    }
}
