public class StockValue {
	public static void buyStock(String symbol, double price) {
		double PE = 8.5;
		double Y = 3.79;
		double BY = 3.46;

		if (symbol == "LULU") {
			double EPS = 7.18;
			double g = .1637;

			double V = (EPS * (PE + (2 * g)) * BY) / Y;
			System.out.println(V);
			System.out.println(V / price);
			if (V / price > 1) {
				buy("LULU");
			}
		} else if (symbol == "TSLA") {
			double EPS = -1.5;
			double g = 0;

			double V = (EPS * (PE + (2 * g)) * BY) / Y;
			System.out.println(V);
			System.out.println(V / price);
			if (V / price > 1) {
				buy("TSLA");
			}
		} else if (symbol == "LUV") {
			double EPS = 1.36;
			double g = .3233;

			double V = (EPS * (PE + (2 * g)) * BY) / Y;
			System.out.println(V);
			System.out.println(V / price);
			if (V / price > 1) {
				buy("LUV");
			}
		} else if (symbol == "AAPL") {
			double EPS = 6.45;
			double g = .13;

			double V = (EPS * (PE + (2 * g)) * BY) / Y;
			System.out.println(V);
			System.out.println(V / price);
			if (V / price > 1) {
				buy("AAPL");
			}
		} else if (symbol == "MSFT") {
			double EPS = .54;
			double g = .092;

			double V = (EPS * (PE + (2 * g)) * BY) / Y;
			System.out.println(V);
			System.out.println(V / price);
			if (V / price > 1) {
				buy("MSFT");
			}
		} else if (symbol == "CMG") {
			double EPS = 10.29;
			double g = .2296;

			double V = (EPS * (PE + (2 * g)) * BY) / Y;
			System.out.println(V);
			System.out.println(V / price);
			if (V / price > 1) {
				buy("CMG");
			}
		} else if (symbol == "ORCL") {
			double EPS = 1.04;
			double g = .1025;

			double V = (EPS * (PE + (2 * g)) * BY) / Y;
			System.out.println(V);
			System.out.println(V / price);
			if (V / price > 1) {
				buy("ORCL");
			}
		} else if (symbol == "SBUX") {
			double EPS = 2.71;
			double g = .1799;

			double V = (EPS * (PE + (2 * g)) * BY) / Y;
			System.out.println(V);
			System.out.println(V / price);
			if (V / price > 1) {
				buy("SBUX");
			}
		}
	}

	private static void buy(String symbol) {
		System.out.println("YOU BOUGHT IT!");
	}
}