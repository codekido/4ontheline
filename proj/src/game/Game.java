package game;

import game.ex.CannotMoveAfterFinished;
import game.ex.ColumnExceeded;
import game.ex.InvalidMove;
import game.ex.NonexistingColumn;

public class Game {

	private int width = 7;
	private boolean playerOneTurn = true;
	private boolean emptyBoard = true;
	private int[] columnCount = new int[width+1];
	private int[][] columns = new int[width+1][6];
	private int p1AlignedVertical = 0;
	private int p1AlignedDiagonal = 0;
	private int p1AlignedHorizontal = 0;
	
	public boolean isEmpty() {
		return emptyBoard;
	}

	public boolean PlayerOnesTurn() {
		return playerOneTurn;
	}

	public boolean play(int column) throws InvalidMove {
		checkWinner();
		isValidColumn(column);
		emptyBoard = false;
		addChipToColumn(column);
		switchTurn();
		return playerOneTurn;
	}


	private void checkWinner() throws CannotMoveAfterFinished {
		checkRows();
		checkCols();
		checkDiag();
	}
	
	

	private void checkCols() throws CannotMoveAfterFinished {
		for (int col=1; col<=width; ++col) {
			checkCol(col);
		}
	}

	private void checkDiag() {
		// TODO Auto-generated method stub
		
	}

	private void checkRows() throws CannotMoveAfterFinished {
		for (int row=0; row<6; ++row) {
			checkCol(row);
		}
	}

	private void checkCol(int col) throws CannotMoveAfterFinished {
		int p1chips = 0;
		int p2chips = 0;
		for (int pos=0; pos<6; ++pos) {
			switch (columns[col][pos]) {
			case 1: ++p1chips;
			case 2: ++p2chips;
			}
		}
		if (p1chips>3 || p2chips>3) {
			throw new CannotMoveAfterFinished();
		}
	}

	private boolean checkRow(int row) {
		int p1chips = 0;
		int p2chips = 0;
		for (int col=1; col<=7; ++col) {
			switch (columns[col][row]) {
			case 1: ++p1chips;
			case 2: ++p2chips;
			}
		}
		if (p1chips>3 || p2chips>3) {
			return true;
		}
		return false;
	}

	private void isValidColumn(int column) throws NonexistingColumn {
		if (column>width || column<1 ) {
			throw new NonexistingColumn();
		}
	}

	private int addChipToColumn(int column) throws ColumnExceeded{
		int position = columnCount[column];
		if (position > 5) {
			throw new ColumnExceeded();
		}
		columns[column][position] = playerOneTurn ? 1 : 2;
		columnCount[column]++;
		return position;
	}

	private void switchTurn() {
		playerOneTurn=!playerOneTurn;
	}

	public void restart() {
		emptyBoard = true;
	}

	public boolean fourOnTheLine() {
		if (p1AlignedVertical >= 4) return true;
		if (p1AlignedDiagonal >= 7) return true;
		if (p1AlignedHorizontal >= 4) return true;
		return false;
	}
	
}
