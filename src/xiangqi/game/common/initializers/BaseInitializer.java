package xiangqi.game.common.initializers;

import java.util.HashMap;
import java.util.LinkedList;

import xiangqi.common.XiangqiColor;
import xiangqi.common.XiangqiCoordinate;
import xiangqi.common.XiangqiPiece;
import xiangqi.common.XiangqiPieceType;
import xiangqi.game.common.MoveValidator;
import xiangqi.game.common.MoveValidators;
import xiangqi.game.common.PieceMoveValidators;
import xiangqi.game.common.XiangqiBoard;
import xiangqi.game.common.XiangqiCoordinateImpl;
import xiangqi.game.common.XiangqiPieceImpl;
import xiangqi.game.common.XiangqiState;

public abstract class BaseInitializer {
	protected XiangqiState state;
	protected HashMap<XiangqiPieceType, LinkedList<MoveValidator<XiangqiState, XiangqiCoordinate>>> pieceMoveValidators;
	protected LinkedList<MoveValidator<XiangqiState, XiangqiCoordinate>> moveValidators;
	
	public XiangqiState getState() {
		return this.state;
	}
	
	public HashMap<XiangqiPieceType, LinkedList<MoveValidator<XiangqiState, XiangqiCoordinate>>> getPieceMoveValidators() {
		return this.pieceMoveValidators;
	}
	
	public LinkedList<MoveValidator<XiangqiState, XiangqiCoordinate>> getMoveValidators() {
		return this.moveValidators;
	}
	
	public int getMaxMoveCount() {
		return 40;
	}
	
	/*
	 * initializeState sets up the board with the starting positions for each piece and all the defaults for the state variables
	 */
	protected XiangqiState initializeState() {
		
		XiangqiState state = new XiangqiState();
		HashMap<XiangqiCoordinate, XiangqiPiece> boardMap = new HashMap<XiangqiCoordinate, XiangqiPiece>();
		
		boardMap.put(XiangqiCoordinateImpl.makeCoordinate(1, 5), 
				XiangqiPieceImpl.makePiece(XiangqiPieceType.GENERAL, XiangqiColor.RED));
		boardMap.put(XiangqiCoordinateImpl.makeCoordinate(1, 4), 
				XiangqiPieceImpl.makePiece(XiangqiPieceType.ADVISOR, XiangqiColor.RED));
		boardMap.put(XiangqiCoordinateImpl.makeCoordinate(1, 6), 
				XiangqiPieceImpl.makePiece(XiangqiPieceType.ADVISOR, XiangqiColor.RED));
		boardMap.put(XiangqiCoordinateImpl.makeCoordinate(4, 1), 
				XiangqiPieceImpl.makePiece(XiangqiPieceType.SOLDIER, XiangqiColor.RED));
		boardMap.put(XiangqiCoordinateImpl.makeCoordinate(4, 3), 
				XiangqiPieceImpl.makePiece(XiangqiPieceType.SOLDIER, XiangqiColor.RED));
		boardMap.put(XiangqiCoordinateImpl.makeCoordinate(4, 5), 
				XiangqiPieceImpl.makePiece(XiangqiPieceType.SOLDIER, XiangqiColor.RED));
		boardMap.put(XiangqiCoordinateImpl.makeCoordinate(4, 7), 
				XiangqiPieceImpl.makePiece(XiangqiPieceType.SOLDIER, XiangqiColor.RED));
		boardMap.put(XiangqiCoordinateImpl.makeCoordinate(4, 9), 
				XiangqiPieceImpl.makePiece(XiangqiPieceType.SOLDIER, XiangqiColor.RED));
		boardMap.put(XiangqiCoordinateImpl.makeCoordinate(1, 1), 
				XiangqiPieceImpl.makePiece(XiangqiPieceType.CHARIOT, XiangqiColor.RED));
		boardMap.put(XiangqiCoordinateImpl.makeCoordinate(1, 9), 
				XiangqiPieceImpl.makePiece(XiangqiPieceType.CHARIOT, XiangqiColor.RED));
		boardMap.put(XiangqiCoordinateImpl.makeCoordinate(1, 3), 
				XiangqiPieceImpl.makePiece(XiangqiPieceType.ELEPHANT, XiangqiColor.RED));
		boardMap.put(XiangqiCoordinateImpl.makeCoordinate(1, 7), 
				XiangqiPieceImpl.makePiece(XiangqiPieceType.ELEPHANT, XiangqiColor.RED));
		
		boardMap.put(XiangqiCoordinateImpl.makeCoordinate(10, 5), 
				XiangqiPieceImpl.makePiece(XiangqiPieceType.GENERAL, XiangqiColor.BLACK));
		boardMap.put(XiangqiCoordinateImpl.makeCoordinate(10, 4), 
				XiangqiPieceImpl.makePiece(XiangqiPieceType.ADVISOR, XiangqiColor.BLACK));
		boardMap.put(XiangqiCoordinateImpl.makeCoordinate(10, 6), 
				XiangqiPieceImpl.makePiece(XiangqiPieceType.ADVISOR, XiangqiColor.BLACK));
		boardMap.put(XiangqiCoordinateImpl.makeCoordinate(7, 1), 
				XiangqiPieceImpl.makePiece(XiangqiPieceType.SOLDIER, XiangqiColor.BLACK));
		boardMap.put(XiangqiCoordinateImpl.makeCoordinate(7, 3), 
				XiangqiPieceImpl.makePiece(XiangqiPieceType.SOLDIER, XiangqiColor.BLACK));
		boardMap.put(XiangqiCoordinateImpl.makeCoordinate(7, 5), 
				XiangqiPieceImpl.makePiece(XiangqiPieceType.SOLDIER, XiangqiColor.BLACK));
		boardMap.put(XiangqiCoordinateImpl.makeCoordinate(7, 7), 
				XiangqiPieceImpl.makePiece(XiangqiPieceType.SOLDIER, XiangqiColor.BLACK));
		boardMap.put(XiangqiCoordinateImpl.makeCoordinate(7, 9), 
				XiangqiPieceImpl.makePiece(XiangqiPieceType.SOLDIER, XiangqiColor.BLACK));
		boardMap.put(XiangqiCoordinateImpl.makeCoordinate(10, 9), 
				XiangqiPieceImpl.makePiece(XiangqiPieceType.CHARIOT, XiangqiColor.BLACK));
		boardMap.put(XiangqiCoordinateImpl.makeCoordinate(10, 1), 
				XiangqiPieceImpl.makePiece(XiangqiPieceType.CHARIOT, XiangqiColor.BLACK));
		boardMap.put(XiangqiCoordinateImpl.makeCoordinate(10, 3), 
				XiangqiPieceImpl.makePiece(XiangqiPieceType.ELEPHANT, XiangqiColor.BLACK));
		boardMap.put(XiangqiCoordinateImpl.makeCoordinate(10, 7), 
				XiangqiPieceImpl.makePiece(XiangqiPieceType.ELEPHANT, XiangqiColor.BLACK));
		
		XiangqiBoard board = new XiangqiBoard(boardMap, 10, 9);
		state.board = board;
		state.turn = XiangqiColor.RED;
		state.moveMessage = "";
		state.moveCount = 0;
		return state;
	}
	
	/*
	 * initializePieceMoveValidators sets up all of the validators for piece movement
	 */
	protected HashMap<XiangqiPieceType, LinkedList<MoveValidator<XiangqiState, XiangqiCoordinate>>> initializePieceMoveValidators() {
		this.pieceMoveValidators = new HashMap<XiangqiPieceType, LinkedList<MoveValidator<XiangqiState, XiangqiCoordinate>>>();
		
		LinkedList<MoveValidator<XiangqiState, XiangqiCoordinate>> generalValidators = new LinkedList<MoveValidator<XiangqiState, XiangqiCoordinate>>();
		generalValidators.add(PieceMoveValidators.isOrthogonal);
		generalValidators.add(PieceMoveValidators.isAdjacent);
		generalValidators.add(PieceMoveValidators.isWithinThePalace);
		
		LinkedList<MoveValidator<XiangqiState, XiangqiCoordinate>> advisorValidators = new LinkedList<MoveValidator<XiangqiState, XiangqiCoordinate>>();
		advisorValidators.add(PieceMoveValidators.isDiagonal);
		advisorValidators.add(PieceMoveValidators.isAdjacent);
		advisorValidators.add(PieceMoveValidators.isWithinThePalace);
		
		LinkedList<MoveValidator<XiangqiState, XiangqiCoordinate>> chariotValidators = new LinkedList<MoveValidator<XiangqiState, XiangqiCoordinate>>();
		chariotValidators.add(PieceMoveValidators.isOrthogonal);
		chariotValidators.add(PieceMoveValidators.isOrthogonalStraightLine);
		
		LinkedList<MoveValidator<XiangqiState, XiangqiCoordinate>> elephantValidators = new LinkedList<MoveValidator<XiangqiState, XiangqiCoordinate>>();
		elephantValidators.add(PieceMoveValidators.isDiagonal);
		elephantValidators.add(PieceMoveValidators.isExactlyTwoSpaces);
		elephantValidators.add(PieceMoveValidators.isIllegalJumpForElephant);
		elephantValidators.add(PieceMoveValidators.isIllegallyCrossingTheRiver);
		
		LinkedList<MoveValidator<XiangqiState, XiangqiCoordinate>> soldierValidators = new LinkedList<MoveValidator<XiangqiState, XiangqiCoordinate>>();
		soldierValidators.add(PieceMoveValidators.isAdjacent);
		soldierValidators.add(PieceMoveValidators.isVerticalOrHorizontalAfterRiver);
		soldierValidators.add(PieceMoveValidators.isIllegallyGoingBackwards);
		
		pieceMoveValidators.put(XiangqiPieceType.GENERAL, generalValidators);
		pieceMoveValidators.put(XiangqiPieceType.ADVISOR, advisorValidators);
		pieceMoveValidators.put(XiangqiPieceType.CHARIOT, chariotValidators);
		pieceMoveValidators.put(XiangqiPieceType.ELEPHANT, elephantValidators);
		pieceMoveValidators.put(XiangqiPieceType.SOLDIER, soldierValidators);
		
		return pieceMoveValidators;
	}
	
	/*
	 * initializeMoveValidators sets up all the validators for general game rules
	 */
	protected LinkedList<MoveValidator<XiangqiState, XiangqiCoordinate>> initializeMoveValidators() {
		this.moveValidators = new LinkedList<MoveValidator<XiangqiState, XiangqiCoordinate>>();
		moveValidators.add(MoveValidators.isCorrectColor);
		moveValidators.add(MoveValidators.areDifferentTeams);
		moveValidators.add(MoveValidators.isGeneralInCheck);
		return moveValidators;
	}
}
