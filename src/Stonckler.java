import java.util.HashMap;
import java.util.Map.Entry;

public class Stonckler {

	public static void main(String[] args) {
		HashMap<String, Stock> stocks = new HashMap<String, Stock>();
		stocks.put("LuLuLemon", new Stock("LULU"));
		stocks.put("Tesla", new Stock("TSLA"));
		stocks.put("Southwest", new Stock("LUV"));
		stocks.put("Apple", new Stock("AAPL"));
		stocks.put("Microsoft", new Stock("MSFT"));
		// stocks.put("Baidu", new Stock("BIDU"));
		// stocks.put("BMW", new Stock("BMW"));
		stocks.put("Chipotle", new Stock("CMG"));
		// stocks.put("Alibaba", new Stock("BABA"));
		stocks.put("Oracle", new Stock("ORCL"));
		stocks.put("Starbucks", new Stock("SBUX"));
		for (Entry<String, Stock> e : stocks.entrySet()) {
			e.getValue().updatePrice();
			System.out.println(e.getKey() + " " + e.getValue().getPrice());
			StockValue.buyStock(e.getValue().getSymbol(), e.getValue().getPrice());
		}
	}
}
