package guess.the.color.project.logic;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.stereotype.Service;

import guess.the.color.project.model.Game;

@Service
public class ColorGeneratorServiceImpl implements ColorGeneratorService {

	public void generateRoundColors(Game game) {
		Color mainColor = generateMainColor();

		String mainColorAsHex = String.format("#%02x%02x%02x", mainColor.getRed(), mainColor.getGreen(),
				mainColor.getBlue());
		game.setMainColor(mainColorAsHex);

		List<Color> colors = generateColorsToChooseFrom(mainColor, game.getNumOfStartingColors());
		List<String> colorsAsHex = new ArrayList<String>();

		for (Color color : colors) {
			String colorString = String.format("#%02x%02x%02x", color.getRed(), color.getGreen(), color.getBlue());
			colorsAsHex.add(colorString);
		}

		game.setColorsToChooseFrom(colorsAsHex);
	}

	private Color generateMainColor() {
		Random random = new Random();
		int r = random.nextInt(255);
		int g = random.nextInt(255);
		int b = random.nextInt(255);

		Color mainColor = new Color(r, g, b);
		return mainColor;
	}

	private List<Color> generateColorsToChooseFrom(Color mainColor, int num) {
		Random random = new Random();
		int randomIndex = random.nextInt(num);
		List<Color> sideColors = new ArrayList<Color>();

		for (int i = 0; i < num; i++) {
			if (i == randomIndex) {
				sideColors.add(mainColor);
				continue;
			}

			int randomRed = random.nextInt(mainColor.getRed() + 1);
			int randomGreen = random.nextInt(mainColor.getGreen() + 1);
			int randomBlue = random.nextInt(mainColor.getBlue() + 1);

			Color color = new Color(randomRed, randomGreen, randomBlue);

			if (sideColors.contains(color) || color.equals(mainColor)) {
				i--;
				continue;
			}

			sideColors.add(color);
		}

		return sideColors;
	}
}
