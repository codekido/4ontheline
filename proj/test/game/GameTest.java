package game;

import static org.junit.Assert.*;
import game.ex.CannotMoveAfterFinished;
import game.ex.ColumnExceeded;
import game.ex.NonexistingColumn;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;


public class GameTest {

	Game g;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		g = new Game();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void IsEmpty_NewGame_True() {
		assertTrue(g.isEmpty());
	}

	@Test
	public void IsEmpty_AfterPlay_False() throws ColumnExceeded, NonexistingColumn {
		g.play(1);
		assertFalse(g.isEmpty());
	}

	@Test
	public void IsPlayerOneTurn_FirstTurn_True() {
		assertTrue(g.PlayerOnesTurn());
	}

	@Test
	public void IsPlayerOneTurn_AfterPlayerOnePlays_False() throws ColumnExceeded, NonexistingColumn {
		g.play(1);
		assertFalse(g.PlayerOnesTurn());
	}
	
	@Test 
	public void IsPlayerOneTurn_AfterPlayerTwoPlays_True() throws ColumnExceeded, NonexistingColumn {
		g.play(1);
		g.play(1);
		assertTrue(g.PlayerOnesTurn());
	}

	@Test(expected=ColumnExceeded.class)
	public void exceededColumn_MoreThanSixPlaysInAColumn_ThrowsColumnExceeded() throws ColumnExceeded, NonexistingColumn {
		for (int i=0; i<7; ++i) {
			g.play(1);		
		}
	}
	
	@Test()
	public void exceededColumn_MoreThan6PlaysInSeveralColumns_DoesNotThrowColumnExceeded() throws ColumnExceeded, NonexistingColumn {
		for (int i=0; i<4; ++i) {
			for (int col=1; col<=3; ++col) {
				g.play(col);
			}
		}
	}
	
	@Test	
	public void isEmpty_RestartingTheGame_True() {
		g.restart();
		assertTrue(g.isEmpty());
	}

	@Test	
	public void isEmpty_RestartingTheGameAfterPlaying_True() throws ColumnExceeded, NonexistingColumn {
		g.play(1);
		g.restart();
		assertTrue(g.isEmpty());
	}
	
	@Test(expected=NonexistingColumn.class)
	public void isValidColumn_PlayingOnColumnEight_ThrowsNonexistingColumn() throws ColumnExceeded, NonexistingColumn {
		g.play(8);
	}

	@Test(expected=NonexistingColumn.class)
	public void isValidColumn_PlayingOnColumnZero_ThrowsNonexistingColumn() throws ColumnExceeded, NonexistingColumn {
		g.play(0);
	}
	
	@Test
	public void fourOnTheLine_MixedPlays_False() throws ColumnExceeded, NonexistingColumn{
		for (int i=0; i<4; ++i) {
			g.play(1);		
		}
		assertFalse(g.fourOnTheLine());
	}	

	@Test
	public void fourOnTheLine_OneWinnerVertical_True() throws ColumnExceeded, NonexistingColumn{
		for (int i=0; i<3; ++i) {
			// each player plays 3 moves in their column
			for (int player=1; player<=2; ++player) {
				g.play(player);		
			}			
		}
		g.play(1); // player 1 plays 4th in col 1 and wins
		assertTrue(g.fourOnTheLine());
	}
	
	@Test
	public void fourOnTheLine_OneWinnerDiagonal_True() throws ColumnExceeded, NonexistingColumn{
		g.play(1);
		g.play(7);
		g.play(2);
		g.play(6);
		g.play(3);
		g.play(4);
		g.play(5);
		g.play(4);
		g.play(2);
		g.play(4);
		g.play(4);
		g.play(3);
		g.play(3);
		
		assertTrue(g.fourOnTheLine());
		
	}
	
	@Test
	public void fourOnTheLine_OneWinnerHorizontal_True() throws ColumnExceeded, NonexistingColumn {
		g.play(1);
		g.play(7);
		g.play(2);
		g.play(6);
		g.play(3);
		g.play(5);
		g.play(4);
		
		assertTrue("Player 1 should win (4 horizontal in the bottom left)", g.fourOnTheLine());
	}

	@Test
	public void fourOnTheLine_PlayeresSwapAtThree_False() throws ColumnExceeded, NonexistingColumn {
		for (int i=0; i<3; ++i) {
			g.play(1);
			g.play(2);
		}
		for (int i=0; i<3; i++) {
			g.play(2);
			g.play(1);
		}
		
		assertFalse("No one should win. The players swapped columns", g.fourOnTheLine());
	}

	@Test(expected=CannotMoveAfterFinished.class)
	public void finished_CannotMoveAfterWin_False() throws ColumnExceeded, NonexistingColumn {
		for (int i=1; i<=4; ++i) {
			g.play(i);
			g.play(i);
		}
	}
}