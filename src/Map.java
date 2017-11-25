import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Created by haim on 24/11/2017.
 */
public class Map {
	private int targetX;
	private int targetY;
	private String[][] map;
	private int size;
	private MapNode start;

	public Map(int mapSize, MapNode startMap, String[][] arr) {
		this.map = arr;
		this.size = mapSize;
		this.targetX = mapSize;
		this.targetY = mapSize;
		this.start = startMap;

	}

	public Stack<MapNode> getNeighbors(MapNode node) {

		int x = node.getX();
		int y = node.getY();
		Stack<MapNode> list = new Stack<MapNode>();
		//forward
		if ((x + 1 < size) && (!map[x + 1][y].equals("W"))) {
			list.push(new MapNode(x + 1, y));
		}
		if ((y + 1 < size) && (!map[x][y + 1].equals("W"))) {
			list.push(new MapNode(x, y + 1));
		}
		if ((x - 1 >= 0) && (map[x - 1][y].equals("W"))) {
			list.push(new MapNode(x - 1, y));
		}
		if ((y - 1 >= 0) && (map[x][y - 1].equals("W"))) {
			list.push(new MapNode(x, y - 1));
		}

		//diagonal
		if ((y - 1 >= 0) && (x - 1 >= 0) && (!map[x - 1][y - 1].equals("W"))) {
			list.push(new MapNode(x - 1, y - 1));
		}
		if ((y - 1 >= 0) && (x + 1 < size) && (!map[x + 1][y - 1].equals("W"))) {
			list.push(new MapNode(x + 1, y - 1));
		}
		if ((y + 1 < size) && (x - 1 >= 0) && (!map[x - 1][y + 1].equals("W"))) {
			list.push(new MapNode(x - 1, y + 1));
		}
		if ((y + 1 < size) && (x + 1 < size) && (!map[x + 1][y + 1].equals("W"))) {
			list.push(new MapNode(x + 1, y + 1));
		}
		return list;
	}

	public MapNode getStart() {
		return this.start;
	}

	public String getNodeStatus(int x, int y) {
		return this.map[x][y];
	}

	public int getSize() {
		return size;
	}

}
