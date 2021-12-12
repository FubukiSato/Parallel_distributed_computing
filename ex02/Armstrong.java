import java.util.Scanner;

public class Armstrong {

    static public synchronized boolean checkArmstrong(int x){ 
        int l = 0, n = x; 
        while(n!=0){ 
            l++; 
            n = n/10; 
        }
        int sum=0;
        int num = x;
        while(num!=0){
            int digit = num%10; 
            sum += Math.pow(digit, l); 
            num = num/10; 
        }
        return(sum == x); 
    }

    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        System.out.print("Please enter N and the number of threads :");
        int maxnum = scanner.nextInt(); int threads = scanner.nextInt();
        scanner.close();
        Thread[] th = new Thread[threads];
        for(int i=0;i<threads;i++){
            th[i] = new Thread1(i,maxnum/4);
        }
        for(Thread t:th){
			t.start();
		}
    }
}

    class Thread1 extends Thread{
        Thread1(int start,int startnum){
            this.start = start*startnum;
            this.startnum = (start+1)*startnum;
        }
        public void run(){
            for(int i=this.start;i<this.startnum;i++){
                if(String.valueOf(i).length() == 1) continue;
                if(Armstrong.checkArmstrong(i)){
                    System.out.println(i);
                }
            }
        }
        int start,startnum;
    }

