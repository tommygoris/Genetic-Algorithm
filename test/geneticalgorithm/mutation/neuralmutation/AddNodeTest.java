/*
 * To change NeuralNetworkUtilities license header, choose License Headers in Project Properties.
 * To change NeuralNetworkUtilities template file, choose Tools | Templates
 * and open the template in the editor.
 */
package geneticalgorithm.mutation.neuralmutation;

import geneticalgorithm.neuralnetwork.utilities.DrawNeuralNetwork;
import geneticalgorithm.neuralnetwork.utilities.NeuralNetworkPanel;
import geneticalgorithm.neuralnetwork.utilities.NeuralNetworkUtilities;
import geneticalgorithm.neuralnetwork.NeuralNetwork;
import geneticalgorithm.neuralnetwork.Node;
import java.util.concurrent.ThreadLocalRandom;
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
public class AddNodeTest {
    
    public AddNodeTest() {
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
     * Test of addNode method, of class AddNode.
     */
    @Test
    public void testAddNode() throws InterruptedException {
//        System.out.println("addNode");
//        double[] inputTrainData = new double[]{1.0,0.0,0.0,1.0};
//        int[] numNodes = new int[]{};
//        int layer = 0;
//        boolean newLayer = true;
//        
//        NeuralNetwork net = new NeuralNetwork();
//        net = NeuralNetworkUtilities.initTree(net, inputTrainData, 1);
//        net = NeuralNetworkUtilities.addHiddenNodes(net, numNodes);
//
//        AddNode instance = new AddNode();
//        
//        NeuralNetwork expResult = new NeuralNetwork();
//        expResult = NeuralNetworkUtilities.initTree(expResult, inputTrainData, 1);
//        expResult = NeuralNetworkUtilities.addHiddenNodes(expResult, new int[]{1});
//        
//        NeuralNetwork result = instance.addNode(net, layer, newLayer);
//        assertTrue(NeuralNetworkUtilities.checkNet(result, expResult));
//        
//        numNodes = new int[]{3,3,3};
//        layer = 3;
//        newLayer = true;       
//        
//        net = new NeuralNetwork();
//        net = NeuralNetworkUtilities.initTree(net, inputTrainData, 1);
//        net = NeuralNetworkUtilities.addHiddenNodes(net, numNodes);
//        
//        expResult = new NeuralNetwork();
//        expResult = NeuralNetworkUtilities.initTree(expResult, inputTrainData, 1);
//        expResult = NeuralNetworkUtilities.addHiddenNodes(expResult, new int[]{3,3,3,1});        
//        
//        result = instance.addNode(net, layer, newLayer);
//        assertTrue(NeuralNetworkUtilities.checkNet(result, expResult));
//        
//        numNodes = new int[]{3,3,3,5,2,2,3,1,23,4,4,2};
//        layer = 3;
//        newLayer = true;       
//        
//        net = new NeuralNetwork();
//        net = NeuralNetworkUtilities.initTree(net, inputTrainData, 1);
//        net = NeuralNetworkUtilities.addHiddenNodes(net, numNodes);
//        
//        expResult = new NeuralNetwork();
//        expResult = NeuralNetworkUtilities.initTree(expResult, inputTrainData, 1);
//        expResult = NeuralNetworkUtilities.addHiddenNodes(expResult, new int[]{3,3,3,1,5,2,2,3,1,23,4,4,2});        
//        
//        result = instance.addNode(net, layer, newLayer);
//        assertTrue(NeuralNetworkUtilities.checkNet(result, expResult));    
//        
//        numNodes = new int[]{3,3,3,5,2,2,3,1,23,4,4,2};
//        layer = 3;
//        newLayer = false;       
//        
//        net = new NeuralNetwork();
//        net = NeuralNetworkUtilities.initTree(net, inputTrainData, 1);
//        net = NeuralNetworkUtilities.addHiddenNodes(net, numNodes);
//        
//        expResult = new NeuralNetwork();
//        expResult = NeuralNetworkUtilities.initTree(expResult, inputTrainData, 1);
//        expResult = NeuralNetworkUtilities.addHiddenNodes(expResult, new int[]{3,3,3,6,2,2,3,1,23,4,4,2});        
//        
//        result = instance.addNode(net, layer, newLayer);
//        assertTrue(NeuralNetworkUtilities.checkNet(result, expResult)); 
//        
//        numNodes = new int[]{3,3,3,5,2,2,3,1,23,4,4,2};
//        layer = 0;
//        newLayer = false;       
//        
//        net = new NeuralNetwork();
//        net = NeuralNetworkUtilities.initTree(net, inputTrainData, 1);
//        net = NeuralNetworkUtilities.addHiddenNodes(net, numNodes);
//        
//        expResult = new NeuralNetwork();
//        expResult = NeuralNetworkUtilities.initTree(expResult, inputTrainData, 1);
//        expResult = NeuralNetworkUtilities.addHiddenNodes(expResult, new int[]{4,3,3,5,2,2,3,1,23,4,4,2});        
//        
//        result = instance.addNode(net, layer, newLayer);
//        assertTrue(NeuralNetworkUtilities.checkNet(result, expResult));  
//        
//        numNodes = new int[]{3,3,3,5,2,2,3,1,23,4,4,2};
//        layer = numNodes.length - 1;
//        newLayer = false;       
//        
//        net = new NeuralNetwork();
//        net = NeuralNetworkUtilities.initTree(net, inputTrainData, 1);
//        net = NeuralNetworkUtilities.addHiddenNodes(net, numNodes);
//        
//        expResult = new NeuralNetwork();
//        expResult = NeuralNetworkUtilities.initTree(expResult, inputTrainData, 1);
//        expResult = NeuralNetworkUtilities.addHiddenNodes(expResult, new int[]{3,3,3,5,2,2,3,1,23,4,4,3});        
//        
//        result = instance.addNode(net, layer, newLayer);
//        assertTrue(NeuralNetworkUtilities.checkNet(result, expResult)); 
//        
//        numNodes = new int[]{3,3,3,5,2,2,3,1,23,4,4,2};
//        layer = 7;
//        newLayer = false;       
//        
//        net = new NeuralNetwork();
//        net = NeuralNetworkUtilities.initTree(net, inputTrainData, 1);
//        net = NeuralNetworkUtilities.addHiddenNodes(net, numNodes);
//        
//        expResult = new NeuralNetwork();
//        expResult = NeuralNetworkUtilities.initTree(expResult, inputTrainData, 1);
//        expResult = NeuralNetworkUtilities.addHiddenNodes(expResult, new int[]{3,3,3,5,2,2,3,2,23,4,4,2});        
//        
//        result = instance.addNode(net, layer, newLayer);
//        assertTrue(NeuralNetworkUtilities.checkNet(result, expResult));   
//        
//        numNodes = new int[]{3,3,3,5,2,2,3,1,23,4,4,2};
//        layer = 7;
//        newLayer = true;       
//        
//        net = new NeuralNetwork();
//        net = NeuralNetworkUtilities.initTree(net, inputTrainData, 1);
//        net = NeuralNetworkUtilities.addHiddenNodes(net, numNodes);
//        
//        expResult = new NeuralNetwork();
//        expResult = NeuralNetworkUtilities.initTree(expResult, inputTrainData, 1);
//        expResult = NeuralNetworkUtilities.addHiddenNodes(expResult, new int[]{3,3,3,5,2,2,3,1,1,23,4,4,2});        
//        
//        result = instance.addNode(net, layer, newLayer);
//        assertTrue(NeuralNetworkUtilities.checkNet(result, expResult));  
//
//        numNodes = new int[]{3,3,3,5,2,2,3,1,23,4,4,2};
//        layer = 0;
//        newLayer = true;       
//        
//        net = new NeuralNetwork();
//        net = NeuralNetworkUtilities.initTree(net, inputTrainData, 1);
//        net = NeuralNetworkUtilities.addHiddenNodes(net, numNodes);
//        
//        expResult = new NeuralNetwork();
//        expResult = NeuralNetworkUtilities.initTree(expResult, inputTrainData, 1);
//        expResult = NeuralNetworkUtilities.addHiddenNodes(expResult, new int[]{1,3,3,3,5,2,2,3,1,23,4,4,2});        
//        
//        result = instance.addNode(net, layer, newLayer);
//        assertTrue(NeuralNetworkUtilities.checkNet(result, expResult));     
//        
//        numNodes = new int[]{3,3,3,5,2,2,3,1,23,4,4,2};
//        layer = numNodes.length;
//        newLayer = true;       
//        
//        net = new NeuralNetwork();
//        net = NeuralNetworkUtilities.initTree(net, inputTrainData, 1);
//        net = NeuralNetworkUtilities.addHiddenNodes(net, numNodes);
//        
//        expResult = new NeuralNetwork();
//        expResult = NeuralNetworkUtilities.initTree(expResult, inputTrainData, 1);
//        expResult = NeuralNetworkUtilities.addHiddenNodes(expResult, new int[]{3,3,3,5,2,2,3,1,23,4,4,2,1});        
//        
//        result = instance.addNode(net, layer, newLayer);
//        assertTrue(NeuralNetworkUtilities.checkNet(result, expResult));       
//        
//        numNodes = new int[]{3,5};
//        layer = 1;
//        newLayer = false;       
//        
//        net = new NeuralNetwork();
//        net = NeuralNetworkUtilities.initTree(net, inputTrainData, 1);
//        net = NeuralNetworkUtilities.addHiddenNodes(net, numNodes);
//        
//        NeuralNetwork checkNet = new NeuralNetwork(net);
//        
//        expResult = new NeuralNetwork();
//        expResult = NeuralNetworkUtilities.initTree(expResult, inputTrainData, 1);
//        expResult = NeuralNetworkUtilities.addHiddenNodes(expResult, new int[]{3,6});        
//        
//        result = instance.addNode(net, layer, newLayer);
//        assertTrue(NeuralNetworkUtilities.checkNet(result, expResult));   
//        
//        DrawNeuralNetwork draw = new DrawNeuralNetwork(result);
//        NeuralNetworkPanel panel = new NeuralNetworkPanel();
        //panel.initPanel();
        //panel.refreshPanel(draw);        
    }
    
    private boolean checkNet(NeuralNetwork firstNet, NeuralNetwork secondNet){
        if (firstNet.inputs.length == secondNet.inputs.length){
            if (firstNet.hiddenNodes.length == secondNet.hiddenNodes.length){
                for (int i = 0; i<firstNet.hiddenNodes.length; i++){
                    if (firstNet.hiddenNodes[i].length != secondNet.hiddenNodes[i].length){
                        return false;                    
                    }
                }
                return firstNet.outputs.length == secondNet.outputs.length;                
            }
        }              
        return false;
    }
}
