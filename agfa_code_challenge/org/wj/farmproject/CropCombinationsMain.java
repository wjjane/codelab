package org.wj.farmproject;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Set;

/**
 * Defines the program entrance main, glue calculation functions (CropCombinations)
 * and crop types (CropTypes) here, provides a simple responsive GUI
 * to allow multiple times calculation. 
 * 
 * @author Jane Wang
 * @version 1.0
 */
public class CropCombinationsMain {
	/* This class mainly hold entrance main, so no need to instantiate this class.*/
    private CropCombinationsMain() {}

    public static void main(String[] args) {
    	    /* Initializes CropCombination object with defined crop types in CropTypes class.*/
        CropCombinations cropComb = new CropCombinations(CropTypes.toStringList());

        /* Defines a simple GUI here. */
        try (InputStreamReader in = new InputStreamReader(System.in);
            BufferedReader bufin = new BufferedReader(in)){
        	     
            printHelp(cropComb);
        	    String line;
            while(true) {
               line = bufin.readLine();
                
               if (line.equals("h")) {
            	       printHelp(cropComb);
               }
               else if (line.equals("q")) {
            	       System.out.println("Bye!");
            	       break;
               }
               else {
            	       /* calculate and print the combinations if input is legal number */
                   int number;
                   try {
                       number = Integer.parseInt(line);
                       cropComb.combineCrops(number);
                       printCropCombinations(cropComb);
                   } 
                   catch (NumberFormatException e) {
            	           System.out.println("warn: wrong input, please try again.");
                   }
                   catch (CropException e) {
           	           System.out.println("warn: " + e.getMessage());
                   }
               }
            } 
         }
         catch (IOException e) {
        	     e.printStackTrace();
         }
    }
    
    /**
     * Utility function to print help menu on GUI.
     * @param cropComb provides available crop type info
     */
	protected static void printHelp(CropCombinations cropComb) {
		System.out.println("Usage:");
		System.out.println("\tExit:\tq");
		System.out.println("\tHelp:\th");
		System.out.println("\tCaculate combinations: input a number shown below:\n");
		List<String> cropList = cropComb.getCropList();
		for (int i = 0; i < cropList.size(); i++) {
			System.out.println((i+1)  + " = " + cropList.subList(0, i+1));
		}
		System.out.println();
	}
	
	/**
	 * Utility function to print combination result.
	 * @param cropComb provides the combination set after each calculation
	 */
	protected static void printCropCombinations(CropCombinations cropComb) {
		Set<String> combSet = cropComb.getCropCombinations();
	    System.out.println("total combination count: " + combSet.size());  
	    System.out.println("---------------------------");
	    combSet.forEach(s -> System.out.println(s));    
	    System.out.println();
	}
}
