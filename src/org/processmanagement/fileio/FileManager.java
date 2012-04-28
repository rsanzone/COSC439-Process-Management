package org.processmanagement.fileio;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

import org.processmanagement.processes.Process;
import org.processmanagement.processes.ProcessComplex;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class FileManager {
	public void printSavedLists(){
		
		  String path = "SavedLists"; 
		 
		  String files;
		  File folder = new File(path);
		  File[] listOfFiles = folder.listFiles(); 
		 
		  for (int i = 0; i < listOfFiles.length; i++) 
		  {
		 
		   if (listOfFiles[i].isFile()) 
		   {
		   files = listOfFiles[i].getName();
		   System.out.println(files);
		      }
		  }
	}
	public void savePList(ArrayList<ProcessComplex> pList) {
		try {

			DocumentBuilderFactory docFactory = DocumentBuilderFactory
					.newInstance();
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

			// root elements
			Document doc = docBuilder.newDocument();
			Element rootElement = doc.createElement("ProcessList");
			doc.appendChild(rootElement);
			for (ProcessComplex p : pList) {
				// Process element
				Element process = doc.createElement("Process");
				rootElement.appendChild(process);

				// name element
				Element name = doc.createElement("name");
				name.appendChild(doc.createTextNode(p.getName()));
				process.appendChild(name);
				
				// burstList element
				Element burstList = doc.createElement("burstList");
				process.appendChild(burstList);
				for (Integer b : p.getBurst()) {
					// burst element
					Element burst = doc.createElement("burst");
					burst.appendChild(doc.createTextNode("" + b));
					burstList.appendChild(burst);
				}
				
				// ioList element
				Element ioList = doc.createElement("ioList");
				process.appendChild(ioList);
				for (Integer i : p.getIoTime()) {
					// burst element
					Element ioTime = doc.createElement("ioTime");
					ioTime.appendChild(doc.createTextNode("" + i));
					ioList.appendChild(ioTime);
				}

				// arrival time element
				Element arrivalTime = doc.createElement("arrivalTime");
				arrivalTime.appendChild(doc.createTextNode(""+p.getArrivalTime()));
				process.appendChild(arrivalTime);

				
			}
			// write the content into xml file
			TransformerFactory transformerFactory = TransformerFactory
					.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(doc);
			System.out.print("Enter the desired file name: ");
			Scanner input = new Scanner(System.in);
			String fileName = input.nextLine();
			if(!fileName.endsWith(".xml"))
				fileName += ".xml";
			StreamResult result = new StreamResult(new File("SavedLists\\"+fileName));

			// Output to console for testing
			// StreamResult result = new StreamResult(System.out);

			transformer.transform(source, result);

			System.out.println("File saved!");

		} catch (ParserConfigurationException pce) {
			pce.printStackTrace();
		} catch (TransformerException tfe) {
			tfe.printStackTrace();
		}

	}
	public ArrayList<Process> loadPList() {
		ArrayList<Process> pList = new ArrayList<Process>();
		try {
			Scanner input = new Scanner(System.in);
			System.out.println("Available saved lists are: ");
			printSavedLists();
			System.out.print("Enter the name of the file you wish to load: ");
			String fileName = input.nextLine();
			File fXmlFile = new File("SavedLists\\"+fileName);
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(fXmlFile);
			doc.getDocumentElement().normalize();
	 
			System.out.println("Root element :" + doc.getDocumentElement().getNodeName());
			NodeList nList = doc.getElementsByTagName("Process");
			System.out.println("-----------------------");
	 
			for (int temp = 0; temp < nList.getLength(); temp++) {
	 
			   Node nNode = nList.item(temp);
			   if (nNode.getNodeType() == Node.ELEMENT_NODE) {
	 
			      Element eElement = (Element) nNode;
		          pList.add(new Process(Integer.parseInt(getTagValue("arrivalTime", eElement)),Integer.parseInt(getTagValue("burst", eElement)),getTagValue("name", eElement)));
			   }
			}
		  } catch (Exception e) {
			e.printStackTrace();
		  }
		return pList;
	}
	private static String getTagValue(String sTag, Element eElement) {
		NodeList nlList = eElement.getElementsByTagName(sTag).item(0).getChildNodes();
	 
	        Node nValue = (Node) nlList.item(0);
	 
		return nValue.getNodeValue();
	  }

}
