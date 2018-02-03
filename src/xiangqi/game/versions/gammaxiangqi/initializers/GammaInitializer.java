package xiangqi.game.versions.gammaxiangqi.initializers;

import xiangqi.game.common.initializers.BaseInitializer;

/**
 * @author jacobhackett
 * GammaInitializer initializes the state and validators for the gamma version of the game
 * This is used within the XiangqiGameFactory to initialize the BaseXiagnqiGame instance
 */
public class GammaInitializer extends BaseInitializer {
	
	public GammaInitializer() {
		this.state = initializeState();
		this.pieceMoveValidators = initializePieceMoveValidators();
		this.moveValidators = initializeMoveValidators();
	}
	
	public int getMaxMoveCount() {
		return 50;
	}
}
