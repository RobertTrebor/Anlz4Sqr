package de.lengsfeld.anlz4sqr.model;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import fi.foyt.foursquare.api.Result;
import fi.foyt.foursquare.api.entities.Category;

public class FlexibleCategoriesTableModel extends AbstractTableModel {

	private static final long serialVersionUID = 1L;
	private Result<Category[]> result;
	private String[] titles;
	private String[][][] categories;
	private ArrayList<Category> catList1;
	private ArrayList<Category> catList2;
	private ArrayList<Category> catList3;
	private int counter = 0;

	public FlexibleCategoriesTableModel(Result<Category[]> result,
			String... titles) {
		super();
		this.titles = titles;
		this.result = result;
	}

	@Override
	public int getColumnCount() {
		return titles.length;
	}

	@Override
	public int getRowCount() {
		if (result != null) {
			counter = 0;
			for(int i=0; i < result.getResult().length; i++) {
				Category cat1[] = result.getResult();
				Category c1 = result.getResult()[i];
				System.out.println(c1);
				catList1.add(c1);
				String s1 = result.getResult()[i].getName();
				//categories[i][0][0] = s1;
				System.out.println(s1);
				counter++;
			
				for(int j=0; j < c1.getCategories().length; j++ ) {
					Category cat2[] = c1.getCategories();
					Category c2 = c1.getCategories()[j];
					catList2.add(c2);
					String s2 = c1.getCategories()[j].getName();
					//categories[i][j][0] = s2;
					counter++;
					
					for (int k = 0; k < c2.getCategories().length; k++ ) {
						Category cat3[] = c2.getCategories();
						Category c3 = c2.getCategories()[k];
						catList3.add(c3);
						String s3 = c2.getCategories()[k].getName();
						//categories[i][j][k] = s3;
						counter++;
					}
				}
			}
				
			System.out.println(counter);
			return counter;
			
		} else
			return 0;
	}

	@Override
	public Object getValueAt(int rowIndex, int colIndex) {
	
		switch(colIndex) {
		case 0:
			if(catList1.get(rowIndex) != null) {
				return catList1.get(rowIndex);
			}
			break;
		case 1:
			if(catList2.get(rowIndex) != null) {
				return catList2.get(rowIndex);
			}
			break;
		case 2:
			if(catList3.get(rowIndex) != null) {
				return catList3.get(rowIndex);
			}
			break;
		}
		return 0;
	}

	@Override
	public String getColumnName(int column) {
		return titles[column];
	}

	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		if (columnIndex == 1) {
			return false;
		}
		return true;
	}

	@Override
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		// System.out.println(telefonNummern[rowIndex]);
		// telefonNummern[rowIndex] = String.valueOf(aValue);
		// System.out.println(Arrays.toString(telefonNummern[0]));
	}
}
