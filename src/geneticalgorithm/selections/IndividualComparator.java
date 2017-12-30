/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package geneticalgorithm.selections;

import geneticalgorithm.Individual;
import java.util.Comparator;

/**
 *
 * @author Tommy
 */
public class IndividualComparator implements Comparator<Individual>  {

    @Override
    public int compare(Individual o1, Individual o2) {
        return o1.fitness < o2.fitness ? -1 : o1.fitness == o2.fitness ? 0 : 1;
          
    }
    
}
