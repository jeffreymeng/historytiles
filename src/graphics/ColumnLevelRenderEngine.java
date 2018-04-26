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
		drawGrid(graphics);
	}

	public void drawGrid(Graphics graphics) {
		Digit[][] grid = level.getAlignedDigitGrid();
		Digit digit;
		String text = "";
		Label[][] label = new Label[grid.length][grid[0].length];
		// the width and height of the level area, and the x and y area.
		int levelWidth, levelHeight, levelX, levelY;

		int levelPadding;

		int equalsBarHeight = 5;
		int equalsBarPadding = 5;

		// calculate level's width, height, x, and y
		levelWidth = panel.getWidth() / 2;

		// add equals line height and padding for top and bottom to the
		// levelHeight
		levelHeight = (panel.getHeight() / 2)
				+ (equalsBarHeight + (equalsBarPadding * 2));
		levelX = (panel.getWidth() / 2) - (levelWidth / 2);
		levelY = (panel.getHeight() / 2) - (levelHeight / 2);

		levelPadding = 10;// on each side
		// rectangle around level.
		// we double the padding for width and height to account for the width
		// of the padding on both sides.
		graphics.drawRect(levelX - levelPadding, levelY - levelPadding,
				levelWidth + (levelPadding * 2), levelHeight
						+ (levelPadding * 2));
		int labelX, labelY, labelContainerWidth, labelContainerHeight, labelLeftmost, labelTopmost;

		int labelBoxPadding;// padding for the box around hidden numbers. Unlike
							// the padding around the
		// whole level, this padding is added into the box

		int labelBoxOffset = 0;// the amount to offset the label box

		labelBoxPadding = 5;

		// labelContainerWidth refers to the box that contains the label, within
		// which we center the number, as opposed to the width of the text
		// itself. the same applies to labelContainerHeight

		// y is first when you visualize like a coordinate panes

		for (int y = 0; y < grid.length; y++) {
			//TODO: add addition sign
			for (int x = 0; x < grid[y].length; x++) {

				digit = grid[y][x];

				if (digit.isSpace()) {
					System.out.print(" ");
					continue;
				}
				// we do these calculations here, above the if statement,
				// because
				// some of them are useful when drawing the box for a hidden
				// number
				labelContainerWidth = levelWidth / (grid[y].length + 1);
				
				//we shift everything to the right one place to make space for the addition sign
				labelLeftmost = (levelX + (labelContainerWidth * (x + 1)));
				labelX = labelLeftmost + (labelContainerWidth / 2);

				// we add the padding for top and bottom, as well as the height
				// of the equals bar
				// here
				labelContainerHeight = (levelHeight / grid.length);
				labelTopmost = levelY + (labelContainerHeight * y);
				labelY = labelTopmost + (labelContainerHeight / 2);

				// if this is the last row, we add the width and padding of the
				// equals bar
				System.out.println(y);
				System.out.println(grid.length - 1);
				if (y == (grid.length - 1)) {
					System.out.println(true);
					labelY += equalsBarHeight + (equalsBarPadding * 2);

					// for the hidden number boxes.
					labelBoxOffset = equalsBarHeight + (equalsBarPadding * 2);
				}

				// for debugging
				text += digit.getValue();

				if (!digit.isVisible()) {
					// draw a empty box

					graphics.drawRect(labelLeftmost + labelBoxPadding,
							labelTopmost + labelBoxPadding + labelBoxOffset,
							labelContainerWidth - (labelBoxPadding * 2),
							labelContainerHeight - (labelBoxPadding * 2) - labelBoxOffset);
					continue;
				}

				label[y][x] = new Label(String.valueOf(digit.getValue()), rubik);

				// System.out.println(levelWidth);
				// System.out.println(grid.length);
				// System.out.println(grid[y].length);

				label[y][x].draw(graphics, labelX, labelY);
			}

			// if this is between the second to last and last rows, we draw the
			// plus sign and bar
			if (y == (grid.length - 1)) {

				// the equals bar y is at the top of the last line, because
				// during that line we offset it's y by 15 px.
				int equalsBarY = (levelY + ((levelHeight / grid.length) * (y)))
						+ equalsBarPadding;
				// draw the bar with padding of 5px on each side
				graphics.fillRect(levelX + equalsBarPadding, equalsBarY,
						levelWidth - (equalsBarPadding * 2), equalsBarHeight);
			}
			text += "\n";

		}
		System.out.println(text);
	}
}
