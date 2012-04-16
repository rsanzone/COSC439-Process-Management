package org.processmanagement.fileio;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

import org.processmanagement.processes.Process;

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

public class WriteXML {
	public void savePList(ArrayList<Process> pList) {
		try {

			DocumentBuilderFactory docFactory = DocumentBuilderFactory
					.newInstance();
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

			// root elements
			Document doc = docBuilder.newDocument();
			Element rootElement = doc.createElement("ProcessList");
			doc.appendChild(rootElement);
			for (Process p : pList) {
				// Process element
				Element process = doc.createElement("Process");
				rootElement.appendChild(process);

				// name element
				Element nickname = doc.createElement("name");
				nickname.appendChild(doc.createTextNode(p.getName()));
				process.appendChild(nickname);
				
				// burst element
				Element firstname = doc.createElement("burst");
				firstname.appendChild(doc.createTextNode(""+p.getBurst()));
				process.appendChild(firstname);

				// arrival time element
				Element lastname = doc.createElement("arrivalTime");
				lastname.appendChild(doc.createTextNode(""+p.getArrivalTime()));
				process.appendChild(lastname);

				
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

}
