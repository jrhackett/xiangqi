/**
 * 
 */
package xiangqi.game.versions.betaxiangqi;

import xiangqi.common.XiangqiPieceType;

/**
 * @author jacobhackett
 *
 */
public class MoveMessageFactory {
	public static String generateMoveMessage(XiangqiPieceType pieceType) {
		switch(pieceType) {
			case GENERAL:
				return "The General can only move to 12, 13 and 14";
			case ADVISOR:
				return "The Advisor can only move one space diagonally";
			case SOLDIER:
				return "The Soldier can only move one space forward";
			case CHARIOT:
				return "The Chariot can only move orthogonally";
			default:
				System.out.println("Not yet implemented");
				break;
		}
		return "";
	}
}
