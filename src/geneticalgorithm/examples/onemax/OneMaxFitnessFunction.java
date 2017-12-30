/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package geneticalgorithm.examples.onemax;

import geneticalgorithm.problem.ProblemInterface;

/**
 *
 * @author Tommy
 */
public class OneMaxFitnessFunction implements ProblemInterface {

    @Override
    public Object fitnessFunction(Object value) {
        String individual = (String)value;
        double fitness = 0.0;
        for (int i = 0; i<individual.length(); i++){
            if (individual.charAt(i) == '1'){
                fitness+=1;
            }
        }
        return fitness;
    }    
}
