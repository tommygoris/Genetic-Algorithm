/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package geneticalgorithm.problem;

/**
 *
 * @author Tommy
 */
public interface ProblemInterface<T> {
    
    public T fitnessFunction(T value);
}
