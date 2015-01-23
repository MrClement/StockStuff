import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;

import org.json.JSONException;
import org.json.JSONObject;

public class YahooAPI {

	public static JSONObject getInfo(String symbol, String source) {
		URL url;
		String result = "";
		String line;
		HttpURLConnection connection;
		BufferedReader rd;
		try {
			//query.yahooapis.com/v1/public/yql?q=select%20*%20from%20yahoo.finance.quotes%20where%20symbol%20in%20(%22MSFT%22%2C%22AAPL%22%2C%22GOOG%22%2C%22MSFT%22)&format=json&diagnostics=true&env=store%3A%2F%2Fdatatables.org%2Falltableswithkeys&callback=
			// http://example.markit.com/TestApi/SampleRequest/xml?count=3&echo=example
			// https://www.quandl.com/api/v1/datasets/FRED/GDP.json
			// https://www.quandl.com/api/v1/datasets/SEC/AAPL_EARNINGSPERSHAREDILUTED_A.json?auth_token=b51eX81Wyiu1umHEa_iJ
			// https://www.quandl.com/api/v1/datasets/DMDRN/AAPL_EPS_GRO_EXP.json?auth_token=b51eX81Wyiu1umHEa_iJ
			String quote;
			if (source.equals("keystats")) {
				quote = "https://query.yahooapis.com/v1/public/yql?q=SELECT%20*%20FROM%20yahoo.finance.keystats%20WHERE%20symbol%3D'"+symbol+"'&format=json&diagnostics=true&env=store%3A%2F%2Fdatatables.org%2Falltableswithkeys&callback=";
			} else if (source.equals("quotes")) {
				quote = "https://query.yahooapis.com/v1/public/yql?q=select%20*%20from%20yahoo.finance.quotes%20where%20symbol%3D'"+symbol+"'&format=json&diagnostics=true&env=store%3A%2F%2Fdatatables.org%2Falltableswithkeys&callback=";

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
			JSONObject j=new JSONObject(result);
			return j;
		} catch (IOException e) {
			return null;
		}
	}

	public static double getEPS(String symbol) {	
		JSONObject stats = YahooAPI.getInfo(symbol, "keystats").getJSONObject("query").getJSONObject("results").getJSONObject("stats");		
		JSONObject EPS = stats.getJSONObject("DilutedEPS");
		return EPS.getDouble("content");
	
	
	}

	public static double getGrowth(String symbol) {
		JSONObject stats = YahooAPI.getInfo(symbol,"keystats").getJSONObject("query").getJSONObject("results").getJSONObject("stats");
		JSONObject growth = stats.getJSONObject("QtrlyEarningsGrowth");
		
		return Double.parseDouble(growth.getString("content").split("%")[0]);
	
	}
	public static double getPrice(String symbol) {	
		JSONObject stats = YahooAPI.getInfo(symbol,"quotes").getJSONObject("query").getJSONObject("results").getJSONObject("quote");
		double change= stats.getDouble("ChangeRealtime");
		double close= stats.getDouble("PreviousClose");
		
	
		return close+change;
	
	
	}

}

