import java.util.HashMap;

public class MyMarkitJSONParser {

	public static HashMap<String, Object> parse(String s) {
		HashMap<String, Object> m = new HashMap<String, Object>();
		s = s.replaceAll("\"", "");
		s = s.substring(1, s.length() - 2);
		while (s.indexOf(",") > 0) {
			String key = s.substring(0, s.indexOf(":"));
			String tempValue = s.substring(s.indexOf(":") + 1, s.indexOf(","));
			Object value = tempValue;
			if (tempValue.indexOf("{") >= 0) {
				tempValue = s.substring(s.indexOf(":") + 1, s.indexOf("}") + 2);
				if (tempValue.length() > 3) {
					value = parse(tempValue);
				} else {
					value = "";
				}
				s = s.substring(s.indexOf("}") + 2);
			} else {
				s = s.substring(s.indexOf(",") + 1);
			}
			m.put(key, value);
		}
		String key = s.substring(0, s.indexOf(":"));
		String value = s.substring(s.indexOf(":") + 1);
		if (value.indexOf("{") >= 0) {
			value = s.substring(s.indexOf(":") + 1, s.indexOf("}") + 1);
		}
		m.put(key, value);

		return m;

	}

}
