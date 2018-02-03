package xiangqi.game.versions.betaxiangqi;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import xiangqi.XiangqiGameFactory;
import xiangqi.common.MoveResult;
import xiangqi.common.XiangqiColor;
import xiangqi.common.XiangqiGame;
import xiangqi.common.XiangqiGameVersion;
import xiangqi.common.XiangqiPiece;
import xiangqi.common.XiangqiPieceType;

public class BetaXiangqiTestCases {
	
	private XiangqiGame game;
	
	@Before
	public void setup() {
		this.game = XiangqiGameFactory.makeXiangqiGame(XiangqiGameVersion.BETA_XQ);
	}
	
	@Test
	public void factoryProducesBetaXiangqiGame() {
		assertNotNull(game);
	}
	
	@Test
	public void redMakesFirstValidMove() {
		assertEquals(MoveResult.OK, game.makeMove(TestCoordinate.makeCoordinate(1, 1), TestCoordinate.makeCoordinate(2, 1)));
	}
	
	@Test
	public void blackMakesValidSecondMove() {
		game.makeMove(TestCoordinate.makeCoordinate(1, 1), TestCoordinate.makeCoordinate(2, 1));
		assertEquals(MoveResult.OK, game.makeMove(TestCoordinate.makeCoordinate(1, 1), TestCoordinate.makeCoordinate(2, 1)));
	}
	
	@Test
	public void getPieceAtCanReturnRedGeneralAtStartOfGameWithRedAspect() {
		final XiangqiPiece p = game.getPieceAt(TestCoordinate.makeCoordinate(1, 3), XiangqiColor.RED);
		assertEquals(XiangqiColor.RED, p.getColor());
		assertEquals(XiangqiPieceType.GENERAL, p.getPieceType());
	}
	
	@Test
	public void getPieceAtCanReturnBlackGeneralAtStartOfGameWithRedAspect() {
		final XiangqiPiece p = game.getPieceAt(TestCoordinate.makeCoordinate(5, 3), XiangqiColor.RED);
		assertEquals(XiangqiColor.BLACK, p.getColor());
		assertEquals(XiangqiPieceType.GENERAL, p.getPieceType());
	}
	
	@Test
	public void getPieceAtCanReturnBlackGeneralAtStartOfGameWithBlackAspect() {
		final XiangqiPiece p = game.getPieceAt(TestCoordinate.makeCoordinate(1, 3), XiangqiColor.BLACK);
		assertEquals(XiangqiColor.BLACK, p.getColor());
		assertEquals(XiangqiPieceType.GENERAL, p.getPieceType());
	}
	
	@Test
	public void getPieceAtCanReturnRedGeneralAtStartOfGameWithBlackAspect() {
		final XiangqiPiece p = game.getPieceAt(TestCoordinate.makeCoordinate(5, 3), XiangqiColor.BLACK);
		assertEquals(XiangqiColor.RED, p.getColor());
		assertEquals(XiangqiPieceType.GENERAL, p.getPieceType());
	}
	
	@Test
	public void getPieceAtCanReturnAdvisorsAtStartOfGameWithRespectiveAspects() {
		final XiangqiPiece red1 = game.getPieceAt(TestCoordinate.makeCoordinate(1, 2), XiangqiColor.RED);
		final XiangqiPiece red2 = game.getPieceAt(TestCoordinate.makeCoordinate(1, 4), XiangqiColor.RED);
		final XiangqiPiece black1 = game.getPieceAt(TestCoordinate.makeCoordinate(1, 2), XiangqiColor.BLACK);
		final XiangqiPiece black2 = game.getPieceAt(TestCoordinate.makeCoordinate(1, 4), XiangqiColor.BLACK);
		
		assertEquals(XiangqiColor.RED, red1.getColor());
		assertEquals(XiangqiPieceType.ADVISOR, red1.getPieceType());
		assertEquals(XiangqiColor.RED, red2.getColor());
		assertEquals(XiangqiPieceType.ADVISOR, red2.getPieceType());
		
		assertEquals(XiangqiColor.BLACK, black1.getColor());
		assertEquals(XiangqiPieceType.ADVISOR, black1.getPieceType());
		assertEquals(XiangqiColor.BLACK, black2.getColor());
		assertEquals(XiangqiPieceType.ADVISOR, black2.getPieceType());
	}
	
	@Test
	public void getPieceAtCanReturnAdvisorsAtStartOfGameWithOppositeAspects() {
		final XiangqiPiece red1 = game.getPieceAt(TestCoordinate.makeCoordinate(5, 2), XiangqiColor.BLACK);
		final XiangqiPiece red2 = game.getPieceAt(TestCoordinate.makeCoordinate(5, 4), XiangqiColor.BLACK);
		final XiangqiPiece black1 = game.getPieceAt(TestCoordinate.makeCoordinate(5, 2), XiangqiColor.RED);
		final XiangqiPiece black2 = game.getPieceAt(TestCoordinate.makeCoordinate(5, 4), XiangqiColor.RED);
		
		assertEquals(XiangqiColor.RED, red1.getColor());
		assertEquals(XiangqiPieceType.ADVISOR, red1.getPieceType());
		assertEquals(XiangqiColor.RED, red2.getColor());
		assertEquals(XiangqiPieceType.ADVISOR, red2.getPieceType());
		
		assertEquals(XiangqiColor.BLACK, black1.getColor());
		assertEquals(XiangqiPieceType.ADVISOR, black1.getPieceType());
		assertEquals(XiangqiColor.BLACK, black2.getColor());
		assertEquals(XiangqiPieceType.ADVISOR, black2.getPieceType());
	}
	
	@Test
	public void getPieceAtCanReturnSoldierAtStartOfGameWithRespectiveAspects() {
		final XiangqiPiece red1 = game.getPieceAt(TestCoordinate.makeCoordinate(2, 3), XiangqiColor.RED);
		final XiangqiPiece black1 = game.getPieceAt(TestCoordinate.makeCoordinate(2, 3), XiangqiColor.BLACK);
		
		assertEquals(XiangqiColor.RED, red1.getColor());
		assertEquals(XiangqiPieceType.SOLDIER, red1.getPieceType());
		
		assertEquals(XiangqiColor.BLACK, black1.getColor());
		assertEquals(XiangqiPieceType.SOLDIER, black1.getPieceType());
	}
	
	@Test
	public void getPieceAtCanReturnSoldierAtStartOfGameWithOppositeAspects() {
		final XiangqiPiece red1 = game.getPieceAt(TestCoordinate.makeCoordinate(4, 3), XiangqiColor.BLACK);
		final XiangqiPiece black1 = game.getPieceAt(TestCoordinate.makeCoordinate(4, 3), XiangqiColor.RED);
		
		assertEquals(XiangqiColor.RED, red1.getColor());
		assertEquals(XiangqiPieceType.SOLDIER, red1.getPieceType());
		
		assertEquals(XiangqiColor.BLACK, black1.getColor());
		assertEquals(XiangqiPieceType.SOLDIER, black1.getPieceType());
	}
	
	@Test
	public void getPieceAtCanReturnChariotsAtStartOfGameWithRespectiveAspects() {
		final XiangqiPiece red1 = game.getPieceAt(TestCoordinate.makeCoordinate(1, 1), XiangqiColor.RED);
		final XiangqiPiece red2 = game.getPieceAt(TestCoordinate.makeCoordinate(1, 5), XiangqiColor.RED);
		final XiangqiPiece black1 = game.getPieceAt(TestCoordinate.makeCoordinate(1, 1), XiangqiColor.BLACK);
		final XiangqiPiece black2 = game.getPieceAt(TestCoordinate.makeCoordinate(1, 5), XiangqiColor.BLACK);
		
		assertEquals(XiangqiColor.RED, red1.getColor());
		assertEquals(XiangqiPieceType.CHARIOT, red1.getPieceType());
		assertEquals(XiangqiColor.RED, red2.getColor());
		assertEquals(XiangqiPieceType.CHARIOT, red2.getPieceType());
		
		assertEquals(XiangqiColor.BLACK, black1.getColor());
		assertEquals(XiangqiPieceType.CHARIOT, black1.getPieceType());
		assertEquals(XiangqiColor.BLACK, black2.getColor());
		assertEquals(XiangqiPieceType.CHARIOT, black2.getPieceType());
	}
	
	@Test
	public void getPieceAtCanReturnChariotsAtStartOfGameWithOppositeAspects() {
		final XiangqiPiece red1 = game.getPieceAt(TestCoordinate.makeCoordinate(5, 1), XiangqiColor.BLACK);
		final XiangqiPiece red2 = game.getPieceAt(TestCoordinate.makeCoordinate(5, 5), XiangqiColor.BLACK);
		final XiangqiPiece black1 = game.getPieceAt(TestCoordinate.makeCoordinate(5, 1), XiangqiColor.RED);
		final XiangqiPiece black2 = game.getPieceAt(TestCoordinate.makeCoordinate(5, 5), XiangqiColor.RED);
		
		assertEquals(XiangqiColor.RED, red1.getColor());
		assertEquals(XiangqiPieceType.CHARIOT, red1.getPieceType());
		assertEquals(XiangqiColor.RED, red2.getColor());
		assertEquals(XiangqiPieceType.CHARIOT, red2.getPieceType());
		
		assertEquals(XiangqiColor.BLACK, black1.getColor());
		assertEquals(XiangqiPieceType.CHARIOT, black1.getPieceType());
		assertEquals(XiangqiColor.BLACK, black2.getColor());
		assertEquals(XiangqiPieceType.CHARIOT, black2.getPieceType());
	}
	
	@Test
	public void getPieceAtReturnsNoneNoneForEmptySquare() {
		final XiangqiPiece emptyRed = game.getPieceAt(TestCoordinate.makeCoordinate(2, 1), XiangqiColor.RED);
		assertEquals(XiangqiColor.NONE, emptyRed.getColor());
		assertEquals(XiangqiPieceType.NONE, emptyRed.getPieceType());
		
		final XiangqiPiece emptyBlack = game.getPieceAt(TestCoordinate.makeCoordinate(2, 1), XiangqiColor.BLACK);
		assertEquals(XiangqiColor.NONE, emptyBlack.getColor());
		assertEquals(XiangqiPieceType.NONE, emptyBlack.getPieceType());
	}
	
	@Test
	public void generalCannotMoveTo23() {
		assertEquals(MoveResult.ILLEGAL, game.makeMove(TestCoordinate.makeCoordinate(1, 3), TestCoordinate.makeCoordinate(2, 3)));
		assertTrue(game.getMoveMessage().length() >= 1);
		assertEquals("The General can only move to 12, 13 and 14", game.getMoveMessage());
	}
	
	@Test
	public void generalCannotMoveTo22() {
		assertEquals(MoveResult.ILLEGAL, game.makeMove(TestCoordinate.makeCoordinate(1, 3), TestCoordinate.makeCoordinate(2, 2)));
		assertTrue(game.getMoveMessage().length() >= 1);
		assertEquals("The General can only move to 12, 13 and 14", game.getMoveMessage());
	}
	
	@Test
	public void generalCannotMoveTo24() {
		assertEquals(MoveResult.ILLEGAL, game.makeMove(TestCoordinate.makeCoordinate(1, 3), TestCoordinate.makeCoordinate(2, 4)));
		assertTrue(game.getMoveMessage().length() >= 1);
		assertEquals("The General can only move to 12, 13 and 14", game.getMoveMessage());
	}
	
	@Test
	public void advisorsCanMoveDiagonally() {
		assertEquals(MoveResult.OK, game.makeMove(TestCoordinate.makeCoordinate(1, 2), TestCoordinate.makeCoordinate(2, 1)));
	}
	
	@Test
	public void advisorsCannotMoveOrthogonally() {
		assertEquals(MoveResult.ILLEGAL, game.makeMove(TestCoordinate.makeCoordinate(1, 2), TestCoordinate.makeCoordinate(2, 2)));
		assertTrue(game.getMoveMessage().length() >= 1);
		assertEquals("The Advisor can only move one space diagonally", game.getMoveMessage());
	}
	
	@Test
	public void cannonsCanMoveUpRanks() {
		assertEquals(MoveResult.OK, game.makeMove(TestCoordinate.makeCoordinate(1, 1), TestCoordinate.makeCoordinate(2, 1)));
	}
	
	@Test
	public void cannonsCannotMoveDiagonally() {
		assertEquals(MoveResult.ILLEGAL, game.makeMove(TestCoordinate.makeCoordinate(1, 1), TestCoordinate.makeCoordinate(2, 2)));
		assertTrue(game.getMoveMessage().length() >= 1);
		assertEquals("The Chariot can only move orthogonally", game.getMoveMessage());
	}
	
	@Test
	public void piecesCannotMoveToASpaceOccupiedByAnAlly() {
		assertEquals(MoveResult.ILLEGAL, game.makeMove(TestCoordinate.makeCoordinate(1, 1), TestCoordinate.makeCoordinate(1, 2)));
		assertTrue(game.getMoveMessage().length() >= 1);
		assertEquals("Ally pieces cannot capture each other", game.getMoveMessage());
	}
	
	@Test
	public void getPieceAtReturnsNoneNoneAfterAPieceMovesFromThatPosition() {
		assertEquals(MoveResult.OK, game.makeMove(TestCoordinate.makeCoordinate(1, 1), TestCoordinate.makeCoordinate(2, 1)));
		final XiangqiPiece p = game.getPieceAt(TestCoordinate.makeCoordinate(1, 1), XiangqiColor.RED);
		assertEquals(XiangqiPieceType.NONE, p.getPieceType());
		assertEquals(XiangqiColor.NONE, p.getColor());
	}
	
	@Test
	public void soldiersCanMoveForwardOneSpace() {
		assertEquals(MoveResult.OK, game.makeMove(TestCoordinate.makeCoordinate(2, 3), TestCoordinate.makeCoordinate(3, 3)));
	}
	
	@Test
	public void soldiersCannotMoveHorizontally() {
		assertEquals(MoveResult.ILLEGAL, game.makeMove(TestCoordinate.makeCoordinate(2, 3), TestCoordinate.makeCoordinate(2, 2)));
		assertTrue(game.getMoveMessage().length() >= 1);
		assertEquals("The Soldier can only move one space forward", game.getMoveMessage());
	}
	
	@Test
	public void soldiersCannotMoveDiagonally() {
		assertEquals(MoveResult.ILLEGAL, game.makeMove(TestCoordinate.makeCoordinate(2, 3), TestCoordinate.makeCoordinate(3, 2)));
		assertTrue(game.getMoveMessage().length() >= 1);
		assertEquals("The Soldier can only move one space forward", game.getMoveMessage());
	}
	
	@Test
	public void soldiersCannotMoveMoreThanOneSpaceForward() {
		assertEquals(MoveResult.ILLEGAL, game.makeMove(TestCoordinate.makeCoordinate(2, 3), TestCoordinate.makeCoordinate(4, 3)));
		assertTrue(game.getMoveMessage().length() >= 1);
		assertEquals("The Soldier can only move one space forward", game.getMoveMessage());
	}
	
	@Test
	public void getPieceAtProperlyReturnsPieceAfterItHasMovedToANewPosition() {
		assertEquals(MoveResult.OK, game.makeMove(TestCoordinate.makeCoordinate(1, 1), TestCoordinate.makeCoordinate(2, 1)));
		final XiangqiPiece p = game.getPieceAt(TestCoordinate.makeCoordinate(2, 1), XiangqiColor.RED);
		assertEquals(XiangqiPieceType.CHARIOT, p.getPieceType());
		assertEquals(XiangqiColor.RED, p.getColor());
	}
	
	@Test
	public void piecesCannotMoveToTheSamePositionTheyOccupy() {
		assertEquals(MoveResult.ILLEGAL, game.makeMove(TestCoordinate.makeCoordinate(1, 1), TestCoordinate.makeCoordinate(1, 1)));
		assertTrue(game.getMoveMessage().length() >= 1);
		assertEquals("Pieces need to move to a different square than they start on", game.getMoveMessage());
	}
	
	@Test
	public void getPieceAtReturnsProperPieceWhenAPieceCapturesAnother() {
		final XiangqiPiece oldPiece = game.getPieceAt(TestCoordinate.makeCoordinate(5, 1), XiangqiColor.RED);
		assertEquals(XiangqiColor.BLACK, oldPiece.getColor());
		assertEquals(XiangqiPieceType.CHARIOT, oldPiece.getPieceType());
		assertEquals(MoveResult.OK, game.makeMove(TestCoordinate.makeCoordinate(1, 1), TestCoordinate.makeCoordinate(5, 1)));
		final XiangqiPiece newPiece = game.getPieceAt(TestCoordinate.makeCoordinate(5, 1), XiangqiColor.RED);
		assertEquals(XiangqiColor.RED, newPiece.getColor());
		assertEquals(XiangqiPieceType.CHARIOT, newPiece.getPieceType());
	}
	
	@Test
	public void blackCannotMoveFirst() {
		assertEquals(MoveResult.ILLEGAL, game.makeMove(TestCoordinate.makeCoordinate(5, 1), TestCoordinate.makeCoordinate(1, 1)));
		assertTrue(game.getMoveMessage().length() >= 1);
		assertEquals("Player can only move pieces of their color", game.getMoveMessage());
	}
	
	@Test
	public void playersTakeTurnsMoving() {
		assertEquals(MoveResult.OK, game.makeMove(TestCoordinate.makeCoordinate(1, 1), TestCoordinate.makeCoordinate(2, 1)));
		assertEquals(MoveResult.OK, game.makeMove(TestCoordinate.makeCoordinate(1, 1), TestCoordinate.makeCoordinate(2, 1)));
		final XiangqiPiece redPiece = game.getPieceAt(TestCoordinate.makeCoordinate(2, 1), XiangqiColor.RED);
		final XiangqiPiece blackPiece = game.getPieceAt(TestCoordinate.makeCoordinate(2, 1), XiangqiColor.BLACK);
		assertEquals(XiangqiColor.RED, redPiece.getColor());
		assertEquals(XiangqiColor.BLACK, blackPiece.getColor());
		assertEquals(MoveResult.OK, game.makeMove(TestCoordinate.makeCoordinate(2, 1), TestCoordinate.makeCoordinate(4, 1)));
	}
	
	@Test
	public void generalCannotMoveToCornersOfFirstRankToLeft() {
		assertEquals(MoveResult.OK, game.makeMove(TestCoordinate.makeCoordinate(1, 1), TestCoordinate.makeCoordinate(3, 1)));
		assertEquals(MoveResult.OK, game.makeMove(TestCoordinate.makeCoordinate(1, 1), TestCoordinate.makeCoordinate(3, 1)));
		assertEquals(MoveResult.OK, game.makeMove(TestCoordinate.makeCoordinate(1, 2), TestCoordinate.makeCoordinate(2, 1)));
		assertEquals(MoveResult.OK, game.makeMove(TestCoordinate.makeCoordinate(1, 4), TestCoordinate.makeCoordinate(2, 5)));
		assertEquals(MoveResult.OK, game.makeMove(TestCoordinate.makeCoordinate(1, 3), TestCoordinate.makeCoordinate(1, 2)));
		assertEquals(MoveResult.OK, game.makeMove(TestCoordinate.makeCoordinate(3, 1), TestCoordinate.makeCoordinate(2, 1)));
		assertEquals(MoveResult.ILLEGAL, game.makeMove(TestCoordinate.makeCoordinate(1, 2), TestCoordinate.makeCoordinate(1, 1)));
		assertTrue(game.getMoveMessage().length() >= 1);
		assertEquals("The General can only move to 12, 13 and 14", game.getMoveMessage());
	}
	
	@Test
	public void generalCannotMoveToCornersOfFirstRankToRight() {
		assertEquals(MoveResult.OK, game.makeMove(TestCoordinate.makeCoordinate(1, 5), TestCoordinate.makeCoordinate(3, 5)));
		assertEquals(MoveResult.OK, game.makeMove(TestCoordinate.makeCoordinate(1, 5), TestCoordinate.makeCoordinate(3, 5)));
		assertEquals(MoveResult.OK, game.makeMove(TestCoordinate.makeCoordinate(1, 4), TestCoordinate.makeCoordinate(2, 5)));
		assertEquals(MoveResult.OK, game.makeMove(TestCoordinate.makeCoordinate(1, 4), TestCoordinate.makeCoordinate(2, 5)));
		assertEquals(MoveResult.OK, game.makeMove(TestCoordinate.makeCoordinate(1, 3), TestCoordinate.makeCoordinate(1, 4)));
		assertEquals(MoveResult.OK, game.makeMove(TestCoordinate.makeCoordinate(3, 5), TestCoordinate.makeCoordinate(3, 4)));
		assertEquals(MoveResult.ILLEGAL, game.makeMove(TestCoordinate.makeCoordinate(1, 4), TestCoordinate.makeCoordinate(1, 5)));
		assertTrue(game.getMoveMessage().length() >= 1);
		assertEquals("The General can only move to 12, 13 and 14", game.getMoveMessage());
	}
	
	@Test
	public void twentiethMoveIsADraw() {
		for(int i = 0; i < 4; i++) {
			assertEquals(MoveResult.OK, game.makeMove(TestCoordinate.makeCoordinate(1, 1), TestCoordinate.makeCoordinate(2, 1)));
			assertEquals(MoveResult.OK, game.makeMove(TestCoordinate.makeCoordinate(1, 1), TestCoordinate.makeCoordinate(2, 1)));
			assertEquals(MoveResult.OK, game.makeMove(TestCoordinate.makeCoordinate(2, 1), TestCoordinate.makeCoordinate(1, 1)));
			assertEquals(MoveResult.OK, game.makeMove(TestCoordinate.makeCoordinate(2, 1), TestCoordinate.makeCoordinate(1, 1)));
		}
		assertEquals(MoveResult.OK, game.makeMove(TestCoordinate.makeCoordinate(1, 1), TestCoordinate.makeCoordinate(2, 1)));
		assertEquals(MoveResult.OK, game.makeMove(TestCoordinate.makeCoordinate(1, 1), TestCoordinate.makeCoordinate(2, 1)));
		assertEquals(MoveResult.OK, game.makeMove(TestCoordinate.makeCoordinate(2, 1), TestCoordinate.makeCoordinate(1, 1)));
		assertEquals(MoveResult.DRAW, game.makeMove(TestCoordinate.makeCoordinate(1, 4), TestCoordinate.makeCoordinate(2, 5)));
	}
	
	@Test
	public void cannonsCannotJumpOverPiecesVerticallyUp() {
		assertEquals(MoveResult.OK, game.makeMove(TestCoordinate.makeCoordinate(1, 1), TestCoordinate.makeCoordinate(2, 1)));
		assertEquals(MoveResult.ILLEGAL, game.makeMove(TestCoordinate.makeCoordinate(1, 5), TestCoordinate.makeCoordinate(5, 5)));
		assertTrue(game.getMoveMessage().length() >= 1);
		assertEquals("Pieces cannot jump over other pieces", game.getMoveMessage());
	}
	
	@Test
	public void cannonsCannotJumpOverPiecesVerticallyDown() {
		assertEquals(MoveResult.OK, game.makeMove(TestCoordinate.makeCoordinate(1, 1), TestCoordinate.makeCoordinate(3, 1)));
		assertEquals(MoveResult.OK, game.makeMove(TestCoordinate.makeCoordinate(1, 1), TestCoordinate.makeCoordinate(3, 1)));
		assertEquals(MoveResult.OK, game.makeMove(TestCoordinate.makeCoordinate(1, 2), TestCoordinate.makeCoordinate(2, 1)));
		assertEquals(MoveResult.OK, game.makeMove(TestCoordinate.makeCoordinate(3, 1), TestCoordinate.makeCoordinate(1, 1)));
		assertEquals(MoveResult.ILLEGAL, game.makeMove(TestCoordinate.makeCoordinate(3, 1), TestCoordinate.makeCoordinate(1, 1)));
		assertTrue(game.getMoveMessage().length() >= 1);
		assertEquals("Pieces cannot jump over other pieces", game.getMoveMessage());
	}
	
	@Test
	public void cannonsCannotJumpOverPiecesHorizontallyToTheRight() {
		assertEquals(MoveResult.OK, game.makeMove(TestCoordinate.makeCoordinate(1, 1), TestCoordinate.makeCoordinate(2, 1)));
		assertEquals(MoveResult.OK, game.makeMove(TestCoordinate.makeCoordinate(1, 1), TestCoordinate.makeCoordinate(3, 1)));
		assertEquals(MoveResult.ILLEGAL, game.makeMove(TestCoordinate.makeCoordinate(2, 1), TestCoordinate.makeCoordinate(2, 5)));
		assertTrue(game.getMoveMessage().length() >= 1);
		assertEquals("Pieces cannot jump over other pieces", game.getMoveMessage());
	}
	
	@Test
	public void cannonsCannotJumpOverPiecesHorizontallyToTheLeft() {
		assertEquals(MoveResult.OK, game.makeMove(TestCoordinate.makeCoordinate(1, 5), TestCoordinate.makeCoordinate(2, 5)));
		assertEquals(MoveResult.OK, game.makeMove(TestCoordinate.makeCoordinate(1, 1), TestCoordinate.makeCoordinate(3, 1)));
		assertEquals(MoveResult.ILLEGAL, game.makeMove(TestCoordinate.makeCoordinate(2, 5), TestCoordinate.makeCoordinate(2, 1)));
		assertTrue(game.getMoveMessage().length() >= 1);
		assertEquals("Pieces cannot jump over other pieces", game.getMoveMessage());
	}
	
	@Test
	public void generalCannotMoveIntoCheckAgainstAChariot() {
		assertEquals(MoveResult.OK, game.makeMove(TestCoordinate.makeCoordinate(1, 4), TestCoordinate.makeCoordinate(2, 5)));
		assertEquals(MoveResult.OK, game.makeMove(TestCoordinate.makeCoordinate(1, 1), TestCoordinate.makeCoordinate(2, 1)));
		assertEquals(MoveResult.OK, game.makeMove(TestCoordinate.makeCoordinate(1, 1), TestCoordinate.makeCoordinate(2, 1)));
		assertEquals(MoveResult.OK, game.makeMove(TestCoordinate.makeCoordinate(2, 1), TestCoordinate.makeCoordinate(2, 2)));
		assertEquals(MoveResult.ILLEGAL, game.makeMove(TestCoordinate.makeCoordinate(1, 3), TestCoordinate.makeCoordinate(1, 4)));
		assertTrue(game.getMoveMessage().length() >= 1);
		assertEquals("The General is in check", game.getMoveMessage());
	}
	
	@Test
	public void generalCannotMoveIntoCheckAgainstAnAdvisor() {
		assertEquals(MoveResult.OK, game.makeMove(TestCoordinate.makeCoordinate(1, 4), TestCoordinate.makeCoordinate(2, 5)));
		assertEquals(MoveResult.OK, game.makeMove(TestCoordinate.makeCoordinate(1, 2), TestCoordinate.makeCoordinate(2, 1)));
		assertEquals(MoveResult.OK, game.makeMove(TestCoordinate.makeCoordinate(2, 5), TestCoordinate.makeCoordinate(3, 4)));
		assertEquals(MoveResult.OK, game.makeMove(TestCoordinate.makeCoordinate(1, 5), TestCoordinate.makeCoordinate(2, 5)));
		assertEquals(MoveResult.OK, game.makeMove(TestCoordinate.makeCoordinate(3, 4), TestCoordinate.makeCoordinate(4, 3)));
		assertEquals(MoveResult.ILLEGAL, game.makeMove(TestCoordinate.makeCoordinate(1, 3), TestCoordinate.makeCoordinate(1, 2)));
		assertTrue(game.getMoveMessage().length() >= 1);
		assertEquals("The General is in check", game.getMoveMessage());
	}
	
	@Test
	public void generalCannotMoveIntoCheckAgainstASoldier() {
		assertEquals(MoveResult.OK, game.makeMove(TestCoordinate.makeCoordinate(1, 4), TestCoordinate.makeCoordinate(2, 5)));
		assertEquals(MoveResult.OK, game.makeMove(TestCoordinate.makeCoordinate(2, 3), TestCoordinate.makeCoordinate(3, 3)));
		assertEquals(MoveResult.OK, game.makeMove(TestCoordinate.makeCoordinate(1, 3), TestCoordinate.makeCoordinate(1, 4)));
		assertEquals(MoveResult.OK, game.makeMove(TestCoordinate.makeCoordinate(3, 3), TestCoordinate.makeCoordinate(4, 3)));
		assertEquals(MoveResult.ILLEGAL, game.makeMove(TestCoordinate.makeCoordinate(1, 4), TestCoordinate.makeCoordinate(1, 3)));
		assertTrue(game.getMoveMessage().length() >= 1);
		assertEquals("The General is in check", game.getMoveMessage());
	}
	
	@Test
	public void generalCannotMoveIntoCheckAgainstTheOtherGeneral() {
		assertEquals(MoveResult.OK, game.makeMove(TestCoordinate.makeCoordinate(1, 4), TestCoordinate.makeCoordinate(2, 5)));
		assertEquals(MoveResult.OK, game.makeMove(TestCoordinate.makeCoordinate(2, 3), TestCoordinate.makeCoordinate(3, 3)));
		assertEquals(MoveResult.OK, game.makeMove(TestCoordinate.makeCoordinate(1, 3), TestCoordinate.makeCoordinate(1, 4)));
		assertEquals(MoveResult.OK, game.makeMove(TestCoordinate.makeCoordinate(3, 3), TestCoordinate.makeCoordinate(4, 3)));
		assertEquals(MoveResult.OK, game.makeMove(TestCoordinate.makeCoordinate(1, 2), TestCoordinate.makeCoordinate(2, 3)));
		assertEquals(MoveResult.OK, game.makeMove(TestCoordinate.makeCoordinate(1, 5), TestCoordinate.makeCoordinate(2, 5)));
		assertEquals(MoveResult.OK, game.makeMove(TestCoordinate.makeCoordinate(2, 3), TestCoordinate.makeCoordinate(1, 2)));
		assertEquals(MoveResult.OK, game.makeMove(TestCoordinate.makeCoordinate(2, 5), TestCoordinate.makeCoordinate(1, 5)));
		assertEquals(MoveResult.ILLEGAL, game.makeMove(TestCoordinate.makeCoordinate(1, 4), TestCoordinate.makeCoordinate(1, 3)));
		assertTrue(game.getMoveMessage().length() >= 1);
		assertEquals("The General is in check", game.getMoveMessage());
	}
	
	@Test
	public void gameIsOverAtCheckmateAndBlackWins() {
		assertEquals(MoveResult.OK, game.makeMove(TestCoordinate.makeCoordinate(1, 4), TestCoordinate.makeCoordinate(2, 5)));
		assertEquals(MoveResult.OK, game.makeMove(TestCoordinate.makeCoordinate(1, 1), TestCoordinate.makeCoordinate(4, 1)));
		assertEquals(MoveResult.OK, game.makeMove(TestCoordinate.makeCoordinate(1, 1), TestCoordinate.makeCoordinate(2, 1)));
		assertEquals(MoveResult.BLACK_WINS, game.makeMove(TestCoordinate.makeCoordinate(4, 1), TestCoordinate.makeCoordinate(5, 1)));
	}
}
