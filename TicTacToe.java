import java.awt.*;
import java.awt.event.*;

class TicTacToe extends Frame implements ActionListener{
	
	char board[][] = {
		{'_','_','_'},
		{'_','_','_'},
		{'_','_','_'}
	};
	
	MiniMax mm;
	Button b[][];
	Label l1,l2;
	Panel p;
	MenuBar mb;
	Menu action;
	MenuItem reset,close;
	int chance = 0;
	
	TicTacToe(){
		setTitle("Unbeatable TicTacToe!!!");
		setSize(300,300);
		setVisible(true);
		
		mb = new MenuBar();
		action = new Menu("Actions");
		reset = new MenuItem("Reset");
		close = new MenuItem("Close");
		
		close.addActionListener(this);
		reset.addActionListener(this);
		
		mb.add(action);
		action.add(reset);
		action.add(close);
		setMenuBar(mb);
		
		
		l1 = new Label("Can You Beat Me ???",Label.CENTER);
		l2 = new Label("You start First!!",Label.CENTER);
		
		p = new Panel();
		p.setLayout(new GridLayout(3,3));
		
		b = new Button[3][3];
		for(int i=0 ; i<3 ; i++){
			for(int j=0 ; j<3 ; j++){
				b[i][j] = new Button("");
				p.add(b[i][j]);
				b[i][j].addActionListener(this);
			}
		}
		
		add(l1,BorderLayout.NORTH);
		add(p,BorderLayout.CENTER);
		add(l2,BorderLayout.SOUTH);
		
		mm = new MiniMax();
		
		addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent we){
				dispose();
			}
		});
	}
	
	public void actionPerformed(ActionEvent ae){
		
		if(ae.getSource() == close){
			dispose();
		}
		else if(ae.getSource() == reset){
			reset();
		}
		
		if(ae.getSource() instanceof Button){
			System.out.println("Button Clicked");
			
			Button b1 = (Button)ae.getSource();
			b1.setLabel("O");
			b1.removeActionListener(this);
			updateBoard();
			
			if(mm.isMoveLeft(board)){
				playNextMove();
			}
			if(mm.evaluate(board) == 10){
				l2.setText("I WON!!!");
			}
			else if(mm.evaluate(board) == -10){
				l2.setText("You WON!!!");
			}
			else if(!mm.isMoveLeft(board)){
				l2.setText("It's a Draw!!!");
			}
		}
	}

	void updateBoard(){
		for(int i=0 ; i<3 ; i++){
			for(int j=0 ; j<3 ; j++){
				String c = b[i][j].getLabel();
				if(c == "X"){board[i][j] = 'X';}
				else if(c == "O"){board[i][j] = 'O';}
				else{board[i][j] = '_';}
			}
		}
	}
	
	void reset(){
		for(int i=0 ; i<3 ; i++){
			for(int j=0 ; j<3 ; j++){
				if(board[i][j] != '_'){
					board[i][j] = '_';
					b[i][j].addActionListener(this);
				}
				b[i][j].setLabel("");
			}
		}
		l2.setText("You start first!!");
	}
	
	void playNextMove(){
		mm.findBestMove(board);
		int row=mm.bestRow , col=mm.bestCol;
		
		System.out.println("Row: "+row+", Col: "+col);
		b[row][col].setLabel("X");
		b[row][col].removeActionListener(this);
		updateBoard();
	}
	
	public static void main(String args[]){
		new TicTacToe();
	}
}