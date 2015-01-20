import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;

public class QuandlAPI {

	public static HashMap<String, Object> getInfo(String symbol, String source) {
		URL url;
		String result = "";
		String line;
		HttpURLConnection connection;
		BufferedReader rd;
		try {
			// http://example.markit.com/TestApi/SampleRequest/xml?count=3&echo=example
			// https://www.quandl.com/api/v1/datasets/FRED/GDP.json
			// https://www.quandl.com/api/v1/datasets/SEC/AAPL_EARNINGSPERSHAREDILUTED_A.json?auth_token=b51eX81Wyiu1umHEa_iJ
			// https://www.quandl.com/api/v1/datasets/DMDRN/AAPL_EPS_GRO_EXP.json?auth_token=b51eX81Wyiu1umHEa_iJ
			String quote;
			if (source.equals("SEC")) {
				quote = "https://www.quandl.com/api/v1/datasets/SEC/" + symbol
						+ "_EARNINGSPERSHAREDILUTED_A.json?auth_token=b51eX81Wyiu1umHEa_iJ";
				System.out.println(quote);
			} else if (source.equals("DMDRN")) {
				quote = "https://www.quandl.com/api/v1/datasets/DMDRN/" + symbol
						+ "_EPS_GRO_EXP.json?auth_token=b51eX81Wyiu1umHEa_iJ";

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
			HashMap<String, Object> parsedJSON = MyQuandlJSONParser.parse(result);
			return parsedJSON;
		} catch (IOException e) {
			return null;
		}
	}

	public static double getEPS(String symbol) {
		HashMap<String, Object> hm = getInfo(symbol, "SEC");
		System.out.println(hm);
		String myEPS = (String) hm.get("data");
		ArrayList<Object> al = bracketParse(myEPS);
		myEPS = (String) al.get(1);
		return Double.parseDouble(myEPS);
	}

	public static double getGrowth(String symbol) {
		HashMap<String, Object> hm = getInfo(symbol, "DMDRN");
		String myGrowth = (String) hm.get("data");
		ArrayList<Object> al = bracketParse(myGrowth);
		myGrowth = (String) al.get(1);
		return Double.parseDouble(myGrowth);
	}

	public static ArrayList<Object> bracketParse(String first) {
		boolean stillRun = true;
		String key = "";
		String value = "";
		HashMap<String, Object> returner = new HashMap<String, Object>();
		ArrayList<Object> myReturn = new ArrayList<Object>();
		while (stillRun) {
			key = first.substring(first.indexOf("\""), first.indexOf(","));
			first = first.substring(first.indexOf(","));
			value = first.substring(1, first.indexOf("]"));
			first = first.substring(first.indexOf("]") + 2);
			returner.put(key, value);
			myReturn.add(key);
			myReturn.add(value);
			if (first.indexOf("\"") < 0) {
				stillRun = false;
			}
		}
		return myReturn;
	}

}