//Sam Maynard
//September 30, 2011
//Period 2 Dvorsky
//Computer Science

//Knight instantiates the program with a main as well as calls the actual tour.
public class Knight {
	
	private static ChessFrame fr;	//The GUI
	private static int num=0;		
	private static int row,col;
	
	//Main.  Calls Tour, and prints results.
	public static void main(String[] args){
		fr = new ChessFrame();
		
		//Workaround using the JFrame.  If the JFrame buttons call anything directly,
		//the ChessBoard will not update.  Instead, the JButtons change a number which
		//the if's pick up within an infinite loop.  The program exits through a 
		//Sys.exit(0) from the quit button.
		while(true){
			if(num==1){ //All tours
				int completeTours = 0,crntTour;
				for(int cRow=0;cRow<8;cRow++){
					for(int cCol=0;cCol<8;cCol++){
						Tour knight = new Tour(cRow, cCol);	
						crntTour = knight.startTour(fr);
						System.out.println("\nMoves made: " + crntTour + "\n");
						if(crntTour == 64){
							completeTours++;
						}//end if
					}//end for
				}//end if
				System.out.println("\nNumber of complete tours:  " + completeTours);
				num = 0;
			}else if(num == 2){ //Single Tour
				Tour knight = new Tour(row,col);
				System.out.println("\nMoves made: " + knight.startTour(fr) + "\n");
				num = 0;
			}//end else if
		}//end while
	}//end main
	
	//Part of the workaround.  Called when a square is clicked on.
	public static void startSingleTour(int colIn,int rowIn){
		row = rowIn;
		col = colIn;
		num = 2;
	}//end startSingleTour
	
	//Part of the workaround.  Called when "Run all tours" is clicked.
	public static void startAllTours(){
		num = 1;
	}//end startAllTours
}//end Knight
