package xiangqi.game.common;

import xiangqi.common.XiangqiColor;
import xiangqi.common.XiangqiCoordinate;

/**
 * @author jacobhackett
 *
 */
public class XiangqiCoordinateImpl implements XiangqiCoordinate {

	private final int rank;
	private final int file;
	
	private XiangqiCoordinateImpl(int rank, int file) {
		this.rank = rank;
		this.file = file;
	}
	
	/*
	 * Copy constructor
	 */
	public XiangqiCoordinateImpl(XiangqiCoordinate c) {
		this.rank = c.getRank();
		this.file = c.getFile();
	}
	
	static public XiangqiCoordinate makeCoordinate(int rank, int file) {
		return new XiangqiCoordinateImpl(rank, file);
	}
	
	static public XiangqiCoordinate makeCoordinate(XiangqiCoordinate coordinate, XiangqiColor aspect) {
		return aspect == XiangqiColor.RED ? new XiangqiCoordinateImpl(coordinate) :
			XiangqiCoordinateImpl.makeCoordinate(11 - coordinate.getRank(), 10 - coordinate.getFile());
	}
	
	/* (non-Javadoc)
	 * @see xiangqi.common.XiangqiCoordinate#getRank()
	 */
	@Override
	public int getRank() {
		return this.rank;
	}

	/* (non-Javadoc)
	 * @see xiangqi.common.XiangqiCoordinate#getFile()
	 */
	@Override
	public int getFile() {
		return this.file;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + file;
		result = prime * result + rank;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		XiangqiCoordinateImpl other = (XiangqiCoordinateImpl) obj;
		if (file != other.file)
			return false;
		if (rank != other.rank)
			return false;
		return true;
	}

}
