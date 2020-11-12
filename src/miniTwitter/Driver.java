package miniTwitter;

/*
 * Catherine Gronkiewicz
 * Professor Sun
 * CS 3560, Section 01
 * 12 November 2020
 * 
 * Creates single instance of AdminPanel -
 * Singleton pattern
 */

public class Driver {

    public static void main(String[] args) {
        AdminPanel.getInstance().setVisible(true);
    }
    
}