/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package geneticalgorithm.mutation.neuralmutation;

import geneticalgorithm.Individual;
import geneticalgorithm.Population;
import geneticalgorithm.examples.evolveann.EvolveNeuralNetwork;
import geneticalgorithm.examples.evolveann.NeuralNetworkUtilities;
import geneticalgorithm.mutation.MutationInterface;
import geneticalgorithm.neuralnetwork.NeuralNetwork;
import geneticalgorithm.neuralnetwork.Node;
import geneticalgorithm.problem.ProblemInterface;
import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;

/**
 *
 * @author Tommy
 */
public class AddNode implements MutationInterface {
    private final ProblemInterface fitnessFunction;
    private final double mutationRate;
    public AddNode(ProblemInterface<NeuralNetwork> fitnessFunction, double mutationRate){
        this.fitnessFunction = fitnessFunction;
        this.mutationRate = mutationRate;
    }
    
    @Override 
    public Individual[] mutate(Population pop){
        Individual[] newPop = new Individual[pop.population.length];
        EvolveNeuralNetwork.check(pop, "no idea");
        for (int ind = 0; ind<pop.population.length; ind++){
            if (this.mutationRate > ThreadLocalRandom.current().nextDouble()){
                newPop[ind] = pop.population[ind];
                continue;
            }
            
            
            EvolveNeuralNetwork.check(pop, "inside wtf" + " " + ind);
            NeuralNetwork net = (NeuralNetwork)pop.population[ind].individual;

            boolean newLayer = (ThreadLocalRandom.current().nextDouble() > 0.5 || (net.hiddenNodes == null || net.hiddenNodes.length == 0));
            Node[][] newNode = null;
            /// need to add node on a new hidden node layer. In this case, creates a new hidden node layer and adds the node.
            if (newLayer){
                // In the case where our neural network has no hidden nodes. We want to always add a new layer to the network between
                // the inputs and outputs. The boolean newLayer will be forced to true if this is the case, so this if statement
                // always occurs.
                if (net.hiddenNodes == null || net.hiddenNodes.length == 0){
                    newNode = new Node[1][1]; 
                    newNode[0][0] = new Node(ThreadLocalRandom.current().nextGaussian());

                    for (int i = 0; i<net.inputs.length; i++){
                        net.inputs[i].branch = new Node[1];
                        net.inputs[i].connection = new double[1];

                        net.inputs[i].branch[0] = newNode[0][0];
                        net.inputs[i].connection[0] = ThreadLocalRandom.current().nextGaussian();
                    }

                    newNode[0][0].branch = new Node[net.outputs.length];
                    newNode[0][0].connection = new double[net.outputs.length];

                    for (int i = 0; i<net.outputs.length; i++){
                        newNode[0][0].branch[i] = net.outputs[i];
                        newNode[0][0].connection[i] = ThreadLocalRandom.current().nextGaussian();                    
                    }
                    net.hiddenNodes = newNode;
                    NeuralNetworkUtilities.redoBranches(net);
                    net.createBias();
                    // make new indv...
                    double fitness = (double)this.fitnessFunction.fitnessFunction(net);
                    Individual<NeuralNetwork> newIndividual = new Individual<>(net, fitness);
                    newPop[ind] = newIndividual;
                    continue;
                }
                int layerOfNode = ThreadLocalRandom.current().nextInt(net.hiddenNodes.length + 1);
                newNode = new Node[net.hiddenNodes.length + 1][];     

                for (int i = 0; i<layerOfNode; i++){
                    newNode[i] = new Node[net.hiddenNodes[i].length];
                }

                for (int i = layerOfNode + 1; i<newNode.length; i++){
                    newNode[i] = new Node[net.hiddenNodes[i - 1].length];
                }

                newNode[layerOfNode] = new Node[1];
                newNode[layerOfNode][0] = new Node(ThreadLocalRandom.current().nextGaussian());
                // Break the connection from the input nodes to the newly added node.
                if (layerOfNode == 0){

                    for (int i = 0; i<net.inputs.length; i++){
                        net.inputs[i].branch = new Node[1];
                        net.inputs[i].connection = new double[1];

                        net.inputs[i].branch[0] = newNode[0][0];
                        net.inputs[i].connection[0] = ThreadLocalRandom.current().nextGaussian();
                    }

                    newNode[0][0].branch = new Node[net.hiddenNodes[0].length];
                    newNode[0][0].connection = new double[net.hiddenNodes[0].length];

                    for (int i = 0; i<newNode[0].length; i++){                  
                        newNode[0][0].branch[i] = new Node(net.hiddenNodes[0][i]);
                        newNode[0][0].connection[i] = ThreadLocalRandom.current().nextGaussian();
                    }

                    for (int i = 1; i<newNode.length; i++){
                        for (int x = 0; x<newNode[i].length; x++){
                            newNode[i][x] = new Node(net.hiddenNodes[i - 1][x]);
                        }
                    }
                }
                // Break the connection from the last layer of the hidden nodes(newly added layer) to the output nodes.
                else if (layerOfNode == newNode.length - 1){

                    for (int i = 0; i<layerOfNode; i++){
                        for (int x = 0; x<newNode[i].length; x++){
                            newNode[i][x] = new Node(net.hiddenNodes[i][x]);
                        }
                    }

                    for (int i = 0; i<newNode[layerOfNode - 1].length; i++){    

                        newNode[layerOfNode - 1][i].branch = new Node[1];
                        newNode[layerOfNode - 1][i].connection = new double[1];

                        newNode[layerOfNode - 1][i].branch[0] = newNode[layerOfNode][0];
                        newNode[layerOfNode - 1][i].connection[0] = ThreadLocalRandom.current().nextGaussian();
                    }

                    newNode[layerOfNode][0].branch = new Node[net.outputs.length];
                    newNode[layerOfNode][0].connection = new double[net.outputs.length];

                    for (int i = 0; i<net.outputs.length; i++){
                        newNode[layerOfNode][0].branch[i] = net.outputs[i];
                        newNode[layerOfNode][0].connection[i] = ThreadLocalRandom.current().nextGaussian();
                    }
                }
                // Break the connection within the hidden node layer
                else {
                    for (int i = 0; i<layerOfNode; i++){
                        for (int x = 0; x<newNode[i].length; x++){
                            newNode[i][x] =  new Node(net.hiddenNodes[i][x]); 
                        }
                    }


                    for (int i = 0; i<newNode[layerOfNode - 1].length; i++){
                        newNode[layerOfNode - 1][i].branch = new Node[1];
                        newNode[layerOfNode - 1][i].connection = new double[1];


                        newNode[layerOfNode - 1][i].branch[0] = newNode[layerOfNode][0];
                        newNode[layerOfNode - 1][i].connection[0] = ThreadLocalRandom.current().nextGaussian();
                    }

                    newNode[layerOfNode][0].branch = new Node[net.hiddenNodes[layerOfNode].length];
                    newNode[layerOfNode][0].connection = new double[net.hiddenNodes[layerOfNode].length];

                    for (int i = 0; i<newNode[layerOfNode + 1].length; i++){
                        newNode[layerOfNode][0].branch[i] = net.hiddenNodes[layerOfNode][i];
                        newNode[layerOfNode][0].connection[i] = ThreadLocalRandom.current().nextGaussian();                    
                    }

                    for (int i = layerOfNode + 1; i<newNode.length; i++){
                        for (int x = 0; x<newNode[i].length; x++){
                            newNode[i][x] = new Node(net.hiddenNodes[i - 1][x]);
                        }
                    }
                }

            }
            /// add a node to an existing layer.
            else {
                int layerOfNode = ThreadLocalRandom.current().nextInt(net.hiddenNodes.length);
                newNode = new Node[net.hiddenNodes.length][];
                for (int i = 0; i<net.hiddenNodes.length; i++){
                    newNode[i] = new Node[net.hiddenNodes[i].length];
                    for (int x = 0; x<net.hiddenNodes[i].length; x++){
                        newNode[i][x] = new Node(net.hiddenNodes[i][x]);
                    }
                }
                newNode[layerOfNode] = new Node[net.hiddenNodes[layerOfNode].length + 1];  
                newNode[layerOfNode][newNode[layerOfNode].length - 1] = new Node(ThreadLocalRandom.current().nextGaussian());

                for (int i = 0 ; i<net.hiddenNodes[layerOfNode].length; i++){
                    newNode[layerOfNode][i] = net.hiddenNodes[layerOfNode][i];
                }

                /// Only one layer of hidden nodes in the network. 
                if (net.hiddenNodes.length == 1){
                    double[] tempWeights = new double[newNode[0].length];
                    for (int i = 0; i<net.inputs.length; i++){
                        for (int get = 0; get<tempWeights.length - 1; get++){
                           tempWeights[get] = net.inputs[i].connection[get];
                        }
                      net.inputs[i].branch = new Node[newNode[0].length];
                      net.inputs[i].connection = new double[newNode[0].length];
                      for (int x = 0; x<newNode[0].length; x++){
                          net.inputs[i].branch[x] = newNode[0][x];
                      }
                      for (int get = 0; get<tempWeights.length - 1; get++){
                            //System.out.println(tempWeights.length + "  " + net.inputs[i].branch.length);
                            net.inputs[i].connection[get] = tempWeights[get];
                      }                  
                      net.inputs[i].connection[newNode[0].length - 1] = ThreadLocalRandom.current().nextGaussian();
                  }
                    newNode[0][newNode[0].length - 1].branch = new Node[net.outputs.length];
                    newNode[0][newNode[0].length - 1].connection = new double[net.outputs.length];                

                    for (int i = 0; i<net.outputs.length; i++){
                        newNode[0][newNode[0].length - 1].branch[i] = net.outputs[i];
                        newNode[0][newNode[0].length - 1].connection[i] = ThreadLocalRandom.current().nextGaussian();
                    }
                }

                /// Connection between input layer and first layer of hidden nodes with multiple hidden node layer
                else if (layerOfNode == 0) {
                  double[] tempWeights = new double[newNode[0].length];
                  for (int i = 0; i<net.inputs.length; i++){
                        for (int saveWeights = 0; saveWeights < newNode[0].length - 1; saveWeights++){
                            tempWeights[saveWeights] = net.inputs[i].connection[saveWeights];
                        }
                      net.inputs[i].branch = new Node[newNode[0].length];
                      net.inputs[i].connection = new double[newNode[0].length];
                      for (int x = 0; x<newNode[0].length; x++){
                          net.inputs[i].branch[x] = newNode[0][x];
                      }

                      for (int get = 0; get<tempWeights.length - 1; get++){
                            net.inputs[i].connection[get] = tempWeights[get];
                      }                    
                      net.inputs[i].connection[newNode[0].length - 1] = ThreadLocalRandom.current().nextGaussian();
                  }

                  newNode[0][newNode[0].length - 1].branch = new Node[net.hiddenNodes[1].length];
                  newNode[0][newNode[0].length - 1].connection = new double[net.hiddenNodes[1].length];  

                  for (int i = 0; i<net.hiddenNodes[1].length; i++){
                      newNode[0][newNode[0].length - 1].branch[i] = net.hiddenNodes[1][i];
                      newNode[0][newNode[0].length - 1].connection[i] = ThreadLocalRandom.current().nextGaussian();
                  }





                }

                /// Connection between last layer of hidden nodes and output layer
                else if(layerOfNode == newNode.length - 1) {
                    double[] tempWeights = new double[newNode[layerOfNode].length];
                    for (int i = 0; i<newNode[layerOfNode - 1].length; i++){
                        for (int saveWeights = 0; saveWeights < newNode[layerOfNode].length - 1; saveWeights++){
                            tempWeights[saveWeights] = net.hiddenNodes[layerOfNode - 1][i].connection[saveWeights];
                        }
                        newNode[layerOfNode - 1][i].branch = new Node[newNode[layerOfNode].length];
                        newNode[layerOfNode - 1][i].connection = new double[newNode[layerOfNode].length];

                        for (int x = 0; x<newNode[layerOfNode].length; x++){
                            newNode[layerOfNode - 1][i].branch[x] = newNode[layerOfNode][x];                           
                        }                  
                        for (int get = 0; get<tempWeights.length - 1; get++){
                            newNode[layerOfNode - 1][i].connection[get] = tempWeights[get];
                        }
                        newNode[layerOfNode - 1][i].connection[newNode[layerOfNode - 1][i].connection.length - 1] = ThreadLocalRandom.current().nextGaussian();  
                    }
                    newNode[layerOfNode][newNode[layerOfNode].length - 1].branch = new Node[net.outputs.length];
                    newNode[layerOfNode][newNode[layerOfNode].length - 1].connection = new double[net.outputs.length];
                    for (int i = 0; i<net.outputs.length; i++) {
                      newNode[layerOfNode][newNode[layerOfNode].length - 1].branch[i] = net.outputs[i];
                      newNode[layerOfNode][newNode[layerOfNode].length - 1].connection[i] = ThreadLocalRandom.current().nextGaussian();
                    }               
                }

                /// Connection between hidden nodes.
                else {
                    double[] tempWeights = new double[newNode[layerOfNode].length];
                    for (int i = 0; i<newNode[layerOfNode - 1].length; i++){
                        for (int saveWeights = 0; saveWeights < newNode[layerOfNode].length - 1; saveWeights++){
                            tempWeights[saveWeights] = net.hiddenNodes[layerOfNode - 1][i].connection[saveWeights];
                        }
                        newNode[layerOfNode - 1][i].branch = new Node[newNode[layerOfNode].length];
                        newNode[layerOfNode - 1][i].connection = new double[newNode[layerOfNode].length];

                        for (int x = 0; x<newNode[layerOfNode].length; x++){
                            newNode[layerOfNode - 1][i].branch[x] = newNode[layerOfNode][x];
                        }

                        for (int get = 0; get<tempWeights.length - 1; get++){
                            newNode[layerOfNode - 1][i].connection[get] = tempWeights[get];
                        }
                        newNode[layerOfNode - 1][i].connection[newNode[layerOfNode - 1][i].connection.length - 1] = ThreadLocalRandom.current().nextGaussian();  
                    }
                    newNode[layerOfNode][newNode[layerOfNode].length - 1].branch = new Node[newNode[layerOfNode + 1].length];
                    newNode[layerOfNode][newNode[layerOfNode].length - 1].connection = new double[newNode[layerOfNode + 1].length];
                    for (int i = 0; i<newNode[layerOfNode + 1].length; i++) {
                      newNode[layerOfNode][newNode[layerOfNode].length - 1].branch[i] = newNode[layerOfNode + 1][i];
                      newNode[layerOfNode][newNode[layerOfNode].length - 1].connection[i] = ThreadLocalRandom.current().nextGaussian();
                    }
                }           
            }
            net.hiddenNodes = newNode;
            NeuralNetworkUtilities.redoBranches(net);
            net.createBias();            
            double fitness = (double)this.fitnessFunction.fitnessFunction(net);
            Individual<NeuralNetwork> newIndividual = new Individual<>(net, fitness);
            newPop[ind] = newIndividual;
        }
        return newPop;
    }
}
