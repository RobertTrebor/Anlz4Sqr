package de.lengsfeld.anlz4sqr.model;


public class StatsView implements TableViewStrategy {

	private String[] titles = { "Name", "Count", "CheckinsCount", "UsersCount",
			"Categories" };

	@Override
	public String[] tableView() {
		
		System.out.println("--------------------------");
		System.out.println("StatsView Selected");
		System.out.println("--------------------------");
		return titles;
	}

	public String[] getTitles() {
		return titles;
	}

	public void setTitles(String[] titles) {
		this.titles = titles;
	}

	
}
