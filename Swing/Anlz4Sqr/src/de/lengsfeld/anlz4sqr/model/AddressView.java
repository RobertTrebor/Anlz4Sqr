package de.lengsfeld.anlz4sqr.model;


public class AddressView implements TableViewStrategy {

	private String[] titles = { "Name", "Address", "Zip", "City", "State",
			"Country", "CrossStreet" };

	@Override
	public String[] tableView() {

		System.out.println("--------------------------");
		System.out.println("AddressView Selected");
		System.out.println("--------------------------");
		return titles;
	}

}
