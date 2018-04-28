/*
 * Programmer: Jeffrey Meng and Dylan Yang
 * Date: Mar 28 2018
 * Purpose: This class contains the title portion of the game.
 */

package graphics;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class TitlePanel extends JPanel implements ButtonListener {
	
	final int width = 800;
	final int height = 600;
	JFrame frame = new JFrame("Mystery Numbers");
	Font rubik;
	Button playButton;
	MysteryNumbersGame game;

	public TitlePanel(JFrame frame, MysteryNumbersGame game) {
		super();
		rubik = new Font("src/fonts/Rubik/Rubik-Regular.ttf", Font.PLAIN, 15);
		playButton = new Button(Button.BLUE, new Label("Play", rubik,
				Color.WHITE), this);
		playButton.addButtonListener(this);

		this.frame = frame;
		this.game = game;
	}

	public void paintComponent(Graphics graphics) {
		super.paintComponent(graphics);
		int width = frame.getWidth();
		int height = frame.getHeight();

		rubik.setSize(50);
		Label title = new Label("Mystery Numbers", rubik);
		title.draw(graphics, width, height, Label.CENTER, 0, -50);

		rubik.setSize(15);
		/* TODO: fix instructions
		Label instructions = new Label(
				"INSTRUCTIONS\nClick on a slot to select it.\nThen click on a number button to fill it out.\nYou complete the level when the numbers add up.",
				rubik);
		instructions.draw(graphics, width, height, Label.HCENTER, 0, 100);
		 */
		
		rubik.setSize(15);
		Label version = new Label("version 1.0.0", rubik);
		version.draw(graphics, width, height, Label.BOTTOM);

		playButton.draw(graphics, 150, 50, Button.CENTER, 0, 75);

	}

	public void buttonPressed(MouseEvent e, Button source) {

	}

	public void buttonClicked(MouseEvent e, Button source) {
		game.startGame();

	}

	public void buttonReleased(MouseEvent e, Button source) {

	}

}
