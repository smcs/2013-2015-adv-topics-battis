import java.io.*;
import java.util.*;
import javax.xml.stream.*;

public class CdxmlParser {
	public static void main(String[] args) throws XMLStreamException, FileNotFoundException {
		List<Node> nodeList = new LinkedList<Node>();
		List<Bond> bondList = new LinkedList<Bond>();
		Node currNode = null;
		Bond currBond = null;
		String tagContent = null;
		XMLInputFactory factory = XMLInputFactory.newInstance();
		XMLStreamReader reader = factory.createXMLStreamReader(new FileReader(new File("xml/Terephthalic acid.cdxml")));

		while (reader.hasNext()) {
			int event = reader.next();

			switch (event) {
			case XMLStreamConstants.START_ELEMENT:
				if ("n".equals(reader.getLocalName())) {
					currNode = new Node();
					currNode.id = reader.getAttributeValue(null, "id");
					currNode.p = reader.getAttributeValue(null, "p");
					currNode.Z = reader.getAttributeValue(null, "Z");
				}
				if ("b".equals(reader.getLocalName())) {
					currBond = new Bond();
					currBond.id = reader.getAttributeValue(null, "id");
					currBond.Order = reader.getAttributeValue(null, "Order");
				}
				break;
			case XMLStreamConstants.END_ELEMENT:
				switch (reader.getLocalName()) {
				case "n":
					nodeList.add(currNode);
					break;
				case "b":
					bondList.add(currBond);
					break;
				}
			}
		}
		System.out.println("done");
	}
}

class Bond {
	String id;
	String Order;
}

class Node {
	String id;
	String p;
	String Z;
}
