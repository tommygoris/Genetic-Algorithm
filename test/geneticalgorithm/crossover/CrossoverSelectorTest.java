/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package geneticalgorithm.crossover;

import geneticalgorithm.crossover.CrossoverSelector;
import geneticalgorithm.Individual;
import java.util.Arrays;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Ignore;

/**
 *
 * @author Tommy
 */
public class CrossoverSelectorTest {
    
    public CrossoverSelectorTest() {
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
     * Test of crossover method, of class CrossoverSelector.
     */
    @Test
    public void testCrossover_intArr_intArr() {
//        System.out.println("crossover for int array");
//        int[] firstIndividual =  new int[]{1,2,3,4,5};
//        int[] secondIndividual = new int[]{6,7,8,9,10};
//        CrossoverSelector instance = new CrossoverSelector(3);
//        Individual expResult = new Individual(new int[]{1,7,3,9,10});
//        Individual result = instance.crossoverTest(firstIndividual, secondIndividual, new int[]{1,2,3, firstIndividual.length});
//        Assert.assertArrayEquals(expResult.intProblem, result.intProblem);
//        
//        firstIndividual = new int[]{1,2,3,4,5,6,7,8,9,10};
//        secondIndividual = new int[]{11,12,13,14,15,16,17,18,19,20};
//        instance = new CrossoverSelector(7);
//        expResult = new Individual(new int[]{1,2,3,14,5,16,7,18,9,20});
//        result = instance.crossoverTest(firstIndividual, secondIndividual, new int[]{3,4,5,6,7,8,9, firstIndividual.length});
//        Assert.assertArrayEquals(expResult.intProblem, result.intProblem);
    }

    /**
     * Test of crossover method, of class CrossoverSelector.
     */
    @Test
    public void testCrossover_String_String() {
//        System.out.println("string crossover");
//        String firstIndividual = "1111";
//        String secondIndividual = "0000";
//        CrossoverSelector instance = new CrossoverSelector(2);
//        Individual expResult = new Individual("1001");
//        Individual result = instance.crossoverTest(firstIndividual, secondIndividual, new int[]{1,3,4});
//        System.out.println("Compare: " + expResult.stringProblem + "  " + result.stringProblem);
//        assertEquals(expResult.stringProblem, result.stringProblem);
    }
    
    /**
     * Test of crossover method, of class CrossoverSelector.
     */
    @Test
    public void testgetCrossoverLocations_int_int() {
//        System.out.println("get crossover locations");
//        int length = 55;
//        int points = 30;
//        CrossoverSelector instance = new CrossoverSelector(3);
//        int[] expResult = new int[]{1,7,3,5,55};
//        int[] result = instance.getCrossoverLocations(length, points);
//        System.out.println(Arrays.toString(result));
//        Assert.assertArrayEquals(expResult, result);       
    }
    
}
