import java.util.Iterator;
import java.util.Stack;


public class IdsSearcher implements Searcher {
	private int cost = 0;
	private boolean found = false;
	private String rout = null;
	private MapNode desination;
	private Map map;

	@Override
	public void search(Map m, MapNode dest) {
		this.map = m;
		this.desination = dest;
		int depth = m.getSize() * m.getSize();
		for (int i = 0; i < depth; i++) {
			IdsHelper(m, m.getStart(), i);
			if (found) {
				break;
			}
		}
		if (this.rout != null) {
			System.out.print(this.rout + " " + Integer.toString(this.cost));
		} else {
			System.out.print("no solution");
		}
	}

	private void IdsHelper(Map m, MapNode curr, int level_limit) {

		if (curr.equals(this.desination)) {
			this.createRout(curr);
			found = true;
			return;
		}
		if (level_limit <= 0) {
			return;
		} else {
			Stack<MapNode> temp = m.getNeighbors(curr);
			Iterator<MapNode> iter = temp.iterator();
			while (iter.hasNext()) {
				iter.next().setParant(curr);
			}
			iter = temp.iterator();
			while (iter.hasNext()) {
				IdsHelper(m, iter.next(), level_limit - 1);
			}
		}
	}

	private void createRout(MapNode m) {
		if (this.rout == null) { //todo how to fix ??
			this.rout = "G";
			while (m.getParant() != null) {
				int x = m.getParant().getX();
				int y = m.getParant().getY();
				String temp = this.map.getNodeStatus(x, y);
				this.cost += this.getCost(temp);
				this.rout += temp;
				m = m.getParant();
			}
		}
	}

	private int getCost(String node) {
		int val = 0;

		switch (node) {
			case "R":
				val = 1;
				break;
			case "D":
				val = 3;
				break;
			case "H":
				val = 10;
				break;
			case "S":
				val = 0; //todo check!!
				break;
			case "G":
				val = 0; //todo check!!
				break;
			default:
				int temp = 0;
				break;
		}
		return val;
	}
}
