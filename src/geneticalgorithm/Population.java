/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package geneticalgorithm;

import geneticalgorithm.crossover.CrossoverInterface;
import geneticalgorithm.crossover.CrossoverSelector;
import geneticalgorithm.mutation.MutationInterface;
import geneticalgorithm.selections.IndividualSelector;
import geneticalgorithm.selections.SelectionInterface;

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
    
    public Population(Individual[] pop, CrossoverInterface crossoverAlgorithm){
        this.population = pop;
        Individualselector = new IndividualSelector();
        this.crossoverSelector = new CrossoverSelector(crossoverAlgorithm);
    }
    
    //// Returns a new mutated population.
    public Individual[] mutate(MutationInterface mutation){
        Individual[] newIndividualsPopulation = new Individual[this.population.length];
        newIndividualsPopulation = mutation.mutate(this);       
        return newIndividualsPopulation;
    }
    
    //// Returns a new crossovered population.
    public Individual[] crossover(SelectionInterface selection){
        Individual[] newIndividualsPopulation = new Individual[this.population.length];
        
        for (int i = 0; i < this.population.length; i++){
            Individual firstIndividual = this.selectIndividual(selection);
            Individual secondIndividual = this.selectIndividual(selection);
            newIndividualsPopulation[i] = this.crossoverSelector.crossover(firstIndividual, secondIndividual);
        }
        return newIndividualsPopulation;
    }
    
    private Individual selectIndividual(SelectionInterface selectionAlgorithm){
        return this.Individualselector.SelectIndividiual(selectionAlgorithm, this);  
    }
    
    
}
