package dchaves_CSCI201_Assignment3;

import javax.swing.JFrame;

@SuppressWarnings("serial")
public class BattleshipFrame extends JFrame{
	
	public BattleshipFrame(BattleshipGrid bsg) {
		super("Battleship");
		
		add(bsg);
		
		setSize(640,480);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setVisible(true);
	}

}