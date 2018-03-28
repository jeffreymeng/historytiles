/*
 * Programmer: Jeffrey Meng and Dylan Yang
 * Date: Mar 28 2018
 * Purpose:
 */

package graphics;

import java.awt.*;
import java.io.*;

import javax.swing.*;

public class Label {
	private String text;
	private Font font;
	public Label(String name, Font font) {
		this.font = font;
	}
	
	public void draw(Graphics graphics, int width, int height) {
		
		graphics.setFont(font.get());

		int titleWidth = graphics.getFontMetrics().stringWidth(text);
		int titleHeight = graphics.getFontMetrics().getHeight();
		// we don't need to pass title because height is static across the font
		graphics.drawString(text, ((width / 2) - (titleWidth / 2)),
				((height / 2) - (titleHeight / 2)));// get the top left corner

	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Font getFont() {
		return font;
	}

	public void setFont(Font font) {
		this.font = font;
	}
}
