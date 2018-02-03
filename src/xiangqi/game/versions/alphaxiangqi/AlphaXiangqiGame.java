/**
 * 
 */
package xiangqi.game.versions.alphaxiangqi;

import xiangqi.common.MoveResult;
import xiangqi.common.XiangqiColor;
import xiangqi.common.XiangqiCoordinate;
import xiangqi.common.XiangqiGame;
import xiangqi.common.XiangqiPiece;
import xiangqi.common.XiangqiPieceType;

/**
 * @author jacobhackett
 *
 */
public class AlphaXiangqiGame implements XiangqiGame {

	private int moveCount;
	private String illegalMoveMessage;
	
	public AlphaXiangqiGame()
	{
		this.moveCount = 0;
		this.illegalMoveMessage = "";
	}
	
	/*
	 * @see xiangqi.common.XiangqiGame#makeMove(xiangqi.common.XiangqiCoordinate, xiangqi.common.XiangqiCoordinate)
	 */
	@Override
	public MoveResult makeMove(XiangqiCoordinate source, XiangqiCoordinate destination) {
		illegalMoveMessage = "";
        return !isMoveValid(source, destination) ? MoveResult.ILLEGAL : moveCount++ == 0 ? MoveResult.OK : MoveResult.RED_WINS;
	}
	
	private boolean isMoveValid(XiangqiCoordinate source, XiangqiCoordinate destination) 
	{
        boolean result = true;
        result = result && source.getRank() == 1 && source.getFile() == 1;
        result = result && destination.getRank() == 1 && destination.getFile() == 2;
        if (!result) {
            illegalMoveMessage = "You cannot make that move";
        }
        return result;
    }

	/*
	 * @see xiangqi.common.XiangqiGame#getMoveMessage()
	 */
	@Override
	public String getMoveMessage() {
		return this.illegalMoveMessage;
	}

	/*
	 * @see xiangqi.common.XiangqiGame#getPieceAt(xiangqi.common.XiangqiCoordinate, xiangqi.common.XiangqiColor)
	 */
	@Override
	public XiangqiPiece getPieceAt(XiangqiCoordinate where, XiangqiColor aspect) {
		return XiangqiPieceImpl.makePiece(XiangqiPieceType.NONE, XiangqiColor.NONE);
	}

}
