package de.lengsfeld.anlz4sqr.connect;

import fi.foyt.foursquare.api.FoursquareApi;

public class FSConnect {

	private final String ID = ConnectProp.get("id");
	private final String SECRET = ConnectProp.get("secret");
	private final String CALLBACK = ConnectProp.get("callback");

	private static FSConnect instance = null;
	private FoursquareApi foursquareApi = null;

	private FSConnect() {
		foursquareApi = new FoursquareApi(ID, SECRET, CALLBACK);
	}

	public static FSConnect getInstance() {
		if (instance == null) {
			instance = new FSConnect();
		}
		return instance;
	}

	// ----------------------------------------------------------
	public FoursquareApi getFoursquareApi() {
		return foursquareApi;
	}

}
