import java.util.ArrayList;

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
		System.out.println(V / price);
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

	public static void shortTermEval(String symbol, double price, YahooAPI a) {
		ArrayList<Double> dailys = a.getDailys();
		Double dailyLow = 0.0;
		for (int i = 0; i < dailys.size(); i += 2) {
			dailyLow = dailys.get(i);
		}
		dailyLow = dailyLow / (dailys.size() / 2);

		Double dailyHigh = 0.0;
		for (int i = 1; i < dailys.size(); i += 2) {
			dailyHigh = dailys.get(i);
		}
		dailyHigh = dailyHigh / (dailys.size() / 2);

		if (price < dailyLow * 1.1) {
			buy(symbol);
		} else if (price > dailyHigh * 0.9) {
			sell(symbol);
		} else {
			System.out.println("Keep current portfolio");
		}
	}
}