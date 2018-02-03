package xiangqi.game.versions.betaxiangqi;

import java.util.List;
import java.util.function.BiPredicate;

import xiangqi.common.MoveResult;
import xiangqi.common.XiangqiColor;
import xiangqi.common.XiangqiCoordinate;
import xiangqi.common.XiangqiGame;
import xiangqi.common.XiangqiPiece;
import xiangqi.common.XiangqiPieceType;
import xiangqi.game.common.XiangqiCoordinateImpl;

/**
 * @author jacobhackett
 * This is the Game class for the beta version of Xiangqi
 */
public class BetaXiangqiGame implements XiangqiGame {
	
	private BetaXiangqiBoard board;
	private String moveMessage;
	private int moveCount;
	private XiangqiColor turn;
	private final int maxMoveCount = 20;
	
	public BetaXiangqiGame() {
		this.board = new BetaXiangqiBoardImpl();
		this.board.setupBoard();
		this.moveMessage = "";
		this.moveCount = 1;
		this.turn = XiangqiColor.RED;
	}
	
	/*
	 * @see xiangqi.common.XiangqiGame#makeMove(xiangqi.common.XiangqiCoordinate, xiangqi.common.XiangqiCoordinate)
	 */
	@Override
	public MoveResult makeMove(XiangqiCoordinate source, XiangqiCoordinate destination) {
		MoveResult result = this.validateMove(source, destination);
		if(result == MoveResult.OK) {
			this.processMove(source, destination);
			this.turn = this.turn == XiangqiColor.RED ? XiangqiColor.BLACK : XiangqiColor.RED;
			
			if(isDraw()) {
				result = MoveResult.DRAW;
			}
			
			if(isGameOver()) {
				result = this.turn == XiangqiColor.RED ? MoveResult.BLACK_WINS : MoveResult.RED_WINS;
			}
			
			this.moveCount++;
		}
		return result;
	}
	
	private MoveResult validateMove(XiangqiCoordinate source, XiangqiCoordinate destination) {
		XiangqiCoordinateImpl newSource = new XiangqiCoordinateImpl(source);
		XiangqiCoordinateImpl newDestination = new XiangqiCoordinateImpl(destination);
		XiangqiPiece currentPiece = this.board.getPieceAt(source, this.turn);
		XiangqiPiece destinationPiece = this.board.getPieceAt(destination, this.turn);
		this.moveMessage = "";
		
		if(movingWrongColorPiece(currentPiece)) {
			this.moveMessage = "Player can only move pieces of their color";
			return MoveResult.ILLEGAL;
		}
		
		if(sameDestinationAsSource(newSource, newDestination)) {
			this.moveMessage = "Pieces need to move to a different square than they start on";
			return MoveResult.ILLEGAL;
		}
		
		final List<BiPredicate<XiangqiCoordinate, XiangqiCoordinate>> validators =
				ValidatorFactory.makeValidators(currentPiece.getPieceType());
		
		for(BiPredicate<XiangqiCoordinate, XiangqiCoordinate> p : validators) {
			if(!p.test(source, destination)) {
				this.moveMessage = MoveMessageFactory.generateMoveMessage(currentPiece.getPieceType());
				return MoveResult.ILLEGAL;
			}
		}
		
		if(tryingToJumpOverAnotherPiece(source, destination, currentPiece)) {
			this.moveMessage = "Pieces cannot jump over other pieces";
			return MoveResult.ILLEGAL;
		}
		
		if(sameTeam(currentPiece, destinationPiece)) {
			this.moveMessage = "Ally pieces cannot capture each other";
			return MoveResult.ILLEGAL;
		}

		if(generalIsInCheck(source, destination)) {
			this.moveMessage = "The General is in check";
			return MoveResult.ILLEGAL;	
		}
		
		return MoveResult.OK;
	}
	
	private void processMove(XiangqiCoordinate source, XiangqiCoordinate destination) {
		this.board.makeMove(source, destination, this.turn);
	}
	
	private boolean movingWrongColorPiece(XiangqiPiece currentPiece) {
		return currentPiece.getColor() != this.turn;
	}
	
	private boolean sameDestinationAsSource(XiangqiCoordinate source, XiangqiCoordinate destination) {
		return source.equals(destination);
	}
	
	private boolean tryingToJumpOverAnotherPiece(XiangqiCoordinate source, XiangqiCoordinate destination,
			XiangqiPiece currentPiece) {
		return currentPiece.getPieceType() == XiangqiPieceType.CHARIOT  
				&& !this.board.isStraightPath(source, destination, this.turn);
	}
	
	private boolean sameTeam(XiangqiPiece currentPiece, XiangqiPiece destinationPiece) {
		return currentPiece.getColor() == destinationPiece.getColor();
	}
	
	private boolean isDraw() {
		return this.moveCount >= this.maxMoveCount;
	}
	
	private boolean generalIsInCheck(XiangqiCoordinate source, XiangqiCoordinate destination) {
		BetaXiangqiBoard tempBoard = this.board.simulateMove(source, destination, this.turn);
		return generalIsFacingChariot(source, destination, tempBoard) || 
				generalIsFacingAdvisor(source, destination, tempBoard) ||
				generalIsFacingSoldier(source, destination, tempBoard) || 
				generalIsFacingGeneral(source, destination, tempBoard);
	}
	
	private boolean generalIsFacingChariot(XiangqiCoordinate source, XiangqiCoordinate destination, BetaXiangqiBoard tempBoard) {
		XiangqiCoordinate generalCoordinate = tempBoard.findGeneral(this.turn);
		XiangqiColor enemyColor = this.turn == XiangqiColor.RED ? XiangqiColor.BLACK : XiangqiColor.RED;
		for(int i = 1; i < 6; i++) {
			for(int j = 1; j < 6; j++) {
				XiangqiPiece current = tempBoard.getPieceAt(XiangqiCoordinateImpl.makeCoordinate(i, j), XiangqiColor.RED);
				if(current.getColor() == enemyColor && current.getPieceType() == XiangqiPieceType.CHARIOT) {
					if(tempBoard.isStraightPath(generalCoordinate, XiangqiCoordinateImpl.makeCoordinate(i, j), this.turn)) {
						return true;
					}
				}
			}
		}
		return false;
	}
	
	private boolean generalIsFacingAdvisor(XiangqiCoordinate source, XiangqiCoordinate destination, BetaXiangqiBoard tempBoard) {
		XiangqiCoordinate generalCoordinate = tempBoard.findGeneral(this.turn);
		XiangqiColor enemyColor = this.turn == XiangqiColor.RED ? XiangqiColor.BLACK : XiangqiColor.RED;
		for(int i = generalCoordinate.getRank() - 1; i <= generalCoordinate.getRank() + 1; i++) {
			for(int j = generalCoordinate.getFile() - 1; j <= generalCoordinate.getFile() + 1; j++) {
				if(i != generalCoordinate.getRank() && j != generalCoordinate.getFile()) {
					XiangqiPiece current = tempBoard.getPieceAt(XiangqiCoordinateImpl.makeCoordinate(i, j), XiangqiColor.RED);
					if(current.getColor() == enemyColor && current.getPieceType() == XiangqiPieceType.ADVISOR) {
						return true;
					}
				}
			}
		}
		return false;
	}
	
	private boolean generalIsFacingSoldier(XiangqiCoordinate source, XiangqiCoordinate destination, BetaXiangqiBoard tempBoard) {
		XiangqiCoordinate generalCoordinate = tempBoard.findGeneral(this.turn);
		XiangqiColor enemyColor = this.turn == XiangqiColor.RED ? XiangqiColor.BLACK : XiangqiColor.RED;
		XiangqiPiece current = tempBoard.getPieceAt(XiangqiCoordinateImpl.makeCoordinate(generalCoordinate.getRank() + 1,
				generalCoordinate.getFile()), XiangqiColor.RED);
		return current.getColor() == enemyColor && current.getPieceType() == XiangqiPieceType.SOLDIER;
	}
	
	private boolean generalIsFacingGeneral(XiangqiCoordinate source, XiangqiCoordinate destination, BetaXiangqiBoard tempBoard) {
		XiangqiColor enemyColor = this.turn == XiangqiColor.RED ? XiangqiColor.BLACK : XiangqiColor.RED;
		XiangqiCoordinate currentGeneralCoordinate = tempBoard.findGeneral(this.turn);
		XiangqiCoordinate otherGeneralCoordinate = tempBoard.findGeneral(enemyColor);
		return tempBoard.isStraightPath(currentGeneralCoordinate, otherGeneralCoordinate, this.turn);
	}
	
	private boolean isGameOver() {
		for(int i = 1; i < 6; i++) {
			for(int j = 1; j < 6; j++) {
				XiangqiPiece current = this.board.getPieceAt(XiangqiCoordinateImpl.makeCoordinate(i, j), this.turn);
				if(current.getPieceType() != XiangqiPieceType.NONE && current.getColor() != XiangqiColor.NONE) {
					for(int a = 1; a < 6; a++) {
						for(int b = 1; b < 6; b++) {
							if(this.validateMove(XiangqiCoordinateImpl.makeCoordinate(i, j), XiangqiCoordinateImpl.makeCoordinate(a, b)) 
									== MoveResult.OK) {
								return false;
							}
						}
					}
				}
			}
		}
		return true;
	}

	/* 
	 * @see xiangqi.common.XiangqiGame#getMoveMessage()
	 */
	@Override
	public String getMoveMessage() {
		return this.moveMessage;
	}

	/*
	 * @see xiangqi.common.XiangqiGame#getPieceAt(xiangqi.common.XiangqiCoordinate, xiangqi.common.XiangqiColor)
	 */
	@Override
	public XiangqiPiece getPieceAt(XiangqiCoordinate where, XiangqiColor aspect) {
		return this.board.getPieceAt(where, aspect);
	}
}
