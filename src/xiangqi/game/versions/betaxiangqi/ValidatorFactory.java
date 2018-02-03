package xiangqi.game.versions.betaxiangqi;

import java.util.LinkedList;
import java.util.List;
import java.util.function.BiPredicate;

import xiangqi.common.XiangqiCoordinate;
import xiangqi.common.XiangqiPieceType;

/**
 * This is the validator factory for the beta version of Xiangqi
 * @author jacobhackett
 */
public class ValidatorFactory {
	
	private static BiPredicate<XiangqiCoordinate, XiangqiCoordinate> adjacentValidator =
			(XiangqiCoordinate c1, XiangqiCoordinate c2) -> {
				return Math.abs(c1.getRank() - c2.getRank()) <= 1 && Math.abs(c1.getFile() - c2.getFile()) <= 1;
			};
	private static BiPredicate<XiangqiCoordinate, XiangqiCoordinate> horizontalValidator =
			(XiangqiCoordinate c1, XiangqiCoordinate c2) -> {
				return c1.getRank() == c2.getRank();
			};
	private static BiPredicate<XiangqiCoordinate, XiangqiCoordinate> verticalValidator =
			(XiangqiCoordinate c1, XiangqiCoordinate c2) -> {
				return c1.getFile() == c2.getFile();
			};
	private static BiPredicate<XiangqiCoordinate, XiangqiCoordinate> orthogonalValidator =
			(XiangqiCoordinate c1, XiangqiCoordinate c2) -> {
				return c1.getRank() == c2.getRank() || c1.getFile() == c2.getFile();
			};
	private static BiPredicate<XiangqiCoordinate, XiangqiCoordinate> diagonalValidator =
			(XiangqiCoordinate c1, XiangqiCoordinate c2) -> {
				return Math.abs(c1.getRank() - c2.getRank()) == Math.abs(c1.getFile() - c2.getFile());
			};
	private static BiPredicate<XiangqiCoordinate, XiangqiCoordinate> generalZoneValidator =
			(XiangqiCoordinate c1, XiangqiCoordinate c2) -> {
				return c1.getFile() > 1 && c2.getFile() > 1 && c1.getFile() < 5 && c2.getFile() < 5;
			};
			
	/**
	 * makeValidators produces a list of validators for a piece type, describing what constraints that piece has for movement
	 * @param pieceType the piece
	 * @return the list of validation functions
	 */
	public static List<BiPredicate<XiangqiCoordinate, XiangqiCoordinate>> makeValidators(XiangqiPieceType pieceType) {
		List<BiPredicate<XiangqiCoordinate, XiangqiCoordinate>> validators =
				new LinkedList<BiPredicate<XiangqiCoordinate, XiangqiCoordinate>>();
		
		switch(pieceType) {
			case GENERAL:
				validators.add(horizontalValidator);
				validators.add(generalZoneValidator);
				break;
			case ADVISOR:
				validators.add(adjacentValidator);
				validators.add(diagonalValidator);
				break;
			case SOLDIER:
				validators.add(adjacentValidator);
				validators.add(verticalValidator);
				break;
			case CHARIOT:
				validators.add(orthogonalValidator);
				break;
			default:
				System.out.println("Not yet implemented");
				break;
		}
		
		return validators;
	}
}
