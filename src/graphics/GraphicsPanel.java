package graphics;

import java.awt.*;

import javax.swing.*;

public class GraphicsPanel extends JPanel{
	final int width = 800;
	final int height = 600;
	JFrame frame = new JFrame("Mystery Numbers");
	public void paintComponent(Graphics graphics) {
		int width = frame.getWidth();
		int height = frame.getHeight();
		super.paintComponent(graphics);
		
		//graphics.setFont(new Font("Arial", Font.SANS_SERIF));
		//graphics.drawString("Mystery Numbers", 100, 100);
		
		
	}
	
	public void setupWindow(GraphicsPanel panel) {
		
		
		frame.setSize(width, height);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(panel);
		frame.setVisible(true);
		
	}

}
