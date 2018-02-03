package xiangqi.game.common;

import java.util.LinkedList;

/*
 * XiangqiHistory functions as a data structure to hold past board states
 * As of Delta, this is just used to determine if perpetual moves are happening
 */
public class XiangqiHistory {

	private LinkedList<XiangqiBoard> history;
	
	public XiangqiHistory() {
		this.history = new LinkedList<XiangqiBoard>();
	}
	
	/**
	 * addBoardState adds the XiangqiBoard to the data structure
	 * @param board the board to add
	 */
	public void addBoardState(Object board) {
		this.history.add((XiangqiBoard)board);
	}
	
	/**
	 * isIllegalRepetition tells whether or not the same board state has appeared in 3 of the last 5 moves in the history
	 * @return true if 3 or more repeats, false if not
	 */
	public boolean isIllegalRepetition() {
		int maxSize = this.history.size();
		XiangqiBoard current = this.history.get(maxSize - 1);
		int repeats = 0;
		for(int i = maxSize - 5; i < maxSize && i >= 0; i++) {
			if(history.get(i).equals(current)) {
				repeats += 1;
			}
		}
		
		return maxSize >= 5 && repeats >= 3;
	}
}
