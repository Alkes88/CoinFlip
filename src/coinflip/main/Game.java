package coinflip.main;

import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class Game {
    enum CoinSide {
        HEADS,
        TAILS
    }
    private void startMenu() {
        printMessage("=== CoinFlip ===");
        printMessage("1. Play a single game");
        printMessage("2. Play a 'best of 3' game");
        printMessage("3. Show TopScore");
        printMessage("4. Quit game");
        printMessage("================");
        printMessage("Enter your choice(1,2,3,4): ");
        printMessage("Pressed 1.");
    }

    private TopScoreKeeper topScoreKeeper;

    public Game(){
        topScoreKeeper = new TopScoreKeeper();
    }

    public void start() throws InterruptedException {
        int score = 0;
        printMessage("What's the most you ever lost on a coin toss?");
        Scanner userInput = new Scanner(System.in);
        do {
            startMenu();
            String choice = getChoiceFromUser(userInput);

            CoinSide validChoice = isValid(choice);
            if (validChoice != null) {
                String coinFace = flipCoin();

                tossing(choice, CoinSide.valueOf(coinFace));

                if (coinFace.equals(choice)) {
                    printMessage("Well done!");
                    score += 1;
                } else {
                    printMessage("Oops... Will you hold still please?");
                }
            } else {
                printMessage("What was that? One more time. Make your choice!");
                delay(2);
                continue;
            }

            topScoreKeeper.updateTopScore(score);

            String playAgain = playAgain(score, userInput);

            if (playAgain.equals("y")) {
                printMessage("Again...");
            } else {
                printMessage("Goodbye... for now");
                break;
            }
            delay(1);
        } while (true);
    }

    public static void printMessage(String message) {
        System.out.println(message);
    }

    private static void delay(int seconds) throws InterruptedException {
        TimeUnit.SECONDS.sleep(seconds);
    }

    private static String flipCoin() {
        CoinSide[] choices = {CoinSide.HEADS, CoinSide.TAILS};
        return choices[new Random().nextInt(choices.length)].toString();
    }

    private static String getChoiceFromUser(Scanner userInput) {
        printMessage("You stand to win everything. Call it!");
        printMessage("It's either 'heads' or 'tails'");
        return userInput.nextLine();
    }

    private static CoinSide isValid(String choice) {
        if ((choice.equalsIgnoreCase(CoinSide.HEADS.toString())) || (choice.equalsIgnoreCase(CoinSide.TAILS.toString()))) {
            int randomSide = (int) (Math.random() * 2);
            return (randomSide == 0) ? CoinSide.HEADS : CoinSide.TAILS;
        } else {
            return null;
        }
    }

    private static void tossing(String choice, CoinSide coinFace) throws InterruptedException {
        printMessage("You chose " + choice);
        printMessage("Flipping coin...");
        delay(2);
        printMessage("The coin landed on... " + coinFace + "!");
        delay(1);
    }

    private static String playAgain(int score, Scanner userInput) throws InterruptedException {
        delay(2);
        printMessage("Your score is: " + score);
        printMessage("Do you want to play again? (y/n)");
        return userInput.nextLine();
    }
}
