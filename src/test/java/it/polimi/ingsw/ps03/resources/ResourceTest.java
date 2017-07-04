package it.polimi.ingsw.ps03.resources;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class ResourceTest  {
	
	private String rName;
	private int value;
	
	public void Resource(String resName, int value){
		rName = resName;
		this.value = value;
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
	public void add(int adder){
		value = value + adder;
	}
	public void sub(int subber){
		if(this.value < value){
			throw new IllegalArgumentException();
		}
		value = value - subber;
	}

	@Before
	public void setUp() throws Exception {
		Resource("COINS", 3);
	}

	@Test
	public void testGetName() {
	  assertEquals("COINS", this.rName);
	}

	@Test
	public void testGetValue() {
	  assertEquals(3,this.value);
	}

	@Test
	public void testSetValue() {
	  setValue(5);
	  assertEquals(5,this.value);
	}

	@Test
	public void testAdd() {
       add(6);
 	  assertEquals(9,this.value);
	}

	@Test
	public void testSub() {
		sub(2);
		  assertEquals(1,this.value);
	}

}
