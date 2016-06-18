import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/*  
	Name: Daniel Sledd      
	Course: CNT 4714 Spring 2016    
	Assignment title: Project 2 – Synchronized, Cooperating Threads Under Locking  
    Due Date: February 14, 2016 
*/
public class SynchronizedAccountBuffer implements AccountBuffer {

    private Lock accessLock = new ReentrantLock();

    private Condition canRead = accessLock.newCondition();
    private Condition canWrite = accessLock.newCondition();

    private int accountBalance = 0;
    private boolean occupied = false;


    @Override
    public void withdraw(int value, String threadName)
    {
        accessLock.lock();

        try {
            while (accountBalance - value < 0) {
                printState("", "Thread " + threadName + " withdraws " + value, "Withdraw - Blocked - Insufficient Funds");
                canWrite.await();
            }

            accountBalance -= value;

            printState("", "Thread " + threadName + " withdraws " + value, "Balance: " + accountBalance);
            canRead.signal();

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        finally
        {
            accessLock.unlock();
        }
    }

    @Override
    public void deposit(int value, String threadName) {
        int readValue = 0;
        accessLock.lock();

        accountBalance += value;
        printState("Thread " + threadName + " deposits " + value, "" , "Balance: " + accountBalance);

        canWrite.signal();

        accessLock.unlock();


    }

    public void printState(String depositString, String withdrawString, String balance)
    {
        System.out.printf("%-40s%-40s%-40s\n", depositString, withdrawString, balance);
    }
}
