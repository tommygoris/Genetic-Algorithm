package geneticalgorithm.examples.evolvenet;

import java.util.ArrayList;
import java.util.List;

public class NodeGraph {
    private List<List<GraphNeuralNode>> allInputNodes;
    private List<GraphNeuralNode> calculationNodes;

    public NodeGraph(List<List<GraphNeuralNode>> allInputNodes)
    {
        this.allInputNodes = allInputNodes;
        this.calculationNodes = new ArrayList<>();
    }

    public List<List<GraphNeuralNode>> getAllInputNodes()
    {
        return this.getAllInputNodes();
    }

    public List<GraphNeuralNode> getCalculationNodes()
    {
        return this.calculationNodes;
    }
}
