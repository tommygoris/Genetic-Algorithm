/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package geneticalgorithm.strategies;

import geneticalgorithm.Individual;
import geneticalgorithm.Population;
import java.util.Arrays;

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
        //Individual[] savePop = new Individual[this.savePop];
        //System.out.println(Arrays.toString(pop.population));
        Individual[] savePop = new Individual[pop.population.length];
        for(int i = 0; i < pop.population.length; i++){
            savePop[i] = new Individual(pop.population[i], pop.population[i].fitness);
        }
        Arrays.sort(savePop);
        for(int i = 0; i < bestPop.length; i++){
            this.bestPop[i] = savePop[i];
        }
        System.out.println(Arrays.toString(this.bestPop));
        //System.out.println(this.bestPop[0]);
        System.out.println(Arrays.toString(pop.population));
    }
    
    public Individual[] getBestPop(){
        return this.bestPop;
    }
    
}
