package guess.the.color.project.logic;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.stereotype.Service;

@Service
public class RoundServiceImpl implements RoundService {

	public Color generateMainColor() {
		Random random = new Random();
		int r = random.nextInt(255);
		int g = random.nextInt(255);
		int b = random.nextInt(255);

		Color mainColor = new Color(r, g, b);
		return mainColor;
	}

	public List<Color> generateColorsToChooseFrom(Color mainColor, int num) {
		Random random = new Random();
		int randomIndex = random.nextInt(num);
		List<Color> sideColors = new ArrayList<Color>();

		for (int i = 0; i < num; i++) {
			if (i == randomIndex) {
				sideColors.add(mainColor);
				continue;
			}

			int randomRed = random.nextInt(255 - mainColor.getRed());
			int randomGreen = random.nextInt(255 - mainColor.getGreen());
			int randomBlue = random.nextInt(255 - mainColor.getBlue());

			Color color = new Color(randomRed, randomGreen, randomBlue);
			sideColors.add(color);
		}

		return sideColors;
	}
}
