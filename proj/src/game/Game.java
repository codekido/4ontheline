package game;

import game.ex.ColumnExceeded;

public class Game {

	private boolean playerOneTurn = true;
	private boolean emptyBoard = true;
	private int[] columnCount = new int[7];	
	
	public boolean isEmpty() {
		return emptyBoard;
	}

	public boolean PlayerOnesTurn() {
		return playerOneTurn;
	}

	public boolean play(int column) throws ColumnExceeded {
		emptyBoard = false;
		playerOneTurn=!playerOneTurn;
		//playCount++;
		
		columnCount[column] = ++columnCount[column];
		
		if (columnCount[column]>6) {
			throw new ColumnExceeded();
		}
		return playerOneTurn;
	}

	public void restart() {
		emptyBoard = true;
	}
	
}
