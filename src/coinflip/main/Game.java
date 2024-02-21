package coinflip.main;

import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class Game {
    enum CoinSide {
        HEADS,
        TAILS
    }
    public void start() throws InterruptedException {
        int score = 0;
        printMessage("What's the most you ever lost on a coin toss?");
        Scanner userInput = new Scanner(System.in);
        do {
            String choice = getChoiceFromUser(userInput);

            boolean validChoice = isValid(choice);
            if (validChoice) {
                String coinFace = flipCoin();

                tossing(choice, CoinSide.valueOf(coinFace));

                if (coinFace.equalsIgnoreCase(choice)) {
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

    private static boolean isValid(String choice) {
        return (choice.equalsIgnoreCase(CoinSide.HEADS.toString())) || (choice.equalsIgnoreCase(CoinSide.TAILS.toString()));
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
