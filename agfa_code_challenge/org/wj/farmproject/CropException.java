package org.wj.farmproject;

/**
* This class defines crop related exceptions to support
* various error handling in our crop program.
* 
* 
* @author Jane Wang
* @version 1.0
*/
public class CropException extends Exception{

	private static final long serialVersionUID = 1L;

	public CropException() {
		super();
	}
	
	public CropException(String info) {
		super(info);
	}
}
