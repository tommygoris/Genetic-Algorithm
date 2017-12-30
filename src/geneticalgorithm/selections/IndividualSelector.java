/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package geneticalgorithm.selections;

import geneticalgorithm.Individual;
import geneticalgorithm.Population;

/**
 *
 * @author Tommy
 */
public class IndividualSelector {
      
    public Individual SelectIndividiual(SelectionInterface selector, Population pop){
        return selector.selectIndividual(pop);
    }
}
