package VierGewinnt;


import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.paint.Color;

public class MainApp extends Application {

	int pitch [][] = new int [6][7];				//picht[zeile][spalte]=Spieler der Platz besetzt
	static int points [] = new int [7];			//in der Klammer der Array steht welche Spalte betrachtet wird und das Ergebniss das gespeichert wird sind die Anzahl Spielchips in der Spalte
	private int coordinateX, coordinateY;
	
	public static void main(String[] args) {

		launch(args);
	}
	
	public static void initColumns() {
		for (int i=0; i<points.length; i++) {
			points[i]=0;			
		}
	}
	
	public void refreshPitch(int column, int player) {
		points[column]++;							//Spielchips dazuzählen
		int row = 6-points[column];
		pitch[row][column]=player;
		setCoordinateX(column);
		setCoordinateY(row);
	}
	
	public boolean searchingWinner() {
		boolean win = false;
		//horizontal 4er Reihe
		for (int row=0; row<6; row++) {
			for (int column=0; column<4; column++) {
				if ((pitch[row][column]==pitch[row][column+1])&&
						(pitch[row][column+1]==pitch[row][column+2])&&
						(pitch[row][column+2]==pitch[row][column+3])) {
					win = true;
				}
			}
		}
		//vertikal 4er Reihe
		for (int column=0; column<7; column++) {
			for (int row=0; row<3; row++) {
				if ((pitch[row][column]==pitch[row+1][column])&&
						(pitch[row+1][column]==pitch[row+2][column])&&
						(pitch[row+2][column]==pitch[row+3][column])) {
					win = true;
				}
			}
		}
		
		//diagnoal von links unten nach rechts oben 4er Reihe
		for (int row=3; row<6; row++) {
			for (int column=0; column<4; column++) {
				if ((pitch[row][column]==pitch[row-1][column+1])&&
						(pitch[row-1][column+1]==pitch[row-2][column+2])&&
						(pitch[row-2][column+2]==pitch[row-3][column+3])) {
					win = true;
				}
			}
		}
		
		//diagonal von rechts unten nach links oben 4er Reihe
		for (int row=0; row<3; row++) {
			for (int column=0; column<4; column++) {
				if ((pitch[row][column]==pitch[row+1][column+1])&&
						(pitch[row+1][column+1]==pitch[row+2][column+2])&&
						(pitch[row+2][column+2]==pitch[row+3][column+3])) {
					win = true;
				}
			}
		}
		
		return win;
	}
  
  //Point Color
	public static Color pointColor(int player) {
		if(player == 1) {
			return Color.RED;
		}else {
			return Color.GREEN;
		}
	}
	
	//Player change
	public int nextPlayer(int player) {
		int reserve;
		player++;
		reserve = player % 2;
		if(reserve == 0) {
			player = 2;
		}else {
			player = 1;
		}
		return player;
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

	@Override
	public void start(Stage primaryStage) throws Exception {
		initColumns();
	}

		
	}

}
