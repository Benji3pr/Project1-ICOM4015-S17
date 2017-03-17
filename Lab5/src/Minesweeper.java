import java.util.Random;

public class Minesweeper {
	private Random generator = new Random();
	private int column = 9; 
	private int row = 9;
	private int bombNum = 9; 
	private boolean bombs[][] = new boolean[column][row];  
	
	Minesweeper(int columns, int rows, int bombs) {
		setBombColumn(columns);
		setBombRow(rows);
		setBombNum(bombs);
		
		generateBombs(); 
		printBombs();
	}
	public int getBombColumn() {
		return column;
	}

	public int getBombRow() {
		return row;
	}
	
	public void setBombColumn(int column) {
		this.column = column;
	}

	public void setBombRow(int row) {
		this.row = row;
	}

	public void setBombNum(int bombNum) {
		this.bombNum = bombNum;
	}

	public boolean[][] getBombs() {
		return bombs;
	}
	
	public boolean checkBomb(int x, int y) {
		if(bombs[x][y]==true) {
			return true; 
		}
		return false;
	}

	public void printBombs() {
		for(int i = 0; i<column; i++) {
			for(int j = 0; j<row; j++) {
				System.out.println("bomb in " + i + ", " + j + ":" + bombs[i][j]);
			}
		}
	}
	
	private void generateBombs() {
		int x; 
		int y; 
		
		for(int i = 0; i<this.bombNum; i++) {
			x = generator.nextInt(column); 
			y = generator.nextInt(row); 
						
			if(bombs[x][y]==true) {
				i -= 1;
			}		
			
			else{
				bombs[x][y] = true;
			}
		}
	}
}
