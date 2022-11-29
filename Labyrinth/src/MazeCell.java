
public class MazeCell {
	
		
	String contents;   // empty string if this cell contains no item
		    
	public MazeCell north, south, east, west;
	public int row, col;
	
	public MazeCell(int r, int c) {
		contents = "";
		north = null;
		south = null;
		east = null;
		west = null;    
		row = r;
		col = c;
		
	}

	
	
}
