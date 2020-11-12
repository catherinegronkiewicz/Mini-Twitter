package miniTwitter;

/*
 * Catherine Gronkiewicz
 * Professor Sun
 * CS 3560, Section 01
 * 12 November 2020
 * 
 * This class is visited each time
 * a group total is incremented - Visitor pattern
 */

public class GroupTotalVisitor implements Visitable {
	
	private static int groupTotal;
	
	public GroupTotalVisitor() {}
	
	// returns total number of groups
	public int getGroupTotal() {
		return groupTotal;
	}
	
	// accepts a GroupTotalVisitor visitor
	@Override
	public void accept(Visitor visit) {
		groupTotal++;
		visit.visit(this);
	}

}