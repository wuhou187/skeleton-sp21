package timingtest;
import edu.princeton.cs.algs4.Stopwatch;

/**
 * Created by hug.
 */
public class TimeAList {
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
        timeAListConstruction();
    }

    public static void timeAListConstruction() {
        // TODO: YOUR CODE HERE
        AList<Integer> Ns=new AList<>();
        AList<Integer> opcounts=new AList<>();
        AList<Double> times=new AList<>();
        int temp=1000;
        int MAX=128000;
        while(temp<=MAX)
        {
            Ns.addLast(temp);
            opcounts.addLast(temp);
            temp*=2;
        }

        for(int i=0;i<Ns.size();i++)
        {
            int N=Ns.get(i);
            int j=0;
            AList<Integer> L=new AList<>();
            Stopwatch sw=new Stopwatch();
            while(j<=N)
            {
                L.addLast(j);
                j++;
            }
            times.addLast(sw.elapsedTime());
        }
            printTimingTable(Ns,times,opcounts);
    }
}