package guess.the.color.project.logic;

import guess.the.color.project.model.Game;

public interface GameService {
	public Game startNewGame(String complexity);

	public Game getGame(String gameId);

	public void saveGame(Game game);

	public void setGameSpecifics(String complexity, Game game);
}
