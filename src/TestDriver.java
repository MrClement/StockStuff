import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.JSONObject;

public class TestDriver {

	public static void main(String[] args) {

		URL url;
		String result = "";
		String line;
		HttpURLConnection connection;
		BufferedReader rd;

		try {

			// http://example.markit.com/TestApi/SampleRequest/xml?count=3&echo=example

			String quote = "https://query.yahooapis.com/v1/public/yql?q=SELECT%20*%20FROM%20yahoo.finance.keystats%20WHERE%20symbol%3D'AAPL'&format=json&diagnostics=true&env=store%3A%2F%2Fdatatables.org%2Falltableswithkeys&callback=";
			url = new URL(quote);

			connection = (HttpURLConnection) (url.openConnection());

			connection.setRequestMethod("GET");

			int response = connection.getResponseCode();

			// System.out.println(response);

			rd = new BufferedReader(new InputStreamReader(connection.getInputStream()));

			while ((line = rd.readLine()) != null) {

				result += line + "\n";

			}

			JSONObject test = new JSONObject(result);
			System.out.println(test);

		} catch (IOException e) {
		}

	}

}
