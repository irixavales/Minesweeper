import java.awt.Color;
import java.awt.Component;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;

public class MyMouseAdapter extends MouseAdapter {
	
	public void mousePressed(MouseEvent e) {
		switch (e.getButton()) {
		case 1:		//Left mouse button
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
			break;
		case 3:		//Right mouse button
			//Do nothing
			break;
		default:    //Some other button (2 = Middle mouse button, etc.)
			//Do nothing
			break;
		}
	}

	public void mouseReleased(MouseEvent e) {
		//switch (e.getButton()) {
		//case 1:		//Left mouse button
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

		switch (e.getButton()) {

		case 1:
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
						if(!myPanel.colorArray[myPanel.mouseDownGridX][myPanel.mouseDownGridY].equals(Color.RED)) { //Repaints cell light grey upon left click if the cell is not red.
							Color newColor = null;
							int isMine = MyPanel.isMine(gridX, gridY);
							switch (isMine) {
							case 0:
								newColor = Color.LIGHT_GRAY;
								myPanel.colorArray[gridX][gridY] = newColor;
								MyPanel.surroundingMines(gridX, gridY);
								break;
							case 1:
								myPanel.lostGame();								
								break;
							}
							
							myPanel.repaint();
						}	
					}

				}
			}



			myPanel.repaint();

			break;

		case 3:		//Right mouse button

			if ((myPanel.mouseDownGridX == -1) || (myPanel.mouseDownGridY == -1)) {
				//Had pressed outside
				//Do nothing
			} else {
				if ((gridX == -1) || (gridY == -1)) {
					//Is releasing outside
					//Do nothing
					//			if ((myPanel.mouseDownGridX != gridX) || (myPanel.mouseDownGridY != gridY)) {
					//				//Released the mouse button on a different cell where it was pressed
					//					//Do nothing
					//			
				}else {

					//Released the mouse button on the same cell where it was pressed
					Color newColor = null;	
					if(myPanel.colorArray[gridX][gridY].equals(Color.WHITE)){ //Changes cell to red upon right click while the original color was white
						newColor = Color.RED;
					}

					else if (myPanel.colorArray[gridX][gridY].equals(Color.RED)) { //Changes cell to white upon right click while the original color was red
						newColor = Color.WHITE;
					}
					else {
						break;
					}
					myPanel.colorArray[gridX][gridY] = newColor;
					myPanel.repaint();
				}

				myPanel.repaint();
				break;

			}

	default:    //Some other button (2 = Middle mouse button, etc.)
		//Do nothing
		break;


	}}}
