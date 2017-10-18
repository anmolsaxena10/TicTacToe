class MiniMax{
	int bestRow,bestCol;
	char player = 'X',opponent = 'O';
	
	boolean isMoveLeft(char board[][]){
		for(int i=0 ; i<3 ; i++){
			for(int j=0 ; j<3 ; j++){
				if(board[i][j] == '_'){
					return true;
				}
			}
		}
		return false;
	}
	
	int evaluate(char board[][]){
		
		for(int row=0 ; row<3 ; row++){
			if(board[row][0] == board[row][1] && board[row][1] == board[row][2]){
				if(board[row][0] == player)return(10);
				else if(board[row][0] == opponent)return(-10);
			}
		}
		
		for(int col=0 ; col<3 ; col++){
			if(board[0][col] == board[1][col] && board[1][col] == board[2][col]){
				if(board[0][col] == player)return(10);
				else if(board[0][col] == opponent)return(-10);
			}
		}
		
		if(board[0][0] == board[1][1] && board[2][2] == board[1][1]){
			if(board[0][0] == player)return(10);
			else if(board[0][0] == opponent)return(-10);
		}
		if(board[0][2] == board[1][1] && board[2][0] == board[1][1]){
			if(board[0][2] == player)return(10);
			else if(board[0][2] == opponent)return(-10);
		}
		
		return(0);
	}
	
	int minimax(char board[][], int depth, boolean isMax){
		
		int score = evaluate(board);
		
		if(score == 10 || score == -10){
			return(score);
		}
		
		if(!isMoveLeft(board)){
			return(0);
		}
		
		if(isMax){
			int best = -1000;
			
			for(int i=0 ; i<3 ; i++){
				for(int j=0 ; j<3 ; j++){
					if(board[i][j] == '_'){
						board[i][j] = player;
						
						best = max(best, minimax(board,depth+1,!isMax));
						
						board[i][j] = '_';
					}
				}
			}
			return(best);
		}
		else{
			int best = 1000;
			
			for(int i=0 ; i<3 ; i++){
				for(int j=0 ; j<3 ; j++){
					if(board[i][j] == '_'){
						board[i][j] = opponent;
						
						best = min(best, minimax(board,depth+1,!isMax));
						
						board[i][j] = '_';
					}
				}
			}
			return(best);
		}
	}
	
	void findBestMove(char board[][]){
		int bestVal = -1000;
		bestCol = -1;
		bestRow = -1;
		
		for(int i=0 ; i<3 ; i++){
			for(int j=0 ; j<3 ; j++){
				if(board[i][j] == '_'){
					board[i][j] = player;
					
					int moveVal = minimax(board, 0, false);
					
					board[i][j] = '_';
					
					if(moveVal > bestVal){
						bestRow = i;
						bestCol = j;
						bestVal = moveVal;
					}
				}
			}
		}
	}
	
	int max(int a, int b){
		if(a>b)return(a);
		else return(b);
	}
	int min(int a, int b){
		if(a<b)return(a);
		else return(b);
	}
}