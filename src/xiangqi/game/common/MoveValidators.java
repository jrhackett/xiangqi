package xiangqi.game.common;

import xiangqi.common.XiangqiColor;
import xiangqi.common.XiangqiCoordinate;
import xiangqi.common.XiangqiPiece;
import xiangqi.common.XiangqiPieceType;

public class MoveValidators {
	
	/*
	 * isCorrectColor checks to make sure the player is moving their own piece
	 */
	public static MoveValidator<XiangqiState, XiangqiCoordinate> isCorrectColor =
			(state, from, to) -> {
				XiangqiPiece piece = state.board.getPieceAt(from);
				boolean result = piece.getColor() != XiangqiColor.NONE && piece.getColor() == state.turn;
				if(!result)
					state.moveMessage = "Attempting to move from an empty space or moving the opponent's piece.";
				return result;
			};
		
	/*
	 * areDifferentTeams checks to make sure the player isn't trying to capture their own piece
	 */
	public static MoveValidator<XiangqiState, XiangqiCoordinate> areDifferentTeams = 
			(state, from, to) -> {
				XiangqiPiece fromPiece = state.board.getPieceAt(from);
				XiangqiPiece toPiece = state.board.getPieceAt(to);
				boolean result = fromPiece.getColor() != toPiece.getColor();
				if(!result)
					state.moveMessage = "Pieces cannot capture ally pieces";
				return result;
			};
		
	/*
	 * isGeneralInCheck checks if the general is in check, or is in danger of being captured
	 */
	public static MoveValidator<XiangqiState, XiangqiCoordinate> isGeneralInCheck = 
			(state, from, to) -> {
				XiangqiBoard tempBoard = state.board.simulateMove(from, to);
				boolean result = !(generalIsFacingChariot(from, to, tempBoard, state.turn) || 
						generalIsFacingGeneral(from, to, tempBoard, state.turn) || 
						generalIsFacingSoldier(from, to, tempBoard, state.turn) ||
						generalIsFacingHorse(from, to, tempBoard, state.turn) ||
						generalIsFacingCannon(from, to, tempBoard, state.turn));
				if(!result)
					state.moveMessage = "You cannot move into check";
				return result;
			};
			
			
	/*
	 * generalIsFacingChariot is a helper method that checks to see if the general can be killed by an enemy chariot
	 */
	private static boolean generalIsFacingChariot(XiangqiCoordinate source, XiangqiCoordinate destination, XiangqiBoard tempBoard, XiangqiColor turn) {
		XiangqiCoordinate generalCoordinate = tempBoard.findGeneral(turn);
		XiangqiColor enemyColor = turn == XiangqiColor.RED ? XiangqiColor.BLACK : XiangqiColor.RED;
		for(int i = 1; i <= tempBoard.getMaxRank(); i++) {
			for(int j = 1; j <= tempBoard.getMaxFile(); j++) {
				XiangqiPiece current = tempBoard.getPieceAt(XiangqiCoordinateImpl.makeCoordinate(i, j));
				if(current.getColor() == enemyColor && current.getPieceType() == XiangqiPieceType.CHARIOT) {
					if(tempBoard.isStraightPath(generalCoordinate, XiangqiCoordinateImpl.makeCoordinate(i, j))) {
						return true;
					}
				}
			}
		}
		return false;
	}
	
	/*
	 * generalIsFacingGeneral is a helper method that checks to see if the general can be killed by an enemy general
	 */
	private static boolean generalIsFacingGeneral(XiangqiCoordinate source, XiangqiCoordinate destination, XiangqiBoard tempBoard, XiangqiColor turn) {
		XiangqiColor enemyColor = turn == XiangqiColor.RED ? XiangqiColor.BLACK : XiangqiColor.RED;
		XiangqiCoordinate currentGeneralCoordinate = tempBoard.findGeneral(turn);
		XiangqiCoordinate otherGeneralCoordinate = tempBoard.findGeneral(enemyColor);
		return tempBoard.isStraightPath(currentGeneralCoordinate, otherGeneralCoordinate);
	}
	
	/*
	 * generalIsFacingSoldier is a helper method that checks to see if the general can be killed by an enemy soldier
	 */
	private static boolean generalIsFacingSoldier(XiangqiCoordinate source, XiangqiCoordinate destination, XiangqiBoard tempBoard, XiangqiColor turn) {
		int toAdd = turn == XiangqiColor.RED ? 1 : -1;
		XiangqiColor enemyColor = turn == XiangqiColor.RED ? XiangqiColor.BLACK : XiangqiColor.RED;
		XiangqiCoordinate generalCoordinate = tempBoard.findGeneral(turn);
		XiangqiPiece front = tempBoard.getPieceAt(XiangqiCoordinateImpl.makeCoordinate(generalCoordinate.getRank() + toAdd,
				generalCoordinate.getFile()));
		XiangqiPiece left = tempBoard.getPieceAt(XiangqiCoordinateImpl.makeCoordinate(generalCoordinate.getRank(),
				generalCoordinate.getFile() - 1));
		XiangqiPiece right = tempBoard.getPieceAt(XiangqiCoordinateImpl.makeCoordinate(generalCoordinate.getRank(),
				generalCoordinate.getFile() + 1));
		return (front.getColor() == enemyColor && front.getPieceType() == XiangqiPieceType.SOLDIER) || 
				(left.getColor() == enemyColor && left.getPieceType() == XiangqiPieceType.SOLDIER) || 
				(right.getColor() == enemyColor && right.getPieceType() == XiangqiPieceType.SOLDIER);
	}
	
	/*
	 * generalIsFacingHorse is a helper method that checks to see if the general can be killed by an enemy horse
	 */
	private static boolean generalIsFacingHorse(XiangqiCoordinate source, XiangqiCoordinate destination, XiangqiBoard tempBoard, XiangqiColor turn) {
		XiangqiColor enemyColor = turn == XiangqiColor.RED ? XiangqiColor.BLACK : XiangqiColor.RED;
		XiangqiCoordinate generalCoordinate = tempBoard.findGeneral(turn);
		int gRank = generalCoordinate.getRank();
		int gFile = generalCoordinate.getFile();
			
		return checkHorse(tempBoard, enemyColor, gRank + 1, gFile + 2, gRank + 1, gFile + 1) ||
				checkHorse(tempBoard, enemyColor, gRank + 2, gFile + 1, gRank + 1, gFile + 1) || 
				checkHorse(tempBoard, enemyColor, gRank + 1, gFile - 2, gRank + 1, gFile - 1) || 
				checkHorse(tempBoard, enemyColor, gRank + 2, gFile - 1, gRank + 1, gFile - 1) ||
				checkHorse(tempBoard, enemyColor, gRank - 1, gFile + 2, gRank - 1, gFile - 1) ||
				checkHorse(tempBoard, enemyColor, gRank - 2, gFile + 1, gRank - 1, gFile - 1) ||
				checkHorse(tempBoard, enemyColor, gRank - 1, gFile - 2, gRank - 1, gFile - 1) ||
				checkHorse(tempBoard, enemyColor, gRank - 2, gFile - 1, gRank - 1, gFile - 1);
	}
	
	/*
	 * checkHorse is a helper method for checking the coordinates around a general that could house a dangerous horse
	 */
	private static boolean checkHorse(XiangqiBoard tempBoard, XiangqiColor enemyColor, int horseRank, int horseFile, int blockerRank, int blockerFile) {
		XiangqiPiece horse = tempBoard.getPieceAt(
				XiangqiCoordinateImpl.makeCoordinate(horseRank,
				horseFile));

		XiangqiPiece blocker = tempBoard.getPieceAt(
				XiangqiCoordinateImpl.makeCoordinate(blockerRank,
				blockerFile));
		
		return horse.getColor() == enemyColor && horse.getPieceType() == XiangqiPieceType.HORSE && blocker.getColor() == XiangqiColor.NONE;
	}
	
	/*
	 * generalIsFacingCannon is a helper method that checks to see if the general can be killed by an enemy cannon
	 */
	private static boolean generalIsFacingCannon(XiangqiCoordinate source, XiangqiCoordinate destination, XiangqiBoard tempBoard, XiangqiColor turn) {
		XiangqiColor enemyColor = turn == XiangqiColor.RED ? XiangqiColor.BLACK : XiangqiColor.RED;
		XiangqiCoordinate generalCoordinate = tempBoard.findGeneral(turn);
		int gRank = generalCoordinate.getRank();
		int gFile = generalCoordinate.getFile();
		
		return checkCannon(tempBoard, enemyColor, gRank, tempBoard.getMaxRank(), gFile, gFile) || 
				checkCannon(tempBoard, enemyColor, 1, gRank, gFile, gFile) || 
				checkCannon(tempBoard, enemyColor, gRank, gRank, gFile, tempBoard.getMaxFile()) ||
				checkCannon(tempBoard, enemyColor, gRank, gRank, 1, gFile);
	}
	
	/*
	 * checkCannon is a helper method for checking the coordinates around a general that could house a dangerous cannon
	 */
	private static boolean checkCannon(XiangqiBoard tempBoard, XiangqiColor enemyColor, int startRank, int endRank, int startFile, int endFile) {
		XiangqiColor currentColor = enemyColor == XiangqiColor.RED ? XiangqiColor.BLACK : XiangqiColor.RED;
		int count = 0;
		
		for(int i = startRank; i <= endRank; i++) {
			for(int j = startFile; j <= endFile; j++) {
				
				XiangqiPiece temp = tempBoard.getPieceAt(XiangqiCoordinateImpl.makeCoordinate(i, j));
				if(temp.getColor() == enemyColor && temp.getPieceType() == XiangqiPieceType.CANNON && count == 1) {
					return true;
				}
				else if(!(temp.getPieceType() == XiangqiPieceType.GENERAL && temp.getColor() == currentColor) && 
						temp.getColor() != XiangqiColor.NONE) {
					count += 1;
				}
				
				if(count > 1) {
					return false;
				}
			}
		}
		return false;
	}
}
