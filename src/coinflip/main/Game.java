package coinflip.main;

import java.io.*;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class Game {

    private int topScore = 0;
    private final String SCORE_FILE = "src/coinflip/main/highscore.txt";
    enum CoinSide {
        HEADS,
        TAILS
    }
    public void start() throws InterruptedException {
        loadTopScore();
        boolean exitMenu = false;
        Scanner userInput = new Scanner(System.in);

        while (!exitMenu) {
            printMenu();
            String choice = userInput.nextLine();

            switch (choice) {
                case "1":
                    topScore = playGame(userInput);
                    break;
                case "2":
                    showTopScore();
                    start();
                case "3":
                    exitMenu = true;
                    break;
                default:
                    printMessage("Invalid. Press 1, 2 or 3");
            }
        }
        printMessage("Quitting...");
        saveTopScore();
        userInput.close();
    }

        private int playGame(Scanner userInput) throws InterruptedException {
            int score = 0;
            printMessage("What's the most you ever lost on a coin toss?");
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

            if (score > topScore) {
                topScore = score;
                printMessage("New Top Score: "+ topScore +"!");
            }

            return score;
        }

    private void showTopScore() {
        printMessage("Top Score: "+ topScore);
    }

    private void loadTopScore() {
        try (BufferedReader reader = new BufferedReader(new FileReader(SCORE_FILE))) {
            String line = reader.readLine();
            if (line !=null) {
                topScore = Integer.parseInt(line);
            }
        } catch (FileNotFoundException e) {
                throw new RuntimeException("File not found: "+ SCORE_FILE, e);
        } catch (IOException | NumberFormatException e) {
            printMessage("Error loading highest score: " + e.getMessage());
        }
    }

    private void saveTopScore() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(SCORE_FILE))) {
            writer.write(String.valueOf(topScore));
        } catch (IOException e) {
            printMessage("Error saving highest score: "+ e.getMessage());
        }
    }

    private void printMenu(){
        printMessage("====== MENU ======");
        printMessage("1. Start Game");
        printMessage("2. Show Top Score");
        printMessage("3. Exit");
        printMessage("==================");
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
