/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package geneticalgorithm.examples.nqueens;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Ignore;

/**
 *
 * @author Tommy
 */
public class NQueensFitnessFunctionTest {
    
    public NQueensFitnessFunctionTest() {
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
     * Test of initializeMap method, of class NQueensFitnessFunction.
     */
    @Ignore
    @Test
    public void testInitializeMap() {
        System.out.println("initializeMap");
        NQueensFitnessFunction instance = null;
        instance.initializeMap();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of fitnessFunction method, of class NQueensFitnessFunction.
     */
    @Test
    public void testFitnessFunction() {
        System.out.println("fitnessFunction");
        int[] value = new int[]{1,2,3,1};
        double result = 0;
        NQueensFitnessFunction instance = new NQueensFitnessFunction(4);
        instance.initializeMap();
        double expResult = 200;
        result = (double)instance.fitnessFunction(value);
        assertEquals(expResult, result, 0);
        
        value = new int[]{0,1,2,3};
        expResult = 0;
        result = (double)instance.fitnessFunction(value);
        assertEquals(expResult, result, 0);
        
        value = new int[]{1,1,1,1};
        expResult = 0;
        result = (double)instance.fitnessFunction(value);
        assertEquals(expResult, result, 0);
        
        value = new int[]{2,0,3,1};
        expResult = 600;
        result = (double)instance.fitnessFunction(value);
        assertEquals(expResult, result, 0);
        // TODO review the generated test code and remove the default call to fail.
    }
    
}
