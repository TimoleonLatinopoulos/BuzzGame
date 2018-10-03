/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
public class PlayerTest {
    
    public PlayerTest() {
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
     * Test of addPoints method, of class Player.
     */
    @Test
    public void testAddPoints1() {
        System.out.println("addPoints1");
        int points = 1000;
        Player instance = new Player();
        instance.addPoints(points);
        assertEquals(points, instance.getPoints());
    }
    
    @Test
    public void testAddPoints2() {
        System.out.println("addPoints2");
        int points = 0;
        Player instance = new Player();
        instance.addPoints(points);
        assertEquals(points, instance.getPoints());
    }
    
    @Test
    public void testAddPoints3() {
        System.out.println("addPoints3");
        int points = -1000;
        Player instance = new Player();
        instance.addPoints(points);
        assertEquals(points, instance.getPoints());
    }

    /**
     * Test of subPoints method, of class Player.
     */
    @Test
    public void testSubPoints1() {
        System.out.println("subPoints1");
        int points = 1000;
        Player instance = new Player();
        instance.subPoints(points);
        assertEquals(-points, instance.getPoints());
    }
    
    @Test
    public void testSubPoints2() {
        System.out.println("subPoints1");
        int points = 0;
        Player instance = new Player();
        instance.subPoints(points);
        assertEquals(-points, instance.getPoints());
    }
    
    @Test
    public void testSubPoints3() {
        System.out.println("subPoints3");
        int points = -1000;
        Player instance = new Player();
        instance.subPoints(points);
        assertEquals(-points, instance.getPoints());
    }
    
}
