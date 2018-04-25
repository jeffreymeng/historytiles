/*
 * Programmers: Jeffrey Meng and Dylan Yang
 * Date: Apr 11, 2018
 * Purpose: TODO
 */
package graphics;

import java.awt.Graphics;
import javax.swing.JPanel;
import level.*;

public class ColumnLevelRenderEngine {
	JPanel panel;
	ColumnLevel level;
	Font rubik;

	public ColumnLevelRenderEngine(JPanel panel) {
		this.panel = panel;
		rubik = new Font("src/fonts/Rubik/Rubik-Regular.ttf", Font.PLAIN, 20);
		level = new ColumnLevel(ColumnLevel.ADDITION);
	}

	public void render(Graphics graphics) {

		Digit[][] grid = level.getAlignedDigitGrid();
		Digit digit;
		String text = "";
		Label[][] label = new Label[grid.length][grid[0].length];
		int levelWidth, levelHeight, levelX, levelY;// the width and height of
													// the level area, and the x
													// and y area.

		// calculate level's width, height, x, and y
		levelWidth = panel.getWidth() / 2;
		levelHeight = panel.getHeight() / 2;
		levelX = (panel.getWidth() / 2) - (levelWidth / 2);
		levelY = (panel.getHeight() / 2) - (levelHeight / 2);
		// demo rectangle
		graphics.drawRect(levelX, levelY, levelWidth, levelHeight);
		int labelX, labelY, labelContainerWidth, labelContainerHeight, labelLeftmost, labelTopmost;
		// labelContainerWidth refers to the box that contains the label, within
		// which we center the number, as opposed to the width of the text
		// itself. the same applies to labelContainerHeight
		for (int x = 0; x < grid.length; x++) {
			for (int y = 0; y < grid[x].length; y++) {

				digit = grid[x][y];

				if (digit.isSpace()) {

					continue;
				}
				//we do these calculations here, above the if statement, because
				//some of them are useful when drawing the box for a hidden number
				labelContainerWidth = levelWidth / grid.length;
				labelLeftmost = (levelX + (labelContainerWidth * x));
				labelX = labelLeftmost + (labelContainerWidth / 2);

				labelContainerHeight = levelHeight / grid[x].length;
				labelTopmost = levelY + (labelContainerHeight * y);
				labelY = labelTopmost + (labelContainerHeight / 2);
				
				if (!digit.isVisible()) {
					// draw a empty box
					//TODO: add padding for box
					graphics.drawRect(labelLeftmost, labelTopmost, labelContainerWidth, labelContainerHeight);
					continue;
				}
				
				label[x][y] = new Label(String.valueOf(digit.getValue()), rubik);

				System.out.println(levelWidth);
				System.out.println(grid[x].length);

				

				label[x][y].draw(graphics, labelX, labelY);
			}
			text += "\n";
		}

	}
}
