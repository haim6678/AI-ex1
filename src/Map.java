import java.util.*;

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

	public Queue<MapNode> getNeighbors(MapNode node) {

		int x = node.getX();
		int y = node.getY();
		int time = node.getCreationTime() + 1;
		Queue<MapNode> list = new LinkedList<>();

		//right
		if ((y + 1 < size) && (!map[x][y + 1].equals("W"))) {
			list.add(new MapNode(x, y + 1, time));
		}
		//down right
		if ((y + 1 < size) && (x + 1 < size) && (!map[x + 1][y + 1].equals("W"))) {
			if ((!map[x][y + 1].equals("W")) && (!map[x + 1][y].equals("W"))){
				list.add(new MapNode(x + 1, y + 1, time));
			}
		}
		//down
		if ((x + 1 < size) && (!map[x + 1][y].equals("W"))) {
			list.add(new MapNode(x + 1, y, time));
		}
		//down left
		if ((y - 1 >= 0) && (x + 1 < size) && (!map[x + 1][y - 1].equals("W"))) {
			if ((!map[x + 1][y].equals("W")) && (!map[x][y - 1].equals("W"))) {
				list.add(new MapNode(x + 1, y - 1, time));
			}
		}
		//left
		if ((y - 1 >= 0) && (map[x][y - 1].equals("W"))) {
			list.add(new MapNode(x, y - 1, time));
		}
		//left up
		if ((y - 1 >= 0) && (x - 1 >= 0) && (!map[x - 1][y - 1].equals("W"))) {
			if ((!map[x - 1][y].equals("W")) && (!map[x][y - 1].equals("W"))) {
				list.add(new MapNode(x - 1, y - 1, time));
			}
		}
		//up
		if ((x - 1 >= 0) && (map[x - 1][y].equals("W"))) {

			list.add(new MapNode(x - 1, y, time));
		}
		//up right
		if ((y + 1 < size) && (x - 1 >= 0) && (!map[x - 1][y + 1].equals("W"))) {
			if ((!map[x - 1][y].equals("W")) && (!map[x][y + 1].equals("W"))) {
				list.add(new MapNode(x - 1, y + 1, time));
			}
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
