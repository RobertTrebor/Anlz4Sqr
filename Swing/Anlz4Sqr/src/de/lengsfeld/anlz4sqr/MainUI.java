package de.lengsfeld.anlz4sqr;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JTree;
import javax.swing.ScrollPaneConstants;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;

import de.lengsfeld.anlz4sqr.connect.FSManager;
import de.lengsfeld.anlz4sqr.connect.ConnectVenueCategories;
import de.lengsfeld.anlz4sqr.model.AddressView;
import de.lengsfeld.anlz4sqr.model.CompleteView;
import de.lengsfeld.anlz4sqr.model.FlexibleCategoriesTableModel;
import de.lengsfeld.anlz4sqr.model.FlexibleTableModel;
import de.lengsfeld.anlz4sqr.model.FlexibleView;
import de.lengsfeld.anlz4sqr.model.MyAbstractTableModel;
import de.lengsfeld.anlz4sqr.model.StatsView;
import de.lengsfeld.anlz4sqr.model.TableView;
import de.lengsfeld.anlz4sqr.model.TableViewStrategy;
import de.lengsfeld.anlz4sqr.model.VenueView;
import de.lengsfeld.anlz4sqr.xml.XMLWriter;
import fi.foyt.foursquare.api.Result;
import fi.foyt.foursquare.api.entities.Category;
import fi.foyt.foursquare.api.entities.VenuesSearchResult;

public class MainUI {

	protected JFrame frame;
	private JTable table;
	private Result<VenuesSearchResult> result = null;
	private Result<Category[]> resultCategories = null;
	private MyAbstractTableModel model;
	private JTextField tfLatitude;
	private JTextField tfLongitude;
	private String latitude = "52.531227";
	private String longitude = "13.403921";
	private String coordinates = latitude + "," + longitude;
	private JButton btnNewButton_3;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private TableViewStrategy currentTableView;
	private JTextField tfQuery;
	private JTextField tfCategory;
	private String categoryId = null;
	private JTextField tfCategoryId;

	/**
	 * Create the application.
	 */
	public MainUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 1000, 448);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new BorderLayout(0, 0));

		JPanel panel = new JPanel();
		frame.getContentPane().add(panel, BorderLayout.NORTH);

		table = new VenueView(model);
		table.setAutoCreateRowSorter(true);

		TableView tv = new TableView(new CompleteView());
		currentTableView = (CompleteView) tv.getView();
		table.setModel(new FlexibleTableModel(result, currentTableView
				.tableView()));

		JButton btnConnect = new JButton("Connect");
		btnConnect.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				// result = new Connect().draw3(coordinates, null);
				// table.setModel(new FlexibleTableModel(result, titles));
				// model.fireTableDataChanged();
			}
		});
		btnConnect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		panel.add(btnConnect);

		JButton btnCategories = new JButton("Categories");
		btnCategories.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				resultCategories = new ConnectVenueCategories().draw4();
				table.setModel(new FlexibleCategoriesTableModel(
						resultCategories, "Cat1", "Cat2", "Cat3"));
			}
		});
		panel.add(btnCategories);

		JButton btnXML = new JButton("XML");
		btnXML.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("new XMLWriter");
				new XMLWriter().saveCategories();
			}
		});
		panel.add(btnXML);

		JButton btnNewButton_2 = new JButton("New button");
		panel.add(btnNewButton_2);

		JPanel panel_1 = new JPanel();
		frame.getContentPane().add(panel_1, BorderLayout.WEST);
		GridBagLayout gbl_panel_1 = new GridBagLayout();
		gbl_panel_1.columnWidths = new int[] { 93, 86, 0 };
		gbl_panel_1.rowHeights = new int[] { 20, 20, 0, 23, 0, 0, 0, 0, 0 };
		gbl_panel_1.columnWeights = new double[] { 1.0, 1.0, Double.MIN_VALUE };
		gbl_panel_1.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0,
				1.0, Double.MIN_VALUE };
		panel_1.setLayout(gbl_panel_1);

		JLabel lblLatitude = new JLabel("Latitude:");
		GridBagConstraints gbc_lblLatitude = new GridBagConstraints();
		gbc_lblLatitude.anchor = GridBagConstraints.WEST;
		gbc_lblLatitude.insets = new Insets(0, 0, 5, 5);
		gbc_lblLatitude.gridx = 0;
		gbc_lblLatitude.gridy = 0;
		panel_1.add(lblLatitude, gbc_lblLatitude);

		tfLatitude = new JTextField();
		tfLatitude.setText(latitude);

		GridBagConstraints gbc_tfLatitude = new GridBagConstraints();
		gbc_tfLatitude.fill = GridBagConstraints.HORIZONTAL;
		gbc_tfLatitude.insets = new Insets(0, 0, 5, 0);
		gbc_tfLatitude.gridx = 1;
		gbc_tfLatitude.gridy = 0;
		panel_1.add(tfLatitude, gbc_tfLatitude);
		tfLatitude.setColumns(10);

		JLabel lblLongitude = new JLabel("Longitude:");
		GridBagConstraints gbc_lblLongitude = new GridBagConstraints();
		gbc_lblLongitude.anchor = GridBagConstraints.WEST;
		gbc_lblLongitude.insets = new Insets(0, 0, 5, 5);
		gbc_lblLongitude.gridx = 0;
		gbc_lblLongitude.gridy = 1;
		panel_1.add(lblLongitude, gbc_lblLongitude);

		tfLongitude = new JTextField();
		tfLongitude.setText(longitude);

		GridBagConstraints gbc_tfLongitude = new GridBagConstraints();
		gbc_tfLongitude.fill = GridBagConstraints.HORIZONTAL;
		gbc_tfLongitude.insets = new Insets(0, 0, 5, 0);
		gbc_tfLongitude.gridx = 1;
		gbc_tfLongitude.gridy = 1;
		panel_1.add(tfLongitude, gbc_tfLongitude);
		tfLongitude.setColumns(10);

		JLabel lblQuery = new JLabel("Query:");
		GridBagConstraints gbc_lblQuery = new GridBagConstraints();
		gbc_lblQuery.anchor = GridBagConstraints.WEST;
		gbc_lblQuery.insets = new Insets(0, 0, 5, 5);
		gbc_lblQuery.gridx = 0;
		gbc_lblQuery.gridy = 2;
		panel_1.add(lblQuery, gbc_lblQuery);

		tfQuery = new JTextField();
		GridBagConstraints gbc_tfQuery = new GridBagConstraints();
		gbc_tfQuery.insets = new Insets(0, 0, 5, 0);
		gbc_tfQuery.fill = GridBagConstraints.HORIZONTAL;
		gbc_tfQuery.gridx = 1;
		gbc_tfQuery.gridy = 2;
		panel_1.add(tfQuery, gbc_tfQuery);
		tfQuery.setColumns(10);

		JButton btnNewButton_3_1 = new JButton("View Venues");
		btnNewButton_3_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				coordinates = tfLatitude.getText() + ","
						+ tfLongitude.getText();
				String query = tfQuery.getText();
				System.out.println(categoryId);
				result = new FSManager().draw3(coordinates, query, tfCategoryId.getText());
				System.out.println("@@@@@@@@@@@@@@@@@@");
				System.out.println(currentTableView.toString());
				System.out.println("@@@@@@@@@@@@@@@@@@");
				table.setModel(new FlexibleTableModel(result, currentTableView
						.tableView()));
			}
		});

		JLabel lblCategory = new JLabel("Category:");
		GridBagConstraints gbc_lblCategory = new GridBagConstraints();
		gbc_lblCategory.anchor = GridBagConstraints.WEST;
		gbc_lblCategory.insets = new Insets(0, 0, 5, 5);
		gbc_lblCategory.gridx = 0;
		gbc_lblCategory.gridy = 3;
		panel_1.add(lblCategory, gbc_lblCategory);

		tfCategory = new JTextField();
		GridBagConstraints gbc_tfCategory = new GridBagConstraints();
		gbc_tfCategory.insets = new Insets(0, 0, 5, 0);
		gbc_tfCategory.fill = GridBagConstraints.HORIZONTAL;
		gbc_tfCategory.gridx = 1;
		gbc_tfCategory.gridy = 3;
		panel_1.add(tfCategory, gbc_tfCategory);
		tfCategory.setColumns(10);

		GridBagConstraints gbc_btnNewButton_3_1 = new GridBagConstraints();
		gbc_btnNewButton_3_1.anchor = GridBagConstraints.NORTHWEST;
		gbc_btnNewButton_3_1.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButton_3_1.gridx = 0;
		gbc_btnNewButton_3_1.gridy = 4;
		panel_1.add(btnNewButton_3_1, gbc_btnNewButton_3_1);

		JPanel panel_2 = new JPanel();
		GridBagConstraints gbc_panel_2 = new GridBagConstraints();
		gbc_panel_2.anchor = GridBagConstraints.NORTHWEST;
		gbc_panel_2.insets = new Insets(0, 0, 5, 0);
		gbc_panel_2.gridx = 1;
		gbc_panel_2.gridy = 4;
		panel_1.add(panel_2, gbc_panel_2);
		panel_2.setLayout(new GridLayout(0, 1, 0, 0));

		JRadioButton rdbtnStats = new JRadioButton("Stats");
		rdbtnStats.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				coordinates = tfLatitude.getText() + ","
						+ tfLongitude.getText();
				TableView tv = new TableView(new StatsView());
				currentTableView = (StatsView) tv.getView();
				table.setModel(new FlexibleTableModel(result, currentTableView
						.tableView()));
			}
		});

		JRadioButton rdbtnComplete = new JRadioButton("Complete");
		rdbtnComplete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				coordinates = tfLatitude.getText() + ","
						+ tfLongitude.getText();
				TableView tv = new TableView(new CompleteView());
				currentTableView = (CompleteView) tv.getView();
				table.setModel(new FlexibleTableModel(result, currentTableView
						.tableView()));
			}
		});
		rdbtnComplete.setSelected(true);
		buttonGroup.add(rdbtnComplete);
		panel_2.add(rdbtnComplete);
		buttonGroup.add(rdbtnStats);
		panel_2.add(rdbtnStats);

		JRadioButton rdbtnAddress = new JRadioButton("Address");
		rdbtnAddress.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				coordinates = tfLatitude.getText() + ","
						+ tfLongitude.getText();
				TableView tv = new TableView(new AddressView());
				currentTableView = (AddressView) tv.getView();
				table.setModel(new FlexibleTableModel(result, currentTableView
						.tableView()));
			}
		});
		buttonGroup.add(rdbtnAddress);
		panel_2.add(rdbtnAddress);

		JRadioButton rdbtnFlexible = new JRadioButton("Flexible");
		rdbtnFlexible.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				coordinates = tfLatitude.getText() + ","
						+ tfLongitude.getText();
				TableView tv = new TableView(new FlexibleView());
				currentTableView = (FlexibleView) tv.getView();
				table.setModel(new FlexibleTableModel(result, currentTableView
						.tableView()));
			}
		});
		buttonGroup.add(rdbtnFlexible);
		panel_2.add(rdbtnFlexible);
		
		tfCategoryId = new JTextField();
		GridBagConstraints gbc_tfCategoryId = new GridBagConstraints();
		gbc_tfCategoryId.gridwidth = 2;
		gbc_tfCategoryId.insets = new Insets(0, 0, 5, 5);
		gbc_tfCategoryId.fill = GridBagConstraints.HORIZONTAL;
		gbc_tfCategoryId.gridx = 0;
		gbc_tfCategoryId.gridy = 5;
		panel_1.add(tfCategoryId, gbc_tfCategoryId);
		tfCategoryId.setColumns(10);

		JScrollPane scrollPane_2 = new JScrollPane();
		GridBagConstraints gbc_scrollPane_2 = new GridBagConstraints();
		gbc_scrollPane_2.gridwidth = 2;
		gbc_scrollPane_2.gridheight = 2;
		gbc_scrollPane_2.fill = GridBagConstraints.BOTH;
		gbc_scrollPane_2.gridx = 0;
		gbc_scrollPane_2.gridy = 6;
		panel_1.add(scrollPane_2, gbc_scrollPane_2);

		final JTree tree = new categoryTree();
		tree.addTreeSelectionListener(new TreeSelectionListener() {
			public void valueChanged(TreeSelectionEvent e) {
				DefaultMutableTreeNode node = (DefaultMutableTreeNode) tree
						.getLastSelectedPathComponent();
				if (node == null)
					return;
				Object nodeInfo = node.getUserObject();
				MyObject mO = (MyObject) nodeInfo;
				tfCategory.setText(nodeInfo.toString());
				tfCategoryId.setText(mO.getId());
				categoryId = mO.getId();
			}
		});
		scrollPane_2.setViewportView(tree);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane
				.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);

		scrollPane.setViewportView(table);
		frame.getContentPane().add(scrollPane, BorderLayout.CENTER);
	}
}
