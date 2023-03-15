package coinflip.main;

import java.util.*;
import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        int score = 0;
        printMessage("What's the most you ever lost on a coin toss?");
        do {
            printMessage("You stand to win everything. Call it!");
            printMessage("It's either 'heads' or 'tails'");

            Scanner option = new Scanner(System.in);

            String choice = option.nextLine();  // Read user input

            String optionHead = "heads";
            String optionTails = "tails";

            if(choice.equals(optionHead) || choice.equals(optionTails)) {
                String coinFace = flipCoin();

                printMessage("You chose "+choice);
                printMessage("Flipping coin...");
                delay(2);
                printMessage("The coin landed on... "+coinFace+"!");
                delay(1);

                if (coinFace.equals(choice)) {
                    printMessage("Well done!");
                    score+=1;
                    printMessage("Your score is: "+score+"!");
                    delay(2);
                }
                else {
                    printMessage("Oops... Will you hold still please?");
                    printMessage("Your score is: "+score+"!");
                    delay(2);
                }
            }
            else{
                printMessage("What was that? One more time. Make your choice!");
                delay(2);
                continue;
            }

            printMessage("One more time? 'y' or 'n'");
            String optionRestart = option.nextLine();
            if(optionRestart.equals("y")) {
                printMessage("Again...");
                delay(1);
                break;
            }
            else {
                printMessage("Good day to you...");
                delay(1);
                break;
            }
        } while (true);
    }
    private static void printMessage(String message) {
        System.out.println(message);
    }

    private static void delay(int seconds) throws InterruptedException {
        TimeUnit.SECONDS.sleep(seconds);
    }
    private static String flipCoin(){
        String[] choices = {"heads", "tails"};
        return choices[new Random().nextInt(choices.length)];
    }


}