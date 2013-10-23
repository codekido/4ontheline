package game;

import game.ex.ColumnExceeded;
import game.ex.NonexistingColumn;

public class Game {

	private int width = 7;
	private boolean playerOneTurn = true;
	private boolean emptyBoard = true;
	private int[] columnCount = new int[width+1];
	private boolean[][] columns = new boolean[width+1][6];
	private int p1AlignedVertical = 0;
	private int p1AlignedDiagonal = 0;
	private int p1AlignedHorizontal = 0;
	
	public boolean isEmpty() {
		return emptyBoard;
	}

	public boolean PlayerOnesTurn() {
		return playerOneTurn;
	}

	public boolean play(int column) throws ColumnExceeded, NonexistingColumn {
		
		isValidColumn(column);
		emptyBoard = false;
		
		switchTurn();

		int height = addChipToColumn(column);
		
		if (playerOneTurn) {
			try {
				if (columns[column][height-1] == true) ++p1AlignedVertical;
				if (columns[column-1][height] == true) ++p1AlignedHorizontal;
				if (columns[column+1][height] == true) ++p1AlignedHorizontal;
				if (columns[column-1][height-1] == true) ++p1AlignedDiagonal;
				if (columns[column+1][height+1] == true) ++p1AlignedDiagonal;
				if (columns[column+1][height-1] == true) ++p1AlignedDiagonal;
				if (columns[column-1][height+1] == true) ++p1AlignedDiagonal;
			} catch (IndexOutOfBoundsException e) {
				// Ha! Cleverly ignoring cells out of the board :)
			}
		}		
		
		return playerOneTurn;
	}

	private void isValidColumn(int column) throws NonexistingColumn {
		if (column>width || column<1 ) {
			throw new NonexistingColumn();
		}
	}

	private int addChipToColumn(int column) throws ColumnExceeded{
		columnCount[column]++;
		if (columnCount[column]>6) {
			throw new ColumnExceeded();
		}
		columns[column][columnCount[column]-1] = playerOneTurn;
		return columnCount[column];
	}

	private void switchTurn() {
		playerOneTurn=!playerOneTurn;
	}

	public void restart() {
		emptyBoard = true;
	}

	public boolean fourOnTheLine() {
		if (p1AlignedVertical == 4) return true;
		if (p1AlignedDiagonal  == 7) return true;
		return false;
	}
	
}
