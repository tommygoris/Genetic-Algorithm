/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package geneticalgorithm.selections;

import geneticalgorithm.Individual;
import geneticalgorithm.Population;
import geneticalgorithm.crossover.StringCrossover;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author TommyGoris
 */
public class RankSelectionTest {
    
    public RankSelectionTest() {
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
     * Test of selectIndividual method, of class RankSelection.
     */
    @Test
    public void testSelectIndividual() {
        System.out.println("selectIndividual");
        Individual[] indv = new Individual[10];
        for (int i = 0; i < indv.length; i++){
            indv[i] = new Individual("111", 3);
        }
        Population pop = new Population(indv, new StringCrossover(3, null, 0.5));
        RankSelection instance = new RankSelection();
        Individual expResult = null;
        Individual result = instance.selectIndividual(pop);
    }
    
}
