/*
 * Programmer: Jeffrey Meng and Dylan Yang
 * Date: Mar 28 2018
 * Purpose:
 */

package graphics;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class GraphicsPanel extends JPanel implements MouseListener {
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
	int playButtonWidth, playButtonHeight, playButtonX, playButtonY;
	Color playButtonColor = BLUE;

	public GraphicsPanel(JFrame frame) {
		super();
		addMouseListener(this);

		this.frame = frame;
	}

	public void paintComponent(Graphics graphics) {
		super.paintComponent(graphics);
		int width = frame.getWidth();
		int height = frame.getHeight();

		Font rubik = new Font("src/fonts/Rubik/Rubik-Regular.ttf", Font.PLAIN,
				50);

		Label title = new Label("Mystery Numbers", rubik);
		title.draw(graphics, width, height, Label.CENTER, 0, -50);

		rubik.setSize(15);
		Label version = new Label("version 1.0.0-alpha.1", rubik);
		version.draw(graphics, width, height, Label.BOTTOM);

		// play button
		graphics.setColor(playButtonColor);
		// width is center, height is center + 100px offset

		playButtonWidth = 150;
		playButtonHeight = 50;
		playButtonX = (width / 2 - (playButtonWidth / 2));
		playButtonY = (height / 2 + ((playButtonHeight / 2) + 75/* offset */));

		graphics.fillRect(playButtonX, playButtonY, playButtonWidth,
				playButtonHeight);
		graphics.setColor(BLUE_OUTLINE);
		graphics.drawRect(playButtonX, playButtonY, playButtonWidth,
				playButtonHeight);

		graphics.setColor(Color.WHITE);
		Label playButtonText = new Label("Play", rubik);
		playButtonText.draw(graphics, width, height, Label.CENTER, 0, 140);

	}

	public void mouseClicked(MouseEvent e) {
		if ((e.getX() > playButtonX && e.getX() < (playButtonX + playButtonWidth))
				&& (e.getY() > playButtonY && e.getY() < (playButtonY + playButtonHeight))) {
			System.out.println("Play Button Clicked");
		}

	}

	public void mousePressed(MouseEvent e) {
		if ((e.getX() > playButtonX && e.getX() < (playButtonX + playButtonWidth))
				&& (e.getY() > playButtonY && e.getY() < (playButtonY + playButtonHeight))) {
			playButtonColor = BLUE_DARK;
		}
		this.repaint();
	}

	public void mouseReleased(MouseEvent e) {
		if (playButtonColor != BLUE) {
			playButtonColor = BLUE;
		}
		this.repaint();
	}

	public void mouseEntered(MouseEvent e) {

	}

	public void mouseExited(MouseEvent e) {

	}

}
