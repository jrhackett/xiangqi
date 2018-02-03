
package xiangqi.game.common;

/**
 * @author jacobhackett
 * InvalidCoordinatesException is thrown as a CompletionException when the client sends us invalid coordinates
 * thus that they do not exist on our board
 */
public class InvalidCoordinatesException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1909330717097612490L;

	public InvalidCoordinatesException(){ super(); }
}
