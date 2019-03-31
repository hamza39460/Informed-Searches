package informedSearchingAlgoAI;

enum neighborName {
	upNeighbor, diagonalNeighbor, rightNeighbor

};

public class StateSpace {
	// class to represent nodes...
	public class Node {
		String name;
		Node next = null;
		int cost;
		float heuristicValue;
		int obstacle;
		boolean visited = false;
		Node nextNeighbor;
		String parentName = "";

		Node() {
		}

		Node(String name, int obstacle, float heuristicValue) {
			this.name = name;
			this.obstacle = obstacle;
			this.heuristicValue = heuristicValue;
		}
	}

	Node headNode = null;

	public StateSpace() {

	}

	// function to create new node
	// @param name and obstacle value
	public Node newNode(String name, int obstacle, float heuristicValue) {
		Node newNode = new Node(name, obstacle, heuristicValue);
		return newNode;
	}

	// add nodes to the state space
	// @param Node reference
	public void addNode(Node newNode) {
		if (headNode == null) {
			headNode = newNode;

		} else {
			Node temp = headNode;
			while (temp.next != null) {
				temp = temp.next;
			}
			temp.next = newNode;
			temp = null;
		}
	}

	// @return Node reference
	// @param String name of node
	public Node getNode(String name) {
		Node tempNode = headNode;
		while (tempNode != null) {
			if (tempNode.name.equals(name))
				return tempNode;
			tempNode = tempNode.next;
		}
		return null;
	}

	// @param parentNode to which successor is added
	// @param Node reference which is added to neighborhood
	// @param neighbor name tells to which neighbor it is added
	public void addNeighborNode(Node parentNode, Node neighborNode, neighborName neighbor) {
		if (neighbor.equals(neighborName.upNeighbor))
			neighborNode.cost = 1;
		if (neighbor.equals(neighborName.diagonalNeighbor))
			neighborNode.cost = 2;
		if (neighbor.equals(neighborName.rightNeighbor))
			neighborNode.cost = 3;
		if (parentNode != null) {
			if (parentNode.nextNeighbor == null) {
				parentNode.nextNeighbor = neighborNode;
			} else {
				Node temp = parentNode.nextNeighbor;
				while (temp.nextNeighbor != null) {
					temp = temp.nextNeighbor;
				}
				temp.nextNeighbor = neighborNode;
			}
		}
	}

	public boolean isVisited(Node node) {
		return node.visited;
	}

	public boolean isObstacle(Node node) {
		if (node.obstacle == 0)
			return false;
		return true;
	}

	public void makeConsistent() {
		Node tempNode = headNode;
		while (tempNode != null) {
			Node tempNeighbor = tempNode.nextNeighbor;
			while (tempNeighbor != null) {
				if (Math.abs((tempNode.heuristicValue - tempNeighbor.heuristicValue)) > tempNeighbor.cost) {
					float h = Math.abs(tempNode.heuristicValue - tempNeighbor.cost);
					tempNeighbor.heuristicValue = h;
					getNode(tempNeighbor.name).heuristicValue = h;
				}
				tempNeighbor = tempNeighbor.nextNeighbor;
			}
			tempNode = tempNode.next;
		}
	}
	
}
