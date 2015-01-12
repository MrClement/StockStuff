public class StockValue {
	public static boolean buyStock(String symbol, double price) {
		// measurements about the stock market currently
		double PE = 8.5; // price to earnings (external heuristic)
		double Y = 3.79; // 20 year bond rate (try to update this daily)
		double BY = 3.46; // Average yield of high-grade corporate bonds

		if (symbol == "LULU") {
			double EPS = 7.18; // earnings per share (yearly)
			double g = .1637; // growth estimate (9.81)

			double V = (EPS * (PE + (2 * g)) * BY) / Y; // Value
			System.out.println(V);
			System.out.println(V / price);
			if (V / price > 1) {
				buy("LULU");
				return true;

			}
		} else if (symbol == "TSLA") {
			double EPS = -1.5;
			double g = 0;

			double V = (EPS * (PE + (2 * g)) * BY) / Y;
			System.out.println(V);
			System.out.println(V / price);
			if (V / price > 1) {
				buy("TSLA");
				return true;

			}
		} else if (symbol == "LUV") {
			double EPS = 1.36;
			double g = .3233;

			double V = (EPS * (PE + (2 * g)) * BY) / Y;
			System.out.println(V);
			System.out.println(V / price);
			if (V / price > 1) {
				buy("LUV");
				return true;

			}
		} else if (symbol == "AAPL") {
			double EPS = 6.45;
			double g = .13;

			double V = (EPS * (PE + (2 * g)) * BY) / Y;
			System.out.println(V);
			System.out.println(V / price);
			if (V / price > 1) {
				buy("AAPL");
				return true;

			}
		} else if (symbol == "MSFT") {
			double EPS = .54;
			double g = .092;

			double V = (EPS * (PE + (2 * g)) * BY) / Y;
			System.out.println(V);
			System.out.println(V / price);
			if (V / price > 1) {
				buy("MSFT");
				return true;

			}
		} else if (symbol == "CMG") {
			double EPS = 10.29;
			double g = .2296;

			double V = (EPS * (PE + (2 * g)) * BY) / Y;
			System.out.println(V);
			System.out.println(V / price);
			if (V / price > 1) {
				buy("CMG");
				return true;

			}
		} else if (symbol == "ORCL") {
			double EPS = 1.04;
			double g = .1025;

			double V = (EPS * (PE + (2 * g)) * BY) / Y;
			System.out.println(V);
			System.out.println(V / price);
			if (V / price > 1) {
				buy("ORCL");
				return true;

			}
		} else if (symbol == "SBUX") {
			double EPS = 2.71;
			double g = .1799;

			double V = (EPS * (PE + (2 * g)) * BY) / Y;
			System.out.println(V);
			System.out.println(V / price);
			if (V / price > 1) {
				buy("SBUX");
				return true;
			}
		}
		return false;

	}

	private static void buy(String symbol) {
		System.out.println("YOU BOUGHT IT!");
	}
}