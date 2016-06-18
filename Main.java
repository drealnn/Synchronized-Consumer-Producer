import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/*  
	Name: Daniel Sledd      
	Course: CNT 4714 Spring 2016    
	Assignment title: Project 2 – Synchronized, Cooperating Threads Under Locking  
    Due Date: February 14, 2016 
*/

public class Main {

    public static void main(String[] args)
    {
        AccountBuffer account = new SynchronizedAccountBuffer();
        ExecutorService app = Executors.newFixedThreadPool(9);

        try
        {
            app.execute(new Depositer(account ,"1"));
            app.execute(new Depositer(account ,"2"));
            app.execute(new Depositer(account ,"3"));
            app.execute(new Withdrawer(account ,"1"));
            app.execute(new Withdrawer(account ,"2"));
            app.execute(new Withdrawer(account ,"3"));
            app.execute(new Withdrawer(account ,"4"));
            app.execute(new Withdrawer(account ,"5"));
            app.execute(new Withdrawer(account ,"6"));

        }catch(Exception e)
        {
            e.printStackTrace();
        }

        app.shutdown();
    }
}
