//Sam Maynard
//September 30, 2011
//Period 2 Dvorsky
//Computer Science

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.JTextArea;

//The GUI.  Uses CHessBoard as well as creates buttons and a Text Area of the moves.
@SuppressWarnings("serial")
public class ChessFrame extends JFrame implements ActionListener, MouseListener{
	
	private static JFrame chessFr;
	private static JSlider speed;
	private static ChessBoard bd;
	private static JTextArea moves;
	
	//Constructor.  Creates the board and the buttons and positions them.
	public ChessFrame(){

		chessFr = new JFrame("Chessboard");
		
		chessFr.setSize(1020,430);
		chessFr.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		chessFr.setLayout(null);
		
		moves = new JTextArea("Knights Moves:");
		JScrollPane movesScPn = new JScrollPane(moves);
		bd = new ChessBoard();	
		JLabel sliderLbl = new JLabel("Speed:");
		sliderLbl.setFont(new Font("Dialog",Font.BOLD,16));
		speed = new JSlider(50, 545);
		JLabel optInfo = new JLabel("CLICK ON ANY SQUARE");
		JLabel or = new JLabel("OR");
		JButton allTrsBtn = new JButton("Run All Tours");
		JButton quitBtn = new JButton("QUIT");
		
		moves.setLineWrap(true);
		moves.setEditable(false);
		allTrsBtn.addActionListener(this);
		allTrsBtn.setActionCommand("All");
		quitBtn.addActionListener(this);
		quitBtn.setActionCommand("Quit");
		
		chessFr.add(movesScPn);
		chessFr.add(bd);
		chessFr.add(sliderLbl);
		chessFr.add(speed);
		chessFr.add(optInfo);
		chessFr.add(or);
		chessFr.add(allTrsBtn);
		chessFr.add(quitBtn);
		
		movesScPn.setBounds(5,5,190,235);
		bd.setBounds(200, 0, 603, 405);
		sliderLbl.setBounds(812, 20, 150, 20);
		speed.setBounds(810, 50, 200, 25);
		optInfo.setBounds(843,105,150,50);
		or.setBounds(900, 143, 20, 20);
		allTrsBtn.setBounds(835, 170, 150, 50);
		quitBtn.setBounds(835, 330, 150,50);
		
		chessFr.setVisible(true);
		
		bd.addMouseListener(this);
		addMouseListener(this);
	}//end Constructor ChessFrame
	
	
	//returns the value on the speed slider
	public int getSpeed(){
		return speed.getValue();
	}//end getSpeed
	
	//Called by Tour, which 'forwards' the call to ChessBoard
	public void updateMove(int col, int row, int[][] boardMat){
		bd.updateMove(col,row,boardMat);
		moves.append("n"+ (char)(col+65) + (8-row) + "   ");
	}//end updateMove
	
	//Clears the Text Area holding the moves.  Called from Tour.
	public void clearMoves(){
		moves.setText("Knights Moves:\n");
		bd.clearLines();
	}//end clearMoves
	
	//Listens for button clicks.  Inherited from ActionListener.
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("Quit")){
			System.exit(0);
		}else if(e.getActionCommand().equals("All")){
			Knight.startAllTours();
		}//end if else
	}//end actionPerformed

	//Listens for mouse presses.  Inherited from MouseListener.
	public void mousePressed(MouseEvent e) {
		if(e.getX()	> 158 && e.getY() > 62 && e.getX()< 441 && e.getY() < 346){
			bd.dispSelectionSq((e.getX()-158)/36, (e.getY()-62)/36);
		}//end if
	}//end mousePressed.

	//Listens for mouse releases.  Inherited from MouseListener.
	public void mouseReleased(MouseEvent e) {
		bd.slSqStopDisp();
		if(e.getX()	> 158 && e.getY() > 62 && e.getX()< 441 && e.getY() < 346){
			Knight.startSingleTour((e.getX()-158)/36, (e.getY()-62)/36);
		}//end if
	}//end mouseReleased
	
	//Unused method
	public void mouseClicked(MouseEvent e) {
		//Required by MouseListener
	}//end mouseClicked

	public void mouseEntered(MouseEvent e) {
		//Required by MouseListener
	}//end mouseEnetered

	public void mouseExited(MouseEvent e) {
		//Required by MouseListener
	}//end mouseExited

}//end ChessFrame
