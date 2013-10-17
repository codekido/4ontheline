package game;

import game.ex.ColumnExceeded;

public class Game {

	private boolean playerOneTurn = true;
	private boolean emptyBoard = true;
	private int playCount = 0;
	
	public boolean isEmpty() {
		return emptyBoard;
	}

	public boolean PlayerOnesTurn() {
		return playerOneTurn;
	}

	public boolean play(int column) throws ColumnExceeded {
		emptyBoard = false;
		playerOneTurn=!playerOneTurn;
		playCount++;
		if (playCount>6) {
			throw new ColumnExceeded();
		}
		return playerOneTurn;
	}
	
}
