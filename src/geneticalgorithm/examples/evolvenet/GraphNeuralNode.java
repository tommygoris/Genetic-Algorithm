package geneticalgorithm.examples.evolvenet;

import java.util.ArrayList;
import java.util.List;

public class GraphNeuralNode {
    private double value;
    private List<Double> connections;

    public GraphNeuralNode(double value) {
        this.value = value;
        this.connections = new ArrayList<Double>();
    }

    public Double getValue() {
        return this.value;
    }

    public void setvalue(Double value) {
        this.value = value;
    }

    public List<Double> getConnections() {
        return this.connections;
    }

    public void setConnections(List<Double> connections) {
        this.connections = connections;
    }
}

