package org.wj.farmproject;
import java.util.ArrayList;
import java.util.List;

/**
 * This class defines all available crop types, and
 * utility functions to change to String or String list.
 * It's easy to delete or add new crop types here.
 * 
 * 
 * @author Jane Wang
 * @version 1.0
 */
public enum CropTypes {
	WHEAT("Wheat"),
	CORN("Corn"), 
	BARLEY("Barley"), 
	RYE("Rye"), 
	OATS("Oats"), 
	SOYBEANS("Soybeans"), 
	CANOLA("Canola");
	
    private final String type;

    private CropTypes(final String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return type;
    }
    
    /**
     * Gets all predefined crop types in strings, which 
     * makes things easier to pass to other aggregate functions 
     * than a single enum string.
     * 
     * @return a string list of crop types
     */
    public static List<String> toStringList() {
    	    List<String> list = new ArrayList<>();
    	    for (CropTypes type: CropTypes.values()) {
    	        list.add(type.toString());
    	    }
    	    return list;
    }
}
