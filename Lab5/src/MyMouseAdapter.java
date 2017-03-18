import java.awt.Color;
import java.awt.Component;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JFrame;

public class MyMouseAdapter extends MouseAdapter {
	private Random generator = new Random();
	public int flaggedBombs = 0;  
	public Minesweeper myMinesweeper;

public void mousePressed(MouseEvent e) {
		Component c = e.getComponent();
		while (!(c instanceof JFrame)) {
			c = c.getParent();
			if (c == null) {
				return;
			}
		}
		JFrame myFrame = (JFrame) c;
		MyPanel myPanel = (MyPanel) myFrame.getContentPane().getComponent(0);
		Insets myInsets = myFrame.getInsets();
		int x1 = myInsets.left;
		int y1 = myInsets.top;
		e.translatePoint(-x1, -y1);
		int x = e.getX();
		int y = e.getY();
		myPanel.x = x;
		myPanel.y = y;
		myPanel.mouseDownGridX = myPanel.getGridX(x, y);
		myPanel.mouseDownGridY = myPanel.getGridY(x, y);
		myPanel.repaint();
		int gridX = myPanel.getGridX(x, y);
		int gridY = myPanel.getGridY(x, y);
		
		if(flaggedBombs==myMinesweeper.getBombCount()) {
			String bombAround; 
			int num; 
			for(int i = 0; i<myMinesweeper.getBombColumn(); i++) {
				for(int j = 0; j<myMinesweeper.getBombRow(); j++) {
					bombAround = myMinesweeper.checkBombsArround(i, j); 
					num = Integer.parseInt(bombAround);
					if (myPanel.colorArray[i][j].equals(Color.red)) {
						myPanel.colorArray[i][j] = Color.red;
					} else if(num>0){
						myPanel.numOfBombs[i][j]=(bombAround);
						myPanel.colorArray[i][j] = Color.white;
					} else {
						myPanel.colorArray[i][j] = Color.white;
					}
					myPanel.repaint();
				}
			}
		}
		
		switch (e.getButton()) {
		case 1:		//Left mouse button

			break;
		case 3:		//Right mouse button
			//Do nothing

			if ((gridX == -1) || (gridY == -1)) {
				//Is releasing outside
				//Do nothing
			}
			Color oldColor = myPanel.colorArray[myPanel.mouseDownGridX][myPanel.mouseDownGridY];

			if(oldColor.equals(Color.RED)){
				Color newColor = Color.GRAY;

				myPanel.colorArray[myPanel.mouseDownGridX][myPanel.mouseDownGridY] = newColor;
				myPanel.repaint();
			}

			else if (oldColor.equals(Color.GRAY)){
				Color newColor = Color.RED;
				
				if(myMinesweeper.checkBomb(gridX, gridY)){
					flaggedBombs+=1;
				}
				myPanel.colorArray[myPanel.mouseDownGridX][myPanel.mouseDownGridY] = newColor;
				myPanel.repaint();
			}

			break;
		default:    //Some other button (2 = Middle mouse button, etc.)
			//Do nothing
			break;
		}
	}
	public void mouseReleased(MouseEvent e) {
		switch (e.getButton()) {
		case 1:		//Left mouse button
			Component c = e.getComponent();
			while (!(c instanceof JFrame)) {
				c = c.getParent();
				if (c == null) {
					return;
				}
			}
			JFrame myFrame = (JFrame)c;
			MyPanel myPanel = (MyPanel) myFrame.getContentPane().getComponent(0);  //Can also loop among components to find MyPanel
			Insets myInsets = myFrame.getInsets();
			int x1 = myInsets.left;
			int y1 = myInsets.top;
			e.translatePoint(-x1, -y1);
			int x = e.getX();
			int y = e.getY();
			myPanel.x = x;
			myPanel.y = y;
			int gridX = myPanel.getGridX(x, y);
			int gridY = myPanel.getGridY(x, y);
			if ((myPanel.mouseDownGridX == -1) || (myPanel.mouseDownGridY == -1)) {
				//Had pressed outside
				//Do nothing
			} else {
				if ((gridX == -1) || (gridY == -1)) {
					//Is releasing outside
					//Do nothing
				} else {
					if ((myPanel.mouseDownGridX != gridX) || (myPanel.mouseDownGridY != gridY)) {
						//Released the mouse button on a different cell where it was pressed
						//Do nothing
					} else {
						//Released the mouse button on the same cell where it was pressed

						//On the left column and on the top row... do nothing
						//
						//On the grid other than on the left column and on the top row:
						Color oldColor = myPanel.colorArray[myPanel.mouseDownGridX][myPanel.mouseDownGridY];

						if(oldColor.equals(Color.RED)){
							//Do nothing. It's flagged. 
						} else {
							boolean bombed = false;
							int column = myMinesweeper.getBombColumn();
							int row = myMinesweeper.getBombRow();
							Color newColor = Color.white; 
							
							if(myMinesweeper.checkBomb(gridX, gridY)){
								newColor = Color.black;
								for(int i = 0; i<column; i++) {
									for(int j = 0; j<row; j++) {
										if(myMinesweeper.checkBomb(i, j)){ 
											bombed = true;
											if (myPanel.colorArray[i][j].equals(Color.red)) {
												myPanel.colorArray[i][j] = Color.red;
											} else {
												myPanel.colorArray[i][j] = Color.black;
											}
										} else { 
											if (myPanel.colorArray[i][j].equals(Color.red)) {
												myPanel.colorArray[i][j] = Color.red;
											} else {
												myPanel.colorArray[i][j] = Color.white;
												
											}
										}
										myPanel.repaint();
									}
								}
							} 
							
							String num;
							int numAround; 
							
							if(bombed){
								for(int i = 0; i<column; i++) {
									for(int j = 0; j<row; j++) {
										Color currentColor = myPanel.colorArray[i][j];

										if(currentColor.equals(Color.white)){
											num = myMinesweeper.checkBombsArround(i, j);
											numAround = Integer.parseInt(num);
											if(numAround>0){
												myPanel.numOfBombs[i][j]=(num);
											}
										}
									}
								}								
							}
							
							if(newColor.equals(Color.white)){
								num = myMinesweeper.checkBombsArround(gridX, gridY);
								numAround = Integer.parseInt(num);
								if(numAround>0){
									myPanel.numOfBombs[gridX][gridY]=(num);
								}
							}
							myPanel.colorArray[myPanel.mouseDownGridX][myPanel.mouseDownGridY] = newColor;
							myPanel.repaint();
						}

					}
				}
			}
			myPanel.repaint();
			break;
		case 3:		//Right mouse button
			//Do nothing
			break;
		default:    //Some other button (2 = Middle mouse button, etc.)
			//Do nothing
			break;
		}
	}
}