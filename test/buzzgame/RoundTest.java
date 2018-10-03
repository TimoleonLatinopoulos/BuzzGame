package buzzgame;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Τιμολέων
 */
public class RoundTest {

    public RoundTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of chooseRound method, of class Round.
     */
    @Test
    public void testChooseRound1() {
        System.out.println("chooseRound1");
        boolean flag = false;
        boolean[] playedRounds = {true, false};
        RoundData current = new RoundData(false);
        Round instance = new Round(current);
        int expResult = 1;
        int result = instance.chooseRound(flag, playedRounds, 3);
        assertEquals(expResult, result);
    }

    @Test
    public void testChooseRound2() {
        System.out.println("chooseRound2");
        boolean flag = false;
        boolean[] playedRounds = {false, true};
        RoundData current = new RoundData(false);
        Round instance = new Round(current);
        int expResult = 0;
        int result = instance.chooseRound(flag, playedRounds, 3);
        assertEquals(expResult, result);
    }

    /**
     * Test of resetRounds method, of class Round.
     */
    @Test
    public void testResetRounds1() {
        System.out.println("resetRounds1");
        boolean[] playedRounds = {false, false};
        RoundData current = new RoundData(false);
        Round instance = new Round(current);
        boolean expResult = false;
        boolean result = instance.resetRounds(playedRounds, 5);
        assertEquals(expResult, result);
    }

    @Test
    public void testResetRounds2() {
        System.out.println("resetRounds2");
        boolean[] playedRounds = {false, true};
        RoundData current = new RoundData(false);
        Round instance = new Round(current);
        boolean expResult = false;
        boolean result = instance.resetRounds(playedRounds, 5);
        assertEquals(expResult, result);
    }

    @Test
    public void testResetRounds3() {
        System.out.println("resetRounds3");
        boolean[] playedRounds = {true, false};
        RoundData current = new RoundData(false);
        Round instance = new Round(current);
        boolean expResult = false;
        boolean result = instance.resetRounds(playedRounds, 5);
        assertEquals(expResult, result);
    }

    @Test
    public void testResetRounds4() {
        System.out.println("resetRounds4");
        boolean[] playedRounds = {true, true};
        RoundData current = new RoundData(false);
        Round instance = new Round(current);
        boolean expResult = true;
        boolean result = instance.resetRounds(playedRounds, 5);
        assertEquals(expResult, result);
    }

}
