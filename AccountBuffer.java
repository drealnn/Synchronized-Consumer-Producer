/*  
	Name: Daniel Sledd      
	Course: CNT 4714 Spring 2016    
	Assignment title: Project 2 – Synchronized, Cooperating Threads Under Locking  
    Due Date: February 14, 2016 
*/
public interface AccountBuffer {
    public void withdraw( int value, String threadName );
    public void deposit(int value, String threadName);
}
