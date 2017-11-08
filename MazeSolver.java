import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
public class MazeSolver {

	public MazeSolver(){

	}

	public ArrayList<MazePoint> solveMaze(int[][] maze){ 
		ArrayQueue<MazePoint> queue = new ArrayQueue<MazePoint>(); //queue
		MazePoint start = new MazePoint(0,0); //starting point
		ArrayList<MazePoint> visited = new ArrayList<>(); //visited points
		HashMap<MazePoint, MazePoint> path = new HashMap<>(); //connects adjacent nodes
		MazePoint target = new MazePoint(maze.length - 1, maze[maze.length - 1].length - 1); //most "bottom right" point
		visited.add(start);
		queue.add(start);
		while (!queue.isEmpty()){
			MazePoint current = queue.remove();
			if (current.equals(target)){ //target is reached
				break;
			}
			ArrayList<MazePoint> adjacent = getAdjacent(current, maze); //adjacent points 
			for (int i = 0; i < adjacent.size(); i++){
				MazePoint nextNode = adjacent.get(i);
				if (nextNode != null && !nextNode.contained(visited)){
					queue.add(nextNode);
					visited.add(nextNode);  
					path.put(nextNode, current);
				}
			}
		}
		ArrayList<MazePoint> output = buildPath(maze, visited, path); //return solution path
		if (output.get(output.size() - 1).equals(target)){
			return output;
		} else {
			return null; //returns null if the target is not reached
		}
	}

	private ArrayList<MazePoint> buildPath(int[][] maze, ArrayList<MazePoint> visited, HashMap<MazePoint, MazePoint> path){
		ArrayList<MazePoint> output = new ArrayList<>();
		output.add(visited.get(visited.size() - 1)); //last visited point = target
		MazePoint nextNode = output.get(0);
		while (!output.get(0).equals(new MazePoint(0,0))){
			output.add(0,(path.get(nextNode))); //adjacent point
			nextNode = path.get(nextNode);
		}
		return output;
	}
	
	
	private ArrayList<MazePoint> getAdjacent(MazePoint point, int[][] maze){
		ArrayList<MazePoint> output = new ArrayList<>();
		if (point.x - 1 >= 0 && maze[point.x - 1][point.y] == 0){
			output.add(new MazePoint(point.x - 1, point.y)); //left
		}
		if (point.x + 1 < maze[point.x].length && maze[point.x + 1][point.y] == 0){
			output.add(new MazePoint(point.x + 1, point.y)); //right
		} 
		if (point.y - 1 >= 0 && maze[point.x][point.y - 1] == 0){
			output.add(new MazePoint(point.x, point.y - 1)); //down
		}
		if (point.y + 1 < maze.length && maze[point.x][point.y + 1] == 0){
			output.add(new MazePoint(point.x, point.y + 1)); //up
		}
		return output;
	}
}
