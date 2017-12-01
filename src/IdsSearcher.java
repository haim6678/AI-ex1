import java.util.Iterator;
import java.util.Queue;
import java.util.Stack;


public class IdsSearcher implements Searcher {
	//local variables
	private int cost = 0;
	private boolean found = false;
	private String rout = null;
	private MapNode desination;
	private Map map;
	private Stack<MapNode> pruningStack;

	//start searching

	/**
	 *the main function that calls the dfs function until the
	 * max depth
	 */
	@Override
	public void search(Map m, MapNode dest) {
		this.map = m;
		this.desination = dest;
		int depth = m.getSize();
		//increace the depth every iteration
		for (int i = 0; i <= depth; i++) {
			this.pruningStack = new Stack<>();
			this.pruningStack.push(m.getStart());
			//call the dfs function
			IdsHelper(m, i);
			if (found) {
				break;
			}
		}
		//print the output
		if (this.rout != null) {
			System.out.print(this.rout + " " + Integer.toString(this.cost));
		} else {
			System.out.print("no path");
		}
	}

	/**
	 *search with normal dfs
	 * only limited to the current depth
	 */
	private void IdsHelper(Map m, int level_limit) {

		MapNode curr = null;
		do {
			curr = pruningStack.pop();

			//found the destination
			if (curr.equals(this.desination)) {
				this.createRout(curr);
				found = true;
				return;
			}
			Stack<MapNode> temp = m.getNeighbors(curr, 0);
			while (!temp.empty()){
				MapNode t= temp.pop();
				//if it's not in the open stack then push it
				if (t.getLevel() <= level_limit) {
					if (!this.pruningStack.contains(t)) {
						t.setParant(curr);
						this.pruningStack.push(t);
					}
				}
			}
		} while (!this.pruningStack.empty());

	}


	/**
	 *create the rout string
	 */
	private void createRout(MapNode node) {
		if (this.rout == null) {
			this.rout = "";
			String t ="";
			//uterate until you reach the start and build the rout
			do {
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

	/**
	 *for 2 given node - get the direction you moved
	 * to get from one to the other
	 */
	private String getDirection(MapNode node, MapNode nodeParent) {
		int curX = node.getX();
		int curY = node.getY();
		int parX = nodeParent.getX();
		int parY = nodeParent.getY();
		this.cost += this.getCost(this.map.getNodeStatus(nodeParent.getX(), nodeParent.getY()));
		String direction = "";
		int x = 3;
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

	/**
	 *for a given node get the cost to move to him
	 */
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
				val = 0;
				break;
			case "G":
				val = 0;
				break;
			default:
				int temp = 0;
				break;
		}
		return val;
	}
}
