package miniTwitter;

/*
 * Catherine Gronkiewicz
 * Professor Sun
 * CS 3560, Section 01
 * 12 November 2020
 * 
 * Interface used to declare the visit operations
 * for all the types of Visitable classes - Visitor pattern
 */

public interface Visitor {
	
    public void visit (TotalUserVisitor utv);
    public void visit (GroupTotalVisitor gtv);
    public void visit (TotalTweetsVisitor ttv);
    public void visit (PositivePercentVisitor ppv);
	
}
