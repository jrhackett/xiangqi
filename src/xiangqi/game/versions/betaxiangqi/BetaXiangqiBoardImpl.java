package xiangqi.game.versions.betaxiangqi;

import java.util.HashMap;

import xiangqi.common.XiangqiColor;
import xiangqi.common.XiangqiCoordinate;
import xiangqi.common.XiangqiPiece;
import xiangqi.common.XiangqiPieceType;
import xiangqi.game.common.XiangqiCoordinateImpl;
import xiangqi.game.common.XiangqiPieceImpl;

/**
 * @author jacobhackett
 * This is the Board class for the beta version of Xiangqi
 */
public class BetaXiangqiBoardImpl implements BetaXiangqiBoard {

	/*
	 * This HashMap is the state of the board with all coordinates in the Red aspect frame
	 */
	HashMap<XiangqiCoordinate, XiangqiPiece> board;
	
	public BetaXiangqiBoardImpl() {}
	
	/* (non-Javadoc)
	 * @see xiangqi.game.versions.betaxiangqi.XiangqiBoard#setupBoard()
	 */
	@Override
	public void setupBoard() {
		this.board = new HashMap<XiangqiCoordinate, XiangqiPiece>();
		
		this.board.put(XiangqiCoordinateImpl.makeCoordinate(1, 3), 
				XiangqiPieceImpl.makePiece(XiangqiPieceType.GENERAL, XiangqiColor.RED));
		this.board.put(XiangqiCoordinateImpl.makeCoordinate(1, 2), 
				XiangqiPieceImpl.makePiece(XiangqiPieceType.ADVISOR, XiangqiColor.RED));
		this.board.put(XiangqiCoordinateImpl.makeCoordinate(1, 4), 
				XiangqiPieceImpl.makePiece(XiangqiPieceType.ADVISOR, XiangqiColor.RED));
		this.board.put(XiangqiCoordinateImpl.makeCoordinate(2, 3), 
				XiangqiPieceImpl.makePiece(XiangqiPieceType.SOLDIER, XiangqiColor.RED));
		this.board.put(XiangqiCoordinateImpl.makeCoordinate(1, 1), 
				XiangqiPieceImpl.makePiece(XiangqiPieceType.CHARIOT, XiangqiColor.RED));
		this.board.put(XiangqiCoordinateImpl.makeCoordinate(1, 5), 
				XiangqiPieceImpl.makePiece(XiangqiPieceType.CHARIOT, XiangqiColor.RED));
		
		this.board.put(XiangqiCoordinateImpl.makeCoordinate(5, 3), 
				XiangqiPieceImpl.makePiece(XiangqiPieceType.GENERAL, XiangqiColor.BLACK));
		this.board.put(XiangqiCoordinateImpl.makeCoordinate(5, 2), 
				XiangqiPieceImpl.makePiece(XiangqiPieceType.ADVISOR, XiangqiColor.BLACK));
		this.board.put(XiangqiCoordinateImpl.makeCoordinate(5, 4), 
				XiangqiPieceImpl.makePiece(XiangqiPieceType.ADVISOR, XiangqiColor.BLACK));
		this.board.put(XiangqiCoordinateImpl.makeCoordinate(4, 3), 
				XiangqiPieceImpl.makePiece(XiangqiPieceType.SOLDIER, XiangqiColor.BLACK));
		this.board.put(XiangqiCoordinateImpl.makeCoordinate(5, 5), 
				XiangqiPieceImpl.makePiece(XiangqiPieceType.CHARIOT, XiangqiColor.BLACK));
		this.board.put(XiangqiCoordinateImpl.makeCoordinate(5, 1), 
				XiangqiPieceImpl.makePiece(XiangqiPieceType.CHARIOT, XiangqiColor.BLACK));	
	}
	
	private BetaXiangqiBoardImpl(HashMap<XiangqiCoordinate, XiangqiPiece> tempBoard) {
		this.board = tempBoard;
	}

	/*
	 * @see xiangqi.game.versions.betaxiangqi.XiangqiBoard#getPieceAt(xiangqi.common.XiangqiCoordinate)
	 */
	@Override
	public XiangqiPiece getPieceAt(XiangqiCoordinate coordinate, XiangqiColor aspect) {
		return getPiece(getCoordinateInBoardFrame(coordinate,  aspect));
	}
	
	private XiangqiPiece getPiece(XiangqiCoordinate coordinate) {
		return this.board.containsKey(coordinate) ? this.board.get(coordinate) :
				XiangqiPieceImpl.makePiece(XiangqiPieceType.NONE, XiangqiColor.NONE);
	}
	
	private XiangqiCoordinate getCoordinateInBoardFrame(XiangqiCoordinate coordinate, XiangqiColor aspect) {
		return aspect == XiangqiColor.RED ? new XiangqiCoordinateImpl(coordinate) :
				XiangqiCoordinateImpl.makeCoordinate(6 - coordinate.getRank(), 6 - coordinate.getFile());
	}
	
	
	/*
	 * @see xiangqi.game.versions.betaxiangqi.XiangqiBoard#makeMove(xiangqi.common.XiangqiCoordinate)
	 */
	@Override
	public void makeMove(XiangqiCoordinate from, XiangqiCoordinate to, XiangqiColor turn) {
		XiangqiPiece pieceToMove = getPieceAt(from, turn);
		this.board.put(getCoordinateInBoardFrame(to,  turn), pieceToMove);
		this.board.remove(getCoordinateInBoardFrame(from,  turn), pieceToMove);
	}
	
	/*
	 * @see xiangqi.game.versions.betaxiangqi.XiangqiBoard#isStraightPath(xiangqi.common.XiangqiCoordinate)
	 */
	public boolean isStraightPath(XiangqiCoordinate source, XiangqiCoordinate destination, XiangqiColor aspect) {
		int currentFile = source.getFile();
		int currentRank = source.getRank();
		int destinationFile = destination.getFile();
		int destinationRank = destination.getRank();
		if(currentRank == destinationRank) {
			int smaller = currentFile > destinationFile ? destinationFile : currentFile;
			int larger = currentFile > destinationFile ? currentFile : destinationFile;
			for(int i = smaller + 1; i < larger; i++) {
				if(getPieceAt(XiangqiCoordinateImpl.makeCoordinate(currentRank, i), aspect).getPieceType() != XiangqiPieceType.NONE) {
					return false;
				}
			}
		}
		else if(currentFile == destinationFile) {
			int smaller = currentRank > destinationRank ? destinationRank : currentRank;
			int larger = currentRank > destinationRank ? currentRank : destinationRank;
			for(int i = smaller + 1; i < larger; i++) {
				if(getPieceAt(XiangqiCoordinateImpl.makeCoordinate(i, currentFile), aspect).getPieceType() != XiangqiPieceType.NONE) {
					return false;
				}
			}
		}
		else if(currentFile != destinationFile && currentRank != destinationRank) {
			return false;
		}
		return true;
	}
	
	/*
	 * @see xiangqi.game.versions.betaxiangqi.XiangqiBoard#findGeneral(xiangqi.common.XiangqiColor)
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
	

	/*
	 * @see xiangqi.game.versions.betaxiangqi.XiangqiBoard#simulateMove(xiangqi.common.XiangqiCoordinate)
	 */
	public BetaXiangqiBoard simulateMove(XiangqiCoordinate source, XiangqiCoordinate destination, XiangqiColor aspect) {
		HashMap<XiangqiCoordinate, XiangqiPiece> tempBoard = new HashMap<XiangqiCoordinate, XiangqiPiece>(this.board);
		XiangqiPiece pieceToMove = getPieceAt(source, aspect);
		tempBoard.put(getCoordinateInBoardFrame(destination,  aspect), pieceToMove);
		tempBoard.remove(getCoordinateInBoardFrame(source,  aspect), pieceToMove);
		return new BetaXiangqiBoardImpl(tempBoard);
	}
}
