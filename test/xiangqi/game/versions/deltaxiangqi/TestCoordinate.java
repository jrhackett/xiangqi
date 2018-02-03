package xiangqi.game.versions.deltaxiangqi;

import xiangqi.common.XiangqiCoordinate;

public class TestCoordinate implements XiangqiCoordinate {

	private int rank, file;
	
	static public TestCoordinate makeCoordinate(int rank, int file) {
		return new TestCoordinate(rank, file);
	}
	
	private TestCoordinate(int rank, int file) {
		this.rank = rank;
		this.file = file;
	}
	/*
	 * @see xiangqi.common.XiangqiCoordinate#getRank()
	 */
	@Override
	public int getRank() {
		return this.rank;
	}

	/* 
	 * @see xiangqi.common.XiangqiCoordinate#getFile()
	 */
	@Override
	public int getFile() {
		return this.file;
	}

}
