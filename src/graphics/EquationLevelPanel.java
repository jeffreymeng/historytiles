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

public class EquationLevelPanel extends JPanel {

	private EquationLevel level = new EquationLevel(1, EquationLevel.COEFFICIENT, EquationLevel.MULTIPLY);

	public void paintComponent(Graphics g) {

	}

	public void setupWindow(EquationLevelPanel p) {
		JFrame f = new JFrame("Column Level Panel");

		Object[] equation = level.getEquation();

		f.setLayout(new GridLayout(1, equation.length));
		
		JPanel[] equationPanels = new JPanel[equation.length];
		JLabel[] equationLabels = new JLabel[equation.length];
		
		for (int i = 0; i < equation.length; i++) {
			if ((equation[i] instanceof Digit && !((Digit) equation[i]).isVisible()) ||
					(equation[i] instanceof Number && !((Number) equation[i]).isVisible()))
				equationLabels[i] = new JLabel("?");
			else
				equationLabels[i] = new JLabel(equation[i].toString());
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
