package dchaves_CSCI201_Assignment3;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.Border;

@SuppressWarnings("serial")
public class BattleshipFrame extends JFrame{
	
	public BattleshipFrame(BattleshipGrid bsgp, BattleshipGrid bsgc) {
		super("Battleship");
		setLayout(new GridLayout(1,2));

		JLabel pLabel = new JLabel("Player", SwingConstants.CENTER);
		JLabel cLabel = new JLabel("Computer", SwingConstants.CENTER);
		JLabel log = new JLabel("Log:");
				
		bsgp.setBorder(BorderFactory.createLineBorder(Color.black));
		bsgc.setBorder(BorderFactory.createLineBorder(Color.black));
		
		JPanel player = new JPanel(new BorderLayout());
		player.add(bsgp,BorderLayout.CENTER);
		player.add(pLabel,BorderLayout.PAGE_START);
		player.add(log, BorderLayout.PAGE_END);

		JPanel computer = new JPanel(new BorderLayout());
		computer.add(bsgc,BorderLayout.CENTER);
		computer.add(cLabel, BorderLayout.PAGE_START);
				
		add(player);
		add(computer);
		
		setSize(880,480);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setVisible(true);
	}

}