package coinflip.main;

import java.util.Random;
import java.util.Scanner;

public class GameOperations {
    /**
     * Simulates flipping a coin.
     * This method randomly selects a coin face (heads or tails) and returns it as a string.
     *
     * @return the result of flipping the coin (either "heads" or "tails")
     */
    static String flipCoin() {
        Game.CoinSide[] choices = {Game.CoinSide.HEADS, Game.CoinSide.TAILS};
        return choices[new Random().nextInt(choices.length)].toString();
    }

    /**
     * Gets the user's choice from the console input.
     * This method prompts the user to enter their choice and returns the input as a string.
     *
     * @param userInput the Scanner object used to read user input
     * @return the user's choice as a string
     */
    static String getChoiceFromUser(Scanner userInput) {
        Abbreviations.printMessage("You stand to win everything. Call it!");
        Abbreviations.printMessage("It's either 'heads' or 'tails'");
        return userInput.nextLine();
    }

    /**
     * Checks if the user's choice is valid.
     * This method verifies whether the user's choice is either "heads" or "tails",
     * regardless of case.
     *
     * @param choice the user's choice
     * @return true if the choice is valid, false otherwise
     */
    static boolean isValid(String choice) {
        return choice.equalsIgnoreCase(Game.CoinSide.HEADS.toString()) || choice.equalsIgnoreCase(Game.CoinSide.TAILS.toString());
    }

    /**
     * Simulates tossing a coin and prints the result.
     * This method displays the user's choice and simulates flipping a coin.
     * It then prints the result of the coin flip.
     *
     * @param choice   the user's choice ("heads" or "tails")
     * @param coinFace the result of the coin flip (HEADS or TAILS)
     * @throws InterruptedException if the thread is interrupted while sleeping
     */
    static void tossing(String choice, Game.CoinSide coinFace) throws InterruptedException {
        Abbreviations.printMessage("You chose " + choice);
        Abbreviations.printMessage("Flipping coin...");
        Abbreviations.delay(2);
        Abbreviations.printMessage("The coin landed on... " + coinFace + "!");
        Abbreviations.delay(1);
    }

    /**
     * Prompts the player to play again and returns their choice.
     * This method asks the player if they want to play another round.
     * It then returns their choice ("y" for yes, "n" for no).
     *
     * @param score     the player's current score
     * @param userInput the Scanner object used to read user input
     * @return the player's choice to play again ("y" for yes, "n" for no)
     * @throws InterruptedException if the thread is interrupted while sleeping
     */
    static String playAgain(int score, Scanner userInput) throws InterruptedException {
        Abbreviations.delay(2);
        Abbreviations.printMessage("Your score is: " + score);
        Abbreviations.printMessage("Do you want to play again?");
        return userInput.nextLine();
    }
}