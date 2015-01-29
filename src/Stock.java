public class Stock {

	private String symbol;
	private double price;
	private String time;
	private boolean bought;
	private double EPS;
	private double growth;

	public double getEPS() {
		return EPS;
	}

	public double getGrowth() {
		return growth;
	}

	public Stock(String symbol) {
		this.symbol = symbol;
//		YahooAPI y=new YahooAPI(symbol);
		updatePrice();
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
		// gets current stock price from Yahoo
		// How?
		System.out.println(symbol);
		try {
			YahooAPI a = new YahooAPI(symbol);

			price = a.getCurrentPrice();
			bought = StockValue.buyStock(symbol, price, a);
			time = a.getTime();
			EPS = a.getEPS();
			growth = a.getGrowth();
		} catch (NullPointerException e) {

		}
	}

	public String getTime() {
		return time;
	}

	public String toString() {
		return symbol + " " + "Price: " + price + " Bought? " + bought;
	}

}
