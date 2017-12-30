/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package geneticalgorithm.selections;

import geneticalgorithm.Individual;
import geneticalgorithm.Population;
import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;

/**
 *
 * @author Tommy
 */
public class RankSelection implements SelectionInterface{

    public Individual selectIndividual(Population pop) {
        Arrays.sort(pop.population, new IndividualComparator());
        Individual chosenIndividual = null;
        int[] fitness = new int[pop.population.length];
        fitness[0] = 1;
        int sum = 0;
        for (int i = 1; i < fitness.length; i++){           
            fitness[i] = 1 + fitness[i-1];
            sum += i;
        }
        int valToCheck = (int)(ThreadLocalRandom.current().nextDouble() * sum);
        sum = 0;
        for (int i = 0; i < fitness.length; i++){
            sum += fitness[i];
            if (sum > valToCheck){
                chosenIndividual = pop.population[i];
                break;
            }
        }
        if (chosenIndividual == null){
            chosenIndividual = pop.population[pop.population.length - 1];
        }
        return chosenIndividual;
    }
}
        