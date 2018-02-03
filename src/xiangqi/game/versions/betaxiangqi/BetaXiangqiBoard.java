/**
 * 
 */
package xiangqi.game.versions.betaxiangqi;

import xiangqi.common.XiangqiColor;
import xiangqi.common.XiangqiCoordinate;
import xiangqi.common.XiangqiPiece;

/**
 * @author jacobhackett
 *
 */
public interface BetaXiangqiBoard {
	/**
	 * setup the board in Xiangqi
	 * initialize the board state for the game using whatever data structure
	 * the current implementation calls for
	 */
	public void setupBoard();
	
	/**
	 * getPieceAt gets the XiangqiPiece at a specific coordinate based on the aspect/perspective
	 * @param coordinate the XiangqiCoordinate that we want to look at
	 * @param aspect the perspective of the board we want to look at the board from
	 * @return the piece occupying that space, empty space returns a XiangqiPiece with None, None for the color and piece type
	 */
	public XiangqiPiece getPieceAt(XiangqiCoordinate coordinate, XiangqiColor aspect);
	
	/**
	 * makeMove updates the board state to reflect moving the piece in the from coordinate to the to coordinate
	 * @param from the starting coordinate
	 * @param to the ending coordinate
	 */
	public void makeMove(XiangqiCoordinate from, XiangqiCoordinate to, XiangqiColor turn);

	/**
	 * indicates whether or not there is a straight, unblocked path from source to destination
	 * @param source the starting coordinate
	 * @param destination the ending coordinate
	 * @return
	 */
	public boolean isStraightPath(XiangqiCoordinate source, XiangqiCoordinate destination, XiangqiColor aspect);
	
	/**
	 * findGeneral returns the coordinate for the general of the aspect team
	 * @param aspect the color general to look for
	 * @return the coordinate of that general
	 */
	public XiangqiCoordinate findGeneral(XiangqiColor aspect);
	
	/**
	 * simulateMove creates a new board implementation and processes the given move
	 * @param source the starting coordinate
	 * @param destination the ending coordinate
	 * @param aspect the color
	 * @return the new board with that move processed
	 */
	public BetaXiangqiBoard simulateMove(XiangqiCoordinate source, XiangqiCoordinate destination, XiangqiColor aspect);
}
