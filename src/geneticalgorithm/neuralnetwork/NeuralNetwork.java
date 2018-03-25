/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package geneticalgorithm.neuralnetwork;

import geneticalgorithm.neuralnetwork.utilities.DrawNeuralNetwork;
import java.awt.Graphics;
import java.util.concurrent.ThreadLocalRandom;
import javax.swing.JFrame;


/**
 *
 * @author Tommy
 */
public class NeuralNetwork {
     public Node[] inputs;
     public Node[] outputs;
     public Node[][] hiddenNodes;
     public Node[] bias;
     private final double biasValue = 1;
     
     public NeuralNetwork(Node[] inputs, Node[] outputs, Node[][] hiddenNodes){
         this.inputs = inputs;
         this.outputs = outputs;
         this.hiddenNodes = hiddenNodes;
    }
     
    public NeuralNetwork(Node[] inputs, Node[] outputs){
        this.inputs = inputs;
        this.outputs = outputs;
    }
     
    public NeuralNetwork(){
    }
     
    public NeuralNetwork(NeuralNetwork copy){
        this.inputs = copy.inputs;
        this.outputs = copy.outputs;
        this.hiddenNodes = copy.hiddenNodes;
    }
    
    public void createBias(){
        if (this.hiddenNodes != null){
           this.bias = new Node[this.hiddenNodes.length + 1];
           for (int i = 0; i<bias.length - 1; i++){
               bias[i] = new Node(biasValue);
               bias[i].connection = new double[this.hiddenNodes[i].length];
               for (int x = 0; x<this.hiddenNodes[i].length; x++){
                   bias[i].connection[x] = ThreadLocalRandom.current().nextGaussian();
               }
           }
           bias[bias.length - 1] = new Node(biasValue);
           bias[bias.length - 1].connection = new double[this.outputs.length];
           for (int i = 0; i <this.outputs.length; i++){
               bias[bias.length - 1].connection[i] = ThreadLocalRandom.current().nextGaussian();
           }
        }
        else {
            this.bias = new Node[1];
            bias[0] = new Node(biasValue);
            bias[0].connection = new double[this.outputs.length];
            for (int x = 0; x<this.outputs.length; x++){
                bias[0].connection[x] = ThreadLocalRandom.current().nextGaussian();
            }
        }
    }
}
    