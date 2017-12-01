import java.util.Iterator;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Stack;

/**
 * Created by haim on 24/11/2017.
 */
public class UCS_Searcher implements Searcher {
	private String rout = null;
	private Map map;
	private int cost = 0;
	private MapNode destination;
	private int time;

	@Override
	public void search(Map m, MapNode dest) {
		this.destination = dest;
		this.map = m;
		this.time = -1;
		PriorityQueue<MapNode> queue = new PriorityQueue<>(10, new NodeComperator());
		MapNode start = new MapNode(0, 0, 0, 0, 0);
		//set priority fields
		start.setNodeStatus("S");
		start.setRoutCost(0);
		start.setParant(null);
		queue.add(start);
		//start searching
		while (queue.size() > 0) {
			MapNode curr = queue.poll();

			if (curr.equals(dest)) {
				this.getRout(curr);
				break;
			} else {
				this.time++;
				Stack<MapNode> list = this.map.getNeighbors(curr, time);
				for (MapNode tempi : list) {
					tempi.setNodeStatus(this.map.getNodeStatus(tempi.getX(), tempi.getY()));
					if (!queue.contains(tempi)) {
						if (curr.getRoutCost() + this.calcHeuristicCost(tempi) + this.getCost(tempi) < tempi.getRoutCost()) {
							tempi.setParant(curr);
							tempi.setRoutCost(curr.getRoutCost() + this.calcHeuristicCost(tempi) + this.getCost(tempi));
						}
						queue.add(tempi);
					}
				}
			}
		}
		if (this.rout != null) {
			System.out.print(this.rout + " " + Integer.toString(this.cost));
		} else {
			System.out.print("no path");
		}
	}

	private double calcHeuristicCost(MapNode node) {
		int xDist = node.getX() - this.destination.getX();
		int yDist = node.getY() - this.destination.getY();
		double squre = Math.pow(xDist, 2) + Math.pow(yDist, 2);
		return Math.sqrt(squre);
	}

	private void getRout(MapNode node) {
		if (this.rout == null) {
			this.rout = "";
			String t = "";
			do {
				int a = 3;
				t = getDirection(node, node.getParant());
				t += this.rout;
				this.rout = t;
				String temp1 = "";
				String currStrVal = this.map.getNodeStatus(node.getParant().getX(), node.getParant().getY());
				if (!currStrVal.equals("S")) {
					temp1 += "-";
				}
				temp1 += this.rout;
				this.rout = temp1;

				int x = node.getParant().getX();
				int y = node.getParant().getY();
				String temp = this.map.getNodeStatus(x, y);

				node = node.getParant();
				if (temp.equals("S")) {
					break;
				}
			} while (node.getParant() != null);
		}
	}

	private String getDirection(MapNode node, MapNode nodeParent) {
		int curX = node.getX();
		int curY = node.getY();
		int parX = nodeParent.getX();
		int parY = nodeParent.getY();
		this.cost += this.getCost(nodeParent);
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
