package geneticalgorithm.examples.evolvenet;

import geneticalgorithm.Individual;
import geneticalgorithm.Population;
import geneticalgorithm.mutation.MutationInterface;
import geneticalgorithm.problem.ProblemInterface;

import java.util.List;

public class AddNodeToGraph implements MutationInterface {
    private ProblemInterface fitnessFunction;
    private double mutationRate;

    public AddNodeToGraph(ProblemInterface fitnessFunction, double mutationRate)
    {
        this.fitnessFunction = fitnessFunction;
        this.mutationRate = mutationRate;
    }
    @Override
    public Individual[] mutate(Population pop) {
        Individual[] newPop = new Individual[pop.population.length];
        for (Individual indv : pop.population)
        {
            NodeGraph nodeGraph = (NodeGraph)indv.individual;

            List<GraphNeuralNode> currentNodes = nodeGraph.getCalculationNodes();
        }

        return newPop;
    }
}
