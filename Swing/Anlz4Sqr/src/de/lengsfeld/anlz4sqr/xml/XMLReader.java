package de.lengsfeld.anlz4sqr.xml;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.jdom2.Attribute;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

public class XMLReader {

	public void readCategories() {
		System.out.println("XML Reader started...");
		readAllXML("Categories.xml");
	}

	public static void readAllXML(String path) {
		Document doc = null;
		SAXBuilder b = new SAXBuilder();
		try {
			doc = b.build(new File(path));
			Element element = doc.getRootElement();
			readAllNodes(element);
		} catch (JDOMException | IOException e) {
			e.printStackTrace();
		}
	}

	public static void readAllNodes(Element element) {

		for (Element e : element.getChildren()) {
			List<Attribute> attr = e.getAttributes();
			String a = "";

			for (Attribute at : attr) {
				a += at.getName() + "=" + at.getValue();
			}

			System.out.print(e.getName() + ": " + a + "\t");
			System.out.println(e.getTextTrim());
			readAllNodes(e);
		}
	}
}
