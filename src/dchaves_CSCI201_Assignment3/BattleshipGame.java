package dchaves_CSCI201_Assignment3;

import java.util.Scanner;

public class BattleshipGame {
	
	public static Scanner scan = new Scanner(System.in);
	
	private BattleshipGrid bsg, bsg2;
	
	BattleshipGame() {
		bsg = new BattleshipGrid();
		bsg2 = new BattleshipGrid();
		new BattleshipFrame(bsg, bsg2);
	}
	
	public static void main(String[] args) {
		
		BattleshipGame bsgm = new BattleshipGame();
		bsgm.readFile();
		bsgm.playGame();
		System.exit(0);
	}

	private void playGame() {
		boolean isOver = false;
		int turn = 1;
		scan.useDelimiter(System.getProperty("line.separator"));
		do{
			System.out.print("Turn "+turn+" - Please enter a Coordinate:");
			if(bsg.hitCoord(scan.nextLine())) turn++;
			isOver = (bsg.getNumSunk()==5);
		}while(!isOver);
		System.out.println("You sank all the ships!");
	}

	
	private void readFile() {
		do {
			System.out.print("Please enter a valid file path:");
		} while(!bsg.loadMap("battle.txt"));
	}
}
