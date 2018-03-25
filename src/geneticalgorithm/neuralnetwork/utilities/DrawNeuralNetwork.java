/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package geneticalgorithm.neuralnetwork.utilities;

import geneticalgorithm.neuralnetwork.NeuralNetwork;
import geneticalgorithm.neuralnetwork.Node;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.text.DecimalFormat;
import java.util.Arrays;
import javax.swing.JPanel;

/**
 *
 * @author Tommy
 */
public class DrawNeuralNetwork extends JPanel {

    private NeuralNetwork net;
    public DrawNeuralNetwork(NeuralNetwork net){
        this.net = net;
    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g); //To change body of generated methods, choose Tools | Templates.
        this.setBackground(Color.WHITE);       
        g.setColor(Color.BLUE);
        
        int nodeChange = 0;
        int heightChange = 0;        
        // Gets the size of the longest layer in the network.
        int maxSize = Math.max(Math.max(net.inputs.length, net.outputs.length), Arrays.stream(this.net.hiddenNodes).mapToInt(row -> row.length).max().getAsInt());
        for (int i = 0; i<net.inputs.length;  i++){
            for (int x = 0; x<net.hiddenNodes[0].length; x++){
                this.connectTwoNodes(g, 100, 100, heightChange, nodeChange);
                nodeChange+=100;
           }
            heightChange += 100;
            nodeChange = 0;           
        }
        nodeChange = 0;
        heightChange = 0;
        if (net.hiddenNodes.length == 1){
            for (int i = 0; i<net.hiddenNodes[0].length; i++){
                for (int x = 0; x<net.outputs.length; x++){
                    this.connectTwoNodes(g, 300, 100, heightChange, nodeChange);
                    nodeChange+=100;
                }
                heightChange += 100;
                nodeChange = 0; 
            }
        }
        else{
            int xDif = 0;
            for (int i = 0; i<net.hiddenNodes.length - 1; i++){
                for (int x = 0; x<net.hiddenNodes[i].length; x++){
                   for (int y = 0; y<net.hiddenNodes[i + 1].length; y++){
                       this.connectTwoNodes(g, 300 + xDif, 100, heightChange, nodeChange);
                       nodeChange+=100;
                   }
                   heightChange += 100;
                   nodeChange = 0; 
                }
                heightChange = 0;
                xDif+=200;
            }
            nodeChange = 0;
            heightChange = 0;
            
            for (int i = 0; i<net.hiddenNodes[net.hiddenNodes.length - 1].length; i++){
                for (int x = 0; x<net.outputs.length; x++){
                    this.connectTwoNodes(g, 300 + xDif, 100, heightChange, nodeChange);
                    nodeChange+=100;
                }
                heightChange += 100;
                nodeChange = 0; 
            }
        }
        this.checkValidity(g, maxSize);
    } 
    
    private void connectTwoNodes(Graphics g, int x, int y, int firstYChange , int secondYChange){
        int size = 80;
        g.drawOval(x, y + firstYChange, size, size);
        g.drawOval(x + 200, y + secondYChange, size, size);
        g.drawLine(x + size, y + 40 + firstYChange, x + 200, 140 + secondYChange);
    }
    
    private void checkValidity(Graphics g, int maxSize){
        DecimalFormat df = new DecimalFormat("#.##");
        int startHeight = (maxSize + 1) * 100 + 50;
        int startX = 100;
        g.setFont(new Font("TimesRoman", Font.PLAIN, 50)); 
        g.drawString("Neural network weights and branches table", startX, startHeight);
        
        startHeight+=50;
        g.setFont(new Font("TimesRoman", Font.PLAIN, 30)); 
        g.drawString("Input Nodes branch and connections", startX, startHeight);
        startHeight+=50;
        for (int i = 0; i<net.inputs.length; i++){
            g.drawString("Input Node " + (i + 1) + " with value of  " + net.inputs[i].toString(), startX, startHeight);
            startHeight+=50;
            for (int x = 0; x<net.hiddenNodes[0].length; x++){
                g.drawString("Points to Node with value of " + net.inputs[i].branch[x].toString(), startX, startHeight);
                startHeight+=50;
                g.drawString("Connection of " + df.format(net.inputs[i].connection[x]), startX, startHeight);
                startHeight+=50;
            }
        }
        startHeight = (maxSize + 1) * 100 + 100;
        startX += 600;
        if (net.hiddenNodes.length == 1){
            
            for (int i = 0; i<net.hiddenNodes[0].length; i++){
                g.drawString("Layer " + (1) + " Hidden Node " + (i + 1) + " with value of  " + net.hiddenNodes[0][i].toString(), startX, startHeight);
                startHeight+=50;
                for (int x = 0; x<net.outputs.length; x++){
                    g.drawString("Points to output Node " + (x + 1), startX, startHeight);
                    startHeight+=50;
                    g.drawString("Connection of " + df.format(net.hiddenNodes[0][i].connection[x]), startX, startHeight);
                    startHeight+=50;
                }
            }
            startX += 600;
            startHeight = (maxSize + 1) * 100 + 100;
            g.drawString("Output Layer with " + net.outputs.length + " nodes", startX, startHeight);
        }
        else {
            for (int i = 0; i<net.hiddenNodes.length - 1; i++){
                for (int x = 0; x<net.hiddenNodes[i].length; x++){
                    g.drawString("Layer " + (i + 1) + " Hidden Node " + (x + 1) + " with value of  " + net.hiddenNodes[i][x].toString(), startX, startHeight);
                    startHeight+=50;
                    for (int f = 0; f<net.hiddenNodes[i + 1].length; f++){
                        g.drawString("Points to Node with value of " + net.hiddenNodes[i][x].branch[f].toString(), startX, startHeight);
                        startHeight+=50;
                        g.drawString("Connection of " + df.format(net.hiddenNodes[i][x].connection[f]), startX, startHeight);
                        startHeight+=50;
                    }
                }
                startHeight = (maxSize + 1) * 100 + 100;
                startX += 600;
            }
            
            for (int i = 0; i<net.hiddenNodes[net.hiddenNodes.length - 1].length; i++){
                g.drawString("Layer " + (net.hiddenNodes.length) + " Hidden Node " + (i + 1) + " with value of  " + net.hiddenNodes[net.hiddenNodes.length - 1][i].toString(), startX, startHeight);
                startHeight+=50;
                for (int x = 0; x<net.outputs.length; x++){
                    g.drawString("Points to output Node " + (x + 1), startX, startHeight);
                    startHeight+=50;
                    g.drawString("Connection of " + df.format(net.hiddenNodes[net.hiddenNodes.length - 1][i].connection[x]), startX, startHeight);
                    startHeight+=50;
                }
            }
            startX += 600;
            startHeight = (maxSize + 1) * 100 + 100;
            g.drawString("Output Layer with " + net.outputs.length + " nodes", startX, startHeight);
        }
    }
}
