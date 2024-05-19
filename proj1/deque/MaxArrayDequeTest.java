package deque;

import org.junit.Test;
import static org.junit.Assert.*;
import java.util.Comparator;


/** Performs some basic linked list tests. */
public class MaxArrayDequeTest
{

    @Test
    public void addIsEmptySizeTest()
    {
        Comparator<Integer> cmp=new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                if(o1>o2)
                {
                    return 1;
                }
                if(o1<o2)
                {
                    return -1;
                }
                else
                {
                    return 0;
                }
            }
        };

        Comparator<String> cmp1=new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.compareTo(o2);
            }
        };

        MaxArrayDeque<Integer> mad1=new MaxArrayDeque<>(cmp);
        MaxArrayDeque<String> mad2=new MaxArrayDeque<>(cmp1);
        mad1.addFirst(1);
        assertEquals("the size should be 1",1,mad1.size());
        assertFalse("the size is not null",mad1.isEmpty());
        mad2.addLast("hello");
        assertEquals("the size should be 1",1,mad2.size());
        assertFalse("the size is not null", mad2.isEmpty());
    }

    @Test
    public void addRemoveTest()
    {
        Comparator<Integer> cmp=new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
               if(o1>o2)
               {
                   return 1;
               }
               if(o1<o2)
               {
                   return -1;
               }
               else
               {
                   return 0;
               }
            }
        };

        Comparator<String> cmp1=new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.compareTo(o2);
            }
        };
        MaxArrayDeque<Integer> mad1=new MaxArrayDeque<>(cmp);
        MaxArrayDeque<String> mad2=new MaxArrayDeque<>(cmp1);
        mad1.addLast(1);
        mad1.addLast(2);
        mad1.removeLast();
        assertEquals("the size should be 1",1,mad1.size());
        mad2.addFirst("one");
        mad2.addFirst("two");
        mad2.addFirst("three");
        mad2.removeFirst();
        assertEquals("the size should be 2",2,mad2.size());
    }

    @Test
    public void removeEmptyTest()
    {
        Comparator<Integer> cmp=new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                if(o1>o2)
                {
                    return 1;
                }
                if(o1<o2)
                {
                    return -1;
                }
                else
                {
                    return 0;
                }
            }
        };
        MaxArrayDeque<Integer> mad1=new MaxArrayDeque<>(cmp);
        mad1.addLast(1);
        mad1.removeLast();
        mad1.removeLast();
        assertEquals("the value of removeLast() returning should be null now",null,mad1.removeLast());
    }

    @Test
    public void emptyNullReturnTest()
    {
        Comparator<Integer> cmp=new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                if(o1>o2)
                {
                    return 1;
                }
                if(o1<o2)
                {
                    return -1;
                }
                else
                {
                    return 0;
                }
            }
        };
        MaxArrayDeque<Integer> mad1=new MaxArrayDeque<>(cmp);
        assertEquals("should return null",null,mad1.removeLast());
        assertEquals("should return null",null,mad1.removeFirst());
    }
}