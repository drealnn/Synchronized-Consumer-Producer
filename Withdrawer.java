import java.util.Random;

/*  
	Name: Daniel Sledd      
	Course: CNT 4714 Spring 2016    
	Assignment title: Project 2 – Synchronized, Cooperating Threads Under Locking  
    Due Date: February 14, 2016 
*/
public class Withdrawer implements Runnable {
     AccountBuffer account;
     String threadName;
     private static Random generator = new Random();

    public Withdrawer(AccountBuffer account, String threadName) {
        this.account = account;
        this.threadName = threadName;
    }


    @Override
    public void run() {
        while(true)
        {
            try {
                Thread.sleep(generator.nextInt(5));
                account.withdraw(1 + generator.nextInt(49), threadName);
            } catch(InterruptedException e)
            {
                e.printStackTrace();
            }
        }
    }
}
