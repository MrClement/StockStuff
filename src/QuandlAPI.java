import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;

public class QuandlAPI {
	public static HashMap<String, Object> getInfo(String symbol) {

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

			String quote = "https://www.quandl.com/api/v1/datasets/ZEE/AAPL_Q.json?auth_token=b51eX81Wyiu1umHEa_iJ";
			url = new URL(quote);

			connection = (HttpURLConnection) (url.openConnection());

			connection.setRequestMethod("GET");

			int response = connection.getResponseCode();

			// System.out.println(response);

			rd = new BufferedReader(new InputStreamReader(connection.getInputStream()));

			while ((line = rd.readLine()) != null) {

				result += line + "\n";

			}

			HashMap<String, Object> parsedJSON = QuandlParser.parse(result);
			return parsedJSON;
		} catch (IOException e) {
			return null;
		}
	}
	public HashMap<String, Object> getGrowth(String symbol){
		try{
		URL url;
		String result = "";
		String line;
		HttpURLConnection connection;
		BufferedReader rd;
		
		String growth = "https://www.quandl.com/api/v1/datasets/ZEE/AAPL_Q.json?auth_token=b51eX81Wyiu1umHEa_iJ";
		url = new URL(growth);

		connection = (HttpURLConnection) (url.openConnection());

		connection.setRequestMethod("GET");

		int response = connection.getResponseCode();

		rd = new BufferedReader(new InputStreamReader(connection.getInputStream()));

		while ((line = rd.readLine()) != null) {

			result += line + "\n";

		}

		HashMap<String, Object> parsedJSON = QuandlParser.parse(result);
		return parsedJSON;
	} catch (IOException e) {
		return null;
	}	
	}
}
