/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package geneticalgorithm.strategies;

import geneticalgorithm.Individual;
import geneticalgorithm.Population;
import geneticalgorithm.crossover.StringCrossover;
import geneticalgorithm.examples.onemax.OneMaxFitnessFunction;
import geneticalgorithm.examples.onemax.OneMaxProblem;
import geneticalgorithm.selections.TournamentSelection;
import java.util.concurrent.ThreadLocalRandom;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author wgoris31
 */
public class ElitismStrategyTest {
    final OneMaxFitnessFunction fitnessFunction = new OneMaxFitnessFunction();
    int populationSize = 100;
    int lengthOfProblem = 20;
    
    public ElitismStrategyTest() {
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
     * Test of Strategy method, of class ElitismStrategy.
     */
    @Test
    public void testStrategy() {
        TournamentSelection tournament = new TournamentSelection(7, 0.95);
        StringCrossover crossover = new StringCrossover(199, fitnessFunction, 1);
        //Population pop = new Population(OneMaxProblem.createRandomPopulation(), crossover);
        System.out.println("Strategy");
        //Population pop = null;
        ElitismStrategy instance = new ElitismStrategy(10);
        //instance.Strategy(pop);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getBestPop method, of class ElitismStrategy.
     */
    @Test
    public void testGetBestPop() {
        System.out.println("getBestPop");
        ElitismStrategy instance = null;
        Individual[] expResult = null;
        //Individual[] result = instance.getbestPop(pop);
        //assertArrayEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
    public Individual[] createRandomPopulation(){
        Individual[] population = new Individual[this.populationSize];
        for (int i = 0; i <populationSize; i++){
            population[i] = createRandomIndividual();    
        }
        return population;
    }
    
    private  Individual createRandomIndividual(){
        StringBuilder oneMax = new StringBuilder();
        for (int i = 0; i<lengthOfProblem; i++){
            oneMax.append(ThreadLocalRandom.current().nextDouble() > 0.5 ? 1 : 0);
        }
        double fitness = (double)fitnessFunction.fitnessFunction(oneMax.toString());
        return new Individual(oneMax.toString(), fitness);
    }
    
    private static void printPopulation(Population pop){
        for (int i = 0; i<pop.population.length; i++){
            System.out.println(pop.population[i].individual);
        }
    }
    
    
}
