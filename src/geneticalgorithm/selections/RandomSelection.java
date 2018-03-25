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
 * @author TommyGoris
 */
public class RandomSelection implements SelectionInterface{

    @Override
    public Individual selectIndividual(Population pop) {
        return pop.population[ThreadLocalRandom.current().nextInt()*(pop.population.length - 1)];
    }
    
}
