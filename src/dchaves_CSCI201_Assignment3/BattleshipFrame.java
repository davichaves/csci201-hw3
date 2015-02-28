package dchaves_CSCI201_Assignment3;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.filechooser.FileNameExtensionFilter;

@SuppressWarnings("serial")
public class BattleshipFrame extends JFrame{
	
	public BattleshipFrame(BattleshipGrid bsgp, BattleshipGrid bsgc) {
		super("Battleship");
		setLayout(new GridLayout(1,2));

		JLabel pLabel = new JLabel("Player", SwingConstants.CENTER);
		JLabel cLabel = new JLabel("Computer", SwingConstants.CENTER);
		JLabel log = new JLabel("Log: Edit mode");
		JLabel file = new JLabel("File:");
		log.setFont(new Font("Arial", Font.PLAIN, 24));
		
		JPanel chooseFile = new JPanel();
		chooseFile.setLayout(new GridLayout(1,2));
		
		JButton selectFile = new JButton("Select File:");
		selectFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				JFileChooser chooser = new JFileChooser();
			    FileNameExtensionFilter filter = new FileNameExtensionFilter(
			        ".battle files", "battle");
			    chooser.setFileFilter(filter);
			    int returnVal = chooser.showOpenDialog(getParent());
			    if(returnVal == JFileChooser.APPROVE_OPTION) {
			       System.out.println("You chose to open this file: " +
			            chooser.getSelectedFile().getName());
			    }
			}
		});
		
		JButton start = new JButton("START");
		
		chooseFile.add(selectFile);
		chooseFile.add(file);
		chooseFile.add(start);
				
		bsgp.setBorder(BorderFactory.createLineBorder(Color.black));
		bsgc.setBorder(BorderFactory.createLineBorder(Color.black));
		
		JPanel player = new JPanel(new BorderLayout());
		player.add(bsgp,BorderLayout.CENTER);
		player.add(pLabel,BorderLayout.PAGE_START);
		player.add(log, BorderLayout.PAGE_END);

		JPanel computer = new JPanel(new BorderLayout());
		computer.add(bsgc,BorderLayout.CENTER);
		computer.add(cLabel, BorderLayout.PAGE_START);
		computer.add(chooseFile, BorderLayout.PAGE_END);
				
		add(player);
		add(computer);
		
		setSize(880,480);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setVisible(true);
	}

}