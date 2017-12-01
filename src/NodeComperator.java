import java.util.Comparator;

/**
 * node comparator for the priority queue
 * implemented according to the instructions in the exercise
 */
public class NodeComperator implements Comparator<MapNode> {
	/**
	 *check who is bigger
	 */
	@Override
	public int compare(MapNode o1, MapNode o2) {
		double o1value = o1.getRoutCost();
		double o2value = o2.getRoutCost();

		if (o1value == o2value) {
			int temp = calcEvenValu(o1, o2);
			return temp;
		}
		if (o1value > o2value) {
			return 1;
		}
		return -1;
	}

	/**
	 *if they have even cost then check creation time
	 */
	private int calcEvenValu(MapNode o1, MapNode o2) {
		if (o1.getCreationTime() > o2.getCreationTime()) {
			return 1;
		} else if (o1.getCreationTime() < o2.getCreationTime()) {
			return -1;
		} else {
			return checkPrior(o1, o2);
		}
	}

	/**
	 *if they have even creation time then check direction
	 */
	private int checkPrior(MapNode o1, MapNode o2) {
		if (o1.getPriority() > o2.getPriority()) {
			return 1;
		}
		return -1;
	}
}
