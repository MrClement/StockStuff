public class Stock {

	private String symbol;
	private double price;

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
			price = Double.parseDouble((String) MarkitAPI.getInfo(symbol).get("LastPrice"));
		} catch (NullPointerException e) {

		}
	}

}
