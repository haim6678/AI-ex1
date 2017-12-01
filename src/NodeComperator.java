import java.util.Comparator;

/**
 * Created by haim on 25/11/2017.
 */
public class NodeComperator implements Comparator<MapNode> {
	@Override
	public int compare(MapNode o1, MapNode o2) {
		int o1value = getValue(o1.getNodeStatus());
		int o2value = getValue(o2.getNodeStatus());

		if (o1value == o2value) {
			int temp = calcEvenValu(o1, o2);
			return temp;
		}
		if (o1value > o2value) {
			return 1;
		}
		return -1;
	}

	private int calcEvenValu(MapNode o1, MapNode o2) {
		if (o1.getCreationTime() > o2.getCreationTime()) {
			return 1;
		} else if (o1.getCreationTime() < o2.getCreationTime()) {
			return -1;
		} else {
			return checkPrior(o1, o2);
		}
	}

	private int checkPrior(MapNode o1, MapNode o2) {
		if (o1.getPriority() > o2.getPriority()) {
			return 1;
		}
		return -1;
	}


	private int getValue(String s) {
		int val = 0;
		switch (s) {
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
				val = 0; //todo check!!
				break;
		}
		return val;
	}
}
