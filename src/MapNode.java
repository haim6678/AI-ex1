/**
 * Created by haim on 24/11/2017.
 */
public class MapNode {


	private int x;
	private int y;

	private int creationTime;

	private double routCost = Integer.MAX_VALUE;


	private String nodeStatus;



	private MapNode parant = null;


	public MapNode(int x_loc, int y_loc,int create) {

		this.x = x_loc;
		this.y = y_loc;
	}

	@Override
	public boolean equals(Object o) {

		return (((MapNode) o).getX() == this.x) && (((MapNode) o).getY() == this.y);
	}

	public void setRoutCost(double routCost) {
		this.routCost = routCost;
	}

	public double getRoutCost() {
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

	public void setCreationTime(int creationTime) {
		this.creationTime = creationTime+1;
	}
	public int getCreationTime() {
		return creationTime;
	}

	public void setNodeStatus(String nodeStatus) {
		this.nodeStatus = nodeStatus;
	}
}
