//Sam Maynard
//November 30, 2011
//Period 2 Dvorsky
//Computer Science

//Class Tour.  The logic behind the Knight's moves.  Called from the Knight's main.
public class Tour {

	private static int cRow, cCol, moves;
	private static int[] horizontal = {2,1,-1,-2,-2,-1,1,2};
	private static int[] vertical = {-1,-2,-2,-1,1,2,2,1};
	private static int[][] board = new int[8][8];

	//Constructor. Resets variables.
	public Tour(int rowIn, int colIn){
		for(int i=0;i<8;i++){
			for(int j=0;j<8;j++){
				board[i][j] = 0;
			}//end for
		}//end for
		
		cRow = rowIn;
		cCol = colIn;
		moves = 1;
	}//end Constructor Tour
	
	//Called from Knight.  Instantiates the tour.
	public int startTour(ChessFrame fr){
		
		fr.clearMoves();
		
		int[][] accessMatrix = {{2,3,4,4,4,4,3,2},
								{3,4,6,6,6,6,4,3},
								{4,6,8,8,8,8,6,4},
								{4,6,8,8,8,8,6,4},
								{4,6,8,8,8,8,6,4},
								{4,6,8,8,8,8,6,4},
								{3,4,6,6,6,6,4,3},
								{2,3,4,4,4,4,3,2}};
		
		board[cRow][cCol] = moves;
		update(accessMatrix);
		//Updates the GUI
		fr.updateMove(cCol,cRow,board);
		
		while(move(accessMatrix)){	//Iterates the turn.  Move returns true, until
									//done.  then returns false.
			fr.updateMove(cCol,cRow,board);
			try {
				Thread.sleep(550-fr.getSpeed());
			} catch (InterruptedException e) {
				System.out.println("Can't Sleep");
			}//end try catch
		}//end while
		
		printBoard();
				
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			System.out.println("Can't Sleep");
		}//end try catch
		
		return moves-1;
	}//end startTour
	
	//Outputs the board to the terminal.
	private static void printBoard(){
		for(int i=0;i<8;i++){
			for(int j=0;j<8;j++){
				if(board[i][j] < 10){
					//Adds a 0 to single digit numbers to keep spacing equal.
					System.out.print("0" + board[i][j] + " ");
				}else{
					System.out.print(board[i][j] + " ");
				}//end if else
			}//end for
			System.out.println();
		}//end for
	}//end printBoard
	
	//Calculates best available move and updates board.
	private static boolean move(int[][] accessMatrix){
		int bestMv = -1,bestMvAccessNum=10;
		moves++;
		
		for(int crntMv=0;crntMv<8;crntMv++){
			if(moveIsValid(crntMv)){
				if(accessNum(crntMv,accessMatrix) < bestMvAccessNum){
					bestMv = crntMv;
					bestMvAccessNum = accessNum(crntMv,accessMatrix);
				}//end if
			}//end if
		}//end for
		
		if(bestMv != -1){
			cRow += vertical[bestMv];
			cCol += horizontal[bestMv];
			board[cRow][cCol] = moves;
			
			update(accessMatrix);
			
			return true;
		}else{
			return false;
		}//end if else
	}
	
	//Updates the Access Matrix when changes have been made to the board.
	private static void update(int[][] accessMatrix){
		for(int i=0;i<8;i++){ 
			if((cRow + vertical[i] < 8 && cRow + vertical[i] > -1) && (cCol + horizontal[i] < 8 && cCol + horizontal[i] > -1)){
				accessMatrix[cRow + vertical[i]][cCol + horizontal[i]]--;
			}//end if
		}//end for
	}//end update
	
	//Checks if a possible move is valid.
	private static boolean moveIsValid(int crntMv){
		if(cRow + vertical[crntMv] > 7 || cRow + vertical[crntMv] < 0){
			return false;
		}else if(cCol + horizontal[crntMv] > 7 || cCol + horizontal[crntMv] < 0){
			return false;
		}else if(board[cRow + vertical[crntMv]][cCol + horizontal[crntMv]] != 0){
			return false;
		}else{
			return true;
		}//end if else if else if else
	}//end moveIsUpdate
	
	//Retrieves the access heuristic for a square.  Returns 9 if it would be a final move.
	private static int accessNum(int mv,int[][]accessMatrix){
		int accNum = accessMatrix[cRow + vertical[mv]][cCol + horizontal[mv]];
		if(accNum != 0){
			return accNum;
		}else{
			return 9;
		}//end if else
	}//end accessNum
	
}//end Tour
