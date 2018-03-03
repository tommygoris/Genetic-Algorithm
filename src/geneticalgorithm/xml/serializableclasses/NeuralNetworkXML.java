/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package geneticalgorithm.xml.serializableclasses;
import geneticalgorithm.neuralnetwork.NeuralNetwork;
import geneticalgorithm.neuralnetwork.Node;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class NeuralNetworkXML {
     public Node[] inputs;
     public Node[] outputs;
     public Node[][] hiddenNodes;
     public Node[] bias;
     private double biasValue = 1;
     
     public NeuralNetworkXML(Node[] inputs, Node[] outputs, Node[][] hiddenNodes){
         this.inputs = inputs;
         this.outputs = outputs;
         this.hiddenNodes = hiddenNodes;
    }
     
    public NeuralNetworkXML(Node[] inputs, Node[] outputs){
        this.inputs = inputs;
        this.outputs = outputs;
    }
     
    public NeuralNetworkXML(){
    }
     
    public NeuralNetworkXML(NeuralNetwork copy){
        this.inputs = copy.inputs;
        this.outputs = copy.outputs;
        this.hiddenNodes = copy.hiddenNodes;
    }
    
}
