/**
 * 
 */
package xiangqi.game.versions.betaxiangqi;

import xiangqi.common.XiangqiCoordinate;

/**
 * @author jacobhackett
 *
 */
public class TestCoordinate implements XiangqiCoordinate {

	private final int rank;
	private final int file;
	
	private TestCoordinate(int rank, int file)
	{
		this.rank = rank;
		this.file = file;
	}
	
	public static XiangqiCoordinate makeCoordinate(int rank, int file)
	{
		return new TestCoordinate(rank, file);
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

}
