/**
 * 
 */
package xiangqi.game.versions.deltaxiangqi;

import java.util.HashMap;
import java.util.LinkedList;

import xiangqi.common.MoveResult;
import xiangqi.common.XiangqiColor;
import xiangqi.common.XiangqiCoordinate;
import xiangqi.common.XiangqiPieceType;
import xiangqi.game.common.BaseXiangqiGame;
import xiangqi.game.common.MoveValidator;
import xiangqi.game.common.XiangqiBoard;
import xiangqi.game.common.XiangqiHistory;
import xiangqi.game.common.XiangqiState;

/**
 * @author jacobhackett
 *
 */
public class DeltaXiangqiGame extends BaseXiangqiGame {

	/*
	 * This is a subclass of the BaseXiangqiGame that adds validation for perpetual moves
	 * The board state history is stored within two data structures, one for each team
	 */
	private XiangqiHistory redHistory;
	private XiangqiHistory blackHistory;
	
	public DeltaXiangqiGame(XiangqiState state,
			HashMap<XiangqiPieceType, LinkedList<MoveValidator<XiangqiState, XiangqiCoordinate>>> pieceMoveValidators,
			LinkedList<MoveValidator<XiangqiState, XiangqiCoordinate>> moveValidators, int maxMoveCount) {
		super(state, pieceMoveValidators, moveValidators, maxMoveCount);
		this.redHistory = new XiangqiHistory();
		this.blackHistory = new XiangqiHistory();
	}
	
	/* 
	 * @see xiangqi.common.XiangqiGame#makeMove(xiangqi.common.XiangqiCoordinate, xiangqi.common.XiangqiCoordinate)
	 */
	@Override
	public MoveResult makeMove(XiangqiCoordinate source, XiangqiCoordinate destination) {
		
		MoveResult result = super.makeMove(source, destination);
		
		if(result == MoveResult.OK) {
			if(isIllegalRepetitionOfMoves()) {
				state.moveMessage = "This move produced illegal repetition of board states";
				return state.turn == XiangqiColor.RED ? MoveResult.RED_WINS : MoveResult.BLACK_WINS;
			}
		}
	
		return result;
	}
	
	private boolean isIllegalRepetitionOfMoves() {
		if(state.turn == XiangqiColor.RED) {
			this.blackHistory.addBoardState(new XiangqiBoard(state.board));
			return this.blackHistory.isIllegalRepetition();
		}
		else {
			this.redHistory.addBoardState(new XiangqiBoard(state.board));
			return this.redHistory.isIllegalRepetition();
		}
	}

}
