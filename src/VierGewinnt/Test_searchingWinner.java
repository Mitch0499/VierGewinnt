package VierGewinnt;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class Test_searchingWinner {

private MainApp m = null;
	
	@Before
	public void before () {
		this.m = new MainApp();
	}
	
	@Test
	public void test() {
		m.pitch[3][4]=2;
		m.pitch[2][3]=2;
		m.pitch[1][2]=2;
		m.pitch[0][1]=2;
		assertEquals(m.searchingWinner(), true);
	}

}
