import java.util.Iterator;
import java.util.PriorityQueue;
import java.util.Stack;

/**
 * Created by haim on 24/11/2017.
 */
public class UCS_Searcher implements Searcher {
	private String rout = null;
	private Map map;

	@Override
	public void search(Map m, MapNode dest) {

		this.map = m;
		PriorityQueue<MapNode> queue = new PriorityQueue<>(10, new NodeComperator());
		MapNode start = new MapNode(0, 0);
		start.setNodeStatus("S");
		start.setRoutCost(0);
		start.setParant(null);
		queue.add(start);

		while (queue.size() > 0) {
			MapNode temp = queue.poll();

			if (temp.equals(dest)) {
				this.getRout(temp);
				break;
			} else {
				Stack<MapNode> list = this.map.getNeighbors(temp);
				Iterator<MapNode> iter = list.iterator();
				while (iter.hasNext()) {
					MapNode tempi = iter.next();
					tempi.setNodeStatus(this.map.getNodeStatus(tempi.getX(), tempi.getY()));
					if (temp.getRoutCost() + this.getCost(tempi) < tempi.getRoutCost()) {
						tempi.setParant(temp);
						tempi.setRoutCost(temp.getRoutCost() + this.getCost(tempi));
					}
					queue.add(tempi);
				}
			}

		}
		System.out.print("finish");

	}

	private void getRout(MapNode node) {
		if (this.rout == null) { //todo how to fix ?
			this.rout = "";
			while (node.getParant() != null) {
				String currStrVal = this.map.getNodeStatus(node.getX(), node.getY());
				int x = node.getParant().getX();
				int y = node.getParant().getY();
				String temp = this.map.getNodeStatus(x, y);
				String temp1 = getDirection(node, node.getParant());
				if (!currStrVal.equals("G")) { //todo fix
					temp1 += "-";
				}
				temp1 +=  this.rout;
				this.rout = temp1;
				node = node.getParant();
				if (temp.equals("S")) { //todo fix
					break;
				}
			}
		}
	}

	private String getDirection(MapNode node, MapNode nodeParent) {
		int curX = node.getX();
		int curY = node.getY();
		int parX = nodeParent.getX();
		int parY = nodeParent.getY();
		String direction = "";

		if (curY < parY) {
			direction += "L";
		}
		if (curY > parY) {
			direction += "R";
		}
		if (curX < parX) {
			direction += "U";
		}
		if (curX > parX) {
			direction += "D";
		}
		return direction;
	}

	private int getCost(MapNode node) {
		int x = node.getX();
		int y = node.getY();
		String temp = this.map.getNodeStatus(x, y);
		int val = 0;
		switch (temp) {

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
				int t = 0;
				int f = 0;
				break;
		}
		return val;
	}
}
