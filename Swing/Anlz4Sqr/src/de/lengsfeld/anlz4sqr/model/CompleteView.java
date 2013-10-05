package de.lengsfeld.anlz4sqr.model;


public class CompleteView implements TableViewStrategy {

	private String[] titles = { "Name", "Count", "Url", "CheckinsCount",
			"UsersCount", "*Name", "Address", "Zip", "City", "Country",
			"CrossStreet", "Distance", "Lat", "Lng", "State" };

	@Override
	public String[] tableView() {

		System.out.println("--------------------------");
		System.out.println("CompleteView Selected");
		System.out.println("--------------------------");
		return titles;
	}

}
