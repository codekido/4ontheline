package game;

import game.ex.ColumnExceeded;
import game.ex.NonexistingColumn;

public class Game {

	private int width = 7;
	private boolean playerOneTurn = true;
	private boolean emptyBoard = true;
	private int[] columnCount = new int[width];	
	private int p1AllignedVertical = 0;
	
	public boolean isEmpty() {
		return emptyBoard;
	}

	public boolean PlayerOnesTurn() {
		return playerOneTurn;
	}

	public boolean play(int column) throws ColumnExceeded, NonexistingColumn {
		if (column>width || column<1 ) {
			throw new NonexistingColumn();
		}
		emptyBoard = false;
		
		
		if (playerOneTurn) ++p1AllignedVertical;
		
		playerOneTurn=!playerOneTurn;
		
		columnCount[column] = ++columnCount[column];
		
		
		if (columnCount[column]>6) {
			throw new ColumnExceeded();
		}
		return playerOneTurn;
	}

	public void restart() {
		emptyBoard = true;
	}

	public boolean fourOnTheLine(int i) {
		if (p1AllignedVertical == 4) return true;
		return false;
	}
	
}
