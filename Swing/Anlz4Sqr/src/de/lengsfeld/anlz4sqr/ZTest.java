package de.lengsfeld.anlz4sqr;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;

public class ZTest extends JFrame {

	public ZTest() {
		final JTree tree = new categoryTree();
		tree.addTreeSelectionListener(new TreeSelectionListener() {
			public void valueChanged(TreeSelectionEvent e) {
				DefaultMutableTreeNode node = (DefaultMutableTreeNode) tree
						.getLastSelectedPathComponent();
				if (node == null)
					return;
				Object nodeInfo = node.getUserObject();
				MyObject mO = (MyObject) nodeInfo;
				System.out.println(nodeInfo);
				System.out.println(mO.getId());
			}
		});
		add(new JScrollPane(tree));

		setSize(400, 400);
		setVisible(true);
	}

	public static void main(String[] args) {
		new ZTest();

	}

}
