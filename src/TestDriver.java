
public class TestDriver {

	public static void main(String[] args) {
		
		System.out.println("EPS:"+YahooAPI.getEPS("MSFT"));
		System.out.println("Growth:"+YahooAPI.getGrowth("MSFT"));
		System.out.println("Price:" + YahooAPI.getPrice("MSFT"));
		
		
	
	}

}


