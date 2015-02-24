package dchaves_CSCI201_Assignment3;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class BattleshipFrame extends JFrame{
	
	public BattleshipFrame(BattleshipGrid bsgp, BattleshipGrid bsgc) {
		super("Battleship");
		JLabel pLabel = new JLabel("Player");
		JLabel cLabel = new JLabel("Computer");		
		
//		JPanel player = new JPanel();
		
//		player.add(playerLabel);
//		player.add(bsgPlayer);
		
//		JPanel computer = new JPanel();

//		computer.add(computerLabel);
//		computer.add(bsgComputer);
		
		setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		
		c.gridx = 0;
		c.gridy = 0;
		add(pLabel, c);
		c.gridx = 0;
		c.gridy = 1;
		add(bsgp, c);
		c.gridx = 1;
		c.gridy = 0;
		add(cLabel, c);
		c.gridx = 1;
		c.gridy = 1;
		add(bsgc, c);
		
		setSize(1280,480);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setVisible(true);
	}

}