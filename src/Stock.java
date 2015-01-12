import java.util.HashMap;

public class Stock {

	private String symbol;
	private double price;
	private String time;

	public Stock(String symbol) {
		this.symbol = symbol;
	}

	public String getSymbol() {
		return symbol;
	}

	public double getPrice() {
		return price;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public void updatePrice() {
		// gets current stock price from Markit
		// How?
		System.out.println(symbol);
		try {
			HashMap<String, Object> info = MarkitAPI.getInfo(symbol);
			price = Double.parseDouble((String) info.get("LastPrice"));
			time = (String) info.get("Timestamp");
		} catch (NullPointerException e) {

		}
	}

	public String getTime() {
		return time;
	}

}
