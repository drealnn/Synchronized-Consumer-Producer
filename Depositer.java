import java.util.Random;

/*  
	Name: Daniel Sledd      
	Course: CNT 4714 Spring 2016    
	Assignment title: Project 2 – Synchronized, Cooperating Threads Under Locking  
    Due Date: February 14, 2016 
*/
public class Depositer implements Runnable {
    AccountBuffer account;
    String threadName;
    private static Random generator = new Random();

    public Depositer(AccountBuffer account, String threadName) {
        this.account = account;
        this.threadName = threadName;
    }


    @Override
    public void run() {
        while(true)
        {
            try {
                Thread.sleep(40);
                account.deposit(1 + generator.nextInt(199), threadName);
            } catch(InterruptedException e)
            {
                e.printStackTrace();
            }
        }
    }
}

