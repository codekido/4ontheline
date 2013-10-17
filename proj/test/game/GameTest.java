package game;

import static org.junit.Assert.*;
import game.ex.ColumnExceeded;

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
	public void IsEmpty_AfterPlay_False() throws ColumnExceeded {
		g.play(1);
		assertFalse(g.isEmpty());
	}

	@Test
	public void IsPlayerOneTurn_FirstTurn_True() {
		assertTrue(g.PlayerOnesTurn());
	}

	@Test
	public void IsPlayerOneTurn_AfterPlayerOnePlays_False() throws ColumnExceeded {
		g.play(1);
		assertFalse(g.PlayerOnesTurn());
	}
	
	@Test 
	public void IsPlayerOneTurn_AfterPlayerTwoPlays_True() throws ColumnExceeded {
		g.play(1);
		g.play(1);
		assertTrue(g.PlayerOnesTurn());
	}

	@Test(expected=ColumnExceeded.class)
	public void exceededColumn_MoreThanSixPlaysInAColumn_ThrowsColumnExceeded() throws ColumnExceeded {
		for (int i=0; i<7; ++i) {
			g.play(1);		
		}
	}
	
	@Test()
	public void exceededColumn_MoreThan6PlaysInSeveralColumns_DoesNotThrowColumnExceeded() throws ColumnExceeded {
		for (int i=0; i<4; ++i) {
			for (int col=1; col<=3; ++col) {
				g.play(col);
			}
		}
	}
	
	@Test	
	public void isEmpty_RestartingTheGame_True(){
		g.restart();
		assertTrue(g.isEmpty());
	}
}