package graphics;

import java.awt.*;
import java.io.*;

import javax.swing.*;

public class GraphicsPanel extends JPanel {
	final int width = 800;
	final int height = 600;
	JFrame frame = new JFrame("Mystery Numbers");
	Font font;
	public void paintComponent(Graphics graphics) {
		//int width = frame.getWidth();
		//int height = frame.getHeight();
		super.paintComponent(graphics);

		// a try catch statement is a way to safely run some code that might cause an exception
		// without having all the code break if an exception is thrown.
		// in this case, it might be a corrupted file, but since we know it isn't
		// we will just throw the exception.
		try {
			font = Font.createFont(Font.TRUETYPE_FONT, new File(
					"src/fonts/Rubik/Rubik-Regular.ttf"));

			// Derive and return a 12 pt version:
			// Need to use float otherwise
			// it would be interpreted as style

			font = font.deriveFont(100.0f);
		} catch (IOException | FontFormatException e) {
			
			System.out.println("ERROR init font, fallback to defualt. ERR_MSG:" + e.getMessage());
			throw new RuntimeException(e);
			
			//make fallback font here, and once it works comment out exception above

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
