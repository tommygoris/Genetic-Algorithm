/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package geneticalgorithm.xml;

import geneticalgorithm.neuralnetwork.NeuralNetwork;
import java.beans.XMLDecoder;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 *
 * @author TommyGoris
 */
public class XMLReader {
    public XMLReader(){
        
    }
    
    public void readNeuralNetworkXML(String fileName, String folderName){
        XMLDecoder decoder=null;
	try {
		decoder=new XMLDecoder(new BufferedInputStream(new FileInputStream(folderName + "\\" + fileName + ".xml")));
	} catch (FileNotFoundException e) {
		System.out.println(e);
	}
	NeuralNetwork net =(NeuralNetwork)decoder.readObject();
        System.out.println(net.inputs[0]);
        decoder.close();
    }
}
