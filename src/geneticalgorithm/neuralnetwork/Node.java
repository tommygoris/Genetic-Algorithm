/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package geneticalgorithm.neuralnetwork;

import geneticalgorithm.Individual;
import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.Objects;
import java.util.concurrent.ThreadLocalRandom;

public class Node{
        public double val;
        public Node branch[];
        public double connection[];
        
        public Node(Node copyNode){
            this.val = copyNode.val;
            this.branch = copyNode.branch;
            this.connection = copyNode.connection;
        }
        public Node(double val){
            this.val = val;
        }
        
        public Node(){
            this.val = ThreadLocalRandom.current().nextGaussian();
        }
        
    @Override
    public boolean equals(Object obj){
        Node nodeObj = (Node)obj;      
        return Objects.equals(nodeObj.val, this.val);
    }
    
    @Override
    public String toString(){
        DecimalFormat df = new DecimalFormat("#.##");
        return df.format(this.val);
    }
}
