import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.GregorianCalendar;

import org.json.JSONArray;
import org.json.JSONObject;

public class YahooAPI {

	private double currentPrice;
	private double EPS;
	private String time;
	private double growth;
	private ArrayList<Double> dailies;

	public YahooAPI(String symbol) {
		JSONObject quotes = getInfo(symbol, "quotes");
		JSONObject keystats = getInfo(symbol, "keystats");
		JSONObject historical = getInfo(symbol, "historical");
		JSONObject estimates= getInfo(symbol, "analystestimate");
		currentPrice = establishPrice(quotes);
		EPS = establishEPS(keystats);
		growth = establishGrowth(symbol, estimates);
		time = establishTime(keystats);
		dailies = new ArrayList<Double>();
		dailies = establishDailies(historical);
	}

	public double getCurrentPrice() {
		return currentPrice;
	}

	public double getEPS() {
		return EPS;
	}

	public String getTime() {
		return time;
	}

	public double getGrowth() {
		return growth;
	}

	public ArrayList<Double> getDailys() {
		return dailies;
	}

	public JSONObject getInfo(String symbol, String source) {
		URL url;
		String result = "";
		String line;
		HttpURLConnection connection;
		BufferedReader rd;
		try {
			// query.yahooapis.com/v1/public/yql?q=select%20*%20from%20yahoo.finance.quotes%20where%20symbol%20in%20(%22MSFT%22%2C%22AAPL%22%2C%22GOOG%22%2C%22MSFT%22)&format=json&diagnostics=true&env=store%3A%2F%2Fdatatables.org%2Falltableswithkeys&callback=
			// http://example.markit.com/TestApi/SampleRequest/xml?count=3&echo=example
			// https://www.quandl.com/api/v1/datasets/FRED/GDP.json
			// https://www.quandl.com/api/v1/datasets/SEC/AAPL_EARNINGSPERSHAREDILUTED_A.json?auth_token=b51eX81Wyiu1umHEa_iJ
			// https://www.quandl.com/api/v1/datasets/DMDRN/AAPL_EPS_GRO_EXP.json?auth_token=b51eX81Wyiu1umHEa_iJ
			String quote;
			if (source.equals("keystats")) {
				quote = "https://query.yahooapis.com/v1/public/yql?q=SELECT%20*%20FROM%20yahoo.finance.keystats%20WHERE%20symbol%3D'"
						+ symbol
						+ "'&format=json&diagnostics=true&env=store%3A%2F%2Fdatatables.org%2Falltableswithkeys&callback=";
			} else if (source.equals("quotes")) {
				quote = "https://query.yahooapis.com/v1/public/yql?q=select%20*%20from%20yahoo.finance.quotes%20where%20symbol%3D'"
						+ symbol
						+ "'&format=json&diagnostics=true&env=store%3A%2F%2Fdatatables.org%2Falltableswithkeys&callback=";
			} else if (source.equals("historical")) {
				String startDate = "2014-01-01";
				GregorianCalendar now = new GregorianCalendar();
				String endDate = now.get(GregorianCalendar.YEAR) + "-" + now.get(GregorianCalendar.MONTH) + "-"
						+ now.get(GregorianCalendar.DAY_OF_MONTH);
				quote = "https://query.yahooapis.com/v1/public/yql?q=select%20*%20from%20yahoo.finance.historicaldata%20where%20symbol%20%3D%20%22"
						+ symbol
						+ "%22%20and%20startDate%20%3D%20%22"
						+ startDate
						+ "%22%20and%20endDate%20%3D%20%22"
						+ endDate
						+ "%22&format=json&diagnostics=true&env=store%3A%2F%2Fdatatables.org%2Falltableswithkeys&callback=";
			} else if (source.equals("analystestimate")) { 
				quote="https://query.yahooapis.com/v1/public/yql?q=SELECT%20*%20FROM%20yahoo.finance.analystestimate%20WHERE%20symbol%3D'"+symbol+"'&format=json&diagnostics=true&env=store%3A%2F%2Fdatatables.org%2Falltableswithkeys&callback=";
			} else {
				quote = "";
			}

			url = new URL(quote);
			connection = (HttpURLConnection) (url.openConnection());
			connection.setRequestMethod("GET");
			int response = connection.getResponseCode();
			rd = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			while ((line = rd.readLine()) != null) {

				result += line + "\n";

			}
			JSONObject j = new JSONObject(result);
			return j;
		} catch (IOException e) {
			return null;
		}
	}

	private String establishTime(JSONObject stats) {
		stats = stats.getJSONObject("query");
		return stats.getString("created");
	}

	private ArrayList<Double> establishDailies(JSONObject historical) {
		JSONArray myArray = new JSONArray();
		myArray = historical.getJSONObject("query").getJSONObject("results").getJSONArray("quote");
		int counter = 0;
		for (int i = 0; i < myArray.length(); i++) {
			dailies.add(counter++, myArray.getJSONObject(i).getDouble("High"));
			dailies.add(counter++, myArray.getJSONObject(i).getDouble("Low"));
		}
		return dailies;
	}

	private double establishEPS(JSONObject stats) {
		stats = stats.getJSONObject("query").getJSONObject("results").getJSONObject("stats");
		JSONObject EPS = stats.getJSONObject("DilutedEPS");
		return EPS.getDouble("content");

	}

	  private double establishGrowth(String symbol, JSONObject estimate) {
		    estimate = estimate.getJSONObject("query").getJSONObject("results").getJSONObject("results").getJSONObject("GrowthEst").getJSONObject("Next5Years");
		    return Double.parseDouble(estimate.getString(symbol).split("%")[0]);

		  }

	private double establishPrice(JSONObject quotes) {
		quotes = quotes.getJSONObject("query").getJSONObject("results").getJSONObject("quote");
		double change = quotes.getDouble("ChangeRealtime");
		double close = quotes.getDouble("PreviousClose");
		return close + change;

	}

}
