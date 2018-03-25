/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package geneticalgorithm.crossover.neuralcrossover;

import geneticalgorithm.Individual;
import geneticalgorithm.neuralnetwork.utilities.DrawNeuralNetwork;
import geneticalgorithm.examples.MLPNeuralNetwork.wordguess.NeuralNetworkFitnessFunction;
import geneticalgorithm.neuralnetwork.utilities.NeuralNetworkPanel;
import geneticalgorithm.neuralnetwork.utilities.NeuralNetworkUtilities;
import geneticalgorithm.neuralnetwork.NeuralNetwork;
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
public class NeuralNetworkCrossoverTest {
    
    public NeuralNetworkCrossoverTest() {
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
     * Test of crossover method, of class NeuralNetworkCrossover.
     */
    @Test
    public void testCrossover() {
        NeuralNetwork firstNet = new NeuralNetwork();
        NeuralNetwork secondNet = new NeuralNetwork();
        double[] inputTrainData = new double[]{1.0,0.0,0.0,1.0};
        NeuralNetworkFitnessFunction function = new NeuralNetworkFitnessFunction(new double[]{1});
        NeuralNetworkUtilities.initTree(firstNet, inputTrainData, 2);
        NeuralNetworkUtilities.addHiddenNodes(firstNet, new int[]{2,2});
        
        NeuralNetworkUtilities.initTree(secondNet, inputTrainData, 2);
        NeuralNetworkUtilities.addHiddenNodes(secondNet, new int[]{4, 4});
        Individual first = new Individual(firstNet , 0.0);
        Individual second = new Individual(secondNet, 0.0);
        NeuralNetworkCrossover instance = new NeuralNetworkCrossover(function, 100);
        NeuralNetwork expResult = null;
        Individual result = instance.crossover(first, second);

        firstNet = new NeuralNetwork();
        NeuralNetworkUtilities.initTree(firstNet, inputTrainData, 2);
        NeuralNetworkUtilities.addHiddenNodes(firstNet, new int[]{2});
        
        secondNet = new NeuralNetwork();
        NeuralNetworkUtilities.initTree(secondNet, inputTrainData, 2);
        NeuralNetworkUtilities.addHiddenNodes(secondNet, new int[]{4,4,5,5,5,5,5,5});
        
        result = instance.crossover(first, second);
        
        firstNet = new NeuralNetwork();
        NeuralNetworkUtilities.initTree(firstNet, inputTrainData, 2);
        NeuralNetworkUtilities.addHiddenNodes(firstNet, new int[]{});
        
        secondNet = new NeuralNetwork();
        NeuralNetworkUtilities.initTree(secondNet, inputTrainData, 2);
        NeuralNetworkUtilities.addHiddenNodes(secondNet, new int[]{4,4,5,5,5,5,5,5});
        
        result = instance.crossover(first, second); 
        
        firstNet = new NeuralNetwork();
        NeuralNetworkUtilities.initTree(firstNet, inputTrainData, 2);
        NeuralNetworkUtilities.addHiddenNodes(firstNet, new int[]{});
        
        secondNet = new NeuralNetwork();
        NeuralNetworkUtilities.initTree(secondNet, inputTrainData, 2);
        NeuralNetworkUtilities.addHiddenNodes(secondNet, new int[]{});
        
        result = instance.crossover(first, second); 
       
        firstNet = new NeuralNetwork();
        NeuralNetworkUtilities.initTree(firstNet, inputTrainData, 2);
        NeuralNetworkUtilities.addHiddenNodes(firstNet, new int[]{2,32,32,32222,332,2,2,2,2});
        
        secondNet = new NeuralNetwork();
        NeuralNetworkUtilities.initTree(secondNet, inputTrainData, 2);
        NeuralNetworkUtilities.addHiddenNodes(secondNet, new int[]{2,2,2222,2,2,2,2});
        
        result = instance.crossover(first, second);         
        
        
//        DrawNeuralNetwork draw = new DrawNeuralNetwork(result);
//        NeuralNetworkPanel panel = new NeuralNetworkPanel();
//        panel.initPanel();
//        panel.refreshPanel(draw);
        
//        DrawNeuralNetwork draw2 = new DrawNeuralNetwork(saveFirst);
//        panel.initPanel();
//        panel.refreshPanel(draw2);
//        panel.run();
//        
//        DrawNeuralNetwork draw3 = new DrawNeuralNetwork(saveSecond);
//        panel.initPanel();
//        panel.refreshPanel(draw3);
//        panel.run();
        //assertEquals(expResult, result);
        

        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }
    
    private void drawNetwork(){
        
    }
}
