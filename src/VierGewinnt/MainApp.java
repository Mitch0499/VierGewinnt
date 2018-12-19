package VierGewinnt;

public class MainApp {

	public static void main(String[] args) {
		
	}
	
	//Point Color
	public String pointColor(int player) {
		if(player == 1) {
			return "RED";
		}else {
			return "GREEN";
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
