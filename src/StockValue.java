public class StockValue {

	public static boolean buyStock(String symbol, double price) {
		// measurements about the stock market currently
		double PE = 8.5; // price to earnings (external heuristic)
		double Y = 3.79; // 20 year bond rate (try to update this daily)
		double BY = 3.46; // Average yield of high-grade corporate bonds

		System.out.println("Trying to buy " + symbol);
		double EPS = QuandlAPI.getEPS(symbol);
		double g = QuandlAPI.getGrowth(symbol);

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
}