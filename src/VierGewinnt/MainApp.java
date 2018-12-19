package VierGewinnt;

import javafx.scene.paint.Color;

public class MainApp {

	public static void main(String[] args) {
		
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
	

}
