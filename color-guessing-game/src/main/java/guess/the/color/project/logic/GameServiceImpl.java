package guess.the.color.project.logic;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
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
	private RoundServiceImpl roundService;
	public Map<String, Game> gamesInfo = new ConcurrentHashMap<>();

	public Game startNewGame(String complexity) {
		Game game = new Game();

		game.setId(UUID.randomUUID().toString());
		setGameSpecifics(complexity, game);
		generateRoundColors(game);
		gamesInfo.put(game.getId(), game);

		return game;
	}

	public Game getGame(String gameId) {
		return gamesInfo.get(gameId);
	}

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

	public void generateRoundColors(Game game) {
		Color mainColor = roundService.generateMainColor();

		String mainColorInHex = String.format("#%02x%02x%02x", mainColor.getRed(), mainColor.getGreen(),
				mainColor.getBlue());
		game.setMainColor(mainColorInHex);

		List<Color> colors = roundService.generateColorsToChooseFrom(mainColor, game.getNumOfStartingColors());
		List<String> colorsAsHex = new ArrayList<String>();

		for (Color color : colors) {
			String colorString = String.format("#%02x%02x%02x", color.getRed(), color.getGreen(), color.getBlue());
			colorsAsHex.add(colorString);
		}

		game.setColorsToChooseFrom(colorsAsHex);
	}

	public Game checkColor(Game game, String color) {
		String mainColor = game.getMainColor();

		int currentRound = game.getCurrentRound();
		int maxRounds = game.getRounds();
		int currentScore = game.getScore();

		if (mainColor.equals(color)) {
			if (currentRound == maxRounds) {
				game.setWon(true);
			} else {
				game.setCurrentRound(currentRound + 1);
				generateRoundColors(game);
			}
			currentScore += 100;
		} else {
			currentScore -= 50;
		}

		game.setScore(currentScore);
		return game;
	}
}
