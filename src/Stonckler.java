import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map.Entry;

public class Stonckler {

	public static void main(String[] args) throws IOException {
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

		FileWriter f = new FileWriter("data.txt", true);

		for (Entry<String, Stock> e : stocks.entrySet()) {
			Stock value = e.getValue();
			value.updatePrice();
			System.out.println("Current price: " + e.getKey() + " " + value.getPrice());

			f.write(e.getKey() + " " + value.getPrice() + "\n");
			f.write("Bought? " + StockValue.buyStock(value.getSymbol(), value.getPrice()) + "\n");
			System.out.println("Timestamp-" + e.getKey() + ": " + value.getTime() + "\n");
			f.write("Timestamp-" + e.getKey() + ": " + value.getTime() + "\n");
			f.write("\n");
		}
		f.write("\n");
		f.close();
	}
}
