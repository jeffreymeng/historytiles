/*
 * Programmer: Jeffrey Meng and Dylan Yang
 * Date: Mar 28 2018
 * Purpose: The panel containing the game portion of the game(as opposed to the title/menu portion, etc.)
 */

package graphics;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import level.*;

public class GamePanel extends JPanel /* implements ButtonListener */ {
	final int width = 800;
	final int height = 600;
	JFrame frame = new JFrame("Mystery Numbers");
	Font rubik;
	// Blue: #0000ff
	// Blue Outline: A dark blue border - #0000B3
	// Blue Dark: A slightly darker blue - #000080
	final public Color BLUE = new Color(0, 0, 255);
	final public Color BLUE_DARK = new Color(0, 0, 230);
	final public Color BLUE_OUTLINE = new Color(0, 0, 179);
	int resetButtonWidth, resetButtonHeight, resetButtonX, resetaButtonY;
	Color resetButtonColor = BLUE;
	MysteryNumbersGame game;
	Button playButton;
	Label title, playButtonLabel, instructions;
	ColumnLevelRenderEngine colLevelRenderEngine;

	public GamePanel(JFrame frame, MysteryNumbersGame game) {
		super();

		this.frame = frame;
		this.game = game;

		Font rubik = new Font("src/fonts/Rubik/Rubik-Regular.ttf", Font.PLAIN, 20);

		
		
		colLevelRenderEngine = new ColumnLevelRenderEngine(this);

	}

	public void paintComponent(Graphics graphics) {
		super.paintComponent(graphics);
		int width = frame.getWidth();
		int height = frame.getHeight();
		
		
		

		colLevelRenderEngine.render(graphics);
		

	}

}
