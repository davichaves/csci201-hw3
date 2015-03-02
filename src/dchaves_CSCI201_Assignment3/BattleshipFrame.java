package dchaves_CSCI201_Assignment3;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.KeyStroke;
import javax.swing.SwingConstants;
import javax.swing.filechooser.FileNameExtensionFilter;

@SuppressWarnings("serial")
public class BattleshipFrame extends JFrame{
	private JLabel file;
	private KeyStroke ctrlAKeyStroke, ctrlHKeyStroke;
	private JMenuBar jmb;
	private JMenu infoMenu;
	private JMenuItem infoMenuItem, infoMenuItem2;
	
	public BattleshipFrame(BattleshipGrid bsgp, BattleshipGrid bsgc) {
		super("Battleship");
		setLayout(new GridLayout(1,2));

		JLabel pLabel = new JLabel("Player", SwingConstants.CENTER);
		JLabel cLabel = new JLabel("Computer", SwingConstants.CENTER);
		JLabel log = new JLabel("Log: Edit mode");
		file = new JLabel("File:");
		log.setFont(new Font("Arial", Font.PLAIN, 24));
		
		JPanel chooseFile = new JPanel();
		chooseFile.setLayout(new GridLayout(1,2));
		
		JButton selectFile = new JButton("Select File:");
		selectFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				JFileChooser chooser = new JFileChooser();
				chooser.setCurrentDirectory(new File("."));
			    FileNameExtensionFilter filter = new FileNameExtensionFilter(
			        ".battle files", "battle");
			    chooser.setFileFilter(filter);
			    int returnVal = chooser.showOpenDialog(getParent());
			    if(returnVal == JFileChooser.APPROVE_OPTION) {
			       file.setText("File: " + chooser.getSelectedFile().getName().replaceFirst("[.][^.]+$", ""));
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
		
		addMenu();
				
		add(player);
		add(computer);
		
		setSize(880,480);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setVisible(true);
	}
	
	private void addMenu() {
		ctrlAKeyStroke = KeyStroke.getKeyStroke("control A");
		ctrlHKeyStroke = KeyStroke.getKeyStroke("control H");
		jmb = new JMenuBar();
		infoMenu = new JMenu("Info");
		infoMenuItem = new JMenuItem("How To");
		infoMenuItem2 = new JMenuItem("About");
		
		setJMenuBar(jmb);
		jmb.add(infoMenu);
		infoMenu.add(infoMenuItem);
		infoMenu.add(infoMenuItem2);
		
		infoMenuItem.setAccelerator(ctrlHKeyStroke);
		infoMenuItem.addActionListener(new howTo());
		infoMenuItem2.setAccelerator(ctrlAKeyStroke);
		infoMenuItem2.addActionListener(new about());
		
		
	}
	
	class howTo implements ActionListener { 
		public void actionPerformed(ActionEvent e) { 
			JDialog dialog = new JDialog();
            dialog.setLayout(new BorderLayout());
            JTextArea textArea = new JTextArea();
            textArea.setEditable(false);
            try {
                FileReader reader = new FileReader("how.txt");
                BufferedReader br = new BufferedReader(reader);
                textArea.read( br, null );
                br.close();
                textArea.requestFocus();
            } catch(Exception e2) { System.out.println(e2); }
            JScrollPane scrollPane = new JScrollPane(textArea);
            dialog.add(scrollPane, BorderLayout.CENTER);
            dialog.setTitle("About");
            dialog.setSize(400,400);
            dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
            dialog.setLocationRelativeTo(null);
            dialog.setVisible(true);
		}
	}
	
	class about implements ActionListener { 
		public void actionPerformed(ActionEvent e) {
            JDialog dialog = new JDialog();
            dialog.setLayout(new BorderLayout());
            JLabel name = new JLabel("Made by Davi Chaves", SwingConstants.CENTER);
            dialog.add(name, BorderLayout.PAGE_START);
            JLabel info = new JLabel("CSCI 201: Assignment 3", SwingConstants.CENTER);
            dialog.add(info, BorderLayout.PAGE_END);
            dialog.setTitle("About");
            dialog.setSize(400,400);
            dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
            dialog.setLocationRelativeTo(null);
            dialog.setVisible(true);
		}
	}
}