/*
 * Programmers: Jeffrey Meng and Dylan Yang
 * Date: Apr 26, 2018
 * Purpose: TODO
 */

package graphics;

import java.awt.*;

import javax.swing.*;

import level.*;
import level.Number;

@SuppressWarnings("serial")
public class EquationLevelPanel extends JPanel {

	private EquationLevel level = new EquationLevel(1, EquationLevel.COEFFICIENT, EquationLevel.MULTIPLY);

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
	}

	public void setupWindow(EquationLevelPanel p) {
		JFrame f = new JFrame("Equation Level Panel");

		Object[] equation = level.getEquation();

		f.setLayout(new GridLayout(1, equation.length));

		JPanel[] equationPanels = new JPanel[equation.length];
		JLabel[] equationLabels = new JLabel[equation.length];

		for (int i = 0; i < equation.length; i++) {
			if (equation[i] instanceof Digit && !((Digit) equation[i]).isVisible()) {
				equationLabels[i] = new JLabel("?");
			} else if (equation[i] instanceof Number && !((Number) equation[i]).isVisible()) {
				String questionMarks = "";
				for (int j = 0; j < ((Number) equation[i]).getNumDigits(); j++)
					questionMarks += "?";
				equationLabels[i] = new JLabel(questionMarks);
			} else {
				equationLabels[i] = new JLabel(equation[i].toString());
			}
			equationPanels[i] = new JPanel();
			equationPanels[i].add(equationLabels[i]);
			f.add(equationPanels[i]);
		}

		f.setSize(600, 400);
		f.setLocationRelativeTo(null);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.add(p);
		f.setVisible(true);
	}

	public static void main(String[] args) {
		EquationLevelPanel p = new EquationLevelPanel();
		p.setupWindow(p);
	}
}
