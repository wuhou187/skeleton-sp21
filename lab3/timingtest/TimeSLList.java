package timingtest;
import afu.org.checkerframework.checker.igj.qual.I;
import edu.princeton.cs.algs4.Stopwatch;

/**
 * Created by hug.
 */
public class TimeSLList {
    private static void printTimingTable(AList<Integer> Ns, AList<Double> times, AList<Integer> opCounts) {
        System.out.printf("%12s %12s %12s %12s\n", "N", "time (s)", "# ops", "microsec/op");
        System.out.printf("------------------------------------------------------------\n");
        for (int i = 0; i < Ns.size(); i += 1) {
            int N = Ns.get(i);
            double time = times.get(i);
            int opCount = opCounts.get(i);
            double timePerOp = time / opCount * 1e6;
            System.out.printf("%12d %12.2f %12d %12.2f\n", N, time, opCount, timePerOp);
        }
    }

    public static void main(String[] args) {
        timeGetLast();
    }

    public static void timeGetLast() {
        // TODO: YOUR CODE HERE
       AList<Integer> Ns=new AList<>();
       AList<Integer> opcounts=new AList<>();
       AList<Double> times=new AList<>();
       SLList<Integer> L;
       int Max=128000,temp=1000;
       int M=10000;
       while(temp<=Max)
       {
           Ns.addLast(temp);
           temp*=2;
       }
       for(int i=0;i<Ns.size();i++)
       {
           int N=Ns.get(i);
           L=new SLList<>();
           int j=0;
           while(j<N)
           {
               L.addLast(j);//将此轮每个元素添加至L
               j++;
           }
           Stopwatch sw=new Stopwatch();
           for(int k=0;k<M;k++)
           {
               L.getLast();
           }
           times.addLast(sw.elapsedTime());
           opcounts.addLast(M);
       }
       printTimingTable(Ns,times,opcounts);
    }

}
