package bu.edu.upark.utils;

import org.junit.Assert;

import net.sf.json.JSONObject;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class SearchUtilsTest {
	
	public static JSONObject data;
	public static double[] southwest;
	public static double[] northeast;
	
	
	@BeforeClass 
	public static void before() {
		System.out.println("global");
		String path = "./src/test.json";
		String str = SearchUtils.ReadFile(path);
		data = JSONObject.fromObject(str);
		southwest = SearchUtils.getSouthWest(data);
		northeast = SearchUtils.getNorthEast(data);
	}
	
	@AfterClass
	public static void after() {
		System.out.println("global desotry");
	}
	
	@Before
	public void setUp() throws Exception {
		System.out.println("set up a test");
	}
	
	@After
	public void tearDown() throws Exception {
		System.out.println("tear down a test");
	}
	

	@Test
	public void getZipcodeTest() {
		Assert.assertEquals("94043", SearchUtils.getZipcode(data));
	}
	
	@Test
	public void getFormattedAddressTest() {
		Assert.assertEquals("1600 Amphitheatre Pkwy, Mountain View, CA 94043, USA", SearchUtils.getFormattedAddress(data));
	}
	
	@Test
	public void getSouthwestTest() {
	
	}
	
	@Test
	public void getNortheastTest() {
		
		
	}
	
	@Test
	public void getNewSouthWestTest() {
		
	}
	
	@Test
	public void getNewNorthEestTest() {
		
	}
	


	 
}
