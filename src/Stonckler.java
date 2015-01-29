import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Map.Entry;

public class Stonckler {

  public static void main(String[] args) throws IOException {
    HashMap<String, Stock> stocks = new HashMap<String, Stock>();
    stocks.put("LuLuLemon", new Stock("LULU"));
    stocks.put("Tesla", new Stock("TSLA"));
    stocks.put("Southwest", new Stock("LUV"));
    stocks.put("Apple", new Stock("AAPL"));
    stocks.put("Microsoft", new Stock("MSFT"));
    stocks.put("Baidu", new Stock("BIDU"));
    // stocks.put("BMW", new Stock("BMW"));
    stocks.put("Chipotle", new Stock("CMG"));
    stocks.put("Alibaba", new Stock("BABA"));
    stocks.put("Oracle", new Stock("ORCL"));
    stocks.put("Starbucks", new Stock("SBUX"));

    FileWriter f = new FileWriter("data.csv", true);

    // f.append("Stock Symbol"); f.append(','); f.append("Price");
    // f.append(','); f.append("Date"); f.append('\n');

    f.append('\n');
    for (Entry<String, Stock> e : stocks.entrySet()) {
      System.out.println(e.getValue());
      // e.getValue().updatePrice();
      // System.out.println("Current price: "+e.getKey() + " " +
      // e.getValue().getPrice());
      //
      // f.write(e.getKey()+" "+e.getValue().getPrice()+"\n");
      // System.out.println("Timestamp-"+e.getKey()+": "+e.getValue().getTime()+"\n");

      f.append(e.getKey());
      f.append(',');
      f.append("" + e.getValue().getPrice());
      f.append(',');
      f.append("" + e.getValue().getTime());
      f.append(',');
      f.append("" + e.getValue().getGrowth());
      f.append(',');
      f.append("" + e.getValue().getEPS());
      f.append('\n');
    }
    f.close();
    readInfo();
  }
  
	public static void readInfo() throws FileNotFoundException{
		
		Scanner in=new Scanner(new File ("data.csv"));
		in.useDelimiter(",");
		HashMap<String, ArrayList> getInfo=new HashMap<String, ArrayList>();
		
		while (in.hasNext())
        {
            System.out.print(in.next() + " | ");
        }
         
        in.close();
		
	}
    
}
