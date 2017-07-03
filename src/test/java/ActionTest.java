import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ActionTest {

	private static String actionName;
	public void Action(String name){
		actionName = name;
	}
	
	public static String getAction(){
		return actionName;
	}
	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {}
	

	@Test
	public void testGetAction() {
		String name = "test per il nome";
		Action(name);
	    assertEquals( "test per il nome", getAction());
	}
	


	@Test
	public void testGetBillboard() {
		fail("Not yet implemented");
	}

	@Test
	public void testSetBillboard() {
		fail("Not yet implemented");
	}

}
