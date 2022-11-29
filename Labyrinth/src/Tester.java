/* 
Author: Nicole Spaulding
Date: 11/28/22
Rev: 02
Notes: labyrinth linked list maze
*/
import java.util.ArrayList;

public class Tester {
	public static void main (String[ ] args) {
		Labyrinth L = new Labyrinth();
		
		
//  ADD YOUR OWN SEED HERE TO CHANGE THE MAZE RANDOMIZATION VVV
		long seed = 1;
		String path = "EEEWWSSE";
		
		
		ArrayList<String> items = new ArrayList<String>();
		items.add("spellbook");
		items.add("wand");
		items.add("potion");
		MazeCell start = L.createRandomMaze(seed, items);
	
// TOGGLE BREAKPOINT ON THE NEXT LINE, THEN SOLVE MAZE AND RE-RUN DEBUGGER AFTER CHANGING PATH		
		System.out.print("Maze solved: " + (L.isPathToFreedom(start, path, items)));
		
		
		
	}
	
	
}