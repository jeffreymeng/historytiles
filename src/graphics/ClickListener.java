/*
 * Programmer: Jeffrey Meng and Dylan Yang
 * Date: Mar 28 2018
 * Purpose: Provides the interface for a clickzone class.
 */
package graphics;

import java.awt.event.MouseEvent;

public interface ClickListener {
	//e.getSource does not return the clockzone
	//TODO: consider just returning source?
	//TODO: if above false, consider creating ButtonEvent class?
	public void clickzonePressed(MouseEvent e, Clickzone source);
	public void clickzoneClicked(MouseEvent e, Clickzone source);
	public void clickzoneReleased(MouseEvent e, Clickzone source);
}
