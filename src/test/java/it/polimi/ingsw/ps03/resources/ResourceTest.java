package it.polimi.ingsw.ps03.resources;

import static org.junit.Assert.*;

import org.junit.Test;

public class ResourceTest {
	private String rName;
	private int value=4;
	
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


	@Test
	public void testGetName() {
		Resource("wood", 3);
		assertEquals("wood", getName());
	}

	@Test
	public void testGetValue() {
		Resource("wood", 3);
		assertEquals(3, getValue());
	}

	@Test
	public void testSetValue() {
		setValue(7);
		assertEquals(7,value);
	}

	@Test
	public void testAdd() {
      add(6);
      assertEquals(10,value);
	}

	@Test
	public void testSub() {
		sub(2);
		assertEquals(2,value);
	}
/*	@Test
	public void testSecondSub() {
		sub(6);
		assertEquals(4,value);
	}*/
	


}
