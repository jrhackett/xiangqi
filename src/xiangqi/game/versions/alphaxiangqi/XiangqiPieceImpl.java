/**
 * 
 */
package xiangqi.game.versions.alphaxiangqi;

import xiangqi.common.XiangqiColor;
import xiangqi.common.XiangqiPiece;
import xiangqi.common.XiangqiPieceType;

/**
 * @author jacobhackett
 *
 */
public class XiangqiPieceImpl implements XiangqiPiece {

	private final XiangqiPieceType pieceType;
	private final XiangqiColor color;
	
	public static XiangqiPiece makePiece(XiangqiPieceType pieceType, XiangqiColor color)
	{
		return new XiangqiPieceImpl(pieceType, color);
	}
	
	private XiangqiPieceImpl(XiangqiPieceType pieceType, XiangqiColor color)
	{
		this.pieceType = pieceType;
		this.color = color;
	}
	
	/*
	 * @see xiangqi.common.XiangqiPiece#getColor()
	 */
	@Override
	public XiangqiColor getColor() {
		return this.color;
	}

	/*
	 * @see xiangqi.common.XiangqiPiece#getPieceType()
	 */
	@Override
	public XiangqiPieceType getPieceType() {
		return this.pieceType;
	}
}
