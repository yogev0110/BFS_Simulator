package BFS;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.concurrent.ThreadLocalRandom;

public class Graph {

	private static Node StartNode;
	private static Node EndNode;

	public int getLenght() {
		return lenght;
	}

	public void setLenght(int lenght) {
		this.lenght = lenght;
	}

	public ArrayList<ArrayList<Node>> getGraph() {
		return graph;
	}

	public HashSet<Vertecy> getVerticies() {
		return verticies;
	}

	public int getHeight() {
		return height;
	}

	private static ArrayList<ArrayList<Node>> graph;
	private HashSet<Vertecy> verticies = new HashSet<>();
	private HashSet<Vertecy> deletedVerticies = new HashSet<>();

	private int lenght, height;

	public Graph(int lenght, int height) {

		this.lenght = lenght;
		this.height = height;
		graph = new ArrayList<>(lenght);
		for (int i = 0; i < lenght; i++) {
			graph.add(i, new ArrayList<>(height));
			for (int j = 0; j < height; j++) {
				graph.get(i).add(new Node(i, j, 0));
			}
		}

		for (int i = 0; i < lenght; i++) {
			for (int j = 0; j < height; j++) {
				if (j != height - 1)
					verticies.add(new Vertecy(graph.get(i).get(j), graph.get(i).get(j + 1)));
				if (j != 0)
					verticies.add(new Vertecy(graph.get(i).get(j), graph.get(i).get(j - 1)));
				if (i != lenght - 1)
					verticies.add(new Vertecy(graph.get(i).get(j), graph.get(i + 1).get(j)));
				if (i != 0)
					verticies.add(new Vertecy(graph.get(i).get(j), graph.get(i - 1).get(j)));
			}
		}

		verticies.add(new Vertecy(graph.get(0).get(0), graph.get(0).get(1)));
		verticies.add(new Vertecy(graph.get(0).get(0), graph.get(1).get(0)));
		verticies.add(new Vertecy(graph.get(lenght - 1).get(0), graph.get(lenght - 1).get(1)));
		verticies.add(new Vertecy(graph.get(lenght - 1).get(0), graph.get(lenght - 2).get(0)));
		verticies.add(new Vertecy(graph.get(0).get(height - 1), graph.get(1).get(height - 1)));
		verticies.add(new Vertecy(graph.get(0).get(height - 1), graph.get(0).get(height - 2)));
		verticies.add(new Vertecy(graph.get(lenght - 1).get(height - 1), graph.get(lenght - 2).get(height - 1)));
		verticies.add(new Vertecy(graph.get(lenght - 1).get(height - 1), graph.get(lenght - 1).get(height - 2)));

		for (int i = 0; i < height - 1; i++) {
			verticies.add(new Vertecy(graph.get(0).get(i), graph.get(0).get(i + 1)));
			verticies.add(new Vertecy(graph.get(lenght - 1).get(i), graph.get(lenght - 1).get(i + 1)));
		}

		for (int i = 0; i < lenght - 1; i++) {
			verticies.add(new Vertecy(graph.get(i).get(0), graph.get(i + 1).get(0)));
			verticies.add(new Vertecy(graph.get(i).get(height - 1), graph.get(i + 1).get(height - 1)));
		}
	}

	public Graph(int lenght, int height, int xStart, int yStart, int xEnd, int yEnd) {

		this.lenght = lenght;
		this.height = height;
		graph = new ArrayList<>(lenght);
		for (int i = 0; i < lenght; i++) {
			graph.add(i, new ArrayList<>(height));
			for (int j = 0; j < height; j++) {
				graph.get(i).add(new Node(i, j, 0));
			}
		}

		for (int i = 0; i < lenght; i++) {
			for (int j = 0; j < height; j++) {
				if (j != height - 1)
					verticies.add(new Vertecy(graph.get(i).get(j), graph.get(i).get(j + 1)));
				if (j != 0)
					verticies.add(new Vertecy(graph.get(i).get(j), graph.get(i).get(j - 1)));
				if (i != lenght - 1)
					verticies.add(new Vertecy(graph.get(i).get(j), graph.get(i + 1).get(j)));
				if (i != 0)
					verticies.add(new Vertecy(graph.get(i).get(j), graph.get(i - 1).get(j)));
			}
		}

		verticies.add(new Vertecy(graph.get(0).get(0), graph.get(0).get(1)));
		verticies.add(new Vertecy(graph.get(0).get(0), graph.get(1).get(0)));
		verticies.add(new Vertecy(graph.get(lenght - 1).get(0), graph.get(lenght - 1).get(1)));
		verticies.add(new Vertecy(graph.get(lenght - 1).get(0), graph.get(lenght - 2).get(0)));
		verticies.add(new Vertecy(graph.get(0).get(height - 1), graph.get(1).get(height - 1)));
		verticies.add(new Vertecy(graph.get(0).get(height - 1), graph.get(0).get(height - 2)));
		verticies.add(new Vertecy(graph.get(lenght - 1).get(height - 1), graph.get(lenght - 2).get(height - 1)));
		verticies.add(new Vertecy(graph.get(lenght - 1).get(height - 1), graph.get(lenght - 1).get(height - 2)));

		for (int i = 0; i < height - 1; i++) {
			verticies.add(new Vertecy(graph.get(0).get(i), graph.get(0).get(i + 1)));
			verticies.add(new Vertecy(graph.get(lenght - 1).get(i), graph.get(lenght - 1).get(i + 1)));
		}

		for (int i = 0; i < lenght - 1; i++) {
			verticies.add(new Vertecy(graph.get(i).get(0), graph.get(i + 1).get(0)));
			verticies.add(new Vertecy(graph.get(i).get(height - 1), graph.get(i + 1).get(height - 1)));
		}

		EndNode = graph.get(xEnd).get(yEnd);
		graph.get(xEnd).get(yEnd).setPathFlag(1);
		graph.get(xEnd).get(yEnd).setType(NodeType.END);
		setStartNode(graph.get(xStart).get(yStart));
		graph.get(xStart).get(yStart).setPathFlag(1);
		graph.get(xStart).get(yStart).setType(NodeType.START);
	}

	public static Node getEndNode() {
		return EndNode;
	}

	public static void setEndNode(Node endNode) {
		endNode.setPathFlag(1);
		endNode.setType(NodeType.END);
		EndNode = endNode;
	}

	public ArrayList<Node> returnNeig(Node node) {
		ArrayList<Node> ret = new ArrayList<>();
		for (Vertecy vert : verticies) {
			if (vert.connectedTo(node) != null)
				ret.add(vert.connectedTo(node));
		}
		return ret;
	}

	@Override
	public String toString() {
		String retString = "";
		for (ArrayList<Node> arrayList : graph) {
			for (Node node : arrayList) {
				retString += "O";
				if (node.getY() < height - 1)
					if (verticies.contains(new Vertecy(node, graph.get(node.getX()).get(node.getY() + 1))))
						retString += "-";
					else
						retString += " ";

			}
			retString += "\n";
			for (Node node : arrayList) {
				if (node.getX() != lenght - 1)
					if (verticies.contains(new Vertecy(node, graph.get(node.getX() + 1).get(node.getY()))))
						retString += "|";
					else
						retString += " ";

				retString += " ";

			}
			retString += "\n";
		}
		retString += "\n";
		String answer;
		answer = returnNeig(graph.get(1).get(0)).toString();
		return retString + answer;
	}

	public boolean outputBFS() {

		return recurtion(EndNode);
	}

	public boolean recurtion(Node node) {
		if (node == null)
			return false;
		if (node.getType() == NodeType.START) {
			node.setValue("X");
			String bstyle = "-fx-text-fill: #FF0000;-fx-fill: #FFFF00;-fx-background-color: #FF0000;";
			GraphFX.b[node.getX()][node.getY()].setStyle(bstyle);
			return true;
		}
		node = graph.get(node.getX()).get(node.getY());
		if (recurtion(node.getPredececor())) {
			node.setValue("X");
			String bstyle = "-fx-text-fill: #FF0000;-fx-fill: #FFFF00;-fx-background-color: #FF0000;";
			GraphFX.b[node.getX()][node.getY()].setStyle(bstyle);
			return true;
		}
		return false;
	}

	public String showPath() {

		String retString = "";
		for (ArrayList<Node> arrayList : graph) {
			for (Node node : arrayList) {
				retString += node.getValue();
				if (node.getY() < height - 1)
					if (verticies.contains(new Vertecy(node, graph.get(node.getX()).get(node.getY() + 1))))
						retString += "-";
					else
						retString += " ";

			}
			retString += "\n";
			for (Node node : arrayList) {
				if (node.getX() != lenght - 1)
					if (verticies.contains(new Vertecy(node, graph.get(node.getX() + 1).get(node.getY()))))
						retString += "|";
					else
						retString += " ";

				retString += " ";

			}
			retString += "\n";
		}
		retString += "\n";
		return retString;
	}

	public void removeNode(int x, int y) {
		ArrayList<Vertecy> removelArrayList = new ArrayList<Vertecy>();
		for (Vertecy ver : verticies) {
			if (ver.connectedTo(new Node(x, y, 1)) != null)
				removelArrayList.add(ver);
		}
		deletedVerticies.addAll(removelArrayList);
		verticies.removeAll(removelArrayList);
	}

	public void addNode(int x, int y) {
		ArrayList<Vertecy> removelArrayList = new ArrayList<Vertecy>();
		for (Vertecy vertecyConnected : deletedVerticies) {
			for (Vertecy vertecy : verticies) {
				if (vertecyConnected.connectedTo(new Node(x, y, 1)) != null
						&& vertecy.connectedTo(vertecyConnected.connectedTo(new Node(x, y, 1))) != null)
					removelArrayList.add(vertecyConnected);
			}
		}
		verticies.addAll(removelArrayList);
		deletedVerticies.removeAll(removelArrayList);
	}

	public void ranDeadNodes() {
		for (int i = 0; i < lenght * height / 5; i++) {
			int randomHeight = ThreadLocalRandom.current().nextInt(0, height);
			int randomLenght = ThreadLocalRandom.current().nextInt(0, lenght);
			if (graph.get(randomLenght).get(randomHeight).getPathFlag() == 0) {
				removeNode(randomLenght, randomHeight);
				graph.get(randomLenght).get(randomHeight).setPathFlag(1);
				graph.get(randomLenght).get(randomHeight).setValue("O");
			} else
				i++;
		}
	}

	public static Node getStartNode() {
		return StartNode;
	}

	public static void setStartNode(Node startNode) {
		StartNode = startNode;
		StartNode.setPathFlag(1);
		StartNode.setType(NodeType.START);
	}
}
