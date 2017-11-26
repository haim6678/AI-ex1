import java.util.PriorityQueue;

/**
 * Created by haim on 24/11/2017.
 */
public class Main {

	public static void main(String[] args) {



		Searcher searcher = null;
		Reader reader = new Reader();
		String filePath = args[0];
		String searchType = reader.readSearchType(filePath);
		Map m = reader.read(filePath);


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
		MapNode dest = new MapNode(m.getSize()-1, m.getSize()-1,0);
		searcher.search(m,dest);
		return;

	}
}
