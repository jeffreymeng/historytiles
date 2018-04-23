/*
 * Programmer: Jeffrey Meng and Dylan Yang
 * Date: Mar 28 2018
 * Purpose:
 */

package graphics;

import java.awt.*;

import javax.swing.JPanel;

public class Label {
	private String text;
	private Font font;
	private Color color;
	public final static String TOP = "top";
	public final static String BOTTOM = "bottom";
	public final static String LEFT = "left";
	public final static String RIGHT = "right";
	public final static String VCENTER = "vcenter";
	public final static String HCENTER = "hcenter";
	public final static String CENTER = VCENTER + HCENTER;

	// concatenate option strings to create an option modifier.

	public Label(String text, Font font) {
		this.font = font;
		this.text = text;
		this.color = null;

	}

	public Label(String text, Font font, Color color) {
		this.font = font;
		this.text = text;
		this.color = color;

	}

	public void draw(Graphics graphics, int width, int height, String options) {
		draw(graphics, width, height, options, 0, 0);

	}

	public void draw(Graphics graphics, JPanel panel, String options) {
		draw(graphics, panel.getHeight(), panel.getWidth(), options, 0, 0);

	}

	public void draw(Graphics graphics, JPanel panel, String options,
			int xoffset, int yoffset) {
		draw(graphics, panel.getWidth(), panel.getHeight(), options, xoffset,
				yoffset);
	}

	public void draw(Graphics graphics, int width, int height, String options,
			int xoffset, int yoffset) {
		int x = -1, y = -1;

		graphics.setFont(font.get());

		int titleWidth = graphics.getFontMetrics().stringWidth(text);
		int titleHeight = graphics.getFontMetrics().getHeight();
		int padding = 25; // px
		if (options.indexOf("top") > -1) {
			y = padding;
		} else if (options.indexOf("bottom") > -1) {
			y = (height - padding) - (titleHeight);
		}
		if (options.indexOf("left") > -1) {
			x = padding;
		} else if (options.indexOf("right") > -1) {
			x = (width - padding) - (titleWidth);
		}
		if ((options.indexOf("vcenter") > -1) || (y == -1)) {// Default is
																// center
			y = ((height / 2) - (titleHeight / 2));

		}
		if ((options.indexOf("hcenter") > -1) || (x == -1)) {
			x = ((width / 2) - (titleWidth / 2));

		}
		y += yoffset;
		x += xoffset;

		// we don't need to pass title because height is static across the font
		draw(graphics, x, y);// get the top left corner

	}

	public void draw(Graphics graphics, int x, int y) {
		draw(graphics, x, y, true);
	}

	public void draw(Graphics graphics, int x, int y, boolean autoOffset) {
		if (color != null) {
			graphics.setColor(color);
		}
		System.out.println(text);
		String[] textComponents = text.split("\n");
		int lines = textComponents.length;
	
		int padding = 10;//padding on top
		int textWidth = graphics.getFontMetrics().stringWidth(text);
		int textHeight = graphics.getFontMetrics().getHeight();
		
		y = y - (((lines * textHeight) + (padding * lines)) / 2);
		
		
		for (int i = 0; i < textComponents.length; i ++) {
			graphics.drawString(text, x, (y + (i * (padding * textHeight))));
			System.out.println(i);
			System.out.println(padding * textHeight);
		}
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

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public void clearColor() {
		this.color = null;
	}
}
