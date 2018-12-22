package VierGewinnt;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class Test_nextPlayerMethode {

	private MainApp m = null;
	
	@Before
	public void before () {
		this.m = new MainApp();
	}
	
	@Test
	public void test() {
		m.initPlayer();
		int player=0;
		if (m.getPlayer()==1) {
			player=2;
		}
		if (m.getPlayer()==2) {
			player=1;
		}
		m.nextPlayer();
		assertEquals(m.getPlayer(), player);
	}

}
