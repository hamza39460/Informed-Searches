package informedSearchingAlgoAI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import informedSearchingAlgoAI.StateSpace.Node;

public class A_StarSearch implements SearchingAlgorithm {
	Map<String, Float> qListMap=new HashMap<String, Float>();
	StateSpace graph;
	String startString;
	String goalString;
	ArrayList<String> finalPath=new ArrayList<String>();
	public A_StarSearch()  {

	}
	@Override
	public ArrayList<String> start(StateSpace graph, String startString, String goalString){
		this.goalString=goalString;
		this.startString=startString;
		this.graph=graph;
		SearchingAlgorithm.arrayList.clear();
		this.graph.makeConsistent();
		startAlgorithm(graph.getNode(startString), 0);
		finalPath.add(0,goalString);
		return finalPath;
	}
	@Override
	public void startAlgorithm(StateSpace.Node ParentNode,int cost) {
		
		if(ParentNode.name.equals(goalString)) {
			finalPath=SearchingAlgorithm.getFinalPath(ParentNode, graph);
			return;
		}
		ArrayList<Node> nonVisitedNeighbors=SearchingAlgorithm.get_NonVisistedNeighbors(ParentNode,graph);
		qListMap.remove(ParentNode.name);
		for (int i = 0; i < nonVisitedNeighbors.size(); i++) {
			Node tempNode=nonVisitedNeighbors.get(i);
			int tempCost=tempNode.cost;
			tempNode=graph.getNode(tempNode.name);
			if(tempNode.parentName.equals("")){
				qListMap.put(tempNode.name, tempNode.heuristicValue+tempCost+cost);
				tempNode.parentName=ParentNode.name;
				tempNode.cost=cost+tempCost;
			}
			ParentNode.visited=true;
		}
		String minimumString=SearchingAlgorithm.getMinKey(qListMap, qListMap.keySet());
		Node tempNode=graph.getNode(minimumString);
		startAlgorithm(tempNode, tempNode.cost);
	}
	
	
}
