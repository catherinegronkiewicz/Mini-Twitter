package miniTwitter;

/*
 * Catherine Gronkiewicz
 * Professor Sun
 * CS 3560, Section 01
 * 12 November 2020
 * 
 * This is the entry point which enables an object
 * to be "visited" by the visitor object - Visitor pattern
 */

public interface Visitable {
	
	public void accept(Visitor visit);
	
}
