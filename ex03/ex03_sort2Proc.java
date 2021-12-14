import java.util.*;
import java.util.concurrent.CountDownLatch;


class ex03_sort2Proc
{
    static final int Max = 1001;
    static final int N = 100000;
    static int A[] = new int[N];
    static CountDownLatch latch = new CountDownLatch(1);
    
    static class SortThread implements Runnable
    {
        int Left, Right;
        
        public SortThread(int left, int right) 
        { 
            Left = left; Right = right;   
        }
                        
        public void run()
        {
            Arrays.sort(A, Left, Right);
            latch.countDown();
        }
    }
                    
    static public void main(String args[]) 
    {

        int M1[] = new int[(N/2)+1];
        int M2[] = new int[(N/2)+1];
        int i,j,k;

        Random rand = new Random();
        for(i = 0; i < N; ++i)
            A[i] = rand.nextInt(1000);
        
        new Thread(new SortThread(0, N / 2)).start();
        new Thread(new SortThread(N / 2, N)).start();
        
        try
        {
            latch.await();
            latch.await();
        }
        catch(Exception e){}
            
        k=0;
        for(i=0;i<N/2;i++) M1[i] = A[i];
        M1[i] = Max;
        for(i=N/2,j=0;i<N;i++,j++) M2[j] = A[i];
        M2[j] = Max;
        i=0; j=0; k=0;
        while(true){
            if(M1[i] == M2[j] && M1[i] == Max) break;
            if(M1[i] <= M2[j]){
                A[k] = M1[i]; k++; i++;
            }
            else if(M1[i] >= M2[j]){
                A[k] = M2[j]; k++; j++;
            }
        }
      //for(i=0;i<N;i++) System.out.println(A[i]);
    }
}

