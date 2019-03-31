package informedSearchingAlgoAI;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import informedSearchingAlgoAI.StateSpace.Node;
import informedSearchingAlgoAI.SearchingAlgorithm;

public class IterativeA_Star implements SearchingAlgorithm {
	Map<String, Float> qListMap = new HashMap<String, Float>();
	Map<String, Integer> qListLimit = new HashMap<String, Integer>();
	StateSpace graph;
	String startString;
	String goalString;
	int limit = 0;
	boolean goalFound = false;
	ArrayList<String> finalPath = new ArrayList<String>();

	public IterativeA_Star() {

	}

	// return the limit goal is found
	// return 0 if goal not found;
	public int return_goalLimit() {
		return limit;
	}

	@Override
	public ArrayList<String> start(StateSpace graph, String startString, String goalString) {
		this.goalString = goalString;
		this.startString = startString;
		this.graph = graph;
		SearchingAlgorithm.arrayList.clear();
		this.graph.makeConsistent();
		startAlgorithm(graph.getNode(startString), 0, 0);
		if (goalFound == true) {
			finalPath.add(0, goalString);
			return finalPath;
		} else
			return null;
	}

	// @overloaded method
	public void startAlgorithm(Node ParentNode, int cost, int NodeDepth) {
		if (NodeDepth > limit) {
			limit++;
		}
		if (ParentNode.name.equals(goalString)) {
			if (NodeDepth > limit) {
				goalFound = false;
				limit = 0;
				return;
			}
			finalPath = SearchingAlgorithm.getFinalPath(ParentNode, graph);
			goalFound = true;
			limit = NodeDepth;
			return;
		}
		ArrayList<Node> nonVisitedNeighbors = SearchingAlgorithm.get_NonVisistedNeighbors(ParentNode, graph);
		qListMap.remove(ParentNode.name);
		for (int i = 0; i < nonVisitedNeighbors.size(); i++) {
			Node tempNode = nonVisitedNeighbors.get(i);
			int tempCost = tempNode.cost;
			tempNode = graph.getNode(tempNode.name);
			if (tempNode.parentName.equals("")) {
				qListMap.put(tempNode.name, tempNode.heuristicValue + tempCost + cost);
				qListLimit.put(tempNode.name, NodeDepth);
				tempNode.parentName = ParentNode.name;
				tempNode.cost = cost + tempCost;
			}
			ParentNode.visited = true;
		}
		String minimumString = SearchingAlgorithm.getMinKey(qListMap, qListMap.keySet());
		NodeDepth = qListLimit.get(minimumString);
		Node tempNode = graph.getNode(minimumString);
		startAlgorithm(tempNode, tempNode.cost, NodeDepth + 1);
	}

	@Override
	public void startAlgorithm(StateSpace.Node ParentNode, int cost) {

	}

}
