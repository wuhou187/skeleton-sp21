package deque;

import java.util.Iterator;
import java.util.Objects;

public class LinkedListDeque<T> implements Iterable<T>,Deque<T>
{
    private int size;
    private Node sentinelFront;
    private Node sentinelBack;
    public class Node
    {
        T items;
        Node prev;
        Node next;
        Node(T i,Node p,Node n)
        {
            items=i;
            prev=p;
            next=n;
        }
    }

    LinkedListDeque()
    {
        sentinelFront=new Node(null,null,null);
        sentinelBack=new Node(null,sentinelFront,null);
        sentinelFront.next=sentinelBack;
        size=0;
    }
    @Override
    public void addFirst(T item)
    {
        Node newNode=new Node(item,sentinelFront,sentinelFront.next);//构建新节点
        sentinelFront.next.prev=newNode;//更新prev与next
        sentinelFront.next=newNode;
        size++;
    }

    @Override
    public void addLast(T item)
    {
        Node newNode=new Node(item,sentinelBack.prev,sentinelBack);
        sentinelBack.prev.next=newNode;
        sentinelBack.prev=newNode;
        size++;
    }

    @Override
    public int size()
    {
        return size;
    }

    public void printDeque()
    {
        Node currentNode=sentinelFront.next;
        while(currentNode.next!=null)
        {
            System.out.print(currentNode.items+" ");
            currentNode=currentNode.next;
        }
        System.out.println();
    }

    @Override
    public T removeFirst()
    {
        if(isEmpty())
        {
            return null;
        }
        Node first=sentinelFront.next;
        sentinelFront.next=first.next;
        first.next.prev=sentinelFront;
        T first_item=first.items;
        first=null;
        size--;
        return first_item;
    }

    @Override
    public T removeLast()
    {
        if(isEmpty())
        {
            return null;
        }
        Node last=sentinelBack.prev;
        sentinelBack.prev=last.prev;
        last.prev.next=sentinelBack;
        T last_item=last.items;
        last=null;
        size--;
        return last_item;
    }

    @Override
    public T get(int index)
    {
        if(size==0||index>=size)
        {
            return null;
        }
        Node currentNode=sentinelFront.next;
        for(int i=0;i<index;i++)
        {
            currentNode=currentNode.next;
        }
        return currentNode.items;
    }

    public T getRecursive(int index)
    {
        if(size==0||index>=size)
        {
            return null;
        }
        else
        {
            return getRecursiveHelper(sentinelFront.next,index);
        }
    }

    private T getRecursiveHelper(Node currentNode,int index)
    {
        if(index==0)
        {
            return currentNode.items;
        }
        else
        {
            return getRecursiveHelper(currentNode.next,index-1);
        }
    }

    public Iterator<T> iterator()
    {
        return new LinkedListDequeIterator();
    }

    public class LinkedListDequeIterator implements Iterator<T>
    {
        private Node currentNode;

        LinkedListDequeIterator()
        {
            currentNode=sentinelFront.next;
        }
        public boolean hasNext()
        {
            return currentNode.next!=sentinelBack;
        }

        public T next()
        {
            T item=currentNode.items;
            currentNode=currentNode.next;
            return item;
        }
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
        if(!(o instanceof LinkedListDeque))
        {
            return false;
        }
        LinkedListDeque<T> oi=(LinkedListDeque<T>) o;
        if(oi.size()!=this.size())
        {
            return false;
        }
        for(int i=0;i<oi.size();i++)
        {
            if(!(oi.get(i).equals(this.get(i))))
            {
                return false;
            }
        }
        return true;
    }
    public static void main(String[] args)
    {
        int n=99;
        LinkedListDeque<Integer> L1=new LinkedListDeque<>();
        LinkedListDeque<Integer> L2=new LinkedListDeque<>();
        ArrayDeque<Integer> L3=new ArrayDeque<>();
        for(int i=0;i<n;i++)
        {
            L1.addFirst(i);
        }
        for(int j=n;j>0;j--)
        {
            L2.addLast(j);
            L3.addLast(j);
        }
        System.out.println(L1.equals(L2));
        System.out.println(Objects.equals(L2.get(15), L3.get(15)));
    }
}
