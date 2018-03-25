/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package geneticalgorithm.neuralnetwork.utilities;

import geneticalgorithm.neuralnetwork.utilities.DrawNeuralNetwork;
import java.util.LinkedList;
import java.util.Queue;
import javax.swing.JFrame;

/**
 *
 * @author Tommy
 */
public class NeuralNetworkPanel extends Thread {
    JFrame f = null;
    Thread thread = null;
    Queue<DrawNeuralNetwork> queueToUpdate = new LinkedList<>();
    
    @Override
    public void start() {
        if (thread == null) {
          thread = new Thread(this);
          thread.start();
        }
    }
   
    @Override
    public void run() {
            try {
                DrawNeuralNetwork draw = queueToUpdate.remove();
                if (draw != null){
                    this.f.add(draw);
                    Thread.sleep(3000);
                    this.f.remove(draw);
                    this.f.revalidate();                
                }
            } catch (InterruptedException e) {
        }
    }
    
    public void initPanel(){
        f = new JFrame("Neural Network");
        f.setSize(2000, 2000);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setVisible(true);
    }
    
    public void refreshPanel(DrawNeuralNetwork draw) throws InterruptedException{
          this.f.add(draw);
          Thread.sleep(5000);
    }
}