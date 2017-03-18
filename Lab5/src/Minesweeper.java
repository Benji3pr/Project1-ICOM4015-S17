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
		//printBombs();
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
	
	public String checkBombsArround(int x, int y){
		int amount = 0;
		
		if((x>0)&&(y>0)&&(x<8)&&(y<8)){
			x-=1;
			y-=1;			
			for(int i=x; i<(x+3); i++){
				for(int j=y; j<(y+3); j++){
					if(checkBomb(i,j)){
						amount+=1;
					}
				}
			}
		}
		
		else if(x==0 && y==0){
			for(int i=x; i<(x+2); i++){
				for(int j=y; j<(y+2); j++){
					if(checkBomb(i,j)){
						amount+=1;
					}
				}
			}
		}
		
		else if(x==0 && y==8){
			y-=1;
			for(int i=x; i<(x+2); i++){
				for(int j=y; j<(y+2); j++){
					if(checkBomb(i,j)){
						amount+=1;
					}
				}
			}
		}
		
		else if(x==8 && y==0){
			x-=1;
			for(int i=x; i<(x+2); i++){
				for(int j=y; j<(y+2); j++){
					if(checkBomb(i,j)){
						amount+=1;
					}
				}
			}
		}
		
		else if(x==8 && y==8){
			x-=1;
			y-=1;
			for(int i=x; i<(x+2); i++){
				for(int j=y; j<(y+2); j++){
					if(checkBomb(i,j)){
						amount+=1;
					}
				}
			}
		}
		
		else if(x==0 && y>0 && y<8){
			y-=1;
			for(int i=x; i<(x+2); i++){
				for(int j=y; j<(y+3); j++){
					if(checkBomb(i,j)){
						amount+=1;
					}
				}
			}
		}
		
		else if(x==8 && y>0 && y<8){
			y-=1;
			x-=1;
			for(int i=x; i<(x+2); i++){
				for(int j=y; j<(y+3); j++){
					if(checkBomb(i,j)){
						amount+=1;
					}
				}
			}
		}
		
		else if(y==0 && x>0 && x<8){
			x-=1;
			for(int i=x; i<(x+3); i++){
				for(int j=y; j<(y+2); j++){
					if(checkBomb(i,j)){
						amount+=1;
					}
				}
			}
		}
		
		else if(y==8 && x>0 && x<8){
			x-=1;
			y-=1;
			for(int i=x; i<(x+3); i++){
				for(int j=y; j<(y+2); j++){
					if(checkBomb(i,j)){
						amount+=1;
					}
				}
			}
		}
		
	
		String totalAmount = Integer.toString(amount);
		return totalAmount;
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
