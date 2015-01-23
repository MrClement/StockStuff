public class StockValue {

	public static boolean buyStock(String symbol, double price, YahooAPI a) {
		// measurements about the stock market currently
		double PE = 8.5; // price to earnings (external heuristic)
		double Y = 3.79; // 20 year bond rate (try to update this daily)
		double BY = 3.46; // Average yield of high-grade corporate bonds

		System.out.println("Trying to buy " + symbol);
		double EPS = a.getEPS();
		double g = a.getGrowth();

		double V = (EPS * (PE + (2 * g)) * BY) / Y; // Value

		if (V / price > 1) {
			buy(symbol);
			return true;

		}
		return false;

	}

	private static void buy(String symbol) {
		System.out.println("YOU BOUGHT IT!");
	}

	private static void sell(String symbol) {
		System.out.println("YOU SOLD IT!");
	}

	public static void shortTermEval(String symbol, double price, double dailyLow, double dailyHigh) {
		if (price < dailyLow * 1.1) {
			buy(symbol);
		} else if (price > dailyHigh * 0.9) {
			sell(symbol);
		} else {
			System.out.println("Keep current portfolio");
		}
	}
}