package org.wj.farmproject;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;

public class CropCombinationsTest {
	CropCombinations cropComb = null;
	
	@Before
	public void runBeforeEveryTest() {
		assertTrue (CropTypes.toStringList().size() == 7);
		cropComb = new CropCombinations(CropTypes.toStringList());
		
	}

    @Test
    public void testInitializedStatus() {
    	    assertTrue (cropComb.getCropCombinations().size() == 0);
    	    assertTrue (cropComb.getCropList().size() == 7);
    }
	
    @Test(expected = CropException.class)
    public void testCombineCropException () throws CropException {
    	    cropComb.combineCrops(100);
    }
    
	@Test
	public void testCombineCrops() {
		try {
			cropComb.combineCrops(3);
			assertTrue (cropComb.getCropCombinations().size() == 24);
		} 
		catch (CropException e) {
			fail("CropException shouldn't be thrown in this case.");
			e.printStackTrace();
		}
	}

}
