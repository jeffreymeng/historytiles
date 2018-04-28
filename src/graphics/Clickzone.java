/*
 * Programmer: Jeffrey Meng and Dylan Yang
 * Date: Mar 28 2018
 * Purpose: Allows a user to define an area as a clickzone, then get a callback when certain mouse events are peformed(pressed, released, clicked)
 */

package graphics;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JPanel;

public class Clickzone implements MouseListener {
	ClickListener clickInterface;

	private int x, y, width, height;

	public final static Color BLUE = new Color(0, 0, 255);

	public Clickzone(int x, int y, int width, int height, JPanel panel, ClickListener listener) {

		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		clickInterface = listener;
		panel.addMouseListener(this);
	}

	public void mouseClicked(MouseEvent e) {

		// check if the click was inside the button
		if ((e.getX() > x && e.getX() < (x + width)) && (e.getY() > y && e.getY() < (y + height))) {
			// callback
			clickInterface.clickzoneClicked(e, this);
		}

	}

	public void mousePressed(MouseEvent e) {
		
		// check if the press was inside the button
		if ((e.getX() > x && e.getX() < (x + width)) && (e.getY() > y && e.getY() < (y + height))) {
			
			clickInterface.clickzonePressed(e, this);
		}

	}

	public void mouseReleased(MouseEvent e) {

		if ((e.getX() > x && e.getX() < (x + width)) && (e.getY() > y && e.getY() < (y + height))) {
			
			clickInterface.clickzoneReleased(e, this);
		}
	}

	public void mouseEntered(MouseEvent e) {

	}

	public void mouseExited(MouseEvent e) {

	}

	

	public int getX() {
		return x;
	}

	
	public int getWidth() {
		return width;
	}

	
	public void setWidth(int width) {
		this.width = width;
	}

	
	public int getHeight() {
		return height;
	}

	
	public void setHeight(int height) {
		this.height = height;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	

}
