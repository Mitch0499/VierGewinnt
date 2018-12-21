package VierGewinnt;

import javafx.scene.paint.Color;

public class MainApp {

	public int pitch [][] = new int [6][7];				//picht[zeile][spalte]=Spieler der Platz besetzt
	public int points [] = new int [7];			//in der Klammer der Array steht welche Spalte betrachtet wird und das Ergebniss das gespeichert wird sind die Anzahl Spielchips in der Spalte
	private int coordinateX, coordinateY;
	private int player;
	private String firstPlayer, secondPlayer;
	
	//Setzt dass in allen Spalten keine Spielsteine sind
	private void initColumns() {
		for (int i=0; i<points.length; i++) {
			points[i]=0;			
		}
	}
	
	//Setzt dass kein Spieler einen Spielstein in einem Feld hat
	private void initPitch() {
		for (int i=0; i<6; i++) {
			for (int j=0; j<7; j++) {
				pitch [i][j] = 0;
			}
		}
	}
	
	//Zaehlt die Anzahl Spielsteine hoch und speichert welcher Spieler in welchem Feld den Stein gesetzt hat
	public void refreshPitch(int column, int player) {
		points[column]++;
		int row = 6-points[column];
		pitch[row][column]=player;
		setCoordinateX(column);
		setCoordinateY(row);
	}
	
	//Sucht nach einem Gewinner indem nach 4 Spielsteinen in einer Reihe vom selben Spieler gesucht wird
	public boolean searchingWinner() {
		boolean win = false;
		//horizontal 4er Reihe
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
		//vertikal 4er Reihe
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
		
		//diagnoal von links unten nach rechts oben 4er Reihe
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
		
		//diagonal von rechts unten nach links oben 4er Reihe
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
	
	//Setzt Spiel zurueck
	public void resetGame() {
		initColumns();
		initPitch();
		initPlayer();
	}
	
	//Schliesst Fenster
	public void exitGame() {
		System.exit(0);
	}
  
  //Point Color
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
	
	public void initPlayer() {
		player=0;
	}
	
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

}
