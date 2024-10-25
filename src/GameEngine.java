
// Leave this import as it is. You'll need it
import assignmentfiles.*;

public class GameEngine {
    private String targetWord;
    private int attemptsLeft;
    private boolean isWin;

    public GameEngine(String targetWord) {
        this.targetWord = targetWord;
        this.attemptsLeft = 6;
        this.isWin = false;
    }

    public String playGuess(String guess) {
        if(attemptsLeft > 0)
            this.attemptsLeft--;
        if(guess.equals(targetWord))
            isWin = true;
        return evaluateGuess(targetWord, guess);
    }

    public void main(String[] args) {
        WordLoader wordLoader = new WordLoader();
        String theWord = wordLoader.getRandomWord();
        GameEngine GameEngine = new GameEngine(targetWord);
        GameUI gameUI = new GameUI();
        System.out.println("Enter your guess: ");
        String guess = gameUI.readUserGuess();
        while(!GameEngine.isGameOver()){
            System.out.println("Enter your guess: ");
            String guess1 = gameUI.readUserGuess();
            String result = GameEngine.playGuess(guess1);
            System.out.println(gameUI.displayResult(GameEngine.getAttemptsLeft(), guess1, result));
            if(GameEngine.isGameOver()){
                if(GameEngine.isWin){
                    System.out.println(gameUI.displayWin());
                }else{
                    System.out.println(gameUI.displayLoss(GameEngine.getTargetWord()));
                }
            }


            System.out.println("Try again: ");
        }
    }

    /**
     * Compares the player's guess to the target word and returns feedback.
     * For each character in the guess:
     * - '*' if the character is in the correct position.
     * - '+' if the character is in the target word but in the wrong position.
     * - '-' if the character is not in the target word.
     *
     * The comparison is done up to the length of the shorter word, ignoring any extra characters.
     * Example:
     * targetWord = "taper", guess = "water"
     * Returns: "-*+**"
     **/
    public static String evaluateGuess(String targetWord, String guess) {
        String newS = "";
        if (targetWord.length() <= guess.length()) {
            for (int i = 0; i < targetWord.length(); i++) {
                char tarChar = targetWord.charAt(i);
                char guessChar = guess.charAt(i);
                if (tarChar == guessChar)
                    newS += "*";
                else if (targetWord.indexOf(guessChar) != -1)
                    newS += "+";
                else
                    newS += "-";

            }
            return newS;

        } else {
            for (int i = 0; i < guess.length(); i++) {
                char tarChar = targetWord.charAt(i);
                char guessChar = guess.charAt(i);
                if (tarChar == guessChar)
                    newS += "*";
                else if (targetWord.indexOf(guessChar) != -1)
                    newS += "+";
                else
                    newS += "-";
            }
            return newS;
        }
    }

    public boolean isGameOver() {
        if(attemptsLeft <= 0 || isWin)
            return true;
        else
            return false;
    }

    public boolean isWin() {
        return this.isWin;
    }

    public int getAttemptsLeft() {
        return this.attemptsLeft;
    }

    public String getTargetWord(){
        return this.targetWord;
    }
}
