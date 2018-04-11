/*
 * Programmers: Jeffrey Meng and Dylan Yang
 * Date: Apr 11, 2018
 * Purpose: 
 */
package graphics;

import java.awt.Color;
import javax.swing.JPanel;

public class DraggableButton extends Button{

	public DraggableButton(Color color, Label label, Color labelColor,
			JPanel panel) {
		super(color, label, labelColor, panel);
		
	}
	public DraggableButton(Color color, Label label, JPanel panel) {
		super(color, label, panel);
		
	}

}
