import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class WriteXML implements Runnable {
	public static String xmlFilePath = "/Users/samuelnegash/eclipse-workspace-java/CommunicationNetworkAnalysis/outputFiles/";

	WriteXML(String newFileName) {
		xmlFilePath += newFileName;
	}

	public void run() {
		try {

			DocumentBuilderFactory documentFactory = DocumentBuilderFactory.newInstance();

			DocumentBuilder documentBuilder = documentFactory.newDocumentBuilder();

			Document document = documentBuilder.newDocument();

			// root element
			Element root = document.createElement("graph");
			root.setAttribute("connectivity", "true");
			document.appendChild(root);

			// nodes element
			// Element nodes = document.createElement("node");
			// root.appendChild(nodes);
			for (GraphNode node : ReadXML.Nodes) {
				Element nodes = document.createElement("node");
				root.appendChild(nodes);
				Attr n_id = document.createAttribute("id");
				n_id.setValue(node.get_name());
				nodes.setAttributeNode(n_id);

				// data element 
				Element data = document.createElement("data");
				data.setAttribute("key", "v_id");
				data.appendChild(document.createTextNode(Integer.toString(node.get_num())));
				nodes.appendChild(data);
				
				Element btw = document.createElement("data");
				btw.setAttribute("key", "betweeness");
				btw.appendChild(document.createTextNode("-"));
				nodes.appendChild(btw);

			}
			// edges element
			int counter = 0;
			// Element edges = document.createElement("edge");
			// root.appendChild(edges);
			for (Triplet edge : ReadXML.Edges) {
				Element edges = document.createElement("edge");
				root.appendChild(edges);
				Attr src = document.createAttribute("source");
				src.setValue(edge.getSrc());
				edges.setAttributeNode(src);

				Attr trg = document.createAttribute("target");
				trg.setValue(edge.getTrg());
				edges.setAttributeNode(trg);

				// data element
				Element e_id = document.createElement("data");
				e_id.setAttribute("key", "e_id");
				e_id.appendChild(document.createTextNode(Integer.toString(counter)));
				edges.appendChild(e_id);

				Element e_weight = document.createElement("data");
				e_weight.setAttribute("key", "e_weight");
				e_weight.appendChild(document.createTextNode(Double.toString(edge.getWeight())));
				edges.appendChild(e_weight);
				counter++;
			}

			// create the XML file
			// transform the DOM Object to an XML File
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");	//for formatting purpose
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");		//for formatting purpose
			DOMSource domSource = new DOMSource(document);
			StreamResult streamResult = new StreamResult(new File(xmlFilePath));

			// If you use
			// StreamResult result = new StreamResult(System.out);
			// the output will be pushed to the standard output ...
			// You can use that for debugging

			transformer.transform(domSource, streamResult);
			
			System.out.println("XML-File successfully created!");

		} catch (ParserConfigurationException pce) {
			LoggsToFile.LOGGER.severe(pce.getLocalizedMessage());
		} catch (TransformerException tfe) {
			LoggsToFile.LOGGER.warning(tfe.getMessageAndLocation());
		}
	}
}
