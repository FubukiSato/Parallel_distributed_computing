import java.util.concurrent.CountDownLatch;

class ex03_money
{
    static int flag = 0;
    static int Account = 0;
    static CountDownLatch latch = new CountDownLatch(2);
    //static Semaphore S = new Semaphore(0);
    //static Semaphore M = new Semaphore(1);

    /*static class Spouse implements Runnable
    {
        private int Sum;
        public Spouse(int sum) { Sum = sum; }
                        
        public void run()
        {
            for (int i = 0; i < Sum; i++)
            {
                try { M.acquire(); } catch(Exception e){}
                    
                Account++;
                
                M.release();
            }
            
            S.release();
        }
    }*/

    public class Global {
        public static final Object lock = new Object();
    }

    static class AccountType implements Runnable
    {
        private int Sum;
        public AccountType(int sum) { this.Sum = sum;}
                        
        public int GetValue(){
            return Account;
        }

        public void AddOneUnit(){
            synchronized (Global.lock){ 
            for(int i=0;i<this.Sum;i++){
                Account++;
            }
        }
        }

        public void run(){
            this.AddOneUnit();
            latch.countDown();
        }
    }
                    
    static public void main(String args[]) 
    {
        AccountType husband = new AccountType(500000);
        AccountType wife = new AccountType(500000);
        
        new Thread(husband).start();
        new Thread(wife).start();
        
        try
        {
            latch.await();
        }
        catch(Exception e){}
     
        System.out.println(Account);
    }
}
