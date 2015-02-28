package dchaves_CSCI201_Assignment3;

import java.awt.GridLayout;
import java.awt.Point;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import javax.swing.JLabel;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class BattleshipGrid extends JPanel {
	private JLabel grid[][];
	private ArrayList<Battleship> ships;
	private String myString = new String("ABCDEFGHIJ");
	
	BattleshipGrid() {
		grid = new JLabel[10][10];
		ships = new ArrayList<Battleship>();
		
		setLayout(new GridLayout(11,11));
		for(int i = 0; i < 10; i++) {
			add(new JLabel(""+myString.charAt(i)));
			for(int j = 0; j < 10; j++) {
				grid[j][i] = new JLabel("?");
				add(grid[j][i]);
			}
		}
		
		add(new JLabel("")); //fill in the bottom left corner
		
		for(int i = 0; i < 10; i++) {
			add(new JLabel(""+i));
		}
	}
	
	public int getNumSunk() {
		int total = 0;
		for(Battleship b : ships) {
			if(b.isSunk()) total++;
		}
		return total;
	}
	
	public boolean hitCoord(String coord) {
		if(coord.length()<2 || coord.length()>3) return false;
		char y = coord.charAt(0);
		if(y <'A' || y > 'J') return false;
		
		String x;
		if(coord.charAt(1) <'1' || coord.charAt(1) > '9') return false;
		else x = ""+coord.charAt(1);
		if(coord.length() == 3) {
			if(coord.charAt(2) != '0') return false;
			else x+=coord.charAt(2);
		}
		
		int yPos = (int)(y-'A');
		int xPos = Integer.valueOf(x)-1;
		if(xPos >9) return false;
		
		return hitShips(new Point(xPos,yPos));
	}
	
	private boolean hitShips(Point point) {
		if(!grid[point.x][point.y].getText().equals("?")) return false;
		boolean hit = false;
		for(Battleship bs : ships) {
			if(bs.attackPoint(point)) {
				grid[point.x][point.y].setText(bs.getTag()+"");
				hit = true;
				break;
			} else {
				grid[point.x][point.y].setText("MISS!");
			}
		}
		if(!hit) System.out.println("You missed!");
		return true;
	}

	public void clearGrid() {
		for(int i = 0; i < 10; i++) {
			for(JLabel square : grid[i]) {
				square.setText("?");
			}
		}
	}

	public boolean loadMap(String pathName) {
		Scanner inputScan = null;
		try {
			inputScan = new Scanner(new File(pathName));
			
			char[][] inputMatrix = new char[10][10];
			
			for(int i = 0; i < 10; i++) {
				String temp = inputScan.nextLine();
				if(temp.length() != 10) return false;
				else inputMatrix[i] = temp.toCharArray();
			}
			
			ArrayList<Battleship> dShips = new ArrayList<Battleship>();
			ArrayList<Battleship> dShipsR = new ArrayList<Battleship>();
			
			Map<Character,Integer> charToSize = new HashMap<Character,Integer>();
			charToSize.put('A', 5);
			charToSize.put('B', 4);
			charToSize.put('C', 3);
			charToSize.put('D', 2);
			
			char currentHoriz = '#';
			char currentVerti = '#';
			char currentHorizR = '#';
			char currentVertiR = '#';
			int horizStreak = 0;
			int vertiStreak = 0;
			int horizStreakR = 0;
			int vertiStreakR = 0;
			
			int horizDCount = 0;
			int vertiDCount = 0;
			
			int charCount[] = new int[5];
			for(int i = 0; i < charCount.length; i++) charCount[i] = 0;
			
			for(int i = 0; i < 10; i++) {
				for(int j = 0; j < 10; j++) {
					
					if(inputMatrix[i][j]!='X') charCount[inputMatrix[i][j]-'A']++;
					
					if(inputMatrix[i][j] == currentHoriz) {
						horizStreak++;
						if(horizStreak == charToSize.get(currentHoriz)) {
							Battleship bs = new Battleship(currentHoriz,new Point(j-charToSize.get(currentHoriz)+1,i),new Point(j,i));
							if(currentHoriz == 'D') {
								dShips.add(bs);
								horizDCount++;
							}
							else ships.add(bs);
							currentHoriz = '#';
							horizStreak = 0;
						}
					} 
					else if(inputMatrix[i][j]!='X') {
						currentHoriz = inputMatrix[i][j];
						horizStreak = 1;
					} else {
						currentHoriz = '#';
						horizStreak = 0;
					}
					
					if(inputMatrix[9-i][9-j] == currentHorizR) {
						horizStreakR++;
						if(horizStreakR == 2) {
							dShipsR.add(new Battleship('D',new Point(9-j,9-i),new Point(9-j+2-1,9-i)));
							currentHorizR = '#';
							horizStreakR = 0;
						}
					} 
					else if(inputMatrix[9-i][9-j]=='D') {
						currentHorizR = 'D';
						horizStreakR = 1;
					} else {
						currentHorizR = '#';
						horizStreakR = 0;
					}
					
					if(inputMatrix[j][i] == currentVerti) {
						vertiStreak++;
						if(vertiStreak == charToSize.get(currentVerti)) {
							Battleship bs = new Battleship(currentVerti,new Point(i,j-charToSize.get(currentVerti)+1),new Point(i,j));
							if(currentVerti == 'D') {
								dShips.add(bs);
								vertiDCount++;
							}
							else ships.add(bs);
							currentVerti = '#';
							vertiStreak = 0;
						}
					}
					else if(inputMatrix[j][i]!='X') {
						currentVerti = inputMatrix[j][i];
						vertiStreak = 1;
					} else {
						currentVerti = '#';
						vertiStreak = 0;
					}
					
					if(inputMatrix[9-j][9-i] == currentVertiR) {
						vertiStreakR++;
						if(vertiStreakR == 2) {
							dShipsR.add(new Battleship('D',new Point(9-i,9-j),new Point(9-i,9-j+2-1)));
							currentVertiR = '#';
							vertiStreakR = 0;
						}
					}
					else if(inputMatrix[9-j][9-i]=='D') {
						currentVertiR = 'D';
						vertiStreakR = 1;
					} else {
						currentVertiR = '#';
						vertiStreakR = 0;
					}
					
				}
			}
			
			if(charCount['A'-'A'] != 5) return false;
			if(charCount['B'-'A'] != 4) return false;
			if(charCount['C'-'A'] != 3) return false;
			if(charCount['D'-'A'] != 4) return false;

			if(horizDCount == 2 && vertiDCount == 1) {
				int posToDestroy = -1;
				int pos = 0;
				for(Battleship b : dShips) {
						if(b.getStartPoint().x == b.getEndPoint().x) posToDestroy = pos;
					pos++;
				}
				if(posToDestroy != -1) dShips.remove(posToDestroy);
			}
			
			if(horizDCount == 1 && vertiDCount == 2) {
				int posToDestroy = -1;
				int pos = 0;
				for(Battleship b : dShips) {
						if(b.getStartPoint().y == b.getEndPoint().y) posToDestroy = pos;
					pos++;
				}
				if(posToDestroy != -1) dShips.remove(posToDestroy);
			}
			
			if(horizDCount == 2 && vertiDCount == 2) {
				int posToDestroy = -1;
				int pos = 0;
				for(Battleship b : dShips) {
						if(b.getStartPoint().y == b.getEndPoint().y) posToDestroy = pos;
					pos++;
				}
				if(posToDestroy != -1) dShips.remove(posToDestroy);
				posToDestroy = -1;
				pos = 0;
				for(Battleship b : dShips) {
						if(b.getStartPoint().y == b.getEndPoint().y) posToDestroy = pos;
					pos++;
				}
				if(posToDestroy != -1) dShips.remove(posToDestroy);
			}
			
			if(dShips.size()!=2) return false;
			if(dShips.get(0).getStartPoint().equals(dShips.get(1).getStartPoint())
					|| dShips.get(0).getEndPoint().equals(dShips.get(1).getEndPoint())
					|| dShips.get(0).getStartPoint().equals(dShips.get(1).getEndPoint())
					|| dShips.get(0).getEndPoint().equals(dShips.get(1).getStartPoint())) {
				dShips = dShipsR;
			}
			
			ships.addAll(dShips);
			
			return true;
		} catch (FileNotFoundException e) {
			System.out.println("File path is invalid!");
			return false;
		} finally {
			if(inputScan != null)
			inputScan.close();
		}
	}

}

class Battleship {
	private Point startPoint;
	private Point endPoint;
	private char tag;
	private static final String[] NAMES = {"Aircraft Carrier", "Battleship", "Cruiser", "Destroyer"};
	
	@SuppressWarnings("serial")
	class HitPoint extends Point{boolean hit; HitPoint(int x, int y){super(x,y);hit=false;}}
	private ArrayList<HitPoint> points;
	private int hp;
	public Battleship(char tag, Point startPoint, Point endPoint) {
		this.tag = tag;
		this.startPoint = startPoint;
		this.endPoint = endPoint;
		
		points = new ArrayList<HitPoint>();
		Point toAdd = new Point(startPoint.x,startPoint.y);
		if(startPoint.x == endPoint.x) {
			while(toAdd.y!=endPoint.y) {
				points.add(new HitPoint(toAdd.x,toAdd.y));
				toAdd.y++;
			}
			points.add(new HitPoint(toAdd.x,toAdd.y));
		}
		else if(startPoint.y == endPoint.y) {
			while(toAdd.x!=endPoint.x) {
				points.add(new HitPoint(toAdd.x,toAdd.y));
				toAdd.x++;
			}
			points.add(new HitPoint(toAdd.x,toAdd.y));
		}
		hp = points.size();
	}
	
	public boolean attackPoint(Point point) {
		boolean gotHit = false;
		for(HitPoint hitPoint: points) {
			if(hitPoint.equals(point)) {
				hitPoint.hit = true;
				gotHit = true;
				System.out.println("You hit a "+NAMES[tag-'A']+"!");
				hp--;
				if(hp == 0) System.out.println("You have sunken a "+NAMES[tag-'A']+"!");
			}
		}
		return gotHit;
	}
	public Point getStartPoint() {
		return startPoint;
	}
	public Point getEndPoint() {
		return endPoint;
	}
	public char getTag() {
		return tag;
	}
	public boolean isSunk() {
		if(hp==0) return true;
		else return false;
	}
	
}