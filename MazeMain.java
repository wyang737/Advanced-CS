import java.util.ArrayList;
public class MazeMain {

	public static void main(String[] args){
		int[][] maze = new int[10][10]; //wall is represented by a value of 1, open space = 0
		MazeSolver ms = new MazeSolver();
		buildMaze(maze); // create maze
		printMaze(maze); // print it
		ArrayList<MazePoint> solution = ms.solveMaze(maze);
		System.out.println(solution.size());
		for (MazePoint mp : solution){
			mp.print();
		}
	}
	
	public static void printMaze(int[][] maze){
		for (int i = 0; i < maze.length; i ++){
			for (int j = 0; j < maze.length; j ++){
				System.out.print(maze[i][j]);
			}
			System.out.println("");
		}
	}
	public static void buildMaze(int[][] maze){
		maze[1][0] = 1;
		maze[2][0] = 1;
		maze[3][0] = 1;
		maze[4][0] = 1;
		maze[5][0] = 1;
		maze[5][1] = 1;
		maze[7][1] = 1;
		maze[8][1] = 1;
		maze[3][2] = 1;
		maze[5][2] = 1;
		maze[1][3] = 1;
		maze[3][3] = 1;
		maze[5][3] = 1;
		maze[7][3] = 1;
		maze[8][3] = 1;
		maze[1][4] = 1;
		maze[3][4] = 1;
		maze[5][4] = 1;
		maze[7][4] = 1;
		maze[1][5] = 1;
		maze[2][5] = 1;
		maze[3][5] = 1;
		maze[5][5] = 1;
		maze[7][5] = 1;
		maze[9][5] = 1;
		maze[1][6] = 1;
		maze[5][6] = 1;
		maze[7][6] = 1;
		maze[9][6] = 1;
		maze[1][7] = 1;
		maze[3][7] = 1;
		maze[4][7] = 1;
		maze[5][7] = 1;
		maze[7][7] = 1;
		maze[0][8] = 1;
		maze[7][8] = 1;
		maze[4][9] = 1;
		maze[5][9] = 1;
		maze[6][9] = 1;
		maze[7][9] = 1;
	}
	
}
