package miniTwitter;

/*
 * Catherine Gronkiewicz
 * Professor Sun
 * CS 3560, Section 01
 * 9 December 2020
 * 
 * Concrete Visitor - each type of visitor of 
 * all the visit methods; each Visitor is responsible
 * for different operations - Visitor pattern
 */

public class DisplayVisitor implements Visitor {
	
	// holds most recent user
	private static String lastUser;
	
	@Override
	public void visit(TotalUserVisitor utv) {
		utv.getUserTotal();
		
	}

	@Override
	public void visit(GroupTotalVisitor gtv) {
		gtv.getGroupTotal();	
			
	}

	@Override
	public void visit(TotalTweetsVisitor ttv) {
		ttv.getTotalTweets();
				
	}

	@Override
	public void visit(PositivePercentVisitor ppv) {
		ppv.getPosTotal();
		
	}

	@Override
	public void visit(LastUpdatedVisitor lu) {
		lastUser = lu.getUserID();
	}
	
	// gets the most recent user
	public String getLastUser() {
		return lastUser;
	}

}