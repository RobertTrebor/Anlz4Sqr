package de.lengsfeld.anlz4sqr.connect;

import fi.foyt.foursquare.api.FoursquareApi;
import fi.foyt.foursquare.api.FoursquareApiException;
import fi.foyt.foursquare.api.Result;
import fi.foyt.foursquare.api.entities.CompactVenue;
import fi.foyt.foursquare.api.entities.VenuesSearchResult;

public final class FSManager {
	
	FSConnect fs = FSConnect.getInstance();
	FoursquareApi foursquareApi;
	
	public FSManager() {
		foursquareApi = fs.getFoursquareApi();
	}
	
	public Result<VenuesSearchResult> collectVenues(String ll, String query,
			String categoryId) throws FoursquareApiException {

		System.setProperty("java.net.useSystemProxies", "true");

		
		
//		String id = "NZXTXGVC1WXTLKP5YUOZ3BR0XRBBRKWLPYVPKUZTQWEAMY3Q";
//		String secret = "DDYV0UNDT4OYHHIC03US1T13S5WI4IUD5MI5PXH5LJNOQMB1";
//		String callback = "https://de.foursquare.com/oauth2/authorize";
		
		// First we need a initialize FoursquareApi.
//		FoursquareApi foursquareApi = new FoursquareApi(ID, SECRET, CALLBACK);

		// After client has been initialized we can make queries.
		// Parameters: ll, llAcc, alt, altAcc, query, limit (max 50), intent,
		// categoryId, url, providerId, linkedId
		Result<VenuesSearchResult> result = foursquareApi
				.venuesSearch(ll, null, null, null, query, 50, null,
						categoryId, null, null, null);

		System.out.println("Now using coordinates: " + ll);
		if (result.getMeta().getCode() == 200) {
			// if query was ok we can finally do something with the data
			System.out.println("OK! ");
			System.out.println("Number of items: "
					+ result.getResult().getVenues().length);
			for (CompactVenue venue : result.getResult().getVenues()) {
				System.out.print(venue.getName() + "\t");
			}
			return result;

		} else {
			// TODO: Proper error handling
			System.out.println("Error occured: ");
			System.out.println("  code: " + result.getMeta().getCode());
			System.out.println("  type: " + result.getMeta().getErrorType());
			System.out
					.println("  detail: " + result.getMeta().getErrorDetail());
		}
		return result;
	}

	public Result<VenuesSearchResult> draw3(String coordinates, String query,
			String categoryId) {
		Result<VenuesSearchResult> result = null;
		try {
			result = collectVenues(coordinates, query, categoryId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
}