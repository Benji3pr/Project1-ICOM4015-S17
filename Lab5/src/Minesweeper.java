import java.util.Random;

public class Minesweeper {
	private Random generator = new Random();
	private int xsize = 9; 
	private int ysize = 9; 
	boolean bombs[][]; 
	
	public void Minesweeper() {
		this.generateBombs(); 
	}
	
	public void generateBombs(int bombCount) {
		int x; 
		int y; 
		
		for(int i = 0; i<bombCount; i++) {
			x = generator.nextInt(xsize); 
			y = generator.nextInt(ysize); 
			
			if(bombs[x][y]==true) {
				System.out.println("same");
			}
			bombs[x][y] = true; 
		}
	}
}
