package guess.the.color.project.logic;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import guess.the.color.project.model.Complexity;
import guess.the.color.project.model.Game;

@Service
public class GameServiceImpl implements GameService {

	@Autowired
	private ColorGeneratorService colorService;

	public Map<String, Game> gamesInfo = new ConcurrentHashMap<>();

	@Override
	public Game startNewGame(String complexity) {
		Game game = new Game();

		game.setId(UUID.randomUUID().toString());
		setGameSpecifics(complexity, game);
		colorService.generateRoundColors(game);
		saveGame(game);

		return game;
	}

	@Override
	public Game getGame(String gameId) {
		return gamesInfo.get(gameId);
	}

	@Override
	public void saveGame(Game game) {
		gamesInfo.put(game.getId(), game);
	}

	@Override
	public void setGameSpecifics(String complexity, Game game) {
		switch (complexity) {
		case "Easy":
			game.setComplexity(Complexity.EASY);
			game.setNumOfStartingColors(3);
			game.setRounds(10);
			break;
		case "Medium":
			game.setComplexity(Complexity.MEDIUM);
			game.setNumOfStartingColors(5);
			game.setRounds(15);
			break;
		case "Hard":
			game.setComplexity(Complexity.HARD);
			game.setNumOfStartingColors(8);
			game.setRounds(20);
			break;
		}
	}
}
