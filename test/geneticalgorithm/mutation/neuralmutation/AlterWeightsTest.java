/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package geneticalgorithm.mutation.neuralmutation;

import geneticalgorithm.Individual;
import geneticalgorithm.Population;
import geneticalgorithm.crossover.neuralcrossover.NeuralNetworkCrossover;
import geneticalgorithm.examples.evolveann.NeuralNetworkUtilities;
import geneticalgorithm.examples.evolveann.sinwave.SinWaveFitnessFunction;
import geneticalgorithm.examples.evolveann.sinwave.SinWaveNeuralNetwork;
import geneticalgorithm.neuralnetwork.NeuralNetwork;
import geneticalgorithm.selections.TournamentSelection;
import geneticalgorithm.strategies.ElitismStrategy;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import javafx.util.Pair;
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
public class AlterWeightsTest {
    
    
    private static final int populationSize = 15;
    private static final int inputSize = 50;
    private static final int startingNodes = 1;
    private static final int outputNodes = 1;
    private static SinWaveFitnessFunction fitnessFunction = null;
    
    public AlterWeightsTest() {
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
     * Test of mutate method, of class AlterWeights.
     */
    @Test
    public void testMutate() {        
        NeuralNetwork net = new NeuralNetwork();
        net = NeuralNetworkUtilities.initTree(net, new Double[]{1.0, 1.0, 1.0, 1.0, 1.0}, 1);
        net = NeuralNetworkUtilities.addHiddenNodes(net, new int[]{5,5});
        Individual[] individuals = new Individual[1];
        individuals[0] = new Individual(net, 0);
        fitnessFunction = new SinWaveFitnessFunction((new Double[0]), (new Double[0][0]));
        NeuralNetworkCrossover crossover = new NeuralNetworkCrossover(fitnessFunction, .80);
        System.out.println("mutate");
        AlterWeights alterWeights = new AlterWeights(fitnessFunction, 1.0);
        Population pop = new Population(individuals, crossover);
        alterWeights.mutate(pop);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
   
}
