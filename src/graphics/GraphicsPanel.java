package graphics;

import java.awt.*;
import java.io.*;

import javax.swing.*;

public class GraphicsPanel extends JPanel {
	final int width = 800;
	final int height = 600;
	JFrame frame = new JFrame("Mystery Numbers");

	public void paintComponent(Graphics graphics) {
		//int width = frame.getWidth();
		//int height = frame.getHeight();
		super.paintComponent(graphics);

		// a try catch 
		try {
			Font font = Font.createFont(Font.TRUETYPE_FONT, new File(
					"fonts/Rubik/Rubik-Regular.ttf"));

			// Derive and return a 12 pt version:
			// Need to use float otherwise
			// it would be interpreted as style

			font.deriveFont(12f);
		} catch (IOException | FontFormatException e) {
			// this should never be thrown.
			System.out.println(e.getMessage());
			throw new RuntimeException(e);
		}
		graphics.drawString("Mystery Numbers", 100, 100);

	}

	public void setupWindow(GraphicsPanel panel) {

		frame.setSize(width, height);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(panel);
		frame.setVisible(true);

	}

}
