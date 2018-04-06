/*
 * Programmer: Jeffrey Meng and Dylan Yang
 * Date: Mar 28 2018
 * Purpose:
 */

package graphics;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class GamePanel extends JPanel implements ButtonListener {
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
	
	public GamePanel(JFrame frame, MysteryNumbersGame game) {
		super();
		

		this.frame = frame;
		this.game = game;
	}

	public void paintComponent(Graphics graphics) {
		super.paintComponent(graphics);
		int width = frame.getWidth();
		int height = frame.getHeight();

		Font rubik = new Font("src/fonts/Rubik/Rubik-Regular.ttf", Font.PLAIN,
				50);

		Label title = new Label("Game", rubik);
		title.draw(graphics, width, height, Label.CENTER, 0, -50);
		rubik.setSize(15);
		
		// play button
		Label playButtonLabel = new Label("Play", rubik, Color.WHITE);
		Button playButton = new Button(Button.BLUE, playButtonLabel, this);
		playButton.addButtonListener(this);
		playButton.draw(graphics, 150, 50, Button.CENTER, 0, 150);
		

	}

	public void buttonPressed(MouseEvent e) {
		System.out.println("Button pressed");
		
	}

	public void buttonClicked(MouseEvent e) {
		System.out.println("Button clicked");
		System.out.println(e);
	}
	

	public void buttonReleased(MouseEvent e) {
		System.out.println("Button released");
		
	}

	

}
