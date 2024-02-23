package coinflip.main;

import java.io.*;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

/**
 * The Game class represents a simple coin flipping game.
 * It allows the player to play the game, view the top score, and exit the game.
 */
public class Game {

    // Variable to store the top score
    private int topScore = 0;

    // File path for storing the top score
    private final String SCORE_FILE = "src/coinflip/main/highscore.txt";

    // Enumeration representing the two sides of a coin
    enum CoinSide {
        HEADS,
        TAILS
    }

    /**
     * Starts the game menu.
     * This method loads the top score, initializes the game loop, and handles user inputs.
     * It allows the user to choose between starting the game, viewing the top score, or exiting the menu.
     *
     * @throws InterruptedException if the thread is interrupted while sleeping or waiting
     */
    public void start() throws InterruptedException {
        // Load the top score from the file
        loadTopScore();

        // Initialize variables
        boolean exitMenu = false;
        Scanner userInput = new Scanner(System.in);

        // Start the game loop
        while (!exitMenu) {
            // Display the menu options
            printMenu();

            // Read user input
            String choice = userInput.nextLine();

            // Process user choice
            switch (choice) {
                case "1" ->
                    // Start a new game and update the top score
                        topScore = playGame(userInput);
                case "2" -> {
                    // Display the top score
                    showTopScore();
                    // Start the menu again
                    start();
                }
                case "3" ->
                    // Exit the menu
                        exitMenu = true;
                default ->
                    // Invalid input
                        printMessage("Invalid choice. Press 1, 2, or 3");
            }
        }

        // Display a quitting message
        printMessage("Quitting...");

        // Save the top score to the file
        saveTopScore();

        // Close the scanner
        userInput.close();
    }


    /**
     * Plays the coin flipping game.
     * This method allows the player to play the coin flipping game until they choose to stop.
     * It keeps track of the player's score and updates the top score if a new high score is achieved.
     *
     * @param userInput the Scanner object to read user input from
     * @return the player's final score
     * @throws InterruptedException if the thread is interrupted while sleeping or waiting
     */
    private int playGame(Scanner userInput) throws InterruptedException {
        // Initialize score
        int score = 0;

        // Display introductory message
        printMessage("What's the most you ever lost on a coin toss?");

        // Main game loop
        do {
            // Get user's choice
            String choice = getChoiceFromUser(userInput);

            // Check if the choice is valid
            boolean validChoice = isValid(choice);
            if (validChoice) {
                // Flip the coin
                String coinFace = flipCoin();

                // Toss the coin and display the result
                tossing(choice, CoinSide.valueOf(coinFace));

                // Check if the user guessed correctly
                if (coinFace.equalsIgnoreCase(choice)) {
                    printMessage("Well done!");
                    score += 1; // Increment score
                } else {
                    printMessage("Oops... Will you hold still please?");
                }
            } else {
                // Invalid choice, prompt the user to try again
                printMessage("What was that? One more time. Make your choice!");
                delay(2);
                continue; // Skip the rest of the loop and start again
            }

            // Ask the user if they want to play again
            String playAgain = playAgain(score, userInput);

            // Check if the user wants to play again
            if (playAgain.equals("y") || playAgain.equals("yes")){
                printMessage("Again...");
            } else {
                // User chooses to exit the game
                printMessage("Goodbye... for now");
                break; // Exit the loop
            }

            // Delay before the next iteration
            delay(1);
        } while (true); // Continue until the user chooses to stop playing

        // Check if the player achieved a new high score
        if (score > topScore) {
            topScore = score;
            printMessage("New Top Score: " + topScore + "!");
        }

        // Return the player's final score
        return score;
    }


    /**
     * Displays the top score.
     * This method prints the current top score to the console.
     */
    private void showTopScore() {
        printMessage("Top Score: " + topScore);
    }

    /**
     * Loads the top score from the file.
     * This method reads the top score from the file specified by SCORE_FILE.
     * If the file does not exist or an error occurs while reading, an exception is thrown.
     */
    private void loadTopScore() {
        try (BufferedReader reader = new BufferedReader(new FileReader(SCORE_FILE))) {
            // Read the top score from the file
            String line = reader.readLine();
            if (line != null) {
                topScore = Integer.parseInt(line);
            }
        } catch (FileNotFoundException e) {
            // File not found exception
            throw new RuntimeException("File not found: " + SCORE_FILE, e);
        } catch (IOException | NumberFormatException e) {
            // Error loading highest score
            printMessage("Error loading highest score: " + e.getMessage());
        }
    }

    /**
     * Saves the top score to the file.
     * This method writes the current top score to the file specified by SCORE_FILE.
     * If an error occurs while writing, an exception is thrown.
     */
    private void saveTopScore() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(SCORE_FILE))) {
            // Write the top score to the file
            writer.write(String.valueOf(topScore));
        } catch (IOException e) {
            // Error saving highest score
            printMessage("Error saving highest score: " + e.getMessage());
        }
    }


    /**
     * Prints the main menu of the game.
     * This method displays the options available to the player in the game menu.
     */
    private void printMenu() {
        printMessage("====== MENU ======");
        printMessage("1. Start Game");
        printMessage("2. Show Top Score");
        printMessage("3. Exit");
        printMessage("==================");
    }

    /**
     * Prints a message to the console.
     * This method is used to display messages to the player during the game.
     *
     * @param message the message to be printed
     */
    public static void printMessage(String message) {
        System.out.println(message);
    }

    /**
     * Delays the execution of the program for a specified number of seconds.
     * This method is used to introduce delays in the game, such as between rounds.
     *
     * @param seconds the number of seconds to delay
     * @throws InterruptedException if the thread is interrupted while sleeping
     */
    private static void delay(int seconds) throws InterruptedException {
        TimeUnit.SECONDS.sleep(seconds);
    }

    /**
     * Simulates flipping a coin.
     * This method randomly selects a coin face (heads or tails) and returns it as a string.
     *
     * @return the result of flipping the coin (either "heads" or "tails")
     */
    private static String flipCoin() {
        CoinSide[] choices = {CoinSide.HEADS, CoinSide.TAILS};
        return choices[new Random().nextInt(choices.length)].toString();
    }

    /**
     * Gets the user's choice from the console input.
     * This method prompts the user to enter their choice and returns the input as a string.
     *
     * @param userInput the Scanner object used to read user input
     * @return the user's choice as a string
     */
    private static String getChoiceFromUser(Scanner userInput) {
        printMessage("You stand to win everything. Call it!");
        printMessage("It's either 'heads' or 'tails'");
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
    private static boolean isValid(String choice) {
        return choice.equalsIgnoreCase(CoinSide.HEADS.toString()) || choice.equalsIgnoreCase(CoinSide.TAILS.toString());
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
    private static void tossing(String choice, CoinSide coinFace) throws InterruptedException {
        printMessage("You chose " + choice);
        printMessage("Flipping coin...");
        delay(2);
        printMessage("The coin landed on... " + coinFace + "!");
        delay(1);
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
    private static String playAgain(int score, Scanner userInput) throws InterruptedException {
        delay(2);
        printMessage("Your score is: " + score);
        printMessage("Do you want to play again?");
        return userInput.nextLine();
    }
}