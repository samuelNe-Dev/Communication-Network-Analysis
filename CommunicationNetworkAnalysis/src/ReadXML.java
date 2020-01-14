import javax.xml.parsers.DocumentBuilderFactory;

import javax.xml.parsers.DocumentBuilder;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;


import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;

public class ReadXML{
	
	public static String filepath = "/Users/samuelnegash/Downloads/medium_graph.graphml";
	
	public static ArrayList<GraphNode> Nodes = new ArrayList<GraphNode>();
	public static ArrayList<Triplet> Edges = new ArrayList<Triplet>();
	private static Triplet edge = null;
	
	
	public static Document inputFile() throws ParserConfigurationException, SAXException, IOException {
		//Erstellen eine File Datei für unser Graphml-File
		File xmlFile = new File(filepath);

		/*Vom DocumentBuilderFactory bekommen wir den DocumentBuilder. 
		 *Documentbuilder beinhaltet die API um das DOM Document von der XML-Datei rauszuziehen
		*/
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = factory.newDocumentBuilder();
		Document doc = dBuilder.parse(xmlFile);

		doc.getDocumentElement().normalize();
		return doc;
	}
	
	
	public static void getNodes() throws ParserConfigurationException, SAXException, IOException {
		
		Document xmlFile = inputFile();
		NodeList nList = xmlFile.getElementsByTagName("node");

		for (int i = 0; i < nList.getLength(); i++) {

			Node nNode = nList.item(i);

			// System.out.println("\nCurrent Element: " + nNode.getNodeName());

			if (nNode.getNodeType() == Node.ELEMENT_NODE) {

				Element elem = (Element) nNode;

				String n_id = elem.getAttribute("id");

				Node node1 = elem.getElementsByTagName("data").item(0);
				String value_string = node1.getTextContent();
				int value_int = Integer.parseInt(value_string);
				
				/*
				System.out.printf("Node id: %s%n", n_id);
				System.out.printf("Node Value: %s%n", value_int);
				*/
				
				//Add every Node to an GraphNode - ArrayList
				Nodes.add(new GraphNode(n_id,value_int));
			
			}
		}
		
	}
	
	public static void getEdges() throws ParserConfigurationException, SAXException, IOException {
		Document xmlFile = inputFile();
		NodeList eList = xmlFile.getElementsByTagName("edge");
		for (int i = 0; i < eList.getLength(); i++) {

			Node nNode = eList.item(i);

			// System.out.println("\nCurrent Element: " + nNode.getNodeName());

			if (nNode.getNodeType() == Node.ELEMENT_NODE) {

				Element elem = (Element) nNode;

				String src = elem.getAttribute("source");
				String trg = elem.getAttribute("target");
				// String e_id = id.getTextContent();
				Node weight = elem.getElementsByTagName("data").item(1);
				String wgt = weight.getTextContent();
				int weight_int = Integer.parseInt(wgt);
				
				/*
				System.out.printf("Edge source	: %s%n", src);
				System.out.printf("Edge target	: %s%n", trg);
				System.out.printf("Edge weight	: %s%n\n", wgt);
				*/
				
				edge = new Triplet(src,trg,weight_int);
				
				}
				Edges.add(edge);
				
				
			}
						
		}
	}
		
	
		
	


