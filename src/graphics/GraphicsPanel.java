/*
 * Programmer: Jeffrey Meng and Dylan Yang
 * Date: Mar 28 2018
 * Purpose:
 */


package graphics;

import java.awt.*;
import javax.swing.*;

public class GraphicsPanel extends JPanel {
	final int width = 800;
	final int height = 600;
	JFrame frame = new JFrame("Mystery Numbers");
	Font rubik;
	public void paintComponent(Graphics graphics) {
		super.paintComponent(graphics);
		int width = frame.getWidth();
		int height = frame.getHeight();

		Font rubik = new Font("src/fonts/Rubik/Rubik-Regular.ttf", Font.PLAIN, 20);
		Label title = new Label("Mystery Numbers", rubik);
		title.draw(graphics, width, height, Label.BOTTOM);
	}

	public void setupWindow(GraphicsPanel panel) {
		
		frame.setSize(width, height);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(panel);
		frame.setVisible(true);

	}

}
