package BFS;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class BFS_Alg {

	public static void BFS(Graph graph) {

		Node s = Graph.getStartNode();
		Queue<Node> queue = new LinkedList<Node>();
		queue.add(s);
		ArrayList<Node> round;
		Integer counter = 1;
		s.setValue(counter.toString());
		while (!queue.isEmpty()) {
			round = new ArrayList<Node>();
			while (!queue.isEmpty())
				round.add((Node) queue.remove());
			for (Node node : round) {
				for (Node node2 : graph.returnNeig(node)) {
					if (node2.getValue().equals("0")) {
						queue.add(node2);
						node2.setValue(counter.toString());
						node2.setPredececor(node);
					}
				}
			}
			counter++;
		}
	}
}
