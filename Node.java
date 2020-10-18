package BFS;

public class Node {

	private int x, y;
	private double size;
	private String value = "0";
	private Node predececor;
	private int pathFlag = 0;
	private boolean visited = false;
	private NodeType type = NodeType.SIMPLE;

	public Node(int x, int y, double size) {

		this.x = x;
		this.y = y;
		this.size = size;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public double getSize() {
		return size;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + x;
		result = prime * result + y;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Node other = (Node) obj;
		if (x != other.x)
			return false;
		if (y != other.y)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "(" + x + "," + y + ")";
	}

	public Node getPredececor() {
		return predececor;
	}

	public void setPredececor(Node predececor) {
		this.predececor = predececor;
	}

	public int getPathFlag() {
		return pathFlag;
	}

	public void setPathFlag(int pathFlag) {
		this.pathFlag = pathFlag;
	}

	public NodeType getType() {
		return type;
	}

	public void setType(NodeType type) {
		this.type = type;
	}

	public boolean isVisited() {
		return visited;
	}

	public void setVisited(boolean visited) {
		this.visited = visited;
	}

}
