package de.lengsfeld.anlz4sqr.model;

import javax.swing.table.AbstractTableModel;

import fi.foyt.foursquare.api.Result;
import fi.foyt.foursquare.api.entities.CompactVenue;
import fi.foyt.foursquare.api.entities.VenuesSearchResult;

public class MyAbstractTableModel extends AbstractTableModel {

	private static final long serialVersionUID = 1L;
	private Result<VenuesSearchResult> result;
	private String[] titles;

	// String[] telefonNummern = { "Max", "Anton", "Marta", "Ina" };
	//String[] ueberschrift = { "Venue", "Checked in", "URL", "Checkins",
	//		"Users", "Address" };

	public MyAbstractTableModel(String[] titles,
			Result<VenuesSearchResult> result) {
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

		switch (colIndex) {
		case 0:
			return venue[rowIndex].getName();
		case 1:
			return venue[rowIndex].getHereNow().getCount();
		case 2:
			return venue[rowIndex].getUrl();
		case 3:
			return venue[rowIndex].getStats().getCheckinsCount();
		case 4:
			return venue[rowIndex].getStats().getUsersCount();
		case 5:
			return venue[rowIndex].getLocation().getAddress();
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
