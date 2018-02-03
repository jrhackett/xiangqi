package xiangqi.game.versions.gammaxiangqi;

import static org.junit.Assert.*;

import java.util.concurrent.CompletionException;

import org.junit.Before;
import org.junit.Test;

import xiangqi.XiangqiGameFactory;
import xiangqi.common.MoveResult;
import xiangqi.common.XiangqiColor;
import xiangqi.common.XiangqiGame;
import xiangqi.common.XiangqiGameVersion;
import xiangqi.common.XiangqiPiece;
import xiangqi.common.XiangqiPieceType;
import xiangqi.game.versions.betaxiangqi.TestCoordinate;

public class GetPieceAtTestCases {
	private XiangqiGame game;
	
	@Before
	public void setup() {
		this.game = XiangqiGameFactory.makeXiangqiGame(XiangqiGameVersion.GAMMA_XQ);
	}
	
	@Test
	public void getPieceAtCanReturnRedGeneralAtStartOfGameWithRedAspect() {
		final XiangqiPiece p = game.getPieceAt(TestCoordinate.makeCoordinate(1, 5), XiangqiColor.RED);
		assertEquals(XiangqiColor.RED, p.getColor());
		assertEquals(XiangqiPieceType.GENERAL, p.getPieceType());
	}
	
	@Test
	public void getPieceAtCanReturnBlackGeneralAtStartOfGameWithBlackAspect() {
		final XiangqiPiece p = game.getPieceAt(TestCoordinate.makeCoordinate(1, 5), XiangqiColor.BLACK);
		assertEquals(XiangqiColor.BLACK, p.getColor());
		assertEquals(XiangqiPieceType.GENERAL, p.getPieceType());
	}
	
	@Test
	public void getPieceAtCanReturnRedGeneralAtStartOfGameWithBlackAspect() {
		final XiangqiPiece p = game.getPieceAt(TestCoordinate.makeCoordinate(10, 5), XiangqiColor.BLACK);
		assertEquals(XiangqiColor.RED, p.getColor());
		assertEquals(XiangqiPieceType.GENERAL, p.getPieceType());
	}
	
	@Test
	public void getPieceAtCanReturnAdvisorsAtStartOfGameWithRespectiveAspects() {
		final XiangqiPiece red1 = game.getPieceAt(TestCoordinate.makeCoordinate(1, 4), XiangqiColor.RED);
		final XiangqiPiece red2 = game.getPieceAt(TestCoordinate.makeCoordinate(1, 6), XiangqiColor.RED);
		final XiangqiPiece black1 = game.getPieceAt(TestCoordinate.makeCoordinate(1, 4), XiangqiColor.BLACK);
		final XiangqiPiece black2 = game.getPieceAt(TestCoordinate.makeCoordinate(1, 6), XiangqiColor.BLACK);
		
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
		final XiangqiPiece red1 = game.getPieceAt(TestCoordinate.makeCoordinate(10, 4), XiangqiColor.BLACK);
		final XiangqiPiece red2 = game.getPieceAt(TestCoordinate.makeCoordinate(10, 6), XiangqiColor.BLACK);
		final XiangqiPiece black1 = game.getPieceAt(TestCoordinate.makeCoordinate(10, 4), XiangqiColor.RED);
		final XiangqiPiece black2 = game.getPieceAt(TestCoordinate.makeCoordinate(10, 6), XiangqiColor.RED);
		
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
		final XiangqiPiece red1 = game.getPieceAt(TestCoordinate.makeCoordinate(4, 1), XiangqiColor.RED);
		final XiangqiPiece red2 = game.getPieceAt(TestCoordinate.makeCoordinate(4, 3), XiangqiColor.RED);
		final XiangqiPiece red3 = game.getPieceAt(TestCoordinate.makeCoordinate(4, 5), XiangqiColor.RED);
		final XiangqiPiece red4 = game.getPieceAt(TestCoordinate.makeCoordinate(4, 7), XiangqiColor.RED);
		final XiangqiPiece red5 = game.getPieceAt(TestCoordinate.makeCoordinate(4, 9), XiangqiColor.RED);
		final XiangqiPiece black1 = game.getPieceAt(TestCoordinate.makeCoordinate(4, 1), XiangqiColor.BLACK);
		final XiangqiPiece black2 = game.getPieceAt(TestCoordinate.makeCoordinate(4, 3), XiangqiColor.BLACK);
		final XiangqiPiece black3 = game.getPieceAt(TestCoordinate.makeCoordinate(4, 5), XiangqiColor.BLACK);
		final XiangqiPiece black4 = game.getPieceAt(TestCoordinate.makeCoordinate(4, 7), XiangqiColor.BLACK);
		final XiangqiPiece black5 = game.getPieceAt(TestCoordinate.makeCoordinate(4, 9), XiangqiColor.BLACK);
		
		assertEquals(XiangqiColor.RED, red1.getColor());
		assertEquals(XiangqiPieceType.SOLDIER, red1.getPieceType());
		assertEquals(XiangqiColor.RED, red2.getColor());
		assertEquals(XiangqiPieceType.SOLDIER, red2.getPieceType());
		assertEquals(XiangqiColor.RED, red3.getColor());
		assertEquals(XiangqiPieceType.SOLDIER, red3.getPieceType());
		assertEquals(XiangqiColor.RED, red4.getColor());
		assertEquals(XiangqiPieceType.SOLDIER, red4.getPieceType());
		assertEquals(XiangqiColor.RED, red5.getColor());
		assertEquals(XiangqiPieceType.SOLDIER, red5.getPieceType());
		
		assertEquals(XiangqiColor.BLACK, black1.getColor());
		assertEquals(XiangqiPieceType.SOLDIER, black1.getPieceType());
		assertEquals(XiangqiColor.BLACK, black2.getColor());
		assertEquals(XiangqiPieceType.SOLDIER, black2.getPieceType());
		assertEquals(XiangqiColor.BLACK, black3.getColor());
		assertEquals(XiangqiPieceType.SOLDIER, black3.getPieceType());
		assertEquals(XiangqiColor.BLACK, black4.getColor());
		assertEquals(XiangqiPieceType.SOLDIER, black4.getPieceType());
		assertEquals(XiangqiColor.BLACK, black5.getColor());
		assertEquals(XiangqiPieceType.SOLDIER, black5.getPieceType());
	}
	
	@Test
	public void getPieceAtCanReturnSoldierAtStartOfGameWithOppositeAspects() {
		final XiangqiPiece red1 = game.getPieceAt(TestCoordinate.makeCoordinate(7, 1), XiangqiColor.BLACK);
		final XiangqiPiece red2 = game.getPieceAt(TestCoordinate.makeCoordinate(7, 3), XiangqiColor.BLACK);
		final XiangqiPiece red3 = game.getPieceAt(TestCoordinate.makeCoordinate(7, 5), XiangqiColor.BLACK);
		final XiangqiPiece red4 = game.getPieceAt(TestCoordinate.makeCoordinate(7, 7), XiangqiColor.BLACK);
		final XiangqiPiece red5 = game.getPieceAt(TestCoordinate.makeCoordinate(7, 9), XiangqiColor.BLACK);
		final XiangqiPiece black1 = game.getPieceAt(TestCoordinate.makeCoordinate(7, 1), XiangqiColor.RED);
		final XiangqiPiece black2 = game.getPieceAt(TestCoordinate.makeCoordinate(7, 3), XiangqiColor.RED);
		final XiangqiPiece black3 = game.getPieceAt(TestCoordinate.makeCoordinate(7, 5), XiangqiColor.RED);
		final XiangqiPiece black4 = game.getPieceAt(TestCoordinate.makeCoordinate(7, 7), XiangqiColor.RED);
		final XiangqiPiece black5 = game.getPieceAt(TestCoordinate.makeCoordinate(7, 9), XiangqiColor.RED);
		
		assertEquals(XiangqiColor.RED, red1.getColor());
		assertEquals(XiangqiPieceType.SOLDIER, red1.getPieceType());
		assertEquals(XiangqiColor.RED, red2.getColor());
		assertEquals(XiangqiPieceType.SOLDIER, red2.getPieceType());
		assertEquals(XiangqiColor.RED, red3.getColor());
		assertEquals(XiangqiPieceType.SOLDIER, red3.getPieceType());
		assertEquals(XiangqiColor.RED, red4.getColor());
		assertEquals(XiangqiPieceType.SOLDIER, red4.getPieceType());
		assertEquals(XiangqiColor.RED, red5.getColor());
		assertEquals(XiangqiPieceType.SOLDIER, red5.getPieceType());
		
		assertEquals(XiangqiColor.BLACK, black1.getColor());
		assertEquals(XiangqiPieceType.SOLDIER, black1.getPieceType());
		assertEquals(XiangqiColor.BLACK, black2.getColor());
		assertEquals(XiangqiPieceType.SOLDIER, black2.getPieceType());
		assertEquals(XiangqiColor.BLACK, black3.getColor());
		assertEquals(XiangqiPieceType.SOLDIER, black3.getPieceType());
		assertEquals(XiangqiColor.BLACK, black4.getColor());
		assertEquals(XiangqiPieceType.SOLDIER, black4.getPieceType());
		assertEquals(XiangqiColor.BLACK, black5.getColor());
		assertEquals(XiangqiPieceType.SOLDIER, black5.getPieceType());
	}
	
	@Test
	public void getPieceAtCanReturnChariotsAtStartOfGameWithRespectiveAspects() {
		final XiangqiPiece red1 = game.getPieceAt(TestCoordinate.makeCoordinate(1, 1), XiangqiColor.RED);
		final XiangqiPiece red2 = game.getPieceAt(TestCoordinate.makeCoordinate(1, 9), XiangqiColor.RED);
		final XiangqiPiece black1 = game.getPieceAt(TestCoordinate.makeCoordinate(1, 1), XiangqiColor.BLACK);
		final XiangqiPiece black2 = game.getPieceAt(TestCoordinate.makeCoordinate(1, 9), XiangqiColor.BLACK);
		
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
		final XiangqiPiece red1 = game.getPieceAt(TestCoordinate.makeCoordinate(10, 1), XiangqiColor.BLACK);
		final XiangqiPiece red2 = game.getPieceAt(TestCoordinate.makeCoordinate(10, 9), XiangqiColor.BLACK);
		final XiangqiPiece black1 = game.getPieceAt(TestCoordinate.makeCoordinate(10, 1), XiangqiColor.RED);
		final XiangqiPiece black2 = game.getPieceAt(TestCoordinate.makeCoordinate(10, 9), XiangqiColor.RED);
		
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
	public void getPieceAtReturnsNoneNoneAfterAPieceMovesFromASquare() {
		assertEquals(MoveResult.OK, game.makeMove(TestCoordinate.makeCoordinate(1, 5), TestCoordinate.makeCoordinate(2, 5)));
		final XiangqiPiece emptyRed = game.getPieceAt(TestCoordinate.makeCoordinate(1, 5), XiangqiColor.RED);
		assertEquals(XiangqiColor.NONE, emptyRed.getColor());
		assertEquals(XiangqiPieceType.NONE, emptyRed.getPieceType());
	}
	
	@Test
	public void getPieceAtReturnsPieceAfterItMovesToAnEmptyLocation() {
		assertEquals(MoveResult.OK, game.makeMove(TestCoordinate.makeCoordinate(1, 5), TestCoordinate.makeCoordinate(2, 5)));
		final XiangqiPiece redGeneral = game.getPieceAt(TestCoordinate.makeCoordinate(2, 5), XiangqiColor.RED);
		assertEquals(XiangqiColor.RED, redGeneral.getColor());
		assertEquals(XiangqiPieceType.GENERAL, redGeneral.getPieceType());
	}
	
	@Test
	public void getPieceAtReturnsPieceAfterItMovesToAnOccupiedLocation() {
		assertEquals(MoveResult.OK, game.makeMove(TestCoordinate.makeCoordinate(4, 1), TestCoordinate.makeCoordinate(5, 1)));
		assertEquals(MoveResult.OK, game.makeMove(TestCoordinate.makeCoordinate(4, 9), TestCoordinate.makeCoordinate(5, 9)));
		assertEquals(MoveResult.OK, game.makeMove(TestCoordinate.makeCoordinate(5, 1), TestCoordinate.makeCoordinate(6, 1)));
		final XiangqiPiece redGeneral = game.getPieceAt(TestCoordinate.makeCoordinate(6, 1), XiangqiColor.RED);
		assertEquals(XiangqiColor.RED, redGeneral.getColor());
		assertEquals(XiangqiPieceType.SOLDIER, redGeneral.getPieceType());
	}
	
	@Test(expected=CompletionException.class)
	public void getPieceAtThrowsCompletionExceptionForInvalidCoordinates() {
		game.getPieceAt(TestCoordinate.makeCoordinate(11, 1), XiangqiColor.RED);
	}
}
