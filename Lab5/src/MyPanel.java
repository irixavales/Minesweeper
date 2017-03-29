import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Insets;
import java.util.Random;

import javax.swing.JPanel;

public class MyPanel extends JPanel {
	private static final long serialVersionUID = 3426940946811133635L;
	private static final int GRID_X = 25;
	private static final int GRID_Y = 25;
	private static final int INNER_CELL_SIZE = 29;
	private static final int TOTAL_COLUMNS = 9;
	private static final int TOTAL_ROWS = 9;
	//public int 
	public  int x = -1;
	public  int y = -1;
	public int mouseDownGridX = 0;
	public int mouseDownGridY = 0;
	public static Color[][] colorArray = new Color[TOTAL_COLUMNS][TOTAL_ROWS];
	Random rand = new Random();
	public static int minesNumbers = 10;
	public static int cellsWithoutMines = (TOTAL_COLUMNS * TOTAL_ROWS) - minesNumbers;
	public static int uncoveredCells = 0;
	public static int mine=0;
	public static boolean lostGame = false;
	public static int[][] minesPosition = new int[minesNumbers][2];
	public static int[][] numberOfSurroundingMines = new int[TOTAL_COLUMNS+1][TOTAL_ROWS+1];
	

	public MyPanel() {   //Constructor

		if (INNER_CELL_SIZE + (new Random()).nextInt(1) < 1) {	//Use of "random" to prevent unwanted Eclipse warning
			throw new RuntimeException("INNER_CELL_SIZE must be positive!");
		}
		if (TOTAL_COLUMNS + (new Random()).nextInt(1) < 2) {	//Use of "random" to prevent unwanted Eclipse warning
			throw new RuntimeException("TOTAL_COLUMNS must be at least 2!");
		}
		if (TOTAL_ROWS + (new Random()).nextInt(1) < 3) {	//Use of "random" to prevent unwanted Eclipse warning
			throw new RuntimeException("TOTAL_ROWS must be at least 3!");
		}

		for (int x = 0; x < TOTAL_COLUMNS; x++) {   //The rest of the grid
			for (int y = 0; y < TOTAL_ROWS; y++) {
				colorArray[x][y] = Color.WHITE;
			}
		}

		MinesPosition();
	}

	public int[][] MinesPosition () {
		int xPos=0;
		int yPos=0;
		for (int i = 0; i <= minesNumbers; i++) {

			
//			for(int w=0; w<minesPosition.length; w++){
//				if(minesPosition[w][0] == xPos && minesPosition[w][1]== yPos){
//					found=true;
//				}
//			if(found==true){
			
			xPos = rand.nextInt(TOTAL_COLUMNS);
			yPos = rand.nextInt(TOTAL_ROWS);
			do{
			
//			for(int j=0; i<=minesNumbers; i++){
//				for(int w=0; w<=1; w++)
//					if(minesPosition[j][0]!= && minesPosition[j][1]!= ){
			
//			while(int w=0; w<=minesPosition.length){
//			if(!(xPos==minesPosition[w][0] && yPos==minesPosition[w][1])){
			minesPosition[i][0] = xPos;
			
			minesPosition[i][1] = yPos;
			}while(xPos==minesPosition);
					
		//	}
			
			
			
			System.out.println(xPos+" "+yPos); //Print x and y coordinates for debugging purposes
			
//			}else{
//				i++;
//			}
//			
//			
//		
//			
//			}		
						}return minesPosition;
}

		//TO DO: Finish method to prevent repeating mines.
	

public boolean IsMemberX(){
	int xPos= getX();
	for(int i=0; i<=minesNumbers; i++) {
		for(int j=0; j<=1; j++){
	if (xPos == minesPosition[i][j]) return true;
	}}
return false;}
	
public boolean IsMemberY(){
		int yPos=getY();
		for(int i=0; i<=minesNumbers; i++) {
			for(int j=0; j<=minesNumbers; j++){
		if (yPos == minesPosition[i][j]) return true;
		}}
		return false;}




	

	public int getX (int i) {
		return minesPosition[i][0];
	}

	public int getY (int i) {
		return minesPosition [i][1];
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		//Compute interior coordinates
		Insets myInsets = getInsets();
		int x1 = myInsets.left;
		int y1 = myInsets.top;
		int x2 = getWidth() - myInsets.right - 1;
		int y2 = getHeight() - myInsets.bottom - 1;
		int width = x2 - x1;
		int height = y2 - y1;

		//Paint the background
		g.setColor(Color.LIGHT_GRAY);
		g.fillRect(x1, y1, width + 1, height + 1);

		//Draw the grid
		g.setColor(Color.BLACK);
		for (int y = 0; y <= TOTAL_ROWS; y++) {
			g.drawLine(x1 + GRID_X, y1 + GRID_Y + (y * (INNER_CELL_SIZE + 1)), x1 + GRID_X + ((INNER_CELL_SIZE + 1) * TOTAL_COLUMNS), y1 + GRID_Y + (y * (INNER_CELL_SIZE + 1)));
		}
		for (int x = 0; x <= TOTAL_COLUMNS; x++) {
			g.drawLine(x1 + GRID_X + (x * (INNER_CELL_SIZE + 1)), y1 + GRID_Y, x1 + GRID_X + (x * (INNER_CELL_SIZE + 1)), y1 + GRID_Y + ((INNER_CELL_SIZE + 1) * (TOTAL_ROWS)));
		}

		//Paint cell colors
		for (int x = 0; x < TOTAL_COLUMNS; x++) {
			for (int y = 0; y < TOTAL_ROWS; y++) {
				Color c = colorArray[x][y];
				g.setColor(c);
				g.fillRect(x1 + GRID_X + (x * (INNER_CELL_SIZE + 1)) + 1, y1 + GRID_Y + (y * (INNER_CELL_SIZE + 1)) + 1, INNER_CELL_SIZE, INNER_CELL_SIZE);		
			}
		}

		//Draw number of surrounding mines for uncovered cells
		for (int x = 0; x < TOTAL_COLUMNS; x++) {
			for (int y = 0; y < TOTAL_ROWS; y++) {
				Color c = colorArray[x][y];
				if(c.equals(Color.LIGHT_GRAY)) {
					if (numberOfSurroundingMines[x][y] != 0) {
						switch (numberOfSurroundingMines[x][y]) {
						case 1:
							g.setColor(Color.BLUE);
							break;
						case 2:
							g.setColor(Color.CYAN);
							break;
						case 3:
							g.setColor(Color.GREEN);
							break;
						case 4:
							g.setColor(Color.YELLOW);
							break;
						case 5:
							g.setColor(Color.ORANGE);
							break;
						case 6:
							g.setColor(Color.RED);
							break;
						case 7:
							g.setColor(Color.PINK);
							break;
						case 8:
							g.setColor(Color.MAGENTA);
							break;
						}
						g.setFont(new Font("CALIBRI", Font.BOLD, 10));
						g.drawString(Integer.toString(numberOfSurroundingMines[x][y]),(x1 + GRID_X + (x * (INNER_CELL_SIZE + 1)) + 1) + INNER_CELL_SIZE/2, (y1 + GRID_Y + (y * (INNER_CELL_SIZE + 1)) + 1) + INNER_CELL_SIZE/2);
					}
				}
			}
		}
	}

	public int getGridX(int x, int y) {
		Insets myInsets = getInsets();
		int x1 = myInsets.left;
		int y1 = myInsets.top;
		x = x - x1 - GRID_X;
		y = y - y1 - GRID_Y;
		if (x < 0) {   //To the left of the grid
			return -1;
		}
		if (y < 0) {   //Above the grid
			return -1;
		}
		if ((x % (INNER_CELL_SIZE + 1) == 0) || (y % (INNER_CELL_SIZE + 1) == 0)) {   //Coordinate is at an edge; not inside a cell
			return -1;
		}
		x = x / (INNER_CELL_SIZE + 1);
		y = y / (INNER_CELL_SIZE + 1);

		if (x < 0 || x > TOTAL_COLUMNS -1 || y < 0 || y > TOTAL_ROWS - 1) {   //Outside the rest of the grid
			return -1;
		}
		return x;
	}
	public void getButton(){
		if((x < 0 || x > TOTAL_COLUMNS -1 || y < 0 || y > TOTAL_ROWS - 1) ){
			
			
			
		}
	}
	
	public int getGridY(int x, int y) {
		Insets myInsets = getInsets();
		int x1 = myInsets.left;
		int y1 = myInsets.top;
		x = x - x1 - GRID_X;
		y = y - y1 - GRID_Y;
		if (x < 0) {   //To the left of the grid
			return -1;
		}
		if (y < 0) {   //Above the grid
			return -1;
		}
		if ((x % (INNER_CELL_SIZE + 1) == 0) || (y % (INNER_CELL_SIZE + 1) == 0)) {   //Coordinate is at an edge; not inside a cell
			return -1;
		}
		x = x / (INNER_CELL_SIZE + 1);
		y = y / (INNER_CELL_SIZE + 1);

		if (x < 0 || x > TOTAL_COLUMNS - 1 || y < 0 || y > TOTAL_ROWS - 1) {   //Outside the rest of the grid
			return -1;
		}
		return y;
	}

	//Returns true if cell is a mine
	public static boolean isMine (int xPos, int yPos) {
		for (int i=0; i < minesNumbers; i++) {
			if (minesPosition[i][0] == xPos && minesPosition[i][1] == yPos) {return true;}
		}
		return false;
	}

	//Returns true if cell is on a corner or edge of the grid
	public static boolean isBorder (int xPos, int yPos) {
		if (xPos == 0 || xPos >= TOTAL_COLUMNS - 1) {return true;}
		else if (yPos == 0 || yPos >= TOTAL_ROWS - 1) {return true;}
		return false;
	}

	//Returns number of mines on the six surrounding squares of a cell
	public static void countSurroundingMines (int xPos, int yPos) {
		colorArray[xPos][yPos] = Color.LIGHT_GRAY;
		numberOfSurroundingMines[xPos][yPos] = 0;
		for (int j = 0; j < 3; j++) {
			for (int k = 0; k < 3; k++) {
				if (!(j==1 && k==1)) {
					if (isMine(xPos+j-1, yPos+k-1)) {
						numberOfSurroundingMines[xPos][yPos]++;
					}
				}
			}
		}
		if (numberOfSurroundingMines[xPos][yPos] == 0) {openEmptyMines(xPos, yPos);}
		if(colorArray[xPos][yPos].equals(Color.LIGHT_GRAY)){
			uncoveredCells++;
		}
	}


	public static void openEmptyMines (int xPos, int yPos) {
		if (!isBorder(xPos, yPos)) {
			for (int j = 0; j < 3; j++) {
				for (int k = 0; k < 3; k++) {
					if (!(j==0&&k==0)) {
						if (!colorArray[xPos+j-1][yPos+k-1].equals(Color.LIGHT_GRAY)&&!colorArray[xPos+j-1][yPos+k-1].equals(Color.RED)){
							MyPanel.countSurroundingMines(xPos+j-1, yPos+k-1);
						}
					}
				}
			}
		}
		else {
			if (xPos==0 && yPos==0) { //Verifies if the cells starting from the upper-left corner have not been uncovered or 'flagged' and then executes recursive spacing method
				if (!colorArray[xPos+1][yPos].equals(Color.LIGHT_GRAY)&&!colorArray[xPos+1][yPos].equals(Color.RED))
					MyPanel.countSurroundingMines(xPos+1, yPos+1);
				if (!colorArray[xPos][yPos+1].equals(Color.LIGHT_GRAY)&&!colorArray[xPos][yPos+1].equals(Color.RED))
					MyPanel.countSurroundingMines(xPos, yPos+1);
				if (!colorArray[xPos+1][yPos+1].equals(Color.LIGHT_GRAY)&&!colorArray[xPos+1][yPos+1].equals(Color.RED))
					MyPanel.countSurroundingMines(xPos+1, yPos+1);
			}
			else if (xPos==0 && yPos==TOTAL_ROWS-1) {//Verifies if the cells starting from the lower-left corner have not been uncovered or 'flagged' and then executes recursive spacing method
				if (!colorArray[xPos+1][yPos].equals(Color.LIGHT_GRAY)&&!colorArray[xPos+1][yPos].equals(Color.RED))
					MyPanel.countSurroundingMines(xPos+1, yPos);
				if (!colorArray[xPos][yPos-1].equals(Color.LIGHT_GRAY)&&!colorArray[xPos][yPos-1].equals(Color.RED))
					MyPanel.countSurroundingMines(xPos, yPos-1);
				if (!colorArray[xPos+1][yPos-1].equals(Color.LIGHT_GRAY)&&!colorArray[xPos+1][yPos-1].equals(Color.RED))
					MyPanel.countSurroundingMines(xPos+1, yPos-1);
			}
			else if (xPos==TOTAL_COLUMNS-1 && yPos==0) { //Verifies if the cells starting from the upper-right corner have not been uncovered or 'flagged' and then executes recursive spacing method
				if (!colorArray[xPos-1][yPos].equals(Color.LIGHT_GRAY)&&!colorArray[xPos-1][yPos].equals(Color.RED))
					MyPanel.countSurroundingMines(xPos-1, yPos);
				if (!colorArray[xPos][yPos+1].equals(Color.LIGHT_GRAY)&&!colorArray[xPos][yPos+1].equals(Color.RED))
					MyPanel.countSurroundingMines(xPos, yPos+1);
				if (!colorArray[xPos-1][yPos+1].equals(Color.LIGHT_GRAY)&&!colorArray[xPos-1][yPos+1].equals(Color.RED))
					MyPanel.countSurroundingMines(xPos-1, yPos+1);
			}
			else if (xPos==TOTAL_COLUMNS-1 && yPos==TOTAL_ROWS-1) {//Verifies if the cells starting from the lower-right corner have not been uncovered or 'flagged' and then executes recursive spacing method
				if (!colorArray[xPos-1][yPos].equals(Color.LIGHT_GRAY)&&!colorArray[xPos-1][yPos].equals(Color.RED))
					MyPanel.countSurroundingMines(xPos-1, yPos);
				if (!colorArray[xPos][yPos-1].equals(Color.LIGHT_GRAY)&&!colorArray[xPos][yPos-1].equals(Color.RED))
					MyPanel.countSurroundingMines(xPos, yPos-1);
				if (!colorArray[xPos-1][yPos-1].equals(Color.LIGHT_GRAY)&&!colorArray[xPos-1][yPos-1].equals(Color.RED))
					MyPanel.countSurroundingMines(xPos-1, yPos-1);
			}
			else if (xPos == 0) {
				for (int j = 1; j < 3; j++) {
					for (int k = 0; k < 3; k++) {
						if (!(j==0 && k==0)) {
							if (!colorArray[xPos+j-1][yPos+k-1].equals(Color.LIGHT_GRAY)&&!colorArray[xPos+j-1][yPos+k-1].equals(Color.RED)){
								MyPanel.countSurroundingMines(xPos+j-1, yPos+k-1);
							}
						}
					}
				}
			}
			else if (yPos == 0) {
				for (int j = 0; j < 3; j++) {
					for (int k = 1; k < 3; k++) {
						if (!(j==0 && k==0)) {
							if (!colorArray[xPos+j-1][yPos+k-1].equals(Color.LIGHT_GRAY)&&!colorArray[xPos+j-1][yPos+k-1].equals(Color.RED)){
								MyPanel.countSurroundingMines(xPos+j-1, yPos+k-1);
							}
						}
					}
				}
			}
			else if (xPos == TOTAL_COLUMNS-1) {
				for (int j = 0; j < 2; j++) {
					for (int k = 0; k < 3; k++) {
						if (!(j==0 && k==0)) {
							if (!colorArray[xPos+j-1][yPos+k-1].equals(Color.LIGHT_GRAY)&&!colorArray[xPos+j-1][yPos+k-1].equals(Color.RED)){
								MyPanel.countSurroundingMines(xPos+j-1, yPos+k-1);
							}
						}
					}
				}
			}
			else if (yPos == TOTAL_ROWS-1) {
				for (int j = 0; j < 3; j++) {
					for (int k = 0; k < 2; k++) {
						if (!(j==0 && k==0)) {
							if (!colorArray[xPos+j-1][yPos+k-1].equals(Color.LIGHT_GRAY)&&!colorArray[xPos+j-1][yPos+k-1].equals(Color.RED)){
								MyPanel.countSurroundingMines(xPos+j-1, yPos+k-1);
							}
						}
					}
				}
			}
		}
	}
	public void lostGame () { //Verifies if the user 'lost' and repaints all the mine positions black simultaniouly. 
		lostGame = true;
		for (int i = 0; i < minesNumbers; i++) {
			colorArray[this.getX(i)][this.getY(i)] = Color.BLACK;
			
		}
		
		
	}

	public static boolean wonGame () { //Verifies if the user has won; if the value of uncoveredcells reaches 81 (max of grid in this case) minus the amount of mines, it'll return true. 
	
		if(uncoveredCells == (TOTAL_COLUMNS*TOTAL_ROWS) - minesNumbers){
		return true;
		
	}
	return false;
	}
}
