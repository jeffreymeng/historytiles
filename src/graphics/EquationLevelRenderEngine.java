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
public class EquationLevelRenderEngine extends JPanel {

	private EquationLevel level = new EquationLevel(1, EquationLevel.COEFFICIENT, EquationLevel.MULTIPLY, EquationLevel.CONSTANT);
	private JPanel[] equationPanels;
	private JLabel[] equationLabels;

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
	}

	public void setupWindow(EquationLevelRenderEngine p) {
		JFrame f = new JFrame("Equation Level Panel");

		Object[] equation = level.getEquation();

		f.setLayout(new GridLayout(1, equation.length)); // one row; one column for each equation component

		equationPanels = new JPanel[equation.length];
		equationLabels = new JLabel[equation.length];

		for (int i = 0; i < equation.length; i++) {
			if (equation[i] instanceof Digit && !((Digit) equation[i]).isVisible()) {
				equationLabels[i] = new JLabel("?");
			} else if (equation[i] instanceof Number && !((Number) equation[i]).isVisible()) {
				String questionMarks = ""; // sequence of question marks, one for each digit
				for (int j = 0; j < ((Number) equation[i]).getNumDigits(); j++) // for each digit in equation[i]
					questionMarks += "?"; // add one question mark
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

	public void fill(int index, int number) {
		if (level.fill(index, number)) {
			equationPanels[index].setBackground(Color.GREEN);
			repaint(); // repaint to ensure that now-visible variable is displayed
		} else {
			equationPanels[index].setBackground(Color.RED);
		}
	}
	
	public static void main(String[] args) {
		EquationLevelRenderEngine p = new EquationLevelRenderEngine();
		p.setupWindow(p);
		p.fill(0, 5);
	}
}
