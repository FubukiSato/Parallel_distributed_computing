import java.util.*;
import java.util.concurrent.CountDownLatch;

class ex03_max{
    static int maxmax=0;
    static final int N = 10;
    static int A[] = new int[N];
    static CountDownLatch latch = new CountDownLatch(4);

    static class MaxThread extends Thread{

        MaxThread(int _start,int _end){
            this.start = _start;
            this.end = _end;
        }

        public void run()
        {
        int max      = A[start];
        for (int index = start+1; index < end; index ++) {
            max = Math.max(max, A[index]);
        }
        this.max = max;
        if(maxmax < this.max){
            maxmax = this.max;
        }
        latch.countDown();
        }
        int max,start,end;
    }


    static public void main(String args[]){
        int i;
        Random rand = new Random();
        for(i = 0; i < N; ++i) A[i] = rand.nextInt(100);
        int a= N/4;
        Thread[] th = new Thread[4];
        for(i=0;i<4;i++){
            th[i] = new MaxThread((i*a),(a + i*a));
        }
        for(Thread t:th){
			t.start();
		}
        try
        {
            latch.await();
            latch.await();
            latch.await();
            latch.await();
        }
        catch(Exception e){}

        System.out.println(maxmax);
    }
}