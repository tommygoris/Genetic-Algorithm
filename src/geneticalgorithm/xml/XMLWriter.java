/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package geneticalgorithm.xml;

import geneticalgorithm.neuralnetwork.NeuralNetwork;
import java.beans.XMLEncoder;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.nio.file.Files;

/**
 *
 * @author TommyGoris
 */
public class XMLWriter {
    
    public XMLWriter(){
        
    }
    
    public void writeNeuralNetwork(NeuralNetwork net, String fileName, String folderName){
        if (!new File(folderName).exists()){
            (new File(folderName)).mkdirs();
        }
        XMLEncoder encoder = null;
        try{
             encoder=new XMLEncoder(new BufferedOutputStream(new FileOutputStream(folderName + "\\" + fileName + ".xml")));
           } 
           catch(FileNotFoundException fileNotFound){
                System.out.println("ERROR: While Creating or Opening the File " + fileName + ".xml");
           }
           encoder.writeObject(net);
           encoder.close();   
    }
}
