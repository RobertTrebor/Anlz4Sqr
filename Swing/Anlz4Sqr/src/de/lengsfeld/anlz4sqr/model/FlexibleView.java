package de.lengsfeld.anlz4sqr.model;


public class FlexibleView implements TableViewStrategy {

	private String[] titles = { "Category", "Categories", "CategoryId",
			"MultipleCategories" };

	@Override
	public String[] tableView() {

		System.out.println("--------------------------");
		System.out.println("FlexibleView Selected");
		System.out.println("--------------------------");
		return titles;
	}

}
