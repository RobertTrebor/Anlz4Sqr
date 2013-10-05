package de.lengsfeld.anlz4sqr.xml;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

import de.lengsfeld.anlz4sqr.connect.ConnectVenueCategories;
import fi.foyt.foursquare.api.Result;
import fi.foyt.foursquare.api.entities.Category;

public class XMLWriter {

	private Result<Category[]> result;

	public void saveCategories() {
		System.out.println("XML Writer started...");
		result = new ConnectVenueCategories().draw4();

		System.out.println("Number of items: " + result.getResult().length);
		int counter = 0;

		Element root = new Element("Root");
		Document doc = new Document(root);

		for (Category c : result.getResult()) {
			System.out.print(c.getName());
			System.out.println("\t\t" + c.getId());

			Element e1 = new Element("mainCategory").setText(c.getName());
			e1.setAttribute("ID", c.getId());
			System.out.println(e1.toString());
			root.addContent(e1);

			for (Category subc : c.getCategories()) {
				System.out.print(subc.getName());
				System.out.println("\t\t" + subc.getId());

				Element e2 = new Element("subCategory").setText(subc.getName());
				e2.setAttribute("ID", subc.getId());
				e1.addContent(e2);

				for (Category subsubc : subc.getCategories()) {
					System.out.print(subsubc.getName());
					System.out.println("\t\t\t" + subsubc.getId());
					Element e3 = new Element("subSubCategory").setText(subsubc
							.getName());
					e3.setAttribute("ID", subsubc.getId());
					e2.addContent(e3);
				}
			}
		}
		System.out.println(counter);

		XMLOutputter outputter = new XMLOutputter(Format.getPrettyFormat());
		try {
			FileOutputStream out = new FileOutputStream("Categories.xml");
			outputter.output(doc, out);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
