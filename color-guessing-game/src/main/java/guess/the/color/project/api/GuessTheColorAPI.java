package guess.the.color.project.api;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import guess.the.color.project.logic.GameService;
import guess.the.color.project.logic.RoundManipulationService;
import guess.the.color.project.model.Game;

@RestController
@RequestMapping("/api")
public class GuessTheColorAPI {

	@Autowired
	private GameService gameService;

	@Autowired
	private RoundManipulationService roundService;

	@PostMapping("/games")
	public ResponseEntity<Game> createGame(@RequestBody String complexity, HttpSession session) {

		Game game = gameService.startNewGame(complexity);

		if (session.getAttribute("bestScore") != null) {
			game.setBestScore(Integer.parseInt(session.getAttribute("bestScore").toString()));
		} else {
			session.setAttribute("bestScore", 0);
		}

		return ResponseEntity.ok(game);
	}

	@PostMapping("/color")
	public ResponseEntity<Game> checkColor(@RequestBody Map<String, String> gameData, HttpSession session) {

		Game game = gameService.getGame(gameData.get("gameId"));
		Game modifiedGame = roundService.checkColor(game, gameData.get("color"));

		if (modifiedGame.isWon()) {
			int currentScore = modifiedGame.getScore();
			int bestScore = modifiedGame.getBestScore();

			if (currentScore > bestScore) {
				bestScore = currentScore;
				modifiedGame.setBestScore(bestScore);

				gameService.saveGame(modifiedGame);

				session.setAttribute("bestScore", bestScore);
			}
		}

		return ResponseEntity.ok(modifiedGame);
	}

}
