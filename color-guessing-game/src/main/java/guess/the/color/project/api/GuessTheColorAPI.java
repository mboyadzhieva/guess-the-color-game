package guess.the.color.project.api;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import guess.the.color.project.logic.GameServiceImpl;
import guess.the.color.project.logic.RoundServiceImpl;
import guess.the.color.project.model.Game;

@RestController
@RequestMapping("/api")
public class GuessTheColorAPI {

	@Autowired
	private GameServiceImpl gameService;

	@Autowired
	private RoundServiceImpl roundService;

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

	@GetMapping("/games/{gameId}")
	public Game getGame(@PathVariable Long gameId) {
		return null;
	}

	@PostMapping("/color")
	public ResponseEntity<Game> checkColor(@RequestBody Map<String, String> gameData) {

		// color : "#dfds"
		// gameId: fklsmfknsmlkf
		Game game = gameService.getGame(gameData.get("gameId"));
		Game modifiedGame = gameService.checkColor(game, gameData.get("color"));

		return ResponseEntity.ok(modifiedGame);
	}

}
