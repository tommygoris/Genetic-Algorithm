/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package geneticalgorithm.mutation;

import geneticalgorithm.Individual;
import geneticalgorithm.Population;

/**
 *
 * @author Tommy
 */
public interface MutationInterface {
    public Individual[] mutate(Population pop);
}
