package de.lengsfeld.anlz4sqr.model;


public class TableView {

	private TableViewStrategy view;

	public TableView(TableViewStrategy view) {
		this.view = view;
	}

	public TableViewStrategy getView() {
		return view;
	}

	public void setView(TableViewStrategy view) {
		this.view = view;
	}

}
