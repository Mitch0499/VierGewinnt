package VierGewinnt;

import javafx.scene.paint.Color;

public class LogicModule {

	public int pitch [][] = new int [6][7];				//pitch[row][column] = Player occupys place
	public int points [] = new int [7];					//points[column] = amount of points in column
	private int coordinateX, coordinateY;
	private int player=0;
	private String firstPlayer, secondPlayer;
	private int countGames=1, winsFirstPlayer=0, winsSecondPlayer=0;
	
	//ensure that no points are in the columns
	private void initColumns() {
		for (int i=0; i<points.length; i++) {
			points[i]=0;			
		}
		setCountGames(getCountGames()+1);
	}
	
	//ensure that no one has points on playground
	private void initPitch() {
		for (int i=0; i<6; i++) {
			for (int j=0; j<7; j++) {
				pitch [i][j] = 0;
			}
		}
	}
	
	//count the points, save which Player place points in rectangle
	public void refreshPitch(int column, int player) {
		points[column]++;
		int row = 6-points[column];
		pitch[row][column]=player;
		setCoordinateX(column);
		setCoordinateY(row);
	}
	
	//tie game
	public boolean lookingForDraw() {
		int counter = 0;
		for (int column=0; column<7; column++) {
			if (pitch[0][column]!=0) {
				counter++;
			}
		}
		if (counter==7) {
			return true;
		}
		else {
			return false;
		}
	}
	
	
	//looks for four points in a row
	public boolean searchingWinner() {
		boolean win = false;
		//horizontal line
		for (int row=0; row<6; row++) {
			for (int column=0; column<4; column++) {
				if ((pitch[row][column]==pitch[row][column+1])&&
						(pitch[row][column+1]==pitch[row][column+2])&&
						(pitch[row][column+2]==pitch[row][column+3])&&
						(pitch[row][column]!=0)) {
					win = true;
				}
			}
		}
		//vertical line
		for (int column=0; column<7; column++) {
			for (int row=0; row<3; row++) {
				if ((pitch[row][column]==pitch[row+1][column])&&
						(pitch[row+1][column]==pitch[row+2][column])&&
						(pitch[row+2][column]==pitch[row+3][column])&&
						(pitch[row][column]!=0)) {
					win = true;
				}
			}
		}
		
		//diagonal, left top to bottom right line
		for (int row=3; row<6; row++) {
			for (int column=0; column<4; column++) {
				if ((pitch[row][column]==pitch[row-1][column+1])&&
						(pitch[row-1][column+1]==pitch[row-2][column+2])&&
						(pitch[row-2][column+2]==pitch[row-3][column+3])&&
						(pitch[row][column]!=0)) {
					win = true;
				}
			}
		}
		
		//diagonal, right top to bottom left line
		for (int row=0; row<3; row++) {
			for (int column=0; column<4; column++) {
				if ((pitch[row][column]==pitch[row+1][column+1])&&
						(pitch[row+1][column+1]==pitch[row+2][column+2])&&
						(pitch[row+2][column+2]==pitch[row+3][column+3])&&
						(pitch[row][column]!=0)) {
					win = true;
				}
			}
		}
		
		return win;
	}
	
	//reset game
	public void resetGame() {
		initColumns();
		initPitch();
		initPlayer();
	}
	
	//close all windows
	public void exitGame() {
		System.exit(0);
	}
  
	//Player Point Color
	public Color pointColor(int player) {
		if(player == 1) {
			return Color.RED;
		}else {
			return Color.GREEN;
		}
	}
	
	//Player change
	public void nextPlayer() {
		int reserve;
		player++;
		reserve = player % 2;
		if(reserve == 0) {
			player = 2;
		}else {
			player = 1;
		}
	}
	
	//alternately Player start

	public void initPlayer() {
		if (getCountGames()%2==0)
		{
			player=1;
		}
		if (getCountGames()%2==1)  {
			player=2;
		}
	}
	
	//Getter & Setter
	public int getCoordinateX() {
		return coordinateX;
	}
	public void setCoordinateX(int column1) {
		this.coordinateX = column1;
	}
	public int getCoordinateY() {
		return coordinateY;
	}
	public void setCoordinateY(int row1) {
		this.coordinateY = row1;
	}
	public int getPlayer() {
		return player;
	}
	public String getFirstPlayer() {
		return firstPlayer;
	}
	public void setFirstPlayer(String firstPlayer) {
		this.firstPlayer = firstPlayer;
	}
	public String getSecondPlayer() {
		return secondPlayer;
	}
	public void setSecondPlayer(String secondPlayer) {
		this.secondPlayer = secondPlayer;
	}

	public int getCountGames() {
		return countGames;
	}

	public void setCountGames(int countGames) {
		this.countGames = countGames;
	}

	public int getWinsFirstPlayer() {
		return winsFirstPlayer;
	}

	public void setWinsFirstPlayer(int winsFirstPlayer) {
		this.winsFirstPlayer = winsFirstPlayer;
	}

	public int getWinsSecondPlayer() {
		return winsSecondPlayer;
	}

	public void setWinsSecondPlayer(int winsSecondPlayer) {
		this.winsSecondPlayer = winsSecondPlayer;
	}

}
