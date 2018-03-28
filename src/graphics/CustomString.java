package graphics;

import java.awt.*;
import java.io.*;

import javax.swing.*;

public class CustomString {
	private Font rubik;

	public CustomString() {
		try {
			rubik = Font.createFont(Font.TRUETYPE_FONT,
					new File("src/fonts/Rubik/Rubik-Regular.ttf")).deriveFont(
					40.0f);// float

			// font = font
		} catch (IOException | FontFormatException e) {

			System.out.println("ERROR init font, fallback to defualt. ERR_MSG:"
					+ e.getMessage());
			// throw new RuntimeException(e);

			// If no font is provided because an exception occurred, than
			// graphics font will still be set to default

		}
	}

	public void draw(Graphics graphics, int width, int height) {
		// a try catch statement is a way to safely run some code that might
		// cause an exception
		// without having all the code break if an exception is thrown.
		// in this case, it might be a corrupted file

		if (rubik != null) {
			graphics.setFont(rubik);
		}

		String title = "Mystery Numbers";
		int titleWidth = graphics.getFontMetrics().stringWidth(title);
		int titleHeight = graphics.getFontMetrics().getHeight();
		// we don't need to pass title because height is static across the font
		graphics.drawString(title, ((width / 2) - (titleWidth / 2)),
				((height / 2) - (titleHeight / 2)));// get the top left corner

	}
}
