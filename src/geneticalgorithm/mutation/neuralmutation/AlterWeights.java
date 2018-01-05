/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package geneticalgorithm.mutation.neuralmutation;

import geneticalgorithm.Individual;
import geneticalgorithm.Population;
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
public class AlterWeights implements MutationInterface {
        private final ProblemInterface fitnessFunction;
        private final double mutationRate;
    public AlterWeights(ProblemInterface<NeuralNetwork> fitnessFunction, double mutationRate){
        this.fitnessFunction = fitnessFunction;
        this.mutationRate = mutationRate;
    }

    @Override
    public Individual[] mutate(Population pop) {
        Individual[] newPop = new Individual[pop.population.length];
        
        for (int ind = 0; ind < newPop.length; ind++){
            
            NeuralNetwork net = (NeuralNetwork)pop.population[ind].individual;
            
            if (mutationRate < ThreadLocalRandom.current().nextDouble()){
                newPop[ind] = pop.population[ind];
            }
            int numberOfNodes = net.inputs.length;
            
            for (Node[] array : net.hiddenNodes){
                numberOfNodes += array.length;
            }
            
            int getNode = ThreadLocalRandom.current().nextInt(numberOfNodes);
            
            if (getNode >= net.inputs.length){
                getNode -= net.inputs.length;
                for (int i = 0; i<net.hiddenNodes.length; i++){
                    if (net.hiddenNodes[i].length > getNode || getNode == 0){
                        for (int x = 0; x<net.hiddenNodes[i].length; x++){
                            net.hiddenNodes[i][getNode].connection[x] = ThreadLocalRandom.current().nextGaussian();
                        }
                        break;
                    }
                    getNode -= net.hiddenNodes[i].length;
                }
            }
            else {
                for (int i = 0; i<net.inputs[getNode].connection.length; i++){
                    net.inputs[getNode].connection[i] = ThreadLocalRandom.current().nextGaussian();
                }
            }
            
            newPop[ind] = new Individual(net, (double)this.fitnessFunction.fitnessFunction(net));
        }
        
        return newPop;
    }

  
}
