package xiangqi.game.versions.gammaxiangqi;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import xiangqi.XiangqiGameFactory;
import xiangqi.common.MoveResult;
import xiangqi.common.XiangqiGame;
import xiangqi.common.XiangqiGameVersion;
import xiangqi.game.versions.betaxiangqi.TestCoordinate;

public class GeneralRulesTestCases {
	private XiangqiGame game;
	
	@Before
	public void setup() {
		this.game = XiangqiGameFactory.makeXiangqiGame(XiangqiGameVersion.GAMMA_XQ);
	}
	
	@Test
	public void factoryProducesGammaXiangqiGame() {
		assertNotNull(game);
	}
	
	@Test
	public void redMakesFirstValidMove() {
		assertEquals(MoveResult.OK, game.makeMove(TestCoordinate.makeCoordinate(1, 5), TestCoordinate.makeCoordinate(2, 5)));
	}
	
	@Test
	public void blackMakesValidSecondMove() {
		game.makeMove(TestCoordinate.makeCoordinate(1, 5), TestCoordinate.makeCoordinate(2, 5));
		assertEquals(MoveResult.OK, game.makeMove(TestCoordinate.makeCoordinate(1, 5), TestCoordinate.makeCoordinate(2, 5)));
	}
	
	@Test
	public void generalCannotMoveHorizontallyAtStartOfGameBecauseOfAdvisors() {
		assertEquals(MoveResult.ILLEGAL, game.makeMove(TestCoordinate.makeCoordinate(1, 5), TestCoordinate.makeCoordinate(1, 4)));
		assertTrue(game.getMoveMessage().length() >= 1);
		assertEquals("Pieces cannot capture ally pieces", game.getMoveMessage());
	}
	
	@Test
	public void cannotMoveOtherPiecesOfTheOtherTeam() {
		assertEquals(MoveResult.ILLEGAL, game.makeMove(TestCoordinate.makeCoordinate(10, 5), TestCoordinate.makeCoordinate(9, 4)));
		assertTrue(game.getMoveMessage().length() >= 1);
		assertEquals("Attempting to move from an empty space or moving the opponent's piece.", game.getMoveMessage());
	}
	
	@Test
	public void cannotMoveOtherPiecesFromEmptySpace() {
		assertEquals(MoveResult.ILLEGAL, game.makeMove(TestCoordinate.makeCoordinate(2, 5), TestCoordinate.makeCoordinate(3, 5)));
		assertTrue(game.getMoveMessage().length() >= 1);
		assertEquals("Attempting to move from an empty space or moving the opponent's piece.", game.getMoveMessage());
	}
	
	@Test
	public void drawAfter25TurnsWithNoWinner() {
		for(int i = 0; i < 12; i++) {
			assertEquals(MoveResult.OK, game.makeMove(TestCoordinate.makeCoordinate(1, 1), TestCoordinate.makeCoordinate(2, 1)));
			assertEquals(MoveResult.OK, game.makeMove(TestCoordinate.makeCoordinate(1, 1), TestCoordinate.makeCoordinate(2, 1)));
			assertEquals(MoveResult.OK, game.makeMove(TestCoordinate.makeCoordinate(2, 1), TestCoordinate.makeCoordinate(1, 1)));
			assertEquals(MoveResult.OK, game.makeMove(TestCoordinate.makeCoordinate(2, 1), TestCoordinate.makeCoordinate(1, 1)));
		}
		assertEquals(MoveResult.OK, game.makeMove(TestCoordinate.makeCoordinate(1, 1), TestCoordinate.makeCoordinate(2, 1)));
		assertEquals(MoveResult.DRAW, game.makeMove(TestCoordinate.makeCoordinate(1, 9), TestCoordinate.makeCoordinate(2, 9)));
	}
	
	@Test
	public void cannotMoveIntoCheckAgainstChariot() {
		assertEquals(MoveResult.OK, game.makeMove(TestCoordinate.makeCoordinate(1, 1), TestCoordinate.makeCoordinate(2, 1)));
		assertEquals(MoveResult.OK, game.makeMove(TestCoordinate.makeCoordinate(1, 1), TestCoordinate.makeCoordinate(2, 1)));
		assertEquals(MoveResult.OK, game.makeMove(TestCoordinate.makeCoordinate(2, 1), TestCoordinate.makeCoordinate(2, 6)));
		assertEquals(MoveResult.ILLEGAL, game.makeMove(TestCoordinate.makeCoordinate(1, 5), TestCoordinate.makeCoordinate(2, 4)));
		assertTrue(game.getMoveMessage().length() >= 1);
		assertEquals("You cannot move into check", game.getMoveMessage());
	}
	
	@Test
	public void cannotMoveIntoCheckAgainstGeneral() {
		assertEquals(MoveResult.OK, game.makeMove(TestCoordinate.makeCoordinate(4, 5), TestCoordinate.makeCoordinate(5, 5)));
		assertEquals(MoveResult.OK, game.makeMove(TestCoordinate.makeCoordinate(4, 5), TestCoordinate.makeCoordinate(5, 5)));
		assertEquals(MoveResult.OK, game.makeMove(TestCoordinate.makeCoordinate(5, 5), TestCoordinate.makeCoordinate(6, 5)));
		assertEquals(MoveResult.OK, game.makeMove(TestCoordinate.makeCoordinate(1, 1), TestCoordinate.makeCoordinate(2, 1)));
		assertEquals(MoveResult.ILLEGAL, game.makeMove(TestCoordinate.makeCoordinate(6, 5), TestCoordinate.makeCoordinate(6, 6)));
		assertTrue(game.getMoveMessage().length() >= 1);
		assertEquals("You cannot move into check", game.getMoveMessage());
	}
	
	@Test
	public void cannotMoveIntoCheckAgainstSoldierVertically() {
		assertEquals(MoveResult.OK, game.makeMove(TestCoordinate.makeCoordinate(4, 5), TestCoordinate.makeCoordinate(5, 5)));
		assertEquals(MoveResult.OK, game.makeMove(TestCoordinate.makeCoordinate(4, 5), TestCoordinate.makeCoordinate(5, 5)));
		assertEquals(MoveResult.OK, game.makeMove(TestCoordinate.makeCoordinate(5, 5), TestCoordinate.makeCoordinate(6, 5)));
		assertEquals(MoveResult.OK, game.makeMove(TestCoordinate.makeCoordinate(1, 1), TestCoordinate.makeCoordinate(2, 1)));
		assertEquals(MoveResult.OK, game.makeMove(TestCoordinate.makeCoordinate(6, 5), TestCoordinate.makeCoordinate(7, 5)));
		assertEquals(MoveResult.OK, game.makeMove(TestCoordinate.makeCoordinate(2, 1), TestCoordinate.makeCoordinate(1, 1)));
		assertEquals(MoveResult.OK, game.makeMove(TestCoordinate.makeCoordinate(7, 5), TestCoordinate.makeCoordinate(8, 5)));
		assertEquals(MoveResult.ILLEGAL, game.makeMove(TestCoordinate.makeCoordinate(1, 5), TestCoordinate.makeCoordinate(2, 5)));
		assertTrue(game.getMoveMessage().length() >= 1);
		assertEquals("You cannot move into check", game.getMoveMessage());
	}
	
	@Test
	public void cannotMoveIntoCheckAgainstSoldierHorizontally() {
		assertEquals(MoveResult.OK, game.makeMove(TestCoordinate.makeCoordinate(4, 5), TestCoordinate.makeCoordinate(5, 5)));
		assertEquals(MoveResult.OK, game.makeMove(TestCoordinate.makeCoordinate(4, 5), TestCoordinate.makeCoordinate(5, 5)));
		
		assertEquals(MoveResult.OK, game.makeMove(TestCoordinate.makeCoordinate(5, 5), TestCoordinate.makeCoordinate(6, 5)));
		assertEquals(MoveResult.OK, game.makeMove(TestCoordinate.makeCoordinate(1, 1), TestCoordinate.makeCoordinate(2, 1)));
		
		assertEquals(MoveResult.OK, game.makeMove(TestCoordinate.makeCoordinate(6, 5), TestCoordinate.makeCoordinate(7, 5)));
		assertEquals(MoveResult.OK, game.makeMove(TestCoordinate.makeCoordinate(2, 1), TestCoordinate.makeCoordinate(1, 1)));
		
		assertEquals(MoveResult.OK, game.makeMove(TestCoordinate.makeCoordinate(7, 5), TestCoordinate.makeCoordinate(8, 5)));
		assertEquals(MoveResult.OK, game.makeMove(TestCoordinate.makeCoordinate(1, 1), TestCoordinate.makeCoordinate(2, 1)));
		
		assertEquals(MoveResult.OK, game.makeMove(TestCoordinate.makeCoordinate(1, 5), TestCoordinate.makeCoordinate(2, 5)));
		assertEquals(MoveResult.OK, game.makeMove(TestCoordinate.makeCoordinate(2, 1), TestCoordinate.makeCoordinate(1, 1)));
		
		assertEquals(MoveResult.OK, game.makeMove(TestCoordinate.makeCoordinate(2, 5), TestCoordinate.makeCoordinate(2, 4)));
		assertEquals(MoveResult.OK, game.makeMove(TestCoordinate.makeCoordinate(1, 9), TestCoordinate.makeCoordinate(2, 9)));
		
		assertEquals(MoveResult.OK, game.makeMove(TestCoordinate.makeCoordinate(8, 5), TestCoordinate.makeCoordinate(8, 6)));
		assertEquals(MoveResult.OK, game.makeMove(TestCoordinate.makeCoordinate(1, 1), TestCoordinate.makeCoordinate(2, 1)));
		
		assertEquals(MoveResult.OK, game.makeMove(TestCoordinate.makeCoordinate(8, 6), TestCoordinate.makeCoordinate(9, 6)));
		assertEquals(MoveResult.ILLEGAL, game.makeMove(TestCoordinate.makeCoordinate(1, 5), TestCoordinate.makeCoordinate(2, 5)));
		assertTrue(game.getMoveMessage().length() >= 1);
		assertEquals("You cannot move into check", game.getMoveMessage());
	}
	
	@Test
	public void generalMustMoveOutOfCheck() {
		assertEquals(MoveResult.OK, game.makeMove(TestCoordinate.makeCoordinate(1, 1), TestCoordinate.makeCoordinate(2, 1)));
		assertEquals(MoveResult.OK, game.makeMove(TestCoordinate.makeCoordinate(1, 1), TestCoordinate.makeCoordinate(2, 1)));
		assertEquals(MoveResult.OK, game.makeMove(TestCoordinate.makeCoordinate(2, 1), TestCoordinate.makeCoordinate(2, 6)));
		assertEquals(MoveResult.OK, game.makeMove(TestCoordinate.makeCoordinate(2, 1), TestCoordinate.makeCoordinate(1, 1)));
		assertEquals(MoveResult.OK, game.makeMove(TestCoordinate.makeCoordinate(2, 6), TestCoordinate.makeCoordinate(8, 6)));
		assertEquals(MoveResult.OK, game.makeMove(TestCoordinate.makeCoordinate(1, 1), TestCoordinate.makeCoordinate(2, 1)));
		assertEquals(MoveResult.OK, game.makeMove(TestCoordinate.makeCoordinate(8, 6), TestCoordinate.makeCoordinate(8, 5)));
		assertEquals(MoveResult.ILLEGAL, game.makeMove(TestCoordinate.makeCoordinate(1, 5), TestCoordinate.makeCoordinate(2, 5)));
		assertTrue(game.getMoveMessage().length() >= 1);
		assertEquals("You cannot move into check", game.getMoveMessage());
	}
	
	@Test
	public void checkmateWithRedWinning() {
		assertEquals(MoveResult.OK, game.makeMove(TestCoordinate.makeCoordinate(1, 9), TestCoordinate.makeCoordinate(2, 9)));
		assertEquals(MoveResult.OK, game.makeMove(TestCoordinate.makeCoordinate(1, 4), TestCoordinate.makeCoordinate(2, 5)));
		assertEquals(MoveResult.OK, game.makeMove(TestCoordinate.makeCoordinate(2, 9), TestCoordinate.makeCoordinate(2, 6)));
		assertEquals(MoveResult.OK, game.makeMove(TestCoordinate.makeCoordinate(2, 5), TestCoordinate.makeCoordinate(3, 4)));
		assertEquals(MoveResult.OK, game.makeMove(TestCoordinate.makeCoordinate(2, 6), TestCoordinate.makeCoordinate(8, 6)));
		assertEquals(MoveResult.OK, game.makeMove(TestCoordinate.makeCoordinate(1, 6), TestCoordinate.makeCoordinate(2, 5)));
		assertEquals(MoveResult.OK, game.makeMove(TestCoordinate.makeCoordinate(1, 1), TestCoordinate.makeCoordinate(2, 1)));
		assertEquals(MoveResult.OK, game.makeMove(TestCoordinate.makeCoordinate(2, 5), TestCoordinate.makeCoordinate(3, 6)));
		assertEquals(MoveResult.OK, game.makeMove(TestCoordinate.makeCoordinate(2, 1), TestCoordinate.makeCoordinate(2, 6)));
		assertEquals(MoveResult.OK, game.makeMove(TestCoordinate.makeCoordinate(1, 1), TestCoordinate.makeCoordinate(3, 1)));
		assertEquals(MoveResult.OK, game.makeMove(TestCoordinate.makeCoordinate(8, 6), TestCoordinate.makeCoordinate(8, 4)));
		assertEquals(MoveResult.OK, game.makeMove(TestCoordinate.makeCoordinate(1, 9), TestCoordinate.makeCoordinate(3, 9)));
		assertEquals(MoveResult.OK, game.makeMove(TestCoordinate.makeCoordinate(2, 6), TestCoordinate.makeCoordinate(8, 6)));
		assertEquals(MoveResult.OK, game.makeMove(TestCoordinate.makeCoordinate(4, 1), TestCoordinate.makeCoordinate(5, 1)));
		assertEquals(MoveResult.OK, game.makeMove(TestCoordinate.makeCoordinate(8, 4), TestCoordinate.makeCoordinate(8, 3)));
		assertEquals(MoveResult.OK, game.makeMove(TestCoordinate.makeCoordinate(4, 9), TestCoordinate.makeCoordinate(5, 9)));
		assertEquals(MoveResult.OK, game.makeMove(TestCoordinate.makeCoordinate(8, 6), TestCoordinate.makeCoordinate(8, 7)));
		assertEquals(MoveResult.OK, game.makeMove(TestCoordinate.makeCoordinate(4, 7), TestCoordinate.makeCoordinate(5, 7)));
		assertEquals(MoveResult.OK, game.makeMove(TestCoordinate.makeCoordinate(8, 3), TestCoordinate.makeCoordinate(9, 3)));
		assertEquals(MoveResult.OK, game.makeMove(TestCoordinate.makeCoordinate(4, 5), TestCoordinate.makeCoordinate(5, 5)));
		assertEquals(MoveResult.RED_WINS, game.makeMove(TestCoordinate.makeCoordinate(8, 7), TestCoordinate.makeCoordinate(10, 7)));
	}
	
	@Test
	public void checkmateWithBlackWinning() {
		assertEquals(MoveResult.OK, game.makeMove(TestCoordinate.makeCoordinate(4, 3), TestCoordinate.makeCoordinate(5, 3)));
		assertEquals(MoveResult.OK, game.makeMove(TestCoordinate.makeCoordinate(1, 9), TestCoordinate.makeCoordinate(2, 9)));
		assertEquals(MoveResult.OK, game.makeMove(TestCoordinate.makeCoordinate(1, 4), TestCoordinate.makeCoordinate(2, 5)));
		assertEquals(MoveResult.OK, game.makeMove(TestCoordinate.makeCoordinate(2, 9), TestCoordinate.makeCoordinate(2, 6)));
		assertEquals(MoveResult.OK, game.makeMove(TestCoordinate.makeCoordinate(2, 5), TestCoordinate.makeCoordinate(3, 4)));
		assertEquals(MoveResult.OK, game.makeMove(TestCoordinate.makeCoordinate(2, 6), TestCoordinate.makeCoordinate(8, 6)));
		assertEquals(MoveResult.OK, game.makeMove(TestCoordinate.makeCoordinate(1, 6), TestCoordinate.makeCoordinate(2, 5)));
		assertEquals(MoveResult.OK, game.makeMove(TestCoordinate.makeCoordinate(1, 1), TestCoordinate.makeCoordinate(2, 1)));
		assertEquals(MoveResult.OK, game.makeMove(TestCoordinate.makeCoordinate(2, 5), TestCoordinate.makeCoordinate(3, 6)));
		assertEquals(MoveResult.OK, game.makeMove(TestCoordinate.makeCoordinate(2, 1), TestCoordinate.makeCoordinate(2, 6)));
		assertEquals(MoveResult.OK, game.makeMove(TestCoordinate.makeCoordinate(1, 1), TestCoordinate.makeCoordinate(3, 1)));
		assertEquals(MoveResult.OK, game.makeMove(TestCoordinate.makeCoordinate(8, 6), TestCoordinate.makeCoordinate(8, 4)));
		assertEquals(MoveResult.OK, game.makeMove(TestCoordinate.makeCoordinate(1, 9), TestCoordinate.makeCoordinate(3, 9)));
		assertEquals(MoveResult.OK, game.makeMove(TestCoordinate.makeCoordinate(2, 6), TestCoordinate.makeCoordinate(8, 6)));
		assertEquals(MoveResult.OK, game.makeMove(TestCoordinate.makeCoordinate(4, 1), TestCoordinate.makeCoordinate(5, 1)));
		assertEquals(MoveResult.OK, game.makeMove(TestCoordinate.makeCoordinate(8, 4), TestCoordinate.makeCoordinate(8, 3)));
		assertEquals(MoveResult.OK, game.makeMove(TestCoordinate.makeCoordinate(4, 9), TestCoordinate.makeCoordinate(5, 9)));
		assertEquals(MoveResult.OK, game.makeMove(TestCoordinate.makeCoordinate(8, 6), TestCoordinate.makeCoordinate(8, 7)));
		assertEquals(MoveResult.OK, game.makeMove(TestCoordinate.makeCoordinate(4, 7), TestCoordinate.makeCoordinate(5, 7)));
		assertEquals(MoveResult.OK, game.makeMove(TestCoordinate.makeCoordinate(8, 3), TestCoordinate.makeCoordinate(9, 3)));
		assertEquals(MoveResult.OK, game.makeMove(TestCoordinate.makeCoordinate(4, 5), TestCoordinate.makeCoordinate(5, 5)));
		assertEquals(MoveResult.BLACK_WINS, game.makeMove(TestCoordinate.makeCoordinate(8, 7), TestCoordinate.makeCoordinate(10, 7)));
	}
}
