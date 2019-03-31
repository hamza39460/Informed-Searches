package informedSearchingAlgoAI;

import java.util.ArrayList;
import java.util.Map;
import java.util.Set;

import informedSearchingAlgoAI.StateSpace.Node;

public interface SearchingAlgorithm {
	// @param graph on which algorithm be applied.
	// @param starting node name
	// @param goal node name
	// this function should be called to start the algorithm
	public ArrayList<String>  start(StateSpace graph, String startString, String goalString);

	// @param parent node reference
	// @param cost
	// this function is called be @function start to compute path
	public void startAlgorithm(StateSpace.Node ParentNode, int cost);

	public static int getFinalCost(StateSpace graph,String goalString) {
		return graph.getNode(goalString).cost;
	}
	// @param node reference
	// @return arraylist of non visited and not obstacles neighbors list
	static public ArrayList<Node> get_NonVisistedNeighbors(Node node, StateSpace graph) {
		ArrayList<Node> arrayList = new ArrayList<Node>();
		Node temp = node.nextNeighbor;
		while (temp != null) {
			Node temp2 = graph.getNode(temp.name);
			if (graph.isVisited(temp2) == false && graph.isObstacle(temp2) == false)
				arrayList.add(temp);
			temp = temp.nextNeighbor;
		}
		return arrayList;
	}
	//@param map
	//@param Key Strings
	//@return key with minimum value
	static public String getMinKey(Map<String,Float> map,Set<String>keys) {
	    String minKey = null;
	    float minValue = (float) Integer.MAX_VALUE;
	    for(String key : keys) {
	        float value = map.get(key);
	        if(value < minValue) {
	            minValue = value;
	            minKey = key;
	        }
	    }
	    return minKey;
	}
	ArrayList<String> arrayList=new ArrayList<String>();
	//@param GoalNode reference
	//return arrayList of final path
	static public ArrayList<String> getFinalPath(Node node,StateSpace graph){
		if(!node.parentName.equals(""))
		{
			arrayList.add(node.parentName);
			getFinalPath(graph.getNode(node.parentName), graph);
		}
		return arrayList;
	}
}
