import java.util.*;

public class ex03_Concurrent {
    public static void main(String args[]){
        Scanner scanner = new Scanner(System.in);
        System.out.print("プロセス数入力してください > ");
        Integer process_num = scanner.nextInt();
        scanner.close();
        Thread[] th = new Thread[process_num];
        for(int i=0;i<process_num;i++){
            th[i] = new game(i+1);
        }
    }
}

    class game extends Thread{
        Random random = new Random();

        game(int i){
            this.identifier = i;
        }
        public void run(){
            this.max_num = random.nextInt(10);
        }
        public int getmax_num(){
            return this.max_num;
        }
        public int getnum_sum(){
            return this.num_sum;
        }
        public int get_identifier(){
            return this.identifier;
        }
        private int max_num,num_sum,identifier;
    }

