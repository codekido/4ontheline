package game;

public class Game {

	boolean playerOneTurn = true;
	boolean emptyBoard = true;
	
	public boolean isEmpty() {
		return emptyBoard;
	}

	public boolean PlayerOnesTurn() {
		return playerOneTurn;
	}

	public boolean play(int column) {
		emptyBoard = false;
		playerOneTurn=!playerOneTurn;
		return playerOneTurn;
	}

}
