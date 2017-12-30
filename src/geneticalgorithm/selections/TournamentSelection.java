/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package geneticalgorithm.selections;

import geneticalgorithm.Individual;
import geneticalgorithm.Population;
import java.util.concurrent.ThreadLocalRandom;

/**
 *
 * @author Tommy
 */
public class TournamentSelection implements SelectionInterface {
    /* The number of chosen individuals for the tournament 2 - 7 */
    private final int kValue;
    /* The chance a weaker chosen fitness individual wins over a stronger chosen fitness individual and vice versa  
       100% for elitist selection. .75 or 75% for a 25% chance for a weaker chosen individual to win. */
    private final double percent; 
    public TournamentSelection(int kValue, double percent){
        this.kValue = kValue;
        this.percent = percent;
    }
    
    public Individual selectIndividual(Population pop){     
        int firstSelection = ((int)((pop.population.length - 1) * ThreadLocalRandom.current().nextDouble()));

        Individual chosenIndividual = pop.population[firstSelection];
        
        for (int i = 0; i < this.kValue - 1; i++){
            int secondSelection = (int)((pop.population.length - 1) * ThreadLocalRandom.current().nextDouble());
            Individual secondIndividual = pop.population[secondSelection];
            
            double randomCheck = ThreadLocalRandom.current().nextDouble();
            
            if (chosenIndividual.fitness > secondIndividual.fitness){
                if (this.percent < randomCheck){
                    chosenIndividual = secondIndividual;
                }
            }
            else if (chosenIndividual.fitness < secondIndividual.fitness){
                if (this.percent > randomCheck){
                    chosenIndividual = secondIndividual;
                }
            }
            // Both Individual's fitness are the same. Let's do nothing for now.
            else {
            }
        }                
     return chosenIndividual; 
    }
}
