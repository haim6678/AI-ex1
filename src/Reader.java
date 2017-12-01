import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Scanner;

public class Reader {


	public String readSearchType(String path) {

		String line = null;
		try (Scanner in = new Scanner(new FileReader(path))) {
			StringBuilder sb = new StringBuilder();
			line = in.nextLine();
		} catch (Exception e) {
			System.out.print("error reading input file");
			return null;
		}
		return line;

	}

	public Map read(String path) {


		Map map = null;

		try (BufferedReader br = new BufferedReader(new FileReader(path))) {
			StringBuilder sb = new StringBuilder();
			String line = br.readLine();
			line = br.readLine();
			int mapsize = Integer.parseInt(line);
			String[][] mapStringArr = new String[mapsize][mapsize];
			line = br.readLine();
			int mapStringArrLine = 0;
			while (line != null) {
				for (int i = 0; i < line.length(); i++) {
					mapStringArr[mapStringArrLine][i] = Character.toString(line.charAt(i));
				}
				mapStringArrLine++;
				line = br.readLine();
			}

			map = new Map(mapsize, new MapNode(0, 0,0,0,0), mapStringArr);

		} catch (Exception e) {
			//todo handle error
		}
		return map;
	}
}
