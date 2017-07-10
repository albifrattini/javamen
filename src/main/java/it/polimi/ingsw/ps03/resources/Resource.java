package it.polimi.ingsw.ps03.resources;

import java.io.Serializable;
/**
 * this class represents a Resource and in this class there are the methods to add and sub some resosources
 * @author Amministratore
 *
 */
public abstract class Resource implements Serializable{
	


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String rName;
	private int value;
	
	public Resource(String resName, int value){
		rName = resName;
		this.value = value;
	}
	public Resource(String resName){
		this(resName, 0);
	}

	public String getName(){
		return rName;
	}
	public int getValue(){
		return this.value;
	}
	public void setValue(int value){
		this.value = value;
	}
	/**
	 * this method adds a int to a specific Resource
	 * @param adder int to add
	 */
	public void add(int adder){
		value = value + adder;
	}
	/**
	 * this method sub a int to a specific Resource
	 * @param subber int to sub
	 */
	public void sub(int subber){
		if(this.value < value){
			throw new IllegalArgumentException();
		}
		value = value - subber;
	}
	
	
	@Override
	public String toString(){
		return rName + ": " + value;
	}
}
