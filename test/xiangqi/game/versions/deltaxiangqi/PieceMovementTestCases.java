package xiangqi.game.versions.deltaxiangqi;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import xiangqi.XiangqiGameFactory;
import xiangqi.common.MoveResult;
import xiangqi.common.XiangqiGame;
import xiangqi.common.XiangqiGameVersion;

public class PieceMovementTestCases {
	private XiangqiGame game;
	
	@Before
	public void setup() {
		this.game = XiangqiGameFactory.makeXiangqiGame(XiangqiGameVersion.DELTA_XQ);
	}
	
	@Test
	public void generalCanMoveForwardOneSpace() {
		assertEquals(MoveResult.OK, game.makeMove(TestCoordinate.makeCoordinate(1, 5), TestCoordinate.makeCoordinate(2, 5)));
	}
	
	@Test
	public void generalCannotMoveTwoSpacesForward() {
		assertEquals(MoveResult.ILLEGAL, game.makeMove(TestCoordinate.makeCoordinate(1, 5), TestCoordinate.makeCoordinate(3, 5)));
		assertTrue(game.getMoveMessage().length() >= 1);
		assertEquals("This piece can only move one square at a time", game.getMoveMessage());
	}
	
	@Test
	public void generalCannotMoveDiagonally() {
		assertEquals(MoveResult.ILLEGAL, game.makeMove(TestCoordinate.makeCoordinate(1, 5), TestCoordinate.makeCoordinate(2, 4)));
		assertTrue(game.getMoveMessage().length() >= 1);
		assertEquals("This piece must move orthogonally", game.getMoveMessage());
	}
	
	@Test
	public void generalCannotLeaveThePalace() {
		assertEquals(MoveResult.OK, game.makeMove(TestCoordinate.makeCoordinate(1, 5), TestCoordinate.makeCoordinate(2, 5)));
		assertEquals(MoveResult.OK, game.makeMove(TestCoordinate.makeCoordinate(1, 5), TestCoordinate.makeCoordinate(2, 5)));
		assertEquals(MoveResult.OK, game.makeMove(TestCoordinate.makeCoordinate(2, 5), TestCoordinate.makeCoordinate(2, 4)));
		assertEquals(MoveResult.OK, game.makeMove(TestCoordinate.makeCoordinate(2, 5), TestCoordinate.makeCoordinate(2, 4)));
		assertEquals(MoveResult.ILLEGAL, game.makeMove(TestCoordinate.makeCoordinate(2, 4), TestCoordinate.makeCoordinate(2, 3)));
		assertTrue(game.getMoveMessage().length() >= 1);
		assertEquals("This piece must stay within the palace", game.getMoveMessage());
	}
	
	@Test
	public void advisorsCanMoveDiagonally() {
		assertEquals(MoveResult.OK, game.makeMove(TestCoordinate.makeCoordinate(1, 4), TestCoordinate.makeCoordinate(2, 5)));
	}
	
	@Test
	public void advisorsMustMoveToAnAdjacentSpace() {
		assertEquals(MoveResult.ILLEGAL, game.makeMove(TestCoordinate.makeCoordinate(1, 4), TestCoordinate.makeCoordinate(3, 6)));
		assertTrue(game.getMoveMessage().length() >= 1);
		assertEquals("This piece can only move one square at a time", game.getMoveMessage());
	}
	
	@Test
	public void advisorsCannotLeaveThePalace() {
		assertEquals(MoveResult.ILLEGAL, game.makeMove(TestCoordinate.makeCoordinate(1, 4), TestCoordinate.makeCoordinate(2, 3)));
		assertTrue(game.getMoveMessage().length() >= 1);
		assertEquals("This piece must stay within the palace", game.getMoveMessage());
	}
	
	@Test
	public void chariotsCanMoveVertically() {
		assertEquals(MoveResult.OK, game.makeMove(TestCoordinate.makeCoordinate(1, 1), TestCoordinate.makeCoordinate(2, 1)));
	}
	
	@Test
	public void chariotsCanMoveHorizontally() {
		assertEquals(MoveResult.OK, game.makeMove(TestCoordinate.makeCoordinate(1, 1), TestCoordinate.makeCoordinate(2, 1)));
		assertEquals(MoveResult.OK, game.makeMove(TestCoordinate.makeCoordinate(1, 1), TestCoordinate.makeCoordinate(2, 1)));
		assertEquals(MoveResult.OK, game.makeMove(TestCoordinate.makeCoordinate(2, 1), TestCoordinate.makeCoordinate(2, 2)));
	}
	
	@Test
	public void chariotsCannotMoveDiagonally() {
		assertEquals(MoveResult.ILLEGAL, game.makeMove(TestCoordinate.makeCoordinate(1, 1), TestCoordinate.makeCoordinate(2, 2)));
		assertTrue(game.getMoveMessage().length() >= 1);
		assertEquals("This piece must move orthogonally", game.getMoveMessage());
	}
	
	@Test
	public void chariotsCannotJumpOverOtherPieces() {
		assertEquals(MoveResult.ILLEGAL, game.makeMove(TestCoordinate.makeCoordinate(1, 1), TestCoordinate.makeCoordinate(10, 1)));
		assertTrue(game.getMoveMessage().length() >= 1);
		assertEquals("This piece cannot jump over other pieces", game.getMoveMessage());
	}
	
	@Test
	public void elephantsCanMoveDiagonally() {
		assertEquals(MoveResult.OK, game.makeMove(TestCoordinate.makeCoordinate(1, 3), TestCoordinate.makeCoordinate(3, 1)));
	}
	
	@Test
	public void elephantsCannotMoveLessThanTwoSpaces() {
		assertEquals(MoveResult.ILLEGAL, game.makeMove(TestCoordinate.makeCoordinate(1, 3), TestCoordinate.makeCoordinate(2, 2)));
		assertTrue(game.getMoveMessage().length() >= 1);
		assertEquals("This piece must move exactly two spaces", game.getMoveMessage());
	}
	
	@Test
	public void elephantsCannotMoveMoreThanTwoSpaces() {
		assertEquals(MoveResult.ILLEGAL, game.makeMove(TestCoordinate.makeCoordinate(1, 3), TestCoordinate.makeCoordinate(4, 6)));
		assertTrue(game.getMoveMessage().length() >= 1);
		assertEquals("This piece must move exactly two spaces", game.getMoveMessage());
		assertEquals(MoveResult.ILLEGAL, game.makeMove(TestCoordinate.makeCoordinate(1, 3), TestCoordinate.makeCoordinate(5, 7)));
		assertTrue(game.getMoveMessage().length() >= 1);
		assertEquals("This piece must move exactly two spaces", game.getMoveMessage());
		assertEquals(MoveResult.ILLEGAL, game.makeMove(TestCoordinate.makeCoordinate(1, 3), TestCoordinate.makeCoordinate(6, 8)));
		assertTrue(game.getMoveMessage().length() >= 1);
		assertEquals("This piece must move exactly two spaces", game.getMoveMessage());
		assertEquals(MoveResult.ILLEGAL, game.makeMove(TestCoordinate.makeCoordinate(1, 3), TestCoordinate.makeCoordinate(7, 9)));
		assertTrue(game.getMoveMessage().length() >= 1);
		assertEquals("This piece must move exactly two spaces", game.getMoveMessage());
	}
	
	@Test
	public void elephantsCannotJumpOverOtherPieces() {
		assertEquals(MoveResult.OK, game.makeMove(TestCoordinate.makeCoordinate(1, 5), TestCoordinate.makeCoordinate(2, 5)));
		assertEquals(MoveResult.OK, game.makeMove(TestCoordinate.makeCoordinate(1, 5), TestCoordinate.makeCoordinate(2, 5)));
		assertEquals(MoveResult.OK, game.makeMove(TestCoordinate.makeCoordinate(2, 5), TestCoordinate.makeCoordinate(2, 4)));
		assertEquals(MoveResult.OK, game.makeMove(TestCoordinate.makeCoordinate(1, 3), TestCoordinate.makeCoordinate(3, 1)));
		assertEquals(MoveResult.ILLEGAL, game.makeMove(TestCoordinate.makeCoordinate(1, 3), TestCoordinate.makeCoordinate(3, 5)));
		assertTrue(game.getMoveMessage().length() >= 1);
		assertEquals("This piece cannot jump over other pieces", game.getMoveMessage());
	}
	
	@Test
	public void elephantsCannotCrossTheRiver() {
		assertEquals(MoveResult.OK, game.makeMove(TestCoordinate.makeCoordinate(1, 1), TestCoordinate.makeCoordinate(2, 1)));
		assertEquals(MoveResult.OK, game.makeMove(TestCoordinate.makeCoordinate(1, 3), TestCoordinate.makeCoordinate(3, 5)));
		assertEquals(MoveResult.OK, game.makeMove(TestCoordinate.makeCoordinate(2, 1), TestCoordinate.makeCoordinate(1, 1)));
		assertEquals(MoveResult.OK, game.makeMove(TestCoordinate.makeCoordinate(3, 5), TestCoordinate.makeCoordinate(5, 3)));
		assertEquals(MoveResult.OK, game.makeMove(TestCoordinate.makeCoordinate(1, 1), TestCoordinate.makeCoordinate(2, 1)));
		assertEquals(MoveResult.ILLEGAL, game.makeMove(TestCoordinate.makeCoordinate(5, 3), TestCoordinate.makeCoordinate(7, 5)));
		assertTrue(game.getMoveMessage().length() >= 1);
		assertEquals("This piece cannot cross the river", game.getMoveMessage());
	}
	
	@Test
	public void soldiersCanMoveForward() {
		assertEquals(MoveResult.OK, game.makeMove(TestCoordinate.makeCoordinate(4, 1), TestCoordinate.makeCoordinate(5, 1)));
	}
	
	@Test
	public void soldiersCannotMoveMoreThanOneSpace() {
		assertEquals(MoveResult.ILLEGAL, game.makeMove(TestCoordinate.makeCoordinate(4, 1), TestCoordinate.makeCoordinate(6, 1)));
		assertTrue(game.getMoveMessage().length() >= 1);
		assertEquals("This piece can only move one square at a time", game.getMoveMessage());
	}
	
	@Test
	public void soldiersCanMoveHorizontalAfterTheRiver() {
		assertEquals(MoveResult.OK, game.makeMove(TestCoordinate.makeCoordinate(4, 1), TestCoordinate.makeCoordinate(5, 1)));
		assertEquals(MoveResult.OK, game.makeMove(TestCoordinate.makeCoordinate(4, 1), TestCoordinate.makeCoordinate(5, 1)));
		assertEquals(MoveResult.OK, game.makeMove(TestCoordinate.makeCoordinate(5, 1), TestCoordinate.makeCoordinate(6, 1)));
		assertEquals(MoveResult.OK, game.makeMove(TestCoordinate.makeCoordinate(1, 5), TestCoordinate.makeCoordinate(2, 5)));
		assertEquals(MoveResult.OK, game.makeMove(TestCoordinate.makeCoordinate(6, 1), TestCoordinate.makeCoordinate(6, 2)));
	}
	
	@Test
	public void soldiersCannotMoveHorizontalBeforeCrossingTheRiver() {
		assertEquals(MoveResult.ILLEGAL, game.makeMove(TestCoordinate.makeCoordinate(4, 1), TestCoordinate.makeCoordinate(4, 2)));
		assertTrue(game.getMoveMessage().length() >= 1);
		assertEquals("This piece must move vertically", game.getMoveMessage());
	}
	
	@Test
	public void soldiersCannotMoveBackwards() {
		assertEquals(MoveResult.ILLEGAL, game.makeMove(TestCoordinate.makeCoordinate(4, 1), TestCoordinate.makeCoordinate(3, 1)));
		assertTrue(game.getMoveMessage().length() >= 1);
		assertEquals("This piece cannot move backwards", game.getMoveMessage());
	}
	
	public void testIllegalCoordinates() {
		assertEquals(MoveResult.ILLEGAL, game.makeMove(TestCoordinate.makeCoordinate(11, 1), TestCoordinate.makeCoordinate(10, 1)));
	}
	
	@Test
	public void cannonsCanMoveHorizontally() {
		assertEquals(MoveResult.OK, game.makeMove(TestCoordinate.makeCoordinate(3, 2), TestCoordinate.makeCoordinate(3, 4)));
	}
	
	@Test
	public void cannonsCanMoveVertically() {
		assertEquals(MoveResult.OK, game.makeMove(TestCoordinate.makeCoordinate(3, 2), TestCoordinate.makeCoordinate(5, 2)));
	}
	
	@Test
	public void cannonsCannotMoveDiagonally() {
		assertEquals(MoveResult.ILLEGAL, game.makeMove(TestCoordinate.makeCoordinate(3, 2), TestCoordinate.makeCoordinate(2, 1)));
		assertTrue(game.getMoveMessage().length() >= 1);
		assertEquals("This piece must move orthogonally", game.getMoveMessage());
	}
	
	@Test
	public void horsesMoveInAnLShape() {
		assertEquals(MoveResult.OK, game.makeMove(TestCoordinate.makeCoordinate(1, 2), TestCoordinate.makeCoordinate(3, 1)));
	}
	
	@Test
	public void horsesCannotJumpOverPieces() {
		assertEquals(MoveResult.OK, game.makeMove(TestCoordinate.makeCoordinate(1, 2), TestCoordinate.makeCoordinate(3, 1)));
		assertEquals(MoveResult.OK, game.makeMove(TestCoordinate.makeCoordinate(1, 2), TestCoordinate.makeCoordinate(3, 1)));
		assertEquals(MoveResult.ILLEGAL, game.makeMove(TestCoordinate.makeCoordinate(3, 1), TestCoordinate.makeCoordinate(2, 3)));
		assertTrue(game.getMoveMessage().length() >= 1);
		assertEquals("This piece may not jump over other pieces that are orthogonally adjacent to it", game.getMoveMessage());
		assertEquals(MoveResult.ILLEGAL, game.makeMove(TestCoordinate.makeCoordinate(3, 1), TestCoordinate.makeCoordinate(5, 2)));
		assertTrue(game.getMoveMessage().length() >= 1);
		assertEquals("This piece may not jump over other pieces that are orthogonally adjacent to it", game.getMoveMessage());
	}
	
	@Test
	public void cannonsCanJumpOverPieceToCaptureOpponentPiece() {
		assertEquals(MoveResult.OK, game.makeMove(TestCoordinate.makeCoordinate(3, 2), TestCoordinate.makeCoordinate(3, 1)));
		assertEquals(MoveResult.OK, game.makeMove(TestCoordinate.makeCoordinate(1, 2), TestCoordinate.makeCoordinate(3, 1)));
		assertEquals(MoveResult.OK, game.makeMove(TestCoordinate.makeCoordinate(3, 1), TestCoordinate.makeCoordinate(7, 1)));
	}
	
	@Test
	public void cannonsCannotCapturePieceUnlessJumpingOverOne() {
		assertEquals(MoveResult.ILLEGAL, game.makeMove(TestCoordinate.makeCoordinate(3, 2), TestCoordinate.makeCoordinate(8, 2)));
		assertTrue(game.getMoveMessage().length() >= 1);
		assertEquals("This piece may not capture a piece unless it jumps over exactly one other piece", game.getMoveMessage());
	}
	
	@Test
	public void cannonsCannotJumpOverMultiplePieces() {
		assertEquals(MoveResult.OK, game.makeMove(TestCoordinate.makeCoordinate(3, 2), TestCoordinate.makeCoordinate(3, 1)));
		assertEquals(MoveResult.OK, game.makeMove(TestCoordinate.makeCoordinate(3, 2), TestCoordinate.makeCoordinate(3, 1)));
		assertEquals(MoveResult.ILLEGAL, game.makeMove(TestCoordinate.makeCoordinate(3, 1), TestCoordinate.makeCoordinate(10, 1)));
		assertTrue(game.getMoveMessage().length() >= 1);
		assertEquals("This piece may not capture a piece unless it jumps over exactly one other piece", game.getMoveMessage());
	}
	
	@Test
	public void cannonsCannotJumpOverAPieceUnlessItCapturesOnTheOtherSide() {
		assertEquals(MoveResult.OK, game.makeMove(TestCoordinate.makeCoordinate(3, 2), TestCoordinate.makeCoordinate(3, 1)));
		assertEquals(MoveResult.OK, game.makeMove(TestCoordinate.makeCoordinate(3, 2), TestCoordinate.makeCoordinate(3, 1)));
		assertEquals(MoveResult.ILLEGAL, game.makeMove(TestCoordinate.makeCoordinate(3, 1), TestCoordinate.makeCoordinate(5, 1)));
		assertTrue(game.getMoveMessage().length() >= 1);
		assertEquals("This piece may not capture a piece unless it jumps over exactly one other piece", game.getMoveMessage());
	}
}
