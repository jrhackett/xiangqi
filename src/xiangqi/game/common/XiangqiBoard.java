
package xiangqi.game.common;

import java.util.HashMap;

import xiangqi.common.XiangqiColor;
import xiangqi.common.XiangqiCoordinate;
import xiangqi.common.XiangqiPiece;
import xiangqi.common.XiangqiPieceType;

/**
 * @author jacobhackett
 * XiangqiBoard holds all information about the Xiangqi board including locations of pieces and size constraints
 */
public class XiangqiBoard {

	
	/*
	 * This HashMap is the state of the board with all coordinates in the Red aspect frame
	 */
	private HashMap<XiangqiCoordinate, XiangqiPiece> board;
	private int maxRank;
	private int maxFile;
	
	public XiangqiBoard(HashMap<XiangqiCoordinate, XiangqiPiece> board, int maxRank, int maxFile) {
		this.board = board;
		this.maxRank = maxRank;
		this.maxFile = maxFile;
	}
	
	public XiangqiBoard(XiangqiBoard board) {
		this.board = new HashMap<XiangqiCoordinate, XiangqiPiece>();
		this.maxRank = board.getMaxRank();
		this.maxFile = board.getMaxFile();
		for(int i = 0; i <= board.getMaxRank(); i++) {
			for(int j = 0; j <= board.getMaxFile(); j++) {
				XiangqiCoordinate c = XiangqiCoordinateImpl.makeCoordinate(i, j);
				this.board.put(c, board.getPieceAt(c));
			}
		}
	}

	/**
	 * getPieceAt returns the piece at the given coordinate
	 * @param coordinate the coordinate
	 * @return the piece at coordinate
	 */
	public XiangqiPiece getPieceAt(XiangqiCoordinate coordinate) {
		return this.board.containsKey(coordinate) ? this.board.get(coordinate) :
			XiangqiPieceImpl.makePiece(XiangqiPieceType.NONE, XiangqiColor.NONE);
	}

	/**
	 * makeMove processes the move in our HashMap representation of the board
	 * @param from the starting coordinate
	 * @param to the destination coordinate
	 */
	public void makeMove(XiangqiCoordinate from, XiangqiCoordinate to) {
		XiangqiPiece pieceToMove = getPieceAt(from);
		this.board.put(to, pieceToMove);
		this.board.remove(from, pieceToMove);
	}

	/**
	 * findGeneral returns the coordinate of the general with the given aspect
	 * @param aspect the team aspect to search for
	 * @return the coordinate where that general is
	 */
	public XiangqiCoordinate findGeneral(XiangqiColor aspect) {
		XiangqiCoordinate coordinate = XiangqiCoordinateImpl.makeCoordinate(-1, -1);
		for(XiangqiCoordinate key : this.board.keySet()) {
			XiangqiPiece currentPiece = this.board.get(key);
			if(currentPiece.getPieceType() == XiangqiPieceType.GENERAL && currentPiece.getColor() == aspect) {
				coordinate = key;
				break;
			}
		}
		return coordinate;
	}

	/**
	 * simulateMove returns a new XiangqiBoard instnce with the given move simulated
	 * This is used in checking future moves for checkmate
	 * @param source the starting coordinate of the move to simulate
	 * @param destination the destination coordinate of the move to simulate
	 * @return the new board with that move processed
	 */
	public XiangqiBoard simulateMove(XiangqiCoordinate source, XiangqiCoordinate destination) {
		XiangqiBoard tempBoard = new XiangqiBoard(new HashMap<XiangqiCoordinate, XiangqiPiece>(this.board),
				this.maxRank, this.maxFile);
		tempBoard.makeMove(source, destination);
		return tempBoard;
	}

	/**
	 * isStraightPath checks to see if there is an unblocked straight path between the two coordinates
	 * @param source the starting coordinate
	 * @param destination the destination coordinate
	 * @return true if there is a straight path, false if not
	 */
	public boolean isStraightPath(XiangqiCoordinate source, XiangqiCoordinate destination) {
		int currentFile = source.getFile();
		int currentRank = source.getRank();
		int destinationFile = destination.getFile();
		int destinationRank = destination.getRank();
		if(currentRank == destinationRank) {
			int smaller = currentFile > destinationFile ? destinationFile : currentFile;
			int larger = currentFile > destinationFile ? currentFile : destinationFile;
			for(int i = smaller + 1; i < larger; i++) {
				if(getPieceAt(XiangqiCoordinateImpl.makeCoordinate(currentRank, i)).getPieceType() != XiangqiPieceType.NONE) {
					return false;
				}
			}
		}
		else if(currentFile == destinationFile) {
			int smaller = currentRank > destinationRank ? destinationRank : currentRank;
			int larger = currentRank > destinationRank ? currentRank : destinationRank;
			for(int i = smaller + 1; i < larger; i++) {
				if(getPieceAt(XiangqiCoordinateImpl.makeCoordinate(i, currentFile)).getPieceType() != XiangqiPieceType.NONE) {
					return false;
				}
			}
		}
		else if(currentFile != destinationFile && currentRank != destinationRank) {
			return false;
		}
		return true;
	}
	
	public int getMaxRank() {
		return this.maxRank;
	}
	
	public int getMaxFile() {
		return this.maxFile;
	}

	public void putPiece(XiangqiCoordinate coordinate, XiangqiPiece piece) {
		this.board.put(coordinate, piece);
	}
	
	public boolean equals(Object other) {
		XiangqiBoard otherBoard = (XiangqiBoard)other;
		for(int i = 1; i <= maxRank; i++) {
			for(int j = 1; j <= maxFile; j++) {
				XiangqiPiece p1 = this.getPieceAt(XiangqiCoordinateImpl.makeCoordinate(i, j));
				XiangqiPiece p2 = otherBoard.getPieceAt(XiangqiCoordinateImpl.makeCoordinate(i, j));
				if(p1.getPieceType() != p2.getPieceType() || p1.getColor() != p2.getColor()) {
					return false;
				}
			}
		}
		return true;
	}

}
