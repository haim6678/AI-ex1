import java.io.PrintWriter;

/**
 * Created by haim on 24/11/2017.
 */
public class Main {

	/**
	 * the main function
	 */
	public static void main(String[] args) {


		Searcher searcher = null;
		//read the file
		Reader reader = new Reader();
		String filePath = "input.txt";
		String searchType = reader.readSearchType(filePath);
		Map m = reader.read(filePath);


		//check what kind of search we want
		switch (searchType) {
			case "IDS":
				searcher = new IdsSearcher();
				break;
			case "A*":
				searcher = new UCS_Searcher();
				break;
			default:
				System.out.print("no such search algorithm");
				return;
		}
		//search the map
		MapNode dest = new MapNode(m.getSize() - 1, m.getSize() - 1, 0, 0, 0);
		String res = searcher.search(m, dest);

		//print the result
		PrintWriter writer = null;
		try {

			writer = new PrintWriter("output.txt", "UTF-8");
			if (res == null) {
				writer.println("no path");
				writer.close();
				return;
			}
			writer.println(res);
			writer.close();

		} catch (Exception e) {
			System.out.print("error");
		}
	}
}
