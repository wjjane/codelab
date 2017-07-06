package org.wj.farmproject;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * This is the core class to calculate crop combinations.
 * It decouples from specific crop types, so it requires 
 * an input of predefined crop types to initialize, and 
 * calculates various combinations based on another input number.
 *
 * @author Jane Wang
 * @version 1.0
 */
public class CropCombinations{
    /* To hold all predefined crop types during initialization. */
	private List<String> cropList = new ArrayList<>();
	
	/* To hold the latest result of calculated crop combinations.*/
	private Set<String> combSet = new HashSet<>();
	
	/**
	 * Initializes the crop list with crop types defined in other class.
	 * All later calculations will refer to this list.
	 * 
	 * @param cropList predefined crop types
	 */
	public CropCombinations(List<String> cropList) {
        this.cropList = cropList;
	}
	
	/**
	 * Gets the predefined crop types.
	 * 
	 * @return all crop types in string
	 */
	public List<String> getCropList() {
		return cropList;
	}
	
	/**
	 * Gets the latest calculated result of crop combinations,
	 * or a empty set if there's no action yet.
	 * 
	 * @return all crop combinations in string
	 */
	public Set<String> getCropCombinations() {
		return combSet;
	}
	
	/**
	 * The core function to perform the combination calculation.
	 * It takes the input number to select what crop types from cropList,
	 * then calculate all combinations and save to combSet.
	 * 
	 * @param count the number to select from cropList
	 * @throws CropException if the input number is over list index range
	 */
    public void combineCrops(int count) throws CropException{
    	    /* first check if the input is within cropList index range */
	    if (count <= 0 || count > cropList.size()) {
        	    throw new CropException("illegal number: " + count);
        }
    	
	    /* clear the combSet which may still store previous calculation result */
	    combSet.clear();
	    
	    /* to hold only the mixed crop combinations (the 2 crops per field type) */
	    List<String> mixCropList = new ArrayList<>();
	    
	    /* 1.calculate the mixed crop combinations, and save to the combSet*/
    	    for (int i = 0; i < count; i++) {
    	    	    for (int j = i + 1; j < count; j++) {
    	    	    	    /* find the mixed crop types, and calculate it with rest crop types*/
    	    	        mixCropList.add(cropList.get(i) + "+" + cropList.get(j));

    	    	        for (int k = 0; k < count; k++) {
    	    	        	    if (k != i && k != j) {
    	    	        	    	    mixCropList.add(cropList.get(k));
    	    	        	    }
    	    	        }
    	    	        
    	    	        doCombine(combSet, mixCropList, 0);
     	    	    /* clear the mixCropList after each mixed crop type*/
    	    	        mixCropList.clear();
    	    	    }
    	    }

    	    /* 2.calculate the crop combinations without mixed types, save to the combSet,
    	     * there may be same results as step 1, but combSet will auto-remove all redundant */
    	    List<String> selectCropList = cropList.subList(0, count);
    	    doCombine(combSet, selectCropList, 0);
    }
    
 
    /**
     * A utility function to support recursively calculating combinations
     * and save the result to the result parameter.
     * 
     * @param result the combinations produced after calculation
     * @param strList the source string list 
     * @param start the index of the string list for calculation
     */
    private void doCombine(Set<String> result, List<String> strList, int start){
    	    /* Use stirng builder to hold each combination, and add to the result.*/
        if (start != 0) {
        	   StringBuilder builder = new StringBuilder();
           for (int i = 0; i < start; i++) {
              builder.append(strList.get(i) + " ");
           }
           result.add(builder.toString().trim());
        }

        /* Recursively call doCombine till all possibilities are calculated. */
        for (int i = start; i < strList.size(); i++) {
           swap(strList, start, i);
           doCombine(result, strList, start + 1);
           swap(strList, start, i);
        }
    }
   
    /**
     * A utility function to switch the values of two items in the list.
     * 
     * @param strList the target list for swapping
     * @param pos1 the index of one value to be swapped
     * @param pos2 the index of other value to be swapped
     */
    private void swap(List<String> strList, int pos1, int pos2) {
       String temp = strList.get(pos1);
       strList.set(pos1, strList.get(pos2));
       strList.set(pos2, temp);
    }
}
