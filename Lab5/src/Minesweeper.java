import java.util.Random;

public class Minesweeper {
	private Random generator = new Random();
	private int xsize = 9; 
	private int ysize = 9;
	private int bombNum = 9; 
	private boolean bombs[][] = new boolean[xsize][ysize]; 
	
	Minesweeper() {
		System.out.println("iniated");
		generateBombs(); 
	}
	
	public void generateBombs() {
		int x; 
		int y; 
		
		for(int i = 0; i<this.bombNum; i++) {
			x = generator.nextInt(xsize); 
			y = generator.nextInt(ysize); 
						
			
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
