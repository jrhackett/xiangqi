
package xiangqi.game.common;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.concurrent.CompletionException;

import xiangqi.common.*;

/**
 * @author jacobhackett
 *
 */
public class BaseXiangqiGame implements XiangqiGame {

	/*
	 * This game holds the current state of the game, a maxMoveCount, and sets of validators
	 * The validators ensure that a move in valid before making it
	 * The maxMoveCount is the number of moves before we assign the match a draw
	 */
	protected XiangqiState state;
	protected HashMap<XiangqiPieceType, LinkedList<MoveValidator<XiangqiState, XiangqiCoordinate>>> pieceMoveValidators;
	protected LinkedList<MoveValidator<XiangqiState, XiangqiCoordinate>> moveValidators;
	protected int maxMoveCount;
	
	public BaseXiangqiGame(XiangqiState state, 
			HashMap<XiangqiPieceType, LinkedList<MoveValidator<XiangqiState, XiangqiCoordinate>>> pieceMoveValidators,
			LinkedList<MoveValidator<XiangqiState, XiangqiCoordinate>> moveValidators,
			int maxMoveCount) {
		this.state = state;
		this.pieceMoveValidators = pieceMoveValidators;
		this.moveValidators = moveValidators;
		this.maxMoveCount = maxMoveCount;
	}
	
	/* 
	 * @see xiangqi.common.XiangqiGame#makeMove(xiangqi.common.XiangqiCoordinate, xiangqi.common.XiangqiCoordinate)
	 */
	@Override
	public MoveResult makeMove(XiangqiCoordinate source, XiangqiCoordinate destination) {
		if(checkBounds(source) || checkBounds(destination)) {
			return MoveResult.ILLEGAL;
		}
		
		XiangqiCoordinate newSource = XiangqiCoordinateImpl.makeCoordinate(source, this.state.turn);
		XiangqiCoordinate newDestination = XiangqiCoordinateImpl.makeCoordinate(destination, this.state.turn);
		
		MoveResult result = validateMove(newSource, newDestination);
		
		if(result == MoveResult.OK) {
			this.state.board.makeMove(newSource, newDestination);
			updateGameState();
			
			if(isDraw()) {
				return MoveResult.DRAW;
			}
			
			if(isGameOver()) {
				result = state.turn == XiangqiColor.RED ? MoveResult.BLACK_WINS : MoveResult.RED_WINS;
			}
		}
	
		return result;
	}

	/* 
	 * @see xiangqi.common.XiangqiGame#getMoveMessage()
	 */
	@Override
	public String getMoveMessage() {
		return this.state.moveMessage;
	}

	/* (non-Javadoc)
	 * @see xiangqi.common.XiangqiGame#getPieceAt(xiangqi.common.XiangqiCoordinate, xiangqi.common.XiangqiColor)
	 */
	@Override
	public XiangqiPiece getPieceAt(XiangqiCoordinate where, XiangqiColor aspect) {
		if(checkBounds(where))
			throw new CompletionException("Invalid coordinates", new InvalidCoordinatesException());
		return this.state.board.getPieceAt(XiangqiCoordinateImpl.makeCoordinate(where, aspect));
	}
	
	/**
	 * validateMove goes through all of the validators with the source and destination ensure it's a valid move
	 * @param newSource the source coordinate
	 * @param newDestination the destination coordinate
	 * @return MoveResult to be used within makeMove
	 */
	protected MoveResult validateMove(XiangqiCoordinate newSource, XiangqiCoordinate newDestination) {
		
		for(MoveValidator<XiangqiState, XiangqiCoordinate> m : moveValidators) {
			if(!(m.apply(this.state, newSource, newDestination))) return MoveResult.ILLEGAL;
		}
		
		for(MoveValidator<XiangqiState, XiangqiCoordinate> m : pieceMoveValidators.get(this.state.board.getPieceAt(newSource).getPieceType())) {
			if(!(m.apply(this.state, newSource, newDestination))) return MoveResult.ILLEGAL;
		}
		
		return MoveResult.OK;
	}
	
	/**
	 * checkBounds makes sure that the given coordinate is within the board's constraints
	 * @param where the coordinate to check
	 * @return true if it is in the board, false if not
	 */
	protected boolean checkBounds(XiangqiCoordinate where) {
		return where.getRank() > state.board.getMaxRank() || where.getRank() < 1 
				|| where.getFile() > state.board.getMaxFile() || where.getFile() < 1;
	}
	
	/**
	 * updateGameState updates the variables necessary to change a move to the next move
	 */
	protected void updateGameState() {
		state.turn = state.turn == XiangqiColor.RED ? XiangqiColor.BLACK : XiangqiColor.RED;
		state.moveCount++;
	}
	
	/**
	 * isDraw checks to see if the game is a draw
	 * @return true if the game is a draw, false if not
	 */
	protected boolean isDraw() {
		return this.maxMoveCount > 0 && state.moveCount >= this.maxMoveCount;
	}
	
	/**
	 * isGameOver checks to see if there are any valid moves left on the board for any piece
	 * if there are no valid moves, the game is over
	 * @return true if the game is over, false if not
	 */
	protected boolean isGameOver() {
		for(int i = 1; i <= state.board.getMaxRank(); i++) {
			for(int j = 1; j <= state.board.getMaxFile(); j++) {
				XiangqiPiece current = state.board.getPieceAt(XiangqiCoordinateImpl.makeCoordinate(i, j));
				if(current.getPieceType() != XiangqiPieceType.NONE && current.getColor() != XiangqiColor.NONE) {
					for(int a = 1; a <= state.board.getMaxRank(); a++) {
						for(int b = 1; b <= state.board.getMaxFile(); b++) {
							if(this.validateMove(XiangqiCoordinateImpl.makeCoordinate(i, j), 
									XiangqiCoordinateImpl.makeCoordinate(a, b)) == MoveResult.OK) {
								return false;
							}
						}
					}
				}
			}
		}
		return true;
	}
}
