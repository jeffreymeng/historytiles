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
		Label label;

		for (int x = 0; x < grid.length; x++) {
			for (int y = 0; y < grid[x].length; y++) {
				System.out.println( grid[x].length);
				System.out.println(x);
				System.out.println(y);
				digit = grid[x][y];
				System.out.println(digit.getValue());
				//text += (String.valueOf(digit.getValue())) + "  ";
			}
			text += "\n";
		}
		label = new Label(text, rubik);
		label.draw(graphics, panel, Label.CENTER);
	}
}
