/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package geneticalgorithm.examples.evolveann;

import geneticalgorithm.neuralnetwork.NeuralNetwork;
import geneticalgorithm.neuralnetwork.Node;
import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

/**
 *
 * @author Tommy
 */
public class NeuralNetworkUtilities {
    
    public static NeuralNetwork initTree(NeuralNetwork net, Double[] inputTrainData, int outputs){
        if (inputTrainData.length == 0 || outputs==0){
            System.out.println("Please use correct input/output data");
            return null;
        }
        net.inputs = new Node[inputTrainData.length];
        net.outputs = new Node[outputs];
        
        for (int i = 0; i<inputTrainData.length; i++){
            net.inputs[i] = new Node(inputTrainData[i]);
        }
        
        for (int i = 0; i<net.inputs.length; i++){
            net.inputs[i].branch = new Node[outputs];
            net.inputs[i].connection = new double[outputs];
             for (int j=0; j<net.outputs.length; j++){

                 net.inputs[i].connection[j] = ThreadLocalRandom.current().nextGaussian();
                 net.inputs[i].branch[j] = net.outputs[j]; 
             }
        }
        return net;
    }

    public static NeuralNetwork addHiddenNodes(NeuralNetwork net, int[] numNodes){
        
        if (numNodes == null || numNodes.length == 0){
            return net;
        }
        for (int i = 0; i<numNodes.length; i++){
            if (numNodes[i] == 0){
                return net;
            }
        }
        net.hiddenNodes = new Node[numNodes.length][];
        for (int i = 0; i<numNodes.length; i++){
            net.hiddenNodes[i] = new Node[numNodes[i]];
        }
        for (int i = 0; i<net.hiddenNodes.length; i++){
            for (int j = 0; j<net.hiddenNodes[i].length; j++){
                net.hiddenNodes[i][j] = new Node(ThreadLocalRandom.current().nextGaussian());
            }
        }

        
        for (int i = 0; i<net.inputs.length; i++){
           net.inputs[i].branch = new Node[numNodes[0]];
           net.inputs[i].connection = new double[numNodes[0]];
            for (int j = 0; j<net.hiddenNodes[0].length; j++){
                net.inputs[i].connection[j] = ThreadLocalRandom.current().nextGaussian();
                net.inputs[i].branch[j] = net.hiddenNodes[0][j]; 
            }
        }
        
        
        
        for (int i = 0; i<net.hiddenNodes.length-1; i++){
            for (int j = 0; j<net.hiddenNodes[i].length; j++){
                net.hiddenNodes[i][j].branch = new Node[net.hiddenNodes[i+1].length];
                net.hiddenNodes[i][j].connection = new double[net.hiddenNodes[i+1].length];
                
                for (int f = 0; f<net.hiddenNodes[i+1].length; f++){
                    net.hiddenNodes[i][j].connection[f] = ThreadLocalRandom.current().nextGaussian();
                    net.hiddenNodes[i][j].branch[f] = net.hiddenNodes[i+1][f];
                }
            
            }
        }
        for (int i = 0; i<net.hiddenNodes[net.hiddenNodes.length-1].length; i++){
            net.hiddenNodes[net.hiddenNodes.length-1][i].branch = new Node[net.outputs.length];
            net.hiddenNodes[net.hiddenNodes.length-1][i].connection = new double[net.outputs.length];
            for (int j = 0; j<net.outputs.length; j++){

                net.hiddenNodes[net.hiddenNodes.length-1][i].connection[j] = ThreadLocalRandom.current().nextGaussian();
                net.hiddenNodes[net.hiddenNodes.length-1][i].branch[j] = net.outputs[j]; 
            }
        }
        return net;
    }
    
    public static boolean checkNet(NeuralNetwork firstNet, NeuralNetwork secondNet){
        if (firstNet.inputs.length == secondNet.inputs.length){
            if (firstNet.hiddenNodes == null && secondNet.hiddenNodes == null){
                return true;
            }
            else if (firstNet.hiddenNodes == null && secondNet.hiddenNodes != null){
                return false;
            }
            else if (firstNet.hiddenNodes != null && secondNet.hiddenNodes == null){
                return false;
            }
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
    
    public static boolean deepCheckNet(NeuralNetwork firstNet, NeuralNetwork secondNet){
        if (!NeuralNetworkUtilities.checkNet(firstNet, secondNet)){
            return false;
        }
        for (int i = 0; i<firstNet.hiddenNodes.length; i++){
            for (int j = 0; j<firstNet.hiddenNodes[i].length; j++){
                if (firstNet.hiddenNodes[i][j] != secondNet.hiddenNodes[i][j]){
                    return false;
                }
            }
        }
        return true;
    }
    
    public static NeuralNetwork redoBranches(NeuralNetwork net){
        if (net.hiddenNodes == null || net.hiddenNodes.length == 0){
            for (int i = 0; i<net.inputs.length; i++){
                net.inputs[i].branch = new Node[net.outputs.length];
                net.inputs[i].connection = new double[net.outputs.length];
                for (int x = 0; x<net.outputs.length; x++){
                    net.inputs[i].branch[x] = net.outputs[x];
                    net.inputs[i].connection[x] = ThreadLocalRandom.current().nextGaussian();
                }
            }
            return net;
        }
        for (int i = 0; i<net.inputs.length; i++){
            net.inputs[i].branch = new Node[net.hiddenNodes[0].length];
            net.inputs[i].connection = new double[net.hiddenNodes[0].length];
            for (int x = 0; x<net.hiddenNodes[0].length; x++){
                net.inputs[i].branch[x] = net.hiddenNodes[0][x];
                net.inputs[i].connection[x] = ThreadLocalRandom.current().nextGaussian();
            }
        }
        
        for (int i = 0; i<net.hiddenNodes.length - 1; i++){
            for (int x = 0; x<net.hiddenNodes[i].length; x++){
                net.hiddenNodes[i][x].branch = new Node[net.hiddenNodes[i+1].length];
                net.hiddenNodes[i][x].connection = new double[net.hiddenNodes[i+1].length];
                for (int j = 0; j<net.hiddenNodes[i + 1].length; j++){
                    net.hiddenNodes[i][x].branch[j] = net.hiddenNodes[i+1][j];
                    net.hiddenNodes[i][x].connection[j] = ThreadLocalRandom.current().nextGaussian();
                }
            }
        }
        
        for (int i = 0; i<net.hiddenNodes[net.hiddenNodes.length - 1].length; i++){
            net.hiddenNodes[net.hiddenNodes.length - 1][i].branch = new Node[net.outputs.length];
            net.hiddenNodes[net.hiddenNodes.length - 1][i].connection = new double[net.outputs.length];
            for (int x = 0; x<net.outputs.length; x++){
                net.hiddenNodes[net.hiddenNodes.length - 1][i].branch[x] = net.outputs[x];
                net.hiddenNodes[net.hiddenNodes.length - 1][i].connection[x] = ThreadLocalRandom.current().nextGaussian();
            }            
        }
        
        if (net.hiddenNodes.length == 1 && net.inputs[0].branch.length != net.hiddenNodes[0].length){
            System.out.println("problem");
        }
        return net;
    }
    
    public static void reconnectLayer(Node[] fromLayer, Node[] toLayer){
        
        for (int i = 0; i<fromLayer.length; i++){
            fromLayer[i].branch = new Node[toLayer.length];
            fromLayer[i].connection = new double[toLayer.length];
            for (int x = 0; x<toLayer.length; x++){
                fromLayer[i].branch[x] = toLayer[x];
                fromLayer[i].connection[x] = ThreadLocalRandom.current().nextGaussian();
            }
        }
    }
    
    public static void reconnentLayerSaveWeights(Node[] fromLayer, Node[] toLayer){
        double[] saveWeights = new double[toLayer.length];     
        for (int i = 0; i<fromLayer.length; i++){
            for (int x = 0; x<saveWeights.length; x++){
                if (fromLayer[i].connection == null || fromLayer[i].connection.length <= x){
                    saveWeights[x] =  ThreadLocalRandom.current().nextGaussian();
                }
                else {
                    saveWeights[x] = fromLayer[i].connection[x];
                }
            }
            fromLayer[i].branch = new Node[toLayer.length];
            fromLayer[i].connection = new double[toLayer.length];
                     
            for (int x = 0; x<toLayer.length; x++){
                fromLayer[i].branch[x] = toLayer[x];
            }
            fromLayer[i].connection = saveWeights;
        }
    }
    
    public static ArrayList<ArrayList<Node>> toList(Node[][] node){
        ArrayList<ArrayList<Node>> newList = new ArrayList<>();           
        for (int i = 0; i <node.length; i++){
            ArrayList<Node> interList = new ArrayList<Node>();        
            for (int x = 0; x<node[i].length; x++){
                interList.add(node[i][x]);
            }
            newList.add(interList);
        }
        return newList;
    }
    
}
