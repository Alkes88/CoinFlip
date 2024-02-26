package coinflip.main;

import java.util.concurrent.TimeUnit;

public class Abbreviations {
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
    static void delay(int seconds) throws InterruptedException {
        TimeUnit.SECONDS.sleep(seconds);
    }
}