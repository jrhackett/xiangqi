package xiangqi.game.common;

import xiangqi.common.XiangqiColor;
import xiangqi.common.XiangqiCoordinate;
import xiangqi.common.XiangqiPieceType;

public class PieceMoveValidators {
	/*
	 * isOrthogonal checks to see if a piece is moving either vertically or horizontally
	 */
	public static MoveValidator<XiangqiState, XiangqiCoordinate> isOrthogonal =
			(state, from, to) -> {
				boolean result = from.getFile() == to.getFile() || from.getRank() == to.getRank();
				if(!result)
					state.moveMessage = "This piece must move orthogonally";
				return result;
			};
			
	/*
	 * isOrthogonalOrDiagonal checks to see if a piece is moving vertically, horizontally, or diagonally
	 */
	public static MoveValidator<XiangqiState, XiangqiCoordinate> isOrthogonalOrDiagonal =
			(state, from, to) -> {
				boolean result = from.getFile() == to.getFile()|| from.getRank() == to.getRank() || 
						Math.abs(from.getRank() - to.getRank()) == Math.abs(from.getFile() - to.getFile());
				if(!result)
					state.moveMessage = "This piece must move orthogonally";
				return result;
			};
			
	/*
	 * isAdjacent checks to see if a piece is moving to an adjacent square
	 */
	public static MoveValidator<XiangqiState, XiangqiCoordinate> isAdjacent =
			(state, from, to) -> {
				boolean result = Math.abs(from.getRank() - to.getRank()) <= 1 && Math.abs(from.getFile() - to.getFile()) <= 1;
				if(!result)
					state.moveMessage = "This piece can only move one square at a time";
				return result;
			};
			
	/*
	 * isWithinThePalace checks to see if a piece is within its palace on the board
	 */
	public static MoveValidator<XiangqiState, XiangqiCoordinate> isWithinThePalace =
			(state, from, to) -> {
				boolean result = state.turn == XiangqiColor.RED ? 
						from.getRank() >= 1 && from.getRank() <= 3 && to.getRank() >= 1 && to.getRank() <= 3 : 
							from.getRank() >= 7 && from.getRank() <= 10 && to.getRank() >= 7 && to.getRank() <= 10;
				result = result && from.getFile() >= 4 && from.getFile() <= 6 && to.getFile() >= 4 && to.getFile() <= 6;
				if(!result)
					state.moveMessage = "This piece must stay within the palace";
				return result;
			};
		
	/*
	 * isDiagonal checks to see if a piece is moving diagonally
	 */
	public static MoveValidator<XiangqiState, XiangqiCoordinate> isDiagonal =
			(state, from, to) -> {
				boolean result = Math.abs(from.getRank() - to.getRank()) == Math.abs(from.getFile() - to.getFile());
				if(!result) 
					state.moveMessage = "This piece must move diagonally";
				return result;
			};
	
	/*
	 * isExactlyTwoSpaces checks to see if a piece is moving exactly two spaces
	 */
	public static MoveValidator<XiangqiState, XiangqiCoordinate> isExactlyTwoSpaces = 
			(state, from, to) -> {
				boolean result = Math.abs(from.getRank() - to.getRank()) == 2 &&  Math.abs(from.getFile() - to.getFile()) == 2;
				if(!result)
					state.moveMessage = "This piece must move exactly two spaces";
				return result;
			};
			
	/*
	 * isIllegalJumpForElephant checks to see if there is a piece in the middle of an elephant's move
	 * elephants cannot jump over other pieces
	 */
	public static MoveValidator<XiangqiState, XiangqiCoordinate> isIllegalJumpForElephant = 
			(state, from, to) -> {
				int checkRank = (from.getRank() + to.getRank()) / 2;
				int checkFile = (from.getFile() + to.getFile()) / 2;
				
				boolean result = state.board.getPieceAt(XiangqiCoordinateImpl.makeCoordinate(checkRank, checkFile))
						.getColor() == XiangqiColor.NONE ? true : false;
	
				if(!result) 
					state.moveMessage = "This piece cannot jump over other pieces";
				return result;
			};
			
	/*
	 * isIllegallyCrossingTheRiver checks to see if a piece is crossing the river
	 */
	public static MoveValidator<XiangqiState, XiangqiCoordinate> isIllegallyCrossingTheRiver = 
			(state, from, to) -> {
				
				boolean result = state.turn == XiangqiColor.RED ? 
						from.getRank() <= 5 && to.getRank() <= 5 : 
							from.getRank() >= 6 && to.getRank() >= 6;
	
				if(!result) 
					state.moveMessage = "This piece cannot cross the river";
				return result;
			};
	
	/*
	 * isVerticalOrHorizontalAfterRiver checks to see if a piece is moving vertically or is moving horizontally
	 * a piece with this validator can only move horizontally after they cross the river
	 */
	public static MoveValidator<XiangqiState, XiangqiCoordinate> isVerticalOrHorizontalAfterRiver = 
			(state, from, to) -> {
				XiangqiColor aspect = state.board.getPieceAt(from).getColor();
				boolean isAcrossRiver = false;
				if(aspect == XiangqiColor.RED) {
					if(from.getRank() >= 6)
						isAcrossRiver = true;
				}
				else {
					if(from.getRank() <= 5) 
						isAcrossRiver = true;
				}
				boolean result = from.getFile() == to.getFile() || (from.getRank() == to.getRank() && isAcrossRiver);
				
				if(!result) 
					state.moveMessage = "This piece must move vertically";
				return result;
			};
		
	/*
	 * isIllegallyGoingBackwards checks to see if a piece is moving backwards on the board
	 */
	public static MoveValidator<XiangqiState, XiangqiCoordinate> isIllegallyGoingBackwards = 
			(state, from, to) -> {
				XiangqiColor aspect = state.board.getPieceAt(from).getColor();
				boolean result = false;
				if(aspect == XiangqiColor.RED) {
					result = from.getRank() > to.getRank() ? false : true;
				}
				else {
					result = from.getRank() > to.getRank() ? true : false;
				}
				
				if(!result) 
					state.moveMessage = "This piece cannot move backwards";
				return result;
			};
		
	/*
	 * isOrthogonalStraightLine checks to see if there is a straight, non-blocked straight path 
	 */
	public static MoveValidator<XiangqiState, XiangqiCoordinate> isOrthogonalStraightLine = 
			(state, from, to) -> {
				int currentFile = from.getFile();
				int currentRank = from.getRank();
				int destinationFile = to.getFile();
				int destinationRank = to.getRank();
				boolean result = true;
				if(currentRank == destinationRank) {
					int smaller = currentFile > destinationFile ? destinationFile : currentFile;
					int larger = currentFile > destinationFile ? currentFile : destinationFile;
					for(int i = smaller + 1; i < larger; i++) {
						if(state.board.getPieceAt(XiangqiCoordinateImpl.makeCoordinate(currentRank, i)).getPieceType() != XiangqiPieceType.NONE) {
							result = false;
						}
					}
				}
				else if(currentFile == destinationFile) {
					int smaller = currentRank > destinationRank ? destinationRank : currentRank;
					int larger = currentRank > destinationRank ? currentRank : destinationRank;
					for(int i = smaller + 1; i < larger; i++) {
						if(state.board.getPieceAt(XiangqiCoordinateImpl.makeCoordinate(i, currentFile)).getPieceType() != XiangqiPieceType.NONE) {
							result = false;
						}
					}
				}
				
				if(!result) 
					state.moveMessage = "This piece cannot jump over other pieces";
				return result;
			};
			
	/*
	 * isInAnLShape checks to see if a piece is moving one position orthogonally and then one position diagonally
	 * this movement mimics the knight in western chess
	 */
	public static MoveValidator<XiangqiState, XiangqiCoordinate> isInAnLShape = 
			(state, from, to) -> {
				int fromRank = from.getRank();
				int fromFile = from.getFile();
				int toRank = to.getRank();
				int toFile = to.getFile();
				boolean result = fromRank != toRank && fromFile != toFile && 
						((Math.abs(fromRank - toRank) == 2 && Math.abs(fromFile - toFile) == 1) || 
								(Math.abs(fromRank - toRank) == 1 && Math.abs(fromFile - toFile) == 2));
				
				if(!result) 
					state.moveMessage = "This piece must move in the shape of an L";
				return result;
			};
			
	/*
	 * isClearPathForHorse checks to see if a piece is blocking their path for their orthogonal part of their movement
	 */
	public static MoveValidator<XiangqiState, XiangqiCoordinate> isClearPathForHorse = 
			(state, from, to) -> {
				int fromRank = from.getRank();
				int fromFile = from.getFile();
				int toRank = to.getRank();
				int toFile = to.getFile();
				boolean result = true;
				
				if(Math.abs(fromRank - toRank) == 2) {
					int direction = fromRank > toRank ? -1 : 1;
					result = state.board.getPieceAt(XiangqiCoordinateImpl.makeCoordinate(fromRank + direction, fromFile)).getColor() == XiangqiColor.NONE;
				}
				else {
					int direction = fromFile > toFile ? -1 : 1;
					result = state.board.getPieceAt(XiangqiCoordinateImpl.makeCoordinate(fromRank, fromFile + direction)).getColor() == XiangqiColor.NONE;
				}
				
				if(!result) 
					state.moveMessage = "This piece may not jump over other pieces that are orthogonally adjacent to it";
				return result;
			};
			
	/*
	 * isLegalCaptureForCannon checks to see if the cannon is jumping over exactly one piece and capturing an enemy on the other side
	 */
	public static MoveValidator<XiangqiState, XiangqiCoordinate> isLegalCaptureForCannon = 
			(state, from, to) -> {
				int fromRank = from.getRank();
				int fromFile = from.getFile();
				int toRank = to.getRank();
				int toFile = to.getFile();
				boolean result = true;
				int numPiecesBetween = 0;
				
				
				if(fromFile == toFile) {
					int smaller = fromRank > toRank ? toRank : fromRank;
					int larger = fromRank > toRank ? fromRank : toRank;
					for(int i = smaller + 1; i < larger; i++) {
						if(state.board.getPieceAt(XiangqiCoordinateImpl.makeCoordinate(i, fromFile)).getColor() != XiangqiColor.NONE) {
							numPiecesBetween++;
						}
					}
				}
				else if(fromRank == toRank) {
					int smaller = fromFile > toFile ? toFile : fromFile;
					int larger = fromFile > toFile ? fromFile : toFile;
					for(int i = smaller + 1; i < larger; i++) {
						if(state.board.getPieceAt(XiangqiCoordinateImpl.makeCoordinate(fromRank, i)).getColor() != XiangqiColor.NONE) {
							numPiecesBetween++;
						}
					}
				}
				else {
					result = false;
				}
				
				if(state.board.getPieceAt(XiangqiCoordinateImpl.makeCoordinate(toRank, toFile)).getColor() != XiangqiColor.NONE) {
					if(numPiecesBetween != 1) {
						result = false;
					}
				}
				else {
					if(numPiecesBetween > 0) {
						result = false;
					}
				}

				
				if(!result) 
					state.moveMessage = "This piece may not capture a piece unless it jumps over exactly one other piece";
				return result;
			};
	
}
