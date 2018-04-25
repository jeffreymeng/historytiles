/*
 * Programmer: Jeffrey Meng and Dylan Yang
 * Date: Mar 28 2018
 * Purpose:
 */

package graphics;

import java.awt.FontFormatException;
import java.io.File;
import java.io.IOException;

//this class is basically a holder for the awt font class, except 
// Maybe extend Font in the future?
public class Font {
	private java.awt.Font font;
	private float size;
	private int style;
	public static final int PLAIN = java.awt.Font.PLAIN;
	public static final int BOLD = java.awt.Font.BOLD;
	public static final int ITALIC = java.awt.Font.ITALIC;

	public Font(String location, int style, float size) {
		// a try catch statement is a way to safely run some code that might
		// cause an exception
		// without having all the code break if an exception is thrown.
		// in this case, it might be a corrupted file

		try {
			// can't think of a descriptive name for this class other than Font.
			this.size = size;
			font = java.awt.Font.createFont(java.awt.Font.TRUETYPE_FONT,
					new File(location));

			// font = font
		} catch (IOException | FontFormatException e) {

			System.out.println("ERROR init font, fallback to defualt. ERR_MSG:"
					+ e.getMessage());
			// throw new RuntimeException(e);

			// If no font is provided because an exception occurred, than
			// graphics font will still be set to default

		}

	}

	public java.awt.Font get() {
		return font.deriveFont(style, size);
	}

	public float getSize() {
		return size;
	}

	public void setSize(float size) {
		this.size = size;
	}

	public int getStyle() {
		return style;
	}

	public void setStyle(int style) {
		this.style = style;
	}

}
