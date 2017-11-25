/**
 * Created by haim on 24/11/2017.
 */
public class MapNode {


	private int x;
	private int y;


	private int routCost = Integer.MAX_VALUE;


	private String nodeStatus;



	private MapNode parant = null;


	public MapNode(int x_loc, int y_loc) {

		this.x = x_loc;
		this.y = y_loc;
	}

	@Override
	public boolean equals(Object o) {

		return (((MapNode) o).getX() == this.x) && (((MapNode) o).getY() == this.y);
	}

	public void setRoutCost(int routCost) {
		this.routCost = routCost;
	}

	public int getRoutCost() {
		return routCost;
	}


	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public MapNode getParant() {
		return parant;
	}

	public void setParant(MapNode parant) {
		this.parant = parant;
	}


	public String getNodeStatus() {
		return nodeStatus;
	}

	public void setNodeStatus(String nodeStatus) {
		this.nodeStatus = nodeStatus;
	}
}
