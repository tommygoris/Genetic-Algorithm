/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package geneticalgorithm.xml;

import geneticalgorithm.neuralnetwork.NeuralNetwork;
import geneticalgorithm.neuralnetwork.Node;

/**
 *
 * @author Tommy
 */
public class NewClass {
    
    public static void main(String[] args){
        XMLWriter write = new XMLWriter();
        NeuralNetwork net = new NeuralNetwork();
        net.inputs = new Node[2];
        net.hiddenNodes = new Node[2][2];
        net.outputs = new Node[2];
        net.inputs[0] = new Node(11);
        net.inputs[0].branch = new Node[1];
        net.inputs[0].branch[0] = new Node(1);
        write.writeNeuralNetwork(net, "name", "testing");
        
        XMLReader read = new XMLReader();
        read.readNeuralNetworkXML("name", "testing");
        
        
    }
}
