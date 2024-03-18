package coinflip.main;

import org.junit.Assert;
import org.junit.Test;
import java.util.Scanner;
import static org.junit.Assert.*;


public class GameOperationsTest {

    @Test
    public void flipCoinReturnsHEADSorTAILS(){
        String result = GameOperations.flipCoin();
        assertTrue(result.equals("HEADS") || result.equals("TAILS"));
    }
    @Test
    public void getChoiceFromUserHEADSReturnsActualUserChoice(){
        Scanner scanner = new Scanner("heads");
        String result = GameOperations.getChoiceFromUser(scanner);
        scanner.close(); // Close the scanner to avoid resource leak
        Assert.assertEquals("heads", result);
    }
    @Test
    public void getChoiceFromUserTAILSReturnsActualUserChoice(){
        Scanner scanner = new Scanner("tails");
        String result = GameOperations.getChoiceFromUser(scanner);
        scanner.close(); // Close the scanner to avoid resource leak
        Assert.assertEquals("tails", result);
    }
    @Test
    public void isValidInputFromUserHEADS(){
        boolean result = GameOperations.isValid("heads");
        Assert.assertEquals(true, result);
    }
    @Test
    public void isValidInputFromUserTAILS(){
        boolean result = GameOperations.isValid("tails");
        Assert.assertEquals(true, result);
    }
    /*
    @Test
    public void tossing(){
        GameOperations.tossing("heads", new Coin().coinflip.main.Coin.CoinSide.HEADS);
    }

    @Test
    public void playAgainYES(){
        Scanner scanner = new Scanner("yes"); // Simulate user input
        try {
            String result = GameOperations.playAgain(0, scanner);
            assertEquals("yes", result);
        } catch (InterruptedException e) {
            e.printStackTrace(); // Handle InterruptedException if necessary
        } finally {
            scanner.close(); // Close the scanner to avoid resource leak
        }
    }
*/

}