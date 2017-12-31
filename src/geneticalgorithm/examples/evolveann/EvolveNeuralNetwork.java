/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package geneticalgorithm.examples.evolveann;

import geneticalgorithm.Individual;
import geneticalgorithm.Population;
import geneticalgorithm.crossover.IntArrayCrossover;
import geneticalgorithm.crossover.neuralcrossover.NeuralNetworkCrossover;
import geneticalgorithm.examples.nqueens.NQueensProblem;
import geneticalgorithm.mutation.RandomIntArrayMutation;
import geneticalgorithm.mutation.neuralmutation.AddNode;
import geneticalgorithm.mutation.neuralmutation.DeleteNode;
import geneticalgorithm.neuralnetwork.NeuralNetwork;
import geneticalgorithm.neuralnetwork.Node;
import geneticalgorithm.problem.ProblemUtility;
import geneticalgorithm.selections.TournamentSelection;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

/**
 *
 * @author Tommy
 */
public class EvolveNeuralNetwork {
    //private static final int lengthOfProblem = 10;
    private static final int populationSize = 1000;
    private static final int startingNodes = 1;
    private static final int outputNodes = 1;
    private static final int sizeOfWord = 12;
    private static Double[][] inputEnglishData = null;
    private static Double[][] inputGermanData = null;
    private static final double[] resultsData = null;
    private static ArrayList<String> englishData = null;
    private static ArrayList<String> germanData = null;
    private static NeuralNetworkFitnessFunction fitnessFunction = null;
    final static String alphabet="abcdefghijklmnopqrstuvwxyz";
    private static int solution;
    
    public static void main(String[] args) throws FileNotFoundException{ 
        EvolveNeuralNetwork.readEnglishExcel();
        EvolveNeuralNetwork.readGermanExcel();
        solution = englishData.size() + germanData.size();
        inputEnglishData = new Double[englishData.size()][];
        inputGermanData = new Double[germanData.size()][];
        for (int i = 0; i<englishData.size(); i++){
            inputEnglishData[i] = EvolveNeuralNetwork.stringToIntList(englishData.get(i));
        }
        
        for (int i = 0; i<germanData.size(); i++){
            inputGermanData[i] = EvolveNeuralNetwork.stringToIntList(germanData.get(i));
        }
        
        Double[][] finalInputData = new Double[inputEnglishData.length + inputGermanData.length][];
        
        for (int i = 0; i<inputEnglishData.length; i++){
            finalInputData[i] = inputEnglishData[i];
        }
        
        int x = 0;
        for (int i = inputEnglishData.length; i<inputEnglishData.length + inputGermanData.length; i++){
            finalInputData[i] = inputGermanData[x++];
        }
      
        double[] outputEnglish = new double[inputEnglishData.length];
        Arrays.fill(outputEnglish, 1.0);
        double[] outputGerman = new double[inputGermanData.length];
        Arrays.fill(outputGerman, 0.0);
        Double[] finalOutputData = new Double[inputEnglishData.length + inputGermanData.length];
        
        for (int i = 0; i<inputEnglishData.length; i++){
            finalOutputData[i] = outputEnglish[i];
        }
        x = 0;
        for (int i = inputEnglishData.length; i<inputEnglishData.length + inputGermanData.length; i++){
            finalOutputData[i] = outputGerman[x++];
        }
        long seed = System.nanoTime();
        
        List<Double> outputList = Arrays.asList(finalOutputData);  
        List<Double[]> inputList =  Arrays.asList(finalInputData); 
        Collections.shuffle(outputList, new Random(seed));   
        Collections.shuffle(inputList, new Random(seed));
        finalInputData = inputList.toArray(new Double[0][0]);
        fitnessFunction = new NeuralNetworkFitnessFunction(outputList.toArray(new Double[0]), inputList.toArray(new Double[0][0]));
        TournamentSelection tournament = new TournamentSelection(2, 0.10);
        NeuralNetworkCrossover crossover = new NeuralNetworkCrossover(fitnessFunction, 0.25);
        AddNode addMutation = new AddNode(fitnessFunction, 0.005);
        DeleteNode deleteMutation = new DeleteNode(fitnessFunction, 0.005);
        Population pop = new Population(EvolveNeuralNetwork.createRandomPopulation(), crossover);
        int generation = 0;
        
        while(true){
            NeuralNetwork net = (NeuralNetwork)pop.population[0].individual;
            pop = EvolveNeuralNetwork.cleanUpHiddenLayer(pop);
            pop.population = pop.crossover(tournament);   
            pop.population = pop.mutate(addMutation);  
            pop.population = pop.mutate(deleteMutation);
            pop.bestFitness = ProblemUtility.getBestFitnessMax(pop);
            System.out.println(pop.bestFitness + "  " + generation);
            if (pop.bestFitness == solution){
                System.out.println("Solution found in generation: " + generation);
                System.out.print("The best Individual is ");
                break;
            }
            try{
                //System.out.println(generation + "   " + pop.bestFitness + "  " + solution);
                generation+=1;
                Thread.sleep(10);
            }

            catch (InterruptedException e){
            }
        
      }
        
        
    }
    
    private static Individual[] createRandomPopulation(){
        Individual[] randomPop = new Individual[EvolveNeuralNetwork.populationSize];
        for (int i = 0; i<randomPop.length; i++){
            randomPop[i] = EvolveNeuralNetwork.createRandomIndividual();
        }
        return randomPop;
    }
    
    private static Individual createRandomIndividual(){
        Individual ind = null;
        List<Integer> listPop = new ArrayList<>();
        NeuralNetwork net = new NeuralNetwork();
        net = NeuralNetworkUtilities.initTree(net, EvolveNeuralNetwork.inputEnglishData[0], EvolveNeuralNetwork.outputNodes);
        
        int numOfNodes = ThreadLocalRandom.current().nextInt(EvolveNeuralNetwork.startingNodes);
        for (int i = 0; i<numOfNodes; i++){
            int nodes = ThreadLocalRandom.current().nextInt(1, EvolveNeuralNetwork.startingNodes);
            listPop.add(nodes);
            numOfNodes -= nodes;            
        }    
        //System.out.println(Arrays.toString(listPop.stream().mapToInt(i->i).toArray()));
        net = NeuralNetworkUtilities.addHiddenNodes(net, listPop.stream().mapToInt(i->i).toArray());      
        
        net.createBias();
        
        return new Individual(net, (double)fitnessFunction.fitnessFunction(net));
    }
    
    private static Population cleanUpHiddenLayer(Population pop){
        for (int ind = 0; ind<pop.population.length; ind++){
            int actualSize = 0;
            Node[][] newHiddenLayer = null;
            NeuralNetwork net = (NeuralNetwork)pop.population[ind].individual;
            
            if (net.hiddenNodes == null){
                continue;
            }
            for (int i = 0; i<net.hiddenNodes.length; i++){
                if (net.hiddenNodes[i] != null || net.hiddenNodes[i].length > 0){
                    actualSize += 1;
                }
            }
            int x = 0;
            newHiddenLayer = new Node[actualSize][];
            for (int i = 0; i<net.hiddenNodes.length; i++){
                if (net.hiddenNodes[i] != null || net.hiddenNodes[i].length > 0){
                    newHiddenLayer[i] = new Node[net.hiddenNodes.length];              
                    newHiddenLayer[x++] = net.hiddenNodes[i];
                }
            }
            
            net.hiddenNodes = newHiddenLayer;
            pop.population[ind].individual = net;
        }
        return pop;
    }
    
    public static void check(Population pop, String location){
        for (int ind = 0; ind<pop.population.length; ind++)
        {
            NeuralNetwork net = (NeuralNetwork)pop.population[ind].individual;
            if (net.hiddenNodes == null || net.hiddenNodes.length == 0){
                return;
            }
            if (net.hiddenNodes != null && net.hiddenNodes.length > 0){
                if (net.hiddenNodes[0].length != net.inputs[0].branch.length){
                    System.out.println("problem before" + location);
                }
            }

            for (int i = 0; i<net.hiddenNodes.length - 1; i++){
                    if (net.hiddenNodes[i][0].branch.length != net.hiddenNodes[i + 1].length){
                        System.out.println("problem before" + location);
                    }
            }

            if (net.hiddenNodes != null && net.hiddenNodes.length > 0){
                if (net.hiddenNodes[net.hiddenNodes.length - 1][0].branch.length != net.outputs.length){
                    System.out.println("problem before" + location);
                }
            } 
        }
    }
    
    public static void check(NeuralNetwork net, String location){
        if (net.hiddenNodes == null || net.hiddenNodes.length == 0){
            return;
        }
        if (net.hiddenNodes != null && net.hiddenNodes.length > 0){
            if (net.hiddenNodes[0].length != net.inputs[0].branch.length){
                System.out.println("problem before" + location);
            }
        }

        for (int i = 0; i<net.hiddenNodes.length - 1; i++){
            if (net.hiddenNodes[i][0].branch.length != net.hiddenNodes[i + 1].length){
                System.out.println("problem before" + location);
            }
        }

        if (net.hiddenNodes != null && net.hiddenNodes.length > 0){
            if (net.hiddenNodes[net.hiddenNodes.length - 1][0].branch.length != net.outputs.length){
                System.out.println("problem before" + location);
            }
        } 
    }
    
    public static void readEnglishExcel() throws FileNotFoundException{
        Scanner scanner = new Scanner(new File("500_English.csv"));
        scanner.useDelimiter(",");
        englishData = new ArrayList<>();
        while(scanner.hasNext()){
            englishData.add(scanner.nextLine());
        }
        scanner.close();

    }
    
    public static void readGermanExcel() throws FileNotFoundException{
        Scanner scanner = new Scanner(new File("500_German.csv"));
        scanner.useDelimiter(",");
        germanData = new ArrayList<>();
        while(scanner.hasNext()){
            germanData.add(scanner.nextLine());
        }
        scanner.close();
    }
    
    private static Double[] stringToIntList(String convertString){
        Double[] intList = new Double[sizeOfWord];
        Arrays.fill(intList, 0.0);
        convertString = convertString.toLowerCase();
        for (int i = 0; i<convertString.length(); i++){
            intList[i] = new Double(alphabet.indexOf(convertString.charAt(i))+1);
        }  
        return intList;
    }
}
