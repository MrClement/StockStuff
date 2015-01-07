import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;

public class MarkitAPI {

	public static HashMap<String, Object> getInfo(String symbol) {

		URL url;
		String result = "";
		String line;
		HttpURLConnection connection;
		BufferedReader rd;

		try {

			// http://example.markit.com/TestApi/SampleRequest/xml?count=3&echo=example

			String name = "http://dev.markitondemand.com/Api/v2/Lookup/json?input=Netflix";
			String quote = "http://dev.markitondemand.com/Api/v2/Quote/json?symbol=" + symbol;
			url = new URL(quote);

			connection = (HttpURLConnection) (url.openConnection());

			connection.setRequestMethod("GET");

			int response = connection.getResponseCode();

			// System.out.println(response);

			rd = new BufferedReader(new InputStreamReader(connection.getInputStream()));

			while ((line = rd.readLine()) != null) {

				result += line + "\n";

			}

			HashMap<String, Object> parsedJSON = MyJSONParser.parseJSON(result);

			return parsedJSON;
		} catch (IOException e) {
			return null;
		}

	}
}