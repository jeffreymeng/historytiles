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
		int levelWidth, levelHeight, levelX, levelY;//the width and height of the level area, and the x and y area.
		
		//calculate level's width, height, x, and y
		levelWidth = panel.getWidth() / 2;
		levelHeight = panel.getHeight() / 2;
		levelX = panel.getWidth() - (levelWidth / 2);
		levelY = panel.getHeight() - (levelHeight / 2);
		//demo rectangle
		graphics.drawRect(levelX, levelY,  levelWidth,  levelHeight);
		
		
		int rowLength;
		for (int x = 0; x < grid.length; x++) {
			for (int y = 0; y < grid[x].length; y++) {
				
				digit = grid[x][y];
				
				if (digit.isSpace()) {
					
					continue;
				} else if (!digit.isVisible()) {
					//draw a empty box
					continue;
				}
				
				label[x][y] = new Label(String.valueOf(digit.getValue()), rubik);
				
			}
			text += "\n";
		}
		
	}
}
