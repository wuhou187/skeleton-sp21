package randomizedtest;

import edu.princeton.cs.algs4.StdRandom;
import org.junit.Assert;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Created by hug.
 */
public class TestBuggyAList {
  // YOUR TESTS HERE
    @Test
    public void testThreeAddThreeRemove()
    {
        AListNoResizing<Integer> L=new AListNoResizing<>();
        BuggyAList<Integer> BA=new BuggyAList<>();
        L.addLast(4);
        L.addLast(5);
        L.addLast(6);
        BA.addLast(4);
        BA.addLast(5);
        BA.addLast(6);
        Assert.assertEquals(L.size(),BA.size());
        Assert.assertEquals(L.removeLast(),BA.removeLast());
        Assert.assertEquals(L.removeLast(),BA.removeLast());
        Assert.assertEquals(L.removeLast(),BA.removeLast());
    }

    @Test
    public void randomizedTest(){
        AListNoResizing<Integer> L = new AListNoResizing<>();
        BuggyAList<Integer> B = new BuggyAList<>();

        int N = 5000;
        for (int i = 0; i < N; i += 1) {
            int operationNumber = StdRandom.uniform(0, 3);
            if (operationNumber == 0) {
                // addLast
                int randVal = StdRandom.uniform(0, 100);
                L.addLast(randVal);
                B.addLast(randVal);
                Assert.assertEquals(L.size(),B.size());
            } else if (operationNumber == 1) {
                // size
                if(L.size()==0){
                    continue;
                }
                int RemoveLastL =L.removeLast();
                int RemoveLastB = B.removeLast();
                Assert.assertEquals(RemoveLastB,RemoveLastL);
            } else if (operationNumber==2) {
                if(L.size()==0){
                    continue;
                }
                int LastOfL = L.getLast();
                int LastOfB = B.getLast();
                Assert.assertEquals(LastOfL,LastOfB);
            }
        }
    }
}
