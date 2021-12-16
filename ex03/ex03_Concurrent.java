import java.util.*;

public class ex03_Concurrent {
    public static void main(String args[]){
        Scanner scanner = new Scanner(System.in);
        System.out.print("プロセス数入力してください > ");
        Integer process_num = scanner.nextInt();
        scanner.close();
        Thread[] th = new Thread[process_num];
        Game[] game = new Game[process_num];
        for(int i=0;i<process_num;i++){
            game[i] = new Game(i+1);
        }
        
        for(int i=0;i<process_num;i++){
            th[i] = game[i];
        }
        System.out.println(game[0].get_identifier());
        int i=1;
        int max=-1;
        int flag=0;
        while(flag == 0){
            for(int j=0;j<process_num;j++){
                if(game[j].getnum_sum() == 3){
                    System.out.println("勝者: "+ game[j].get_identifier());
                    flag++;
                }
                break;
            }

            for(int k=0;k<process_num;k++){
                th[k].start();
                System.out.println(game[k].getmax_num());
            }
            System.out.println("");
            

            System.out.println("ラウンド"+i+"の勝者"); i++;
            for(int j=0;j<process_num;j++){
                System.out.println(game[i].getmax_num());
                if(max < game[i].getmax_num()){
                    System.out.println(max);
                    max = game[i].getmax_num();
                }
            }
            for(int j=0;j<process_num;j++){
                if(game[i].getmax_num() == max){
                    System.out.print(game[i].get_identifier()+" ");
                }
            }
        }
    }
}

    class Game extends Thread{
        Random random = new Random();

        Game(int i){
            this.identifier = i;
        }
        public void run(){
            this.max_num = random.nextInt(10);
            System.out.println(this.max_num);
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

