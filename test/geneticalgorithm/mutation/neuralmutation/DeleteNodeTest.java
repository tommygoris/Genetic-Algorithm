/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package geneticalgorithm.mutation.neuralmutation;

import geneticalgorithm.Individual;
import geneticalgorithm.Population;
import geneticalgorithm.crossover.neuralcrossover.NeuralNetworkCrossover;
import geneticalgorithm.neuralnetwork.utilities.DrawNeuralNetwork;
import geneticalgorithm.neuralnetwork.utilities.NeuralNetworkPanel;
import geneticalgorithm.neuralnetwork.utilities.NeuralNetworkUtilities;
import geneticalgorithm.neuralnetwork.NeuralNetwork;
import geneticalgorithm.problem.ProblemInterface;
import geneticalgorithm.selections.TournamentSelection;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Tommy
 */
public class DeleteNodeTest {
    
    public DeleteNodeTest() {
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
     * Test of deleteNode method, of class DeleteNode.
     */
    @Test
    public void testDeleteNode() {
        NeuralNetwork net = new NeuralNetwork();
        double[] inputTrainData = new double[]{1.0,0.0,0.0,1.0};    
        int layerOfNode = 0;
        int locationOfNode = 0;
        
        
        net = NeuralNetworkUtilities.initTree(net, inputTrainData, 1);
        net = NeuralNetworkUtilities.addHiddenNodes(net, new int[]{2,2,2,2,2,2});       
        ProblemInterface fitnessFunction = null;
        Individual ind = new Individual(net, 0.0);
        Individual[] newIndPop = new Individual[1];
        newIndPop[0] = ind;
        TournamentSelection tournament = new TournamentSelection(7, 0.75);
        NeuralNetworkCrossover crossover = new NeuralNetworkCrossover(fitnessFunction, 0.75);
        Population pop = new Population(newIndPop, crossover);
        DeleteNode instance = new DeleteNode();
        NeuralNetwork expResult = new NeuralNetwork();
        expResult = NeuralNetworkUtilities.initTree(expResult, inputTrainData, 1);
        expResult = NeuralNetworkUtilities.addHiddenNodes(expResult, new int[]{2,2,2,2,2,1});
        
        newIndPop = instance.mutate(pop);
        assertTrue(NeuralNetworkUtilities.checkNet(expResult, net));
        
//        DrawNeuralNetwork draw = new DrawNeuralNetwork(newn);
//        NeuralNetworkPanel panel = new NeuralNetworkPanel();
//        panel.initPanel();
//        panel.refreshPanel(draw);   
        
    }
    
}
