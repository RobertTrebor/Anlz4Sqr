package de.lengsfeld.anlz4sqr.model;

import javax.swing.table.AbstractTableModel;

import fi.foyt.foursquare.api.Result;
import fi.foyt.foursquare.api.entities.Category;
import fi.foyt.foursquare.api.entities.CompactVenue;
import fi.foyt.foursquare.api.entities.VenuesSearchResult;

public class FlexibleTableModel extends AbstractTableModel {

	private static final long serialVersionUID = 1L;
	private Result<VenuesSearchResult> result;
	private String[] titles;

	public FlexibleTableModel(Result<VenuesSearchResult> result,
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
			return result.getResult().getVenues().length;
		} else
			return 0;
	}

	@Override
	public Object getValueAt(int rowIndex, int colIndex) {

		CompactVenue[] venue = result.getResult().getVenues();

		switch (titles[colIndex]) {
		case "Name":
			return venue[rowIndex].getName();
		case "Count":
			return venue[rowIndex].getHereNow().getCount();
		case "Url":
			return venue[rowIndex].getUrl();
		case "CheckinsCount":
			return venue[rowIndex].getStats().getCheckinsCount();
		case "UsersCount":
			return venue[rowIndex].getStats().getUsersCount();
		
		case "*Name":
			return venue[rowIndex].getLocation().getName();
		case "Address":
			return venue[rowIndex].getLocation().getAddress();
		case "Zip":
			return venue[rowIndex].getLocation().getPostalCode();
		case "City":	
			return venue[rowIndex].getLocation().getCity();
		case "Country":
			return venue[rowIndex].getLocation().getCountry();
		case "CrossStreet":
			return venue[rowIndex].getLocation().getCrossStreet();
		case "Distance":
			return venue[rowIndex].getLocation().getDistance();
		case "Lat":
			return venue[rowIndex].getLocation().getLat();
		case "Lng":
			return venue[rowIndex].getLocation().getLng();
		case "State":			
			return venue[rowIndex].getLocation().getState();
		case "Category":
			for(Category c : venue[rowIndex].getCategories()) {
				return c.getName();
			} 
		case "Categories":
			for(Category c : venue[rowIndex].getCategories()) {
				return c.getPluralName();
			}		 
		case "CategoryId":
			for(Category c : venue[rowIndex].getCategories()) {
				return c.getId();
			}
		case "MultipleCategories":
			String categories = "";
			for(Category c : venue[rowIndex].getCategories()) {
				categories += c.getName() + ", " + venue[rowIndex].getCategories().length;
			}
			return categories;
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
