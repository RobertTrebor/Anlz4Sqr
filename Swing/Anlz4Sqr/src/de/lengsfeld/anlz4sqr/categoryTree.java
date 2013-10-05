package de.lengsfeld.anlz4sqr;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

import org.jdom2.Attribute;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

class MyObject {
	String cat;
	String id;

	public MyObject(String cat, String id) {
		super();
		this.cat = cat;
		this.id = id;
	}

	@Override
	public String toString() {
		return cat;
	}

	public String getId() {
		return id;
	}

}

public class categoryTree extends JTree {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	DefaultMutableTreeNode root = new DefaultMutableTreeNode(new MyObject(
			"Categories", ""));
	DefaultTreeModel model = new DefaultTreeModel(root);
	DefaultMutableTreeNode currentRoot = new DefaultMutableTreeNode();

	public categoryTree() {
		this.setModel(model);
		System.out.println("XML Reader started...");

		Document doc = null;
		SAXBuilder b = new SAXBuilder();
		try {
			doc = b.build(new File("Categories.xml"));
			Element element = doc.getRootElement();
			currentRoot = root;
			readAllNodes(element, currentRoot);
		} catch (JDOMException | IOException e) {
			e.printStackTrace();
		}
	}

	public void readAllNodes(Element element, DefaultMutableTreeNode currentRoot) {

		for (Element e : element.getChildren()) {
			List<Attribute> attr = e.getAttributes();
			String a = "";

			for (Attribute at : attr) {
				a += at.getValue();
			}

			System.out.print(e.getName() + ": " + a + "\t");
			System.out.println("..." + e.getTextTrim());
			DefaultMutableTreeNode n = new DefaultMutableTreeNode(new MyObject(
					e.getTextTrim(), a));
			System.out.println("************ ID is: " + a);
			currentRoot.add(n);
			readAllNodes(e, n);
		}
	}
}