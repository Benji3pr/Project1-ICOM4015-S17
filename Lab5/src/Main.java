import javax.swing.JFrame;

public class Main {
	public static void main(String[] args) {
		JFrame myFrame = new JFrame("Color Grid");
		myFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		myFrame.setLocation(400, 150);
		myFrame.setSize(400, 400);

		MyPanel myPanel = new MyPanel();
		myFrame.add(myPanel);
//
		MyMouseAdapter myMouseAdapter = new MyMouseAdapter();
		myFrame.addMouseListener(myMouseAdapter);
		
		int bombNum = 80; 
		int columns = myPanel.getColumnSize(); 
		int rows = myPanel.getRowsSize(); 
		
		Minesweeper myMinesweeper= new Minesweeper(columns, rows, bombNum); 
				
		myMinesweeper.setBombColumn(columns); 
		myMinesweeper.setBombRow(rows);
		
		myFrame.setVisible(true);
	}
}