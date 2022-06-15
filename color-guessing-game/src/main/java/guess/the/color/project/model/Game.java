package guess.the.color.project.model;

import java.util.List;

import lombok.Data;

@Data
public class Game {
	private String id;
	private Complexity complexity;
	private String mainColor;
	private List<String> colorsToChooseFrom;
	private int score = 0;
	private int currentRound = 1;
	private int rounds;
	private int bestScore;
	private int numOfStartingColors;
	private boolean isWon;
	
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public Complexity getComplexity() {
		return complexity;
	}
	
	public void setComplexity(Complexity complexity) {
		this.complexity = complexity;
	}
	
	public String getMainColor() {
		return mainColor;
	}
	
	public void setMainColor(String mainColor) {
		this.mainColor = mainColor;
	}
	
	public List<String> getColorsToChooseFrom() {
		return colorsToChooseFrom;
	}
	
	public void setColorsToChooseFrom(List<String> colorsToChooseFrom) {
		this.colorsToChooseFrom = colorsToChooseFrom;
	}
	
	public int getScore() {
		return score;
	}
	
	public void setScore(int score) {
		this.score = score;
	}
	
	public int getCurrentRound() {
		return currentRound;
	}
	
	public void setCurrentRound(int currentRound) {
		this.currentRound = currentRound;
	}
	
	public int getRounds() {
		return rounds;
	}
	
	public void setRounds(int rounds) {
		this.rounds = rounds;
	}
	
	public int getBestScore() {
		return bestScore;
	}
	
	public void setBestScore(int bestScore) {
		this.bestScore = bestScore;
	}
	
	public int getNumOfStartingColors() {
		return numOfStartingColors;
	}
	
	public void setNumOfStartingColors(int numOfStartingColors) {
		this.numOfStartingColors = numOfStartingColors;
	}
	
	public boolean isWon() {
		return isWon;
	}
	
	public void setWon(boolean isWon) {
		this.isWon = isWon;
	}
}
