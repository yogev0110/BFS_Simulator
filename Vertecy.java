package BFS;

public class Vertecy {

	private Node node1, node2;

	public Vertecy(Node node1, Node node2) {
		this.node1 = node1;
		this.node2 = node2;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * node2.hashCode() + prime * node1.hashCode();
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
		Vertecy other = (Vertecy) obj;
		if (other.getNode1().equals(node1))
			if (other.getNode2().equals(node2))
				return true;
		if (other.getNode1().equals(node2))
			if (other.getNode2().equals(node1))
				return true;
		if (other.getNode2().equals(node1))
			if (other.getNode1().equals(node2))
				return true;
		if (other.getNode2().equals(node2))
			if (other.getNode1().equals(node1))
				return true;
		return false;

	}

	public Node getNode1() {
		return node1;
	}

	public Node getNode2() {
		return node2;
	}

	public Node connectedTo(Node node) {
		if (node.equals(node1))
			return node2;
		if (node.equals(node2))
			return node1;
		return null;
	}

}
