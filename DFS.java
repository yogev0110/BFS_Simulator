package BFS;

import java.util.Collections;
import java.util.List;

public class DFS {
	static boolean flag = false;

	// Recursive DFS
	public static void dfs(Node start, Node end, Graph g) {
		List<Node> neighbours = g.returnNeig(start);
		Collections.shuffle(neighbours);
		if (neighbours.contains(end)) {
			flag = true;
			return;
		}
		for (int i = 0; i < neighbours.size(); i++) {
			if (flag)
				return;
			Node n = neighbours.get(i);
			if (n != null && n.getValue().equals("0")) {
				dfs(n, end, g);
			}
		}
	}
}
