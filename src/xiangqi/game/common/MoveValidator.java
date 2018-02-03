package xiangqi.game.common;

@FunctionalInterface
public interface MoveValidator<S, C> {
	public boolean apply(S s, C c, C t);
}
