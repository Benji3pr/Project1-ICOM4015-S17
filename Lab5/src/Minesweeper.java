import java.awt.Color;
import java.util.Random;

public class Minesweeper {
	private Random generator = new Random();
	private int column = 9; 
	private int row = 9;
	private int bombNum = 9; 
	private boolean bombs[][] = new boolean[column][row]; 
	
	private String empty[][] = new String[column][row];
	
	Minesweeper(int columns, int rows, int bombs) {
		setBombColumn(columns);
		setBombRow(rows);
		setBombNum(bombs);		
		generateBombs(); 
		
	}
	public int getBombColumn() {
		return column;
	}

	public int getBombRow() {
		return row;
	}
	
	public int getBombCount() {
		return bombNum; 
	}
		
	public String[][] getEmptySpaces(int x, int y) {
		checkEmpty(x, y);
		return empty; 
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
	
	public String checkBombsArround(int x, int y){
		int amount = 0;
		
		int limitI = 0; 
		int limitJ = 0; 
		
		if((x>0)&&(y>0)&&(x<8)&&(y<8)){
			x-=1;
			y-=1;		
			
			limitI = (x+3); 
			limitJ = (y+3); 
				
			
		} else if(x==0 && y==0){
			limitI = (x+2);
			limitJ = (y+2);
		} else if(x==0 && y==8){
			y-=1;
			
			limitI = (x+2);
			limitJ = (y+2);
		} else if(x==8 && y==0){
			x-=1;
			
			limitI = (x+2);
			limitJ = (y+2);
		} else if(x==8 && y==8){
			x-=1;
			y-=1;
			
			limitI = (x+2);
			limitJ = (y+2);
			
		} else if(x==0 && y>0 && y<8){
			y-=1;
			
			limitI = (x+2);
			limitJ = (y+3);
			
		} else if(x==8 && y>0 && y<8){
			y-=1;
			x-=1;
			
			limitI = (x+2);
			limitJ = (y+3);
			
		} else if(y==0 && x>0 && x<8){
			x-=1;
			
			limitI = (x+3);
			limitJ = (y+2);
			
		} else if(y==8 && x>0 && x<8){
			x-=1;
			y-=1;
			
			limitI = (x+3);
			limitJ = (y+2);
					
		}
		
		for(int i = x; i< limitI; i++){
			for(int j=y; j< limitJ; j++){
				if(checkBomb(i,j)){
					amount+=1;
				}				
			}
		}
	
		String totalAmount = Integer.toString(amount);
		return totalAmount;
	}
	
	public MyPanel paintDidWin(MyPanel myPanel) {
		Color newColor = Color.cyan;
		for(int i = 0; i<column; i++) {
			for(int j = 0; j<row; j++) {
				myPanel.colorArray[i][j] = newColor;
				myPanel.numOfBombs[i][j] = "";
			}
			
		}
		
		return myPanel;
	}
	
	private void checkEmpty(int x, int y) {			
			int limitI = 0; 
			int limitJ = 0; 
			
			if((x>0)&&(y>0)&&(x<8)&&(y<8)){
				x-=1;
				y-=1;		
				
				limitI = (x+3); 
				limitJ = (y+3); 
			} else if(x==0 && y==0){
				limitI = (x+2);
				limitJ = (y+2);
			} else if(x==0 && y==8){
				y-=1;
				
				limitI = (x+2);
				limitJ = (y+2);
			} else if(x==8 && y==0){
				x-=1;
				
				limitI = (x+2);
				limitJ = (y+2);
			} else if(x==8 && y==8){
				x-=1;
				y-=1;
				
				limitI = (x+2);
				limitJ = (y+2);
				
			} else if(x==0 && y>0 && y<8){
				y-=1;
				
				limitI = (x+2);
				limitJ = (y+3);
				
			} else if(x==8 && y>0 && y<8){
				y-=1;
				x-=1;
				
				limitI = (x+2);
				limitJ = (y+3);
				
			} else if(y==0 && x>0 && x<8){
				x-=1;
				
				limitI = (x+3);
				limitJ = (y+2);
				
			} else if(y==8 && x>0 && x<8){
				x-=1;
				y-=1;
				
				limitI = (x+3);
				limitJ = (y+2);
						
			}
			String bombAround = checkBombsArround(x, y); 
			int numBombAround = Integer.parseInt(bombAround);
			
			for(int i = x; i< limitI; i++){
				for(int j=y; j< limitJ; j++){
					bombAround = checkBombsArround(i, j); 
					numBombAround = Integer.parseInt(bombAround);
					if(numBombAround==0) {
						empty[i][j] = "empty";
					} else if(numBombAround>0) {
						empty[i][j] = bombAround;
					} 
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