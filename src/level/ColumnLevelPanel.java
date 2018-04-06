/*
q * Programmers: Jeffrey Meng and Dylan Yang
 * Date: Apr 5, 2018
 * Purpose: TODO
 */

package level;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.GridLayout;

import level.*;

public class ColumnLevelPanel extends JPanel {
	
	private ColumnLevel level = new ColumnLevel('+');
	
	public void paintComponent(Graphics g) {
		
	}
	
	public void setupWindow(ColumnLevelPanel p) {
		JFrame f = new JFrame("Column Level Panel");
		
		Digit[][] numGrid = level.getNumGrid();
		
		// find the maximum number of digits in any number
		// store that number of digits in maxNumLength
		int maxNumLength = 0;
		for (int i = 0; i < numGrid.length; i++)
			if (numGrid[i].length > maxNumLength)
				maxNumLength = numGrid[i].length;
		
		// fill numGrid but with numbers right-aligned
		// numbers are aligned as they would if the problem were being done on paper
		Digit[][] alignedNumGrid = new Digit[numGrid.length][maxNumLength];
		for (int i = 0; i < numGrid.length; i++)
			for (int j = 0; j < numGrid[i].length; j++)
				alignedNumGrid[i][j + (maxNumLength - numGrid[i].length)] = numGrid[i][j];
		
		level.printNumGrid();
		
		for (Digit[] row : alignedNumGrid) {
			for (Digit num : row) {
				if (num instanceof Digit)
					System.out.print(num.getValue() + " ");
				else
					System.out.print("- ");
			}
			System.out.println();
		}
		
		/*
		for (int i = 0; i < alignedNumGrid.length; i++)
			for (int j = 0; i < alignedNumGrid[i].length; j++)
				if ( alignedNumGrid[i][j] instanceof  ) */

		f.setLayout( new GridLayout( maxNumLength, alignedNumGrid.length ));
		
		JPanel[][] panelGrid = new JPanel[alignedNumGrid.length][maxNumLength];
		
		p.setLayout( new GridLayout( alignedNumGrid.length, maxNumLength ) );
		
		for (int i = 0; i < alignedNumGrid.length; i++)
			for (int j = 0; j < alignedNumGrid[i].length; j++) {
				panelGrid[i][j] = new JPanel();
				if (alignedNumGrid[i][j] instanceof Digit) {
					System.out.println("i = " + i);
					System.out.println("j = " + j);
					System.out.println(alignedNumGrid[i][j].getValue());
					panelGrid[i][j].add( new JLabel( String.valueOf( alignedNumGrid[i][j].getValue() ) ), BorderLayout.CENTER );
				}
				f.add(panelGrid[i][j]);
			}
		
		f.setSize(600, 400);
		f.setLocationRelativeTo(null);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.add(p);
		f.setVisible(true);
	}
	
	public static void main(String[] args) {
		ColumnLevelPanel p = new ColumnLevelPanel();
		p.setupWindow(p);
	}
	
}
