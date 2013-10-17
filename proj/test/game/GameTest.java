package game;

import static org.junit.Assert.*;

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
	public void testBoard_IsEmpty_True() {
		assertTrue(g.isEmpty());
	}

	@Test
	public void testBoard_IsPlayerOneTurn_True(){
		assertTrue(g.PlayerOnesTurn());
	}

	@Test
	public void testBoard_IsPlayerOneTurn_False(){
		assertFalse(g.play(1).PlayerOnesTurn());
	}

}
