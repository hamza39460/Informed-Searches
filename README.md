# Informed-Searches
Informed Searches uses domain-Specific Knowledge to search the optimal path to the Goal.
# Algorithms implemented are
1) Best First Search (Uses the Heuristic Distance to find optimal path)
2) A* Search. (Uses the Heuristic Distance+Cost Path to find optimal path)
3) Iterative A* Search. 
>These algorithm are solving the problem stated in **Problem Statement.pdf**

# To run the .jar file  (Executable JAR file)
1) Download the algo.jar file in exe folder and grid.txt file
2) Keep the .JAR file and grid.txt file in same folder
3) Change the directory to the folder where both files (.JAR and grid.txt) are placed.
4) Enter this command
	      java -jar algo.jar
5) Program will execute
# Screenshots of the output
1) Best First Search

![alt-text](https://github.com/hamza39460/Informed-Searches/blob/master/screenshots/BestFirstSearch.PNG)
2) A* Search

![alt-text](https://github.com/hamza39460/Informed-Searches/blob/master/screenshots/AStarSearch..PNG)
3) Iterative A* Search

![alt-text](https://github.com/hamza39460/Informed-Searches/blob/master/screenshots/Iterative_AStartSearch.PNG)
# Code Reading Guide
1) All algortihms (.java classes) are inherited from **SearchingAlgorithm.java**
2) These searches are for graph based State Space.
3) To add other Search you have to inherit it from **SearchingAlgorithm.java**
