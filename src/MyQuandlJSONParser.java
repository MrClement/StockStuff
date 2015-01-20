import java.util.HashMap;

public class MyQuandlJSONParser {
	public static HashMap parse(String fullString) {
		// Scanner s = new Scanner(System.in);
		// String fullString = s.nextLine();
		// It doesn't like the string going directly into the method, this
		// works, but I don't like it.
		boolean stillRun = true;
		String myString = fullString;
		HashMap<String, String> returner = new HashMap<String, String>();
		while (stillRun) {
			String key = myString.substring(myString.indexOf("\"") + 1);
			key = key.substring(0, key.indexOf("\""));
			myString = myString.substring(myString.indexOf("\"") + 1);
			myString = myString.substring(myString.indexOf("\"") + 1);
			String value = "";
			if (myString.indexOf("\"") == 1) {
				myString = myString.substring(2);
				value = myString.substring(0, myString.indexOf("\""));
				myString = myString.substring(myString.indexOf("\"") + 1);
			} else if (myString.indexOf("[[") == 1) {
				value = myString.substring(1, myString.indexOf("]]") + 2);
				myString = myString.substring(myString.indexOf("]]") + 2);
			} else if (myString.indexOf("[") == 1) {
				value = myString.substring(1, myString.indexOf("]") + 1);
				myString = myString.substring(myString.indexOf("]") + 1);
			} else {
				value = myString.substring(myString.indexOf(":") + 1);
				value = value.substring(0, value.indexOf(","));
				myString = myString.substring(myString.indexOf(":") + 1 + value.length());
			}
			if (myString.charAt(0) == '}') {
				returner.put(key, value);
				stillRun = false;
			} else {
				returner.put(key, value);
			}
		}
		return returner;
	}
}