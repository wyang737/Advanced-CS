import java.util.ArrayList;
public class MazePoint {

	public int x;
	public int y;
	
	public MazePoint(int myx, int myy){
		this.x = myx;
		this.y = myy;
	}
	
	public void print(){
		System.out.println("x: " + x + " || " + "y: " + y);
	}
	
	
	public boolean equals(Object obj){
		MazePoint mp = (MazePoint) obj;
		if (x == mp.x && y == mp.y){
			return true;
		}
		return false;
	}
	
	public boolean contained(ArrayList<MazePoint> lst){
		for (int i = 0; i < lst.size(); i ++){
			if (equals(lst.get(i))){
				return true;
			}
		}
		return false;
	}
}
