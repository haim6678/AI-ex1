import java.util.*;

/**
 * the map class
 * we are not keeping a classic map of object,
 * we are just saving the parsed file input for better performance
 * we are creating the node on demand.
 */
public class Map {
	private int targetX;
	private int targetY;
	private String[][] map;
	private int size;
	private MapNode start;

	/**
	 *the constructor
	 */
	public Map(int mapSize, MapNode startMap, String[][] arr) {
		this.map = arr;
		this.size = mapSize;
		this.targetX = mapSize;
		this.targetY = mapSize;
		this.start = startMap;

	}

	/**
	 *get the node Neighbors according to the instructions
	 * received in the exercise
	 */
	public Stack<MapNode> getNeighbors(MapNode node, int time) {

		int x = node.getX();
		int y = node.getY();
		//todo incr
		Stack<MapNode> list = new Stack<>();

		//right
		if ((y + 1 < size) && (!map[x][y + 1].equals("W"))) {
			list.add(new MapNode(x, y + 1, time, 1, node.getLevel() + 1));
		}
		//down right
		if ((y + 1 < size) && (x + 1 < size) && (!map[x + 1][y + 1].equals("W"))) {
			if ((!map[x][y + 1].equals("W")) && (!map[x + 1][y].equals("W"))) {
				list.push(new MapNode(x + 1, y + 1, time, 2, node.getLevel() + 1));
			}
		}
		//down
		if ((x + 1 < size) && (!map[x + 1][y].equals("W"))) {
			list.push(new MapNode(x + 1, y, time, 3, node.getLevel() + 1));
		}

		//down left
		if ((y - 1 >= 0) && (x + 1 < size) && (!map[x + 1][y - 1].equals("W"))) {
			if ((!map[x + 1][y].equals("W")) && (!map[x][y - 1].equals("W"))) {
				list.push(new MapNode(x + 1, y - 1, time, 4, node.getLevel() + 1));
			}
		}
		//left
		if ((y - 1 >= 0) && (map[x][y - 1].equals("W"))) {
			list.push(new MapNode(x, y - 1, time, 5, node.getLevel() + 1));
		}
		//left up
		if ((y - 1 >= 0) && (x - 1 >= 0) && (!map[x - 1][y - 1].equals("W"))) {
			if ((!map[x - 1][y].equals("W")) && (!map[x][y - 1].equals("W"))) {
				list.push(new MapNode(x - 1, y - 1, time, 6, node.getLevel() + 1));
			}
		}
		//up
		if ((x - 1 >= 0) && (map[x - 1][y].equals("W"))) {

			list.push(new MapNode(x - 1, y, time, 7, node.getLevel() + 1));
		}
		//up right
		if ((y + 1 < size) && (x - 1 >= 0) && (!map[x - 1][y + 1].equals("W"))) {
			if ((!map[x - 1][y].equals("W")) && (!map[x][y + 1].equals("W"))) {
				list.push(new MapNode(x - 1, y + 1, time, 8, node.getLevel() + 1));
			}
		}

		return list;
	}

	/**
	 *get start node
	 */
	public MapNode getStart() {
		return this.start;
	}

	/**
	 *get node representation
	 */
	public String getNodeStatus(int x, int y) {
		return this.map[x][y];
	}

	/**
	 *get map size given in file input
	 */
	public int getSize() {
		return size;
	}

}
