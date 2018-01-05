/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package geneticalgorithm.strategies;

import geneticalgorithm.Individual;
import geneticalgorithm.Population;
import java.util.Arrays;
import java.util.Collections;
import java.util.concurrent.ThreadLocalRandom;

/**
 *
 * @author wgoris31
 */
public class ElitismStrategy {
    private final int savePop;
    private Individual[] bestPop = null;
            
    public ElitismStrategy(int savePop){
        this.savePop = savePop;
        this.bestPop = new Individual[savePop];
    }
    
    public void Strategy(Population pop){ 
        Individual[] originalPop = pop.population.clone();
        Arrays.sort(originalPop, Collections.reverseOrder());
        this.bestPop = Arrays.copyOfRange(originalPop, 0, this.savePop);
        System.out.println(Arrays.toString(this.bestPop));
        System.out.println(Arrays.toString(pop.population));
    }
    
    public Individual[] getbestPop(Population pop){
        for(int i = 0; i < this.bestPop.length; i++){
            pop.population[ThreadLocalRandom.current().nextInt(pop.population.length)] = this.bestPop[i]; 
        }
        return pop.population;
    }
    
}
