/*******************************************************************************
 * This files was developed for CS4233: Object-Oriented Analysis & Design.
 * The course was taken at Worcester Polytechnic Institute.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Copyright ©2016-2017 Gary F. Pollice
 *******************************************************************************/

package xiangqi;

import xiangqi.common.*;
import xiangqi.game.common.BaseXiangqiGame;
import xiangqi.game.versions.alphaxiangqi.AlphaXiangqiGame;
import xiangqi.game.versions.betaxiangqi.BetaXiangqiGame;
import xiangqi.game.versions.deltaxiangqi.DeltaXiangqiGame;
import xiangqi.game.versions.deltaxiangqi.initializers.DeltaInitializer;
import xiangqi.game.versions.gammaxiangqi.initializers.GammaInitializer;

/**
 * A simple factory object that creates the appropriate instance of a XiangqiGame.
 * @version Dec 26, 2016
 */
public class XiangqiGameFactory
{
	/**
	 * Factory method that returns an instance of the requested game.
	 * @param version the version requested
	 * @return the instance of the requested game
	 */
	public static XiangqiGame makeXiangqiGame(XiangqiGameVersion version)
	{
		switch(version) {
			case ALPHA_XQ: 
				return new AlphaXiangqiGame();
			case BETA_XQ: 
				return new BetaXiangqiGame();
			case GAMMA_XQ:
				GammaInitializer initGamma = new GammaInitializer();
				return new BaseXiangqiGame(initGamma.getState(), initGamma.getPieceMoveValidators(),
						initGamma.getMoveValidators(), initGamma.getMaxMoveCount());
			case DELTA_XQ:
				DeltaInitializer initDelta = new DeltaInitializer();
				return new DeltaXiangqiGame(initDelta.getState(), initDelta.getPieceMoveValidators(),
						initDelta.getMoveValidators(), initDelta.getMaxMoveCount());
			default: 
				return null;
		}
	}
}
