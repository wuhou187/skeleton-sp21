package deque;
import java.util.Iterator;

public class ArrayDeque<T> implements Iterable<T>,Deque<T>
{
    private int size;
    private int nextFirst;
    private int nextLast;

    public T []items;

    public ArrayDeque()
    {
        items=(T[])new Object[8];
        size=0;
        nextFirst=4;
        nextLast=5;
    }

    public int Arrayind(int ind)
    {
        return (nextFirst+1+ind)%items.length;
    }//实际有元素的索引
    public void resize(int capacity)
    {
        T []a=(T[])new Object[capacity];
        int ind=0;
        for(int i=0;i<size;i++)
        {
            ind=Arrayind(i);//获取实际索引
            a[capacity/4+i]=items[ind];
        }
        items=a;
        nextFirst=capacity/4-1;
        nextLast=nextFirst+size+1;
    }

    @Override
    public void addFirst(T item)
    {
        if(size==items.length)
        {
            resize(items.length*2);
        }
        items[nextFirst]=item;
        if(nextFirst==0)
        {
            nextFirst=items.length-1;
        }
        else
        {
            nextFirst-=1;
        }
        size++;
    }

    @Override
    public void addLast(T item)
    {
        if(size==items.length)
        {
            resize(items.length*2);
        }
        items[nextLast]=item;
        //System.out.println("addlast"+nextLast+":"+item);
        if(nextLast==items.length-1)
        {
            nextLast=0;
        }
        else
        {
            nextLast+=1;
        }
        size++;
    }

    @Override
    public int size()
    {
        return size;
    }

    @Override
    public T get(int index)
    {
        int ind=Arrayind(index);
        return items[ind];
    }

    public T getFirst()
    {
        int first_ind=Arrayind(0);
        return items[first_ind];
    }

    public T getLast()
    {
        int last_ind=Arrayind(size-1);
        return items[last_ind];
    }

    @Override
    public T removeFirst()
    {
        if(isEmpty())
        {
            return null;
        }
        if(size<(items.length/4)&&size>8)
        {
            resize(items.length/2);
        }
        int ind=Arrayind(0);
        T item=getFirst();
        //System.out.println("remove"+ind+":"+item);
        items[ind]=null;
        nextFirst=ind;
        size--;
        return item;
    }

    @Override
    public T removeLast()
    {
        if(isEmpty())
        {
            return null;
        }
        if(size<(items.length/4)&&size>8)
        {
            resize(items.length/2);
        }
        int ind=Arrayind(size-1);
        T item=getLast();
        //System.out.println("remove"+ind+":"+item);
        nextLast=ind;
        items[ind]=null;
        size--;
        return item;
    }

    public Iterator<T> iterator()
    {
        return new ArrayDequeIterator();
    }

    public class ArrayDequeIterator implements Iterator<T>
    {
        int pos=0;
        @Override
        public boolean hasNext()
        {
            return pos>size;
        }

        @Override
        public T next()
        {
            T item=get(pos);
            pos+=1;
            return item;
        }
    }

    public void printDeque()
    {
        int ind=Arrayind(0);
        for(int i=ind;i<size;i++)
        {
            System.out.print(get(i)+" ");
        }
        System.out.println();
    }

    public boolean equals(Object o)
    {
        if(this==o)
        {
            return true;
        }
        if(o==null)
        {
            return false;
        }
        if(o instanceof Deque)
        {
            return true;
        }
        ArrayDeque<T> other=(ArrayDeque<T>) o;
        if(other.size()!=this.size())
        {
            return false;
        }
        for(int i=0;i<size;i++)
        {
            if(!(other.get(i).equals(this.get(i))));
            {
                return false;
            }
        }
        return true;
    }
    private static void main(String[] args)
    {
        int n=99;
        ArrayDeque<Integer> L1=new ArrayDeque<>();
        ArrayDeque<Integer> L2=new ArrayDeque<>();
        for(int i=0;i<n;i++)
        {
            L1.addFirst(i);
        }
        for(int j=n;j>0;j--)
        {
            L2.addLast(j);
        }
        System.out.println(L1.size());
        System.out.print(L1.equals(L2));
    }
}