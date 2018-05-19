import java.util.ArrayList;
import java.util.Random;
import java.util.Stack;


public class MazeMaker{
	
	private static int width;
	private static int height;
	
	static Maze maze;
	
	private static Random randGen = new Random();
	private static Stack<Cell> uncheckedCells = new Stack<Cell>();
	
	public static Maze generateMaze(int w, int h){
		width = w;
		height = h;
		maze = new Maze(width, height);
		
		//select a random cell to start
		uncheckedCells.push(maze.getCell(randGen.nextInt(5), randGen.nextInt(5)));
		//call selectNextPath method with the randomly selected cell
		selectNextPath(uncheckedCells.pop());
		return maze;
	}

	private static void selectNextPath(Cell currentCell) {
		// mark current cell as visited
		currentCell.setBeenVisited(true);
		// check for unvisited neighbors
		ArrayList<Cell> cells = getUnvisitedNeighbors(currentCell);
		// if has unvisited neighbors,
		if(cells.size() > 0) {
			Cell t = cells.get(randGen.nextInt(cells.size()));
			uncheckedCells.push(t);
			if(t.getX() > currentCell.getX()) {
				// It's to the right
				currentCell.setEastWall(false);
				t.setWestWall(false);
			}
			else if(t.getY() < currentCell.getY()) {
				// It's on top
				currentCell.setNorthWall(false);
				t.setSouthWall(false);
			}
			else if(t.getX() < currentCell.getX()) {
				// It's to the left
				currentCell.setWestWall(false);
				t.setEastWall(false);
			}
			else {
				// It's under it
				currentCell.setSouthWall(false);
				t.setNorthWall(false);
			}
			selectNextPath(t);
		}
			// select one at random.
			// push it to the stack
			// remove the wall between the two cells
			// make the new cell the current cell and mark it as visited
				//call the selectNextPath method with the current cell
		else {
			// it's empty
			if(!uncheckedCells.isEmpty()) {
				Cell t = uncheckedCells.pop();
				selectNextPath(t);
			}
		}
		// if all neighbors are visited
			//if the stack is not empty
				// pop a cell from the stack
				// make that the current cell
		
				//call the selectNextPath method with the current cell
	}

	@SuppressWarnings("unused")
	private static void removeWalls(Cell c1, Cell c2) {
		
	}

	private static ArrayList<Cell> getUnvisitedNeighbors(Cell c) {
		ArrayList<Cell> returnThis = new ArrayList<Cell>();
		int x = c.getX();
		int y = c.getY();
		if(check(x-1, y)) {
			if(!maze.getCell(x-1, y).hasBeenVisited()) {
				returnThis.add(maze.getCell(x-1,  y));
			}
		}
		if(check(x, y-1)) {
			if(!maze.getCell(x, y-1).hasBeenVisited()) {
				returnThis.add(maze.getCell(x,  y-1));
			}
		}
		if(check(x, y+1)) {
			if(!maze.getCell(x, y+1).hasBeenVisited()) {
				returnThis.add(maze.getCell(x,  y+1));
			}
		}
		if(check(x+1, y)) {
			if(!maze.getCell(x+1, y).hasBeenVisited()) {
				returnThis.add(maze.getCell(x+1,  y));
			}
		}
		return returnThis;
	}
	
	public static boolean check(int x, int y) {
		if(x > -1 && x < 5 && y > -1 && y < 5) {
			return true;
		}
		return false;
	}
}