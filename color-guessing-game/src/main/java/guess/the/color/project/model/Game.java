package guess.the.color.project.model;

import java.util.List;

import lombok.Data;

@Data
public class Game {
	private String id;
	private Complexity complexity;
	private List<String> colorsToChooseFrom;
	private String mainColor;
	private int score = 0;
	private int currentRound = 0;
	private int rounds;
	private int bestScore;
	private int numOfStartingColors;
	private boolean isWon;
}
