//Sam Maynard
//September 30, 2011
//Period 2 Dvorsky
//Computer Science

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

//Is used in ChessFrame.  Displays the board and the knight.
@SuppressWarnings("serial")
public class ChessBoard extends Container{
	
	private static ImageIcon board = new ImageIcon("chessboard.jpg");
	private static ImageIcon selectSq = new ImageIcon("selectSq.png");
	private static ImageIcon knight = new ImageIcon("knight.gif");
	
	private static int[][] outArr = new int[8][8];
	private static int x=158, y=62, dispSlSq=0, slX, slY;
	
	private static ArrayList<Integer> lX = new ArrayList<Integer>();
	private static ArrayList<Integer> lY = new ArrayList<Integer>();
	
	//Called by ChessFrame called by Tour.  Updates global variables used during paint.
	//Then repaints.
	public void updateMove(int xSq,int ySq,int[][] board){
		x = 158 + xSq * 36;
		y = 62 + ySq * 36;
		lX.add(x+15);
		lY.add(y+15);
		outArr = board;
		this.repaint();
	}//end updateMove
	
	public void clearLines(){
		lX.clear();
		lY.clear();
	}
	
	//Called by ChessFrame  Updates global variables used during paint.
	//Then repaints.
	public void dispSelectionSq(int xIn, int yIn){
		dispSlSq = 1;
		slX = xIn;
		slY = yIn;
	}//end dispSelectionSq
	
	//Called by ChessFrame  Updates global variables used during paint.
	//Then repaints.
	public void slSqStopDisp(){
		dispSlSq = 0;
		this.repaint();
	}//end slSqStopDisp
	
	//Inherited from component.  Is automatically called.  Displays the imported images.
	public void paint(Graphics g){
		board.paintIcon(this, g, 0, 0);
		
		if(dispSlSq == 1){
			selectSq.paintIcon(this, g,156+(36*slX), 61+(36*slY));
		}//end if
		
		g.setColor(Color.ORANGE);
		/*
		g.setColor(new Color((int) (Math.random() * 256),(int) (Math.random() * 256),
				(int) (Math.random() * 256)));
				*/
		for(int i=1;i<lX.size();i++){
			/*g.setColor(new Color((int) (Math.random() * 256),(int) (Math.random() * 256),
					(int) (Math.random() * 256)));*/
			//g.setColor(Color.ORANGE);
			g.drawLine(lX.get(i-1), lY.get(i-1), lX.get(i), lY.get(i));
			g.drawLine(lX.get(i-1), lY.get(i-1)+1, lX.get(i), lY.get(i)+1);
			g.drawLine(lX.get(i-1), lY.get(i-1)-1, lX.get(i), lY.get(i)-1);
		}
		
		g.setFont(new Font("Serif",Font.BOLD,29));
		g.setColor(Color.WHITE);
		for(int i=0;i<8;i++){
			for(int j=0;j<8;j++){
				if(outArr[i][j] != 0){
					if(outArr[i][j]<10){
						g.drawString("0"+Integer.toString(outArr[i][j]), 162+(j*35), 90+(i*36) );
					}else{
						g.drawString(Integer.toString(outArr[i][j]), 162+(j*35), 90+(i*36) );
					}//end if else
				}//end if
			}//end for
		}//end for
		
		
		knight.paintIcon(this, g, x, y);
	}//end paint

}//end ChessBoard