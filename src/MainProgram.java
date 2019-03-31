package informedSearchingAlgoAI;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;

public class MainProgram {

	static Scanner scanner;
	int gridCol;
	int gridRow;
	int startCol;
	int startRow;
	int goalCol;
	int goalRow;
	String goalString;
	String startString;
	StateSpace graph;
	ArrayList<ArrayList<Integer>> NodesObstacle;
	ArrayList<ArrayList<String>> NodesArrayList;
	ArrayList<ArrayList<Float>> NodesHeuristicValue;
	ArrayList<String> finalPath;
	public static void main(String[] args) throws FileNotFoundException {
		MainProgram mainProgram = new MainProgram();
		mainProgram.startProgram();
	}

	void startProgram() throws FileNotFoundException {
		graph = new StateSpace();
		readData();
		makeStateSpace();
		SearchingAlgorithm searchingAlgorithm=new BestFirstSearch();
		finalPath=searchingAlgorithm.start(graph,startString,goalString);
		System.out.println("Start: "+startString);
		System.out.println("Goal: "+goalString);
		System.out.println("Best First SEARCH");
		System.out.println("Cost: " + SearchingAlgorithm.getFinalCost(graph,goalString) );
		printFinalPath();
		printFinalGrid();
		graph = new StateSpace();
		readData();
		makeStateSpace();
		searchingAlgorithm=new A_StarSearch();
		finalPath=searchingAlgorithm.start(graph,startString,goalString);
		System.out.println("Start: "+startString);
		System.out.println("Goal: "+goalString);
		System.out.println("A* SEARCH");
		System.out.println("Cost: " + SearchingAlgorithm.getFinalCost(graph,goalString) );
		printFinalPath();
		printFinalGrid();
		
		searchingAlgorithm=new IterativeA_Star();
		graph=new StateSpace();
		readData();
		makeStateSpace();
		System.out.println("Iterative A* Search");
		finalPath=searchingAlgorithm.start(graph,startString,goalString);
		System.out.println("Cost: " + SearchingAlgorithm.getFinalCost(graph,goalString) );
		System.out.println("Limit at which goal Found:"+((IterativeA_Star)searchingAlgorithm).return_goalLimit());
		printFinalPath();
		printFinalGrid();
		
		searchingAlgorithm=new RBFS();
		graph=new StateSpace();
		readData();
		makeStateSpace();
		System.out.println("RBFS");
		finalPath=searchingAlgorithm.start(graph,startString,goalString);
		System.out.println("Cost: " + SearchingAlgorithm.getFinalCost(graph,goalString) );
		printFinalPath();
		printFinalGrid();
		
	}

	void readData() throws FileNotFoundException {
		scanner = new Scanner(new File("grid.txt"));
		gridCol = scanner.nextInt();
		gridRow = scanner.nextInt();
		startCol = scanner.nextInt();
		startRow = scanner.nextInt();
		goalCol = scanner.nextInt();
		goalRow = scanner.nextInt();
		NodesArrayList = new ArrayList<ArrayList<String>>();
		NodesObstacle = new ArrayList<ArrayList<Integer>>();
		NodesHeuristicValue = new ArrayList<ArrayList<Float>>();
		goalString=goalRow+","+goalCol;
		startString=startRow+","+startCol;
		int k=gridRow-1;
		for (int i = 0; i < gridRow; i++,k--) {
			NodesArrayList.add(new ArrayList<String>());
			NodesObstacle.add(new ArrayList<Integer>());
			NodesHeuristicValue.add(new ArrayList<Float>());
			for (int j = 0; j < gridCol; j++) {
				int obstacleTemp = scanner.nextInt();
				String nameTempString = Integer.toString(k) + "," + j;
				float x=(float) Math.pow(((i+gridRow-1)-goalRow), 2);
				float y=(float) Math.pow((j-goalCol),2);
				float summ=x+y;
				float tempHeuristic=(float) Math.sqrt(summ);  
				NodesObstacle.get(i).add(obstacleTemp);
				NodesArrayList.get(i).add(nameTempString);
				NodesHeuristicValue.get(i).add(tempHeuristic);
				graph.addNode(graph.newNode(nameTempString, obstacleTemp,tempHeuristic));
			}
		}
		scanner.close();
	}

	void makeStateSpace() {
		int i=gridRow-1;
		for (int k = 0; k < gridRow; k++,i--) {
			for (int j = 0; j < gridCol ; j++) {
				String nameTempS = Integer.toString(i) + "," + j;
				StateSpace.Node ParentNode=graph.getNode(nameTempS);
				// for right
				if (j + 1 < gridCol) {
					String nameTempString = Integer.toString(i) + "," + (j + 1);
					int obstacleTemp = NodesObstacle.get(k).get(j + 1);
					float heuristicTemp=NodesHeuristicValue.get(k).get(j+1);
					graph.addNeighborNode(ParentNode, graph.newNode(nameTempString, obstacleTemp,heuristicTemp),neighborName.rightNeighbor);
				}
				// for up
				if (k+1 <gridRow &&i+1<gridRow) {
					String nameTempString = Integer.toString(i+1) + "," + (j);
					int obstacleTemp = NodesObstacle.get(k+1).get(j);
					float heuristicTemp=NodesHeuristicValue.get(k+1).get(j);
					graph.addNeighborNode(ParentNode, graph.newNode(nameTempString, obstacleTemp,heuristicTemp),neighborName.upNeighbor);
				}
				// for diagonal
				if (k+1 <gridRow &&i+1<gridRow &&j+1<gridCol) {
					String nameTempString = Integer.toString(i+1) + "," + (j+1);
					int obstacleTemp = NodesObstacle.get(k+1).get(j+1);
					float heuristicTemp=NodesHeuristicValue.get(k+1).get(j+1);
					graph.addNeighborNode(ParentNode, graph.newNode(nameTempString, obstacleTemp,heuristicTemp),neighborName.diagonalNeighbor);
				}
			}
		}
	}

	void printFinalPath() {
		for (int i = finalPath.size()-1; i>=0; i--) {
			System.out.print(finalPath.get(i)+"->");
		}
		System.out.println();
	}
	
	void printFinalGrid() {
		int k=gridRow-1;
		for (int i = 0; i < gridRow; i++,k--) {
		for (int j = 0; j < gridCol; j++) {
			String tempString=Integer.toString(k)+","+j;
			if(!finalPath.contains(tempString))
				System.out.print(NodesObstacle.get(i).get(j)+" ");
			else
				System.out.print("* ");
		}
		System.out.println();
	}
	}
}
