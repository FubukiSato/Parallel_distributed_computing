import mpi.*;
import java.lang.Math;
import java.util.Random;

class w06_findingPi{
    public static void main(String[] args){
        int N = Integer.parseInt(args[3]);
        int r = Integer.parseInt(args[4]);
        int[] N0 = new int[1];
        int[] recv = new int[1];
        MPI.Init(args);

        for(int i=0;i<N/4;i++){
            Random rand = new Random();
            int x = rand.nextInt(2*r);
            int y = rand.nextInt(2*r);
            if( Math.sqrt( Math.pow((r-x),2)+Math.pow((r-y),2) ) <= r){
                N0[0]++;
            }
        }

        MPI.COMM_WORLD.Reduce(N0, 0, recv, 0, 1, MPI.INT, MPI.SUM, 0);

        if(MPI.COMM_WORLD.Rank() == 0){
            System.out.println("Pi = "+(double)recv[0] / (double)N * 4D);
        }
        MPI.Finalize();
    }
}