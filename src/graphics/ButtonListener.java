/*
 * Programmer: Jeffrey Meng and Dylan Yang
 * Date: Mar 28 2018
 * Purpose: Provides the interface for a button listener class, used in conjunction with Button class.
 */
package graphics;

import java.awt.event.MouseEvent;

public interface ButtonListener {
	//e.getSource does not return the button
	//TODO: consider just returning source?
	//TODO: if above false, consider creating ButtonEvent class?
	public void buttonPressed(MouseEvent e, Button source);
	public void buttonClicked(MouseEvent e, Button source);
	public void buttonReleased(MouseEvent e, Button source);
}
