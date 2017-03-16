import java.util.Random;

public class Minesweeper {
	private Random generator = new Random();
	private int column = 9; 
	private int row = 9;
	private int bombNum = 9; 
	private boolean bombs[][] = new boolean[column][row]; 
	
	Minesweeper() {
		System.out.println("iniated");
		generateBombs(); 
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

	private void generateBombs() {
		int x; 
		int y; 
		
		for(int i = 0; i<this.bombNum; i++) {
			x = generator.nextInt(column); 
			y = generator.nextInt(row); 
						
			
			if(bombs[x][y]==true) {
				System.out.println("same");
				i -= 1;
				System.out.println("bomb is in [" + x + " ]" + "[" + y + " ]" );
			}		
			
			else{
				bombs[x][y] = true;
			}
		}
	}
}
