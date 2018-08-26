/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package geneticalgorithm;

import geneticalgorithm.crossover.CrossoverInterface;
import geneticalgorithm.crossover.CrossoverSelector;
import geneticalgorithm.mutation.MutationInterface;
import geneticalgorithm.problem.RandomPopulationInterface;
import geneticalgorithm.selections.IndividualSelector;
import geneticalgorithm.selections.SelectionInterface;

import java.util.concurrent.ThreadLocalRandom;

/**
 *
 * @author Tommy
 */
public class Population{
    public Individual[] population;
    public double bestFitness = Integer.MIN_VALUE;
    public Individual bestIndividual;
    private final IndividualSelector Individualselector;
    private final CrossoverSelector crossoverSelector;
    private int introduceRandomPop = 0;
    private RandomPopulationInterface randomPopulation;
    
    /* Standard constructor.
    * Create a population given the population, as well as the type of crossover.
    */
    public Population(Individual[] pop, CrossoverInterface crossoverAlgorithm){
        this.population = pop;
        Individualselector = new IndividualSelector();
        this.crossoverSelector = new CrossoverSelector(crossoverAlgorithm);
    }
    
    /* This constructor is used to constantly add random population into the population at each generation.
    *  We pass in a RandomPopulationInterface which defines how many individuals in our population we want to replace.
    *  For example, if we want to introduce 20 random individuals per generation, we create a RandomPopulationInterface
    *  with a value of 20 to replace 20 individuals per generation with random individuals.
    */
    public Population(RandomPopulationInterface randomPopulation, CrossoverInterface crossoverAlgorithm, int introduceRandomPop){
        this.randomPopulation = randomPopulation;
        this.population = randomPopulation.createRandomPopulation();
        Individualselector = new IndividualSelector();
        this.crossoverSelector = new CrossoverSelector(crossoverAlgorithm);
        this.introduceRandomPop = introduceRandomPop;
    }
    
    //// Returns a new mutated population.
    public Individual[] mutate(MutationInterface mutation){
        Individual[] newIndividualsPopulation;
        newIndividualsPopulation = mutation.mutate(this);       
        return newIndividualsPopulation;
    }
    
    //// Returns a new crossovered population.
    public Individual[] crossover(SelectionInterface selection){
        Individual[] newIndividualsPopulation = new Individual[this.population.length];
        
        for (int i = 0; i < this.population.length - introduceRandomPop; i++){
            Individual firstIndividual = this.selectIndividual(selection);
            Individual secondIndividual = this.selectIndividual(selection);
            newIndividualsPopulation[i] = this.crossoverSelector.crossover(firstIndividual, secondIndividual);
        }
        
        newIndividualsPopulation = this.randomPop(newIndividualsPopulation);
        return newIndividualsPopulation;
    }
    
    public Individual[] randomPop(Individual[] newIndividualsPopulation) {
        for (int i = this.population.length - introduceRandomPop; i < this.population.length; i++) {
            newIndividualsPopulation[i] = randomPopulation.createRandomIndividual();
        }
        return newIndividualsPopulation;
    }
    
    private Individual selectIndividual(SelectionInterface selectionAlgorithm){
        return this.Individualselector.SelectIndividiual(selectionAlgorithm, this);  
    }
    
    
}
