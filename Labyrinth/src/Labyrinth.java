import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;

public class Labyrinth {

	public Labyrinth() {
		
	}
	
	 public boolean isPathToFreedom(MazeCell start, String moves, ArrayList<String> needs) {

		    HashSet<String> found = new HashSet<String>();
		    MazeCell cur = start;
		    for(int i = 0; i < moves.length() + 1; i++) {
		        if(cur.contents.length() != 0) { //check if something here
		            found.add(cur.contents);
		        }

		        if(found.containsAll(needs) && i == moves.length()) { //if everything is collected by last move, escape
		            return true;
		        }
		        if(i < moves.length()) {
		            if(moves.charAt(i) == 'N') {
		                if(cur.north == null) {
		                    return false;
		                }
		                cur = cur.north;
		            } else if(moves.charAt(i) == 'E') {
		                if(cur.east == null) {
		                    return false;
		                }
		                cur = cur.east;
		            } else if(moves.charAt(i) == 'S') {
		                if(cur.south == null) {
		                    return false;
		                }
		                cur = cur.south;
		            } else if(moves.charAt(i) == 'W') {
		                if(cur.west == null) {
		                    return false;
		                }
		                cur = cur.west;
		            } else {
		                System.out.println("invalid character contained in moves");
		            }
		        }
		    }
		    return false;
		}
	 

	 //This method creates a randomized 4x4 maze given items and a long seed

	 public MazeCell createRandomMaze(long seed, ArrayList<String> items) {
		/* PSEUDOCODE
		 * store start(0, 0)*
		 * grid bool maze 4x4 true filled, false not*
		 * array edges*
		 * int numberCell = 0*
		 * 	create arraylist of gridlocations edges to store current edges to be expanded upon
		 * 	for each curedge*
		 * 		numberCell++*
		 * 		find number of valid edges(maze, curRow, curCol)*
		 * 		if(numberCell == 4) add spellbook
		 * 		if(numberCell == 9) add wand
		 * 		if(numberCell == 11) add potion
		 * 		if 0, *
		 * 			remove this cell from edges
		 * 		else
		 * 			find random number n between valid edges.length and 1 of new cells*
		 * 			for(int i = n; i > 0; i--) {
		 * 				int m = random number 0 -> edges.length - 1 *
		 * 				if(string[m] == north) {
		 * 					MazeCell next = new MazeCell(cur.row - 1, cur.col)
		 * 					edges.add next
		 * next
		 * 				}
		 * 				if(string[m] == east) {
		 * 					MazeCell next = new MazeCell(cur.row, cur.col+1)
		 * 					edges.add next
		 * 					add location to maze 					
		 * 				}
		 * 				if(string[m] == south) {
		 *  				MazeCell next = new MazeCell(cur.row + 1, cur.col)
		 * 					edges.add next
		 *  				add location to maze					
		 * 				}
		 * 				if(string[m] == west) {
		 * 					MazeCell next = new MazeCell(cur.row, cur.col - 1)
		 * 					edges.add next
		 *  				add location to maze 					
		 * 				}
		 * 				edges.remove cur
		 * 			}
		 * 
		 * return start
		 */
		 Random rand = new Random();
		 rand.setSeed(seed);
		 
		 MazeCell start = new MazeCell(0, 0);
		 Boolean[][] maze = new Boolean[4][4];
		 for(int i = 0; i < 4; i++) {
			 for(int j = 0; j < 4; j++) {
				 maze[i][j] = false;
			 } 
		 }
		 maze[0][0] = true;
		 ArrayList<MazeCell> edges = new ArrayList<MazeCell>();
		 edges.add(start);
		 int cellNum = 0;
		 
		 while (!edges.isEmpty()) {
			 MazeCell cur = edges.get(0);
			 cellNum++;
			 ArrayList<String> validMoves = getValidMoves(maze, cur.row, cur.col);
			 if(cellNum == 4) {
				 cur.contents = items.get(0);
				 items.remove(0);
			 } else if(cellNum == 9) {
				 cur.contents = items.get(0);
				 items.remove(0);
			 } else if(cellNum == 11) {
				 cur.contents = items.get(0);
				 items.remove(0);
			 }
			 
			 if(validMoves.isEmpty()) {
				 edges.remove(0);
			 } else {
				 int n = rand.nextInt(validMoves.size()) + 1;
				 ArrayList<Integer> alreadyDone = new ArrayList<Integer>();
				 for(int i = n; i > 0; i--) {
					
					 int m = rand.nextInt(validMoves.size());
					 while(alreadyDone.contains(m)) {
						 m = rand.nextInt(validMoves.size());
					 }
					 alreadyDone.add(m);
					 if(validMoves.get(m).equals("north")) {
						 MazeCell next = new MazeCell(cur.row - 1, cur.col);
						 cur.north = next;
						 next.south = cur;
						 edges.add(next);
						 maze[next.row][next.col] = true;
					 } else if(validMoves.get(m).equals("east")) {
						 MazeCell next = new MazeCell(cur.row, cur.col + 1);
						 cur.east = next;
						 next.west = cur;
						 edges.add(next);
						 maze[next.row][next.col] = true;
					 } else if(validMoves.get(m).equals("south")) {
						 MazeCell next = new MazeCell(cur.row + 1, cur.col);
						 cur.south = next;
						 next.north = cur;
						 edges.add(next);
						 maze[next.row][next.col] = true;
					 } else if(validMoves.get(m).equals("west")) {
						 MazeCell next = new MazeCell(cur.row, cur.col - 1);
						 cur.west = next;
						 next.east = cur;
						 edges.add(next);
						 maze[next.row][next.col] = true;
					 }
				 }
				 edges.remove(0);
			 }
		 }
		 
		 return start;
	 }

	 public ArrayList<String> getValidMoves(Boolean[][] maze, int curR, int curC) {
		 ArrayList<String> vm = new ArrayList<String>();
		 vm.add("north");
		 vm.add("east");
		 vm.add("south");
		 vm.add("west");
		 
		 GridLocation north = new GridLocation(curR - 1, curC); 
		 GridLocation east = new GridLocation(curR, curC + 1); 
		 GridLocation south = new GridLocation(curR + 1, curC); 
		 GridLocation west = new GridLocation(curR, curC - 1); 
		 
		 if(north.row < 0 || maze[north.row][north.col]) {
			 vm.remove("north");
		 }
		 if(east.col > 3 || maze[east.row][east.col]) {
			 vm.remove("east");
		 }
		 if(south.row > 3 || maze[south.row][south.col]) {
			 vm.remove("south");
		 }
		 if(west.col < 0 || maze[west.row][west.col]) {
			 vm.remove("west");
		 }
		 
		 return vm;
	 }
	

}


