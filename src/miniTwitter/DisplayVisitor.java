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

}
