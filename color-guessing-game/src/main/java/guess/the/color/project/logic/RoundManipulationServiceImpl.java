package guess.the.color.project.logic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import guess.the.color.project.model.Complexity;
import guess.the.color.project.model.Game;

@Service
public class RoundManipulationServiceImpl implements RoundManipulationService {

	@Autowired
	private ColorGeneratorService colorService;

	@Override
	public Game checkColor(Game game, String color) {
		String mainColor = game.getMainColor();
		Complexity complexity = game.getComplexity();

		int currentRound = game.getCurrentRound();
		int maxRounds = game.getRounds();
		int currentScore = game.getScore();
		int currentCircleCount = game.getNumOfStartingColors();

		if (mainColor.equals(color)) {
			if (currentRound % 3 == 0 && complexity != Complexity.HARD) {
				currentCircleCount += 1;
			} else if (currentRound % 4 == 0 && complexity == Complexity.HARD) {
				currentCircleCount += 1;
			}

			game.setNumOfStartingColors(currentCircleCount);

			if (currentRound == maxRounds) {
				game.setWon(true);
			} else {
				game.setCurrentRound(currentRound + 1);
				colorService.generateRoundColors(game);
			}

			if (complexity == Complexity.EASY) {
				currentScore += 100;
			} else if (complexity == Complexity.MEDIUM) {
				currentScore += 200;
			} else if (complexity == Complexity.HARD) {
				currentScore += 300;
			}
		} else {
			currentScore -= 50;
		}

		game.setScore(currentScore);
		return game;
	}
}
