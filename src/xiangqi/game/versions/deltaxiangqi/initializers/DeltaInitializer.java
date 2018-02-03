package xiangqi.game.versions.deltaxiangqi.initializers;

import java.util.HashMap;
import java.util.LinkedList;

import xiangqi.common.XiangqiColor;
import xiangqi.common.XiangqiCoordinate;
import xiangqi.common.XiangqiPieceType;
import xiangqi.game.common.MoveValidator;
import xiangqi.game.common.PieceMoveValidators;
import xiangqi.game.common.XiangqiCoordinateImpl;
import xiangqi.game.common.XiangqiPieceImpl;
import xiangqi.game.common.XiangqiState;
import xiangqi.game.common.initializers.BaseInitializer;

public class DeltaInitializer extends BaseInitializer {
	
	public DeltaInitializer() {
		this.state = initializeState();
		this.pieceMoveValidators = initializePieceMoveValidators();
		this.moveValidators = initializeMoveValidators();
	}
	
	public int getMaxMoveCount() {
		return 0;
	}
	
	/*
	 * initializeState sets up the board with the starting positions for each piece and all the defaults for the state variables
	 */
	protected XiangqiState initializeState() {
		
		XiangqiState state = super.initializeState();
		
		state.board.putPiece(XiangqiCoordinateImpl.makeCoordinate(1, 2), 
				XiangqiPieceImpl.makePiece(XiangqiPieceType.HORSE, XiangqiColor.RED));
		state.board.putPiece(XiangqiCoordinateImpl.makeCoordinate(1, 8), 
				XiangqiPieceImpl.makePiece(XiangqiPieceType.HORSE, XiangqiColor.RED));
		state.board.putPiece(XiangqiCoordinateImpl.makeCoordinate(3, 2), 
				XiangqiPieceImpl.makePiece(XiangqiPieceType.CANNON, XiangqiColor.RED));
		state.board.putPiece(XiangqiCoordinateImpl.makeCoordinate(3, 8), 
				XiangqiPieceImpl.makePiece(XiangqiPieceType.CANNON, XiangqiColor.RED));
		
		state.board.putPiece(XiangqiCoordinateImpl.makeCoordinate(10, 2), 
				XiangqiPieceImpl.makePiece(XiangqiPieceType.HORSE, XiangqiColor.BLACK));
		state.board.putPiece(XiangqiCoordinateImpl.makeCoordinate(10, 8), 
				XiangqiPieceImpl.makePiece(XiangqiPieceType.HORSE, XiangqiColor.BLACK));
		state.board.putPiece(XiangqiCoordinateImpl.makeCoordinate(8, 2), 
				XiangqiPieceImpl.makePiece(XiangqiPieceType.CANNON, XiangqiColor.BLACK));
		state.board.putPiece(XiangqiCoordinateImpl.makeCoordinate(8, 8), 
				XiangqiPieceImpl.makePiece(XiangqiPieceType.CANNON, XiangqiColor.BLACK));
		
		return state;
	}
	
	/*
	 * initializePieceMoveValidators sets up all of the validators for piece movement
	 */
	protected HashMap<XiangqiPieceType, LinkedList<MoveValidator<XiangqiState, XiangqiCoordinate>>> initializePieceMoveValidators() {
		
		this.pieceMoveValidators = super.initializePieceMoveValidators();
		
		LinkedList<MoveValidator<XiangqiState, XiangqiCoordinate>> cannonValidators = new LinkedList<MoveValidator<XiangqiState, XiangqiCoordinate>>();
		cannonValidators.add(PieceMoveValidators.isOrthogonal);
		cannonValidators.add(PieceMoveValidators.isLegalCaptureForCannon);
		
		LinkedList<MoveValidator<XiangqiState, XiangqiCoordinate>> horseValidators = new LinkedList<MoveValidator<XiangqiState, XiangqiCoordinate>>();
		horseValidators.add(PieceMoveValidators.isInAnLShape);
		horseValidators.add(PieceMoveValidators.isClearPathForHorse);
		
		this.pieceMoveValidators.put(XiangqiPieceType.CANNON, cannonValidators);
		this.pieceMoveValidators.put(XiangqiPieceType.HORSE, horseValidators);
		
		return pieceMoveValidators;
	}
}
