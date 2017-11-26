import java.util.Iterator;
import java.util.Queue;
import java.util.Stack;


public class IdsSearcher implements Searcher {
	private int cost = 0;
	private boolean found = false;
	private String rout = null;
	private MapNode desination;
	private Map map;
	private Stack<MapNode> pruningStack;
	@Override
	public void search(Map m, MapNode dest) {
		this.map = m;
		this.desination = dest;
		int depth = m.getSize() * m.getSize();

		for (int i = 0; i < 8*25; i++) {
			this.pruningStack = new Stack<>();
			this.pruningStack.push(m.getStart());
			IdsHelper(m, i);
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

	private void IdsHelper(Map m, int level_limit) {

		MapNode curr = pruningStack.pop();
		if (curr.equals(this.desination)) {
			this.createRout(curr);
			found = true;
			return;
		}
		if (level_limit <= 0) {
			return;
		} else {
			Queue<MapNode> temp = m.getNeighbors(curr);
			Iterator<MapNode> iter = temp.iterator();
			while (iter.hasNext()) {
				MapNode t = iter.next();
				t.setParant(curr);
				if (t.equals(this.desination)) {
					this.createRout(curr);
					found = true;
					return;
				}
				if (!pruningStack.contains(t)) {
					pruningStack.push(t);
				}
				IdsHelper(m, level_limit - 1);
			}
		}
	}

	private void createRout(MapNode node) {
		if (this.rout == null) { //todo how to fix ?
			this.rout = "";
			rout = getDirection(this.desination,node);
			while (node.getParant() != null) {
				String currStrVal = this.map.getNodeStatus(node.getX(), node.getY());
				int x = node.getParant().getX();
				int y = node.getParant().getY();
				String temp = this.map.getNodeStatus(x, y);
				String temp1 = getDirection(node, node.getParant());
				if (!currStrVal.equals("G")) { //todo fix
					temp1 += "-";
				}
				int r=4;
				temp1 += this.rout;
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
		this.cost+= this.getCost(this.map.getNodeStatus(nodeParent.getX(),nodeParent.getY()));
		String direction = "";
		int x=3;
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
