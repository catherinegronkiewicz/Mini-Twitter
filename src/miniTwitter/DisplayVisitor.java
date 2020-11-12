package miniTwitter;

/*
 * Catherine Gronkiewicz
 * Professor Sun
 * CS 3560, Section 01
 * 12 November 2020
 * 
 * Concrete Visitor - each type of visitor of 
 * all the visit methods; each Visitor is responsible
 * for different operations - Visitor pattern
 */

public class DisplayVisitor implements Visitor {
	
	@Override
	public void visit(TotalUserVisitor utv) {
		System.out.println("Visiting TotalUserVisitor class!");
		utv.getUserTotal();
		
	}

	@Override
	public void visit(GroupTotalVisitor gtv) {
		System.out.println("Visiting GroupTotalVisitor class!");
		gtv.getGroupTotal();	
			
	}

	@Override
	public void visit(TotalTweetsVisitor ttv) {
		System.out.println("Visiting TotalTweetsVisitor class!");
		ttv.getTotalTweets();
				
	}

	@Override
	public void visit(PositivePercentVisitor ppv) {
		System.out.println("Visiting PositivePercentVisitor class!");
		ppv.getPosTotal();
		
	}

}