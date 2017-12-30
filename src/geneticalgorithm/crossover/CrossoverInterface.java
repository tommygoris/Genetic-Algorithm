/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package geneticalgorithm.crossover;

import geneticalgorithm.Individual;

/**
 *
 * @author Tommy
 */
public interface CrossoverInterface {
    public Individual crossover(Individual first, Individual second);
}
