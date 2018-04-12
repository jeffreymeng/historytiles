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
	}

	public void render(Graphics graphics) {
		Digit[][] grid = level.getAlignedDigitGrid();
		String text = "";
		Label label;
		
		for (int x = 0; x < grid.length; x ++) {
			for (int y = 0; y < grid[x].length; y ++) {
				text += grid[x][y] + "  ";
			}
			text += "\n";
		}
		label = new Label(text, rubik);
		label.draw(graphics, panel, Label.CENTER);
	}
}
