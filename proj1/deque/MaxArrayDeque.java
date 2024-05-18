package deque;
import java.util.Comparator;
public class MaxArrayDeque<T> extends ArrayDeque<T>
{
    private Comparator<T> cmp;

    public MaxArrayDeque(Comparator<T> c)
    {
        super();//构造基类对象
        cmp=c;
    }

    public T max()
    {
       if(isEmpty())
       {
           return null;
       }
       T maxitem=this.get(0);
       for(T i:this)
       {
           if(cmp.compare(i,maxitem)>0)
           {
               maxitem=i;
           }
       }
       return maxitem;
    }

    public T max(Comparator<T> c)
    {
        if(isEmpty())
        {
            return null;
        }
        T maxitem=this.get(0);
        for(T i:this)
        {
            if(c.compare(i,maxitem)>0)
            {
                maxitem=i;
            }
        }
        return maxitem;
    }

    public static void main(String[] args)
    {
        Comparator<Integer> cmp=new Comparator<Integer>()
        {
            @Override
            public int compare(Integer o1, Integer o2)
            {
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
        int n=99;
        for(int i=0;i<99;i++)
        {
            mad1.addLast(i);
        }
        System.out.println(mad1.max());
        System.out.println(mad1.max(cmp));

        Comparator<String> cmp1=new Comparator<String>()
        {
            @Override
            public int compare(String o1, String o2)
            {
                return o1.compareTo(o2);
            }
        };
        MaxArrayDeque<String> mad2=new MaxArrayDeque<>(cmp1);
        mad2.addLast("one");
        mad2.addLast("two");
        mad2.addLast("three");
        System.out.println(mad2.max());
        System.out.println(mad2.max(cmp1));
    }
}
