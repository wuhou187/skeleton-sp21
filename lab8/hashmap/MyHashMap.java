package hashmap;

import java.util.*;

/**
 *  A hash table-backed Map implementation. Provides amortized constant time
 *  access to elements via get(), remove(), and put() in the best case.
 *
 *  Assumes null keys will never be inserted, and does not resize down upon remove().
 *  @author YOUR NAME HERE
 */
public class MyHashMap<K, V> implements Map61B<K, V> {

    /**
     * Protected helper class to store key/value pairs
     * The protected qualifier allows subclass access
     */
    protected class Node {
        K key;
        V value;

        Node(K k, V v) {
            key = k;
            value = v;
        }
    }

    /* Instance Variables */
    private Collection<Node>[] buckets;
    private  int size;
    private int length;
    private double loadFactor;
    // You should probably define some more!

    /** Constructors */
    public MyHashMap() {
        size=16;
        length=0;
        loadFactor=0.75;
        buckets=createTable(size);
    }

    public MyHashMap(int initialSize) {
        size=initialSize;
        length=0;
        loadFactor=0.75;
        buckets=createTable(size);
    }

    /**
     * MyHashMap constructor that creates a backing array of initialSize.
     * The load factor (# items / # buckets) should always be <= loadFactor
     *
     * @param initialSize initial size of backing array
     * @param maxLoad maximum load factor
     */
    public MyHashMap(int initialSize, double maxLoad) {
        size=initialSize;
        length=0;
        loadFactor=maxLoad;
        buckets=createTable(size);
    }

    /**
     * Returns a new node to be placed in a hash table bucket
     */
    private Node createNode(K key, V value) {
        return new Node(key,value);
    }

    /**
     * Returns a data structure to be a hash table bucket
     *
     * The only requirements of a hash table bucket are that we can:
     *  1. Insert items (`add` method)
     *  2. Remove items (`remove` method)
     *  3. Iterate through items (`iterator` method)
     *
     * Each of these methods is supported by java.util.Collection,
     * Most data structures in Java inherit from Collection, so we
     * can use almost any data structure as our buckets.
     *
     * Override this method to use different data structures as
     * the underlying bucket type
     *
     * BE SURE TO CALL THIS FACTORY METHOD INSTEAD OF CREATING YOUR
     * OWN BUCKET DATA STRUCTURES WITH THE NEW OPERATOR!
     */
    protected Collection<Node> createBucket() {
        return new ArrayList<>();
    }

    /**
     * Returns a table to back our hash table. As per the comment
     * above, this table can be an array of Collection objects
     *
     * BE SURE TO CALL THIS FACTORY METHOD WHEN CREATING A TABLE SO
     * THAT ALL BUCKET TYPES ARE OF JAVA.UTIL.COLLECTION
     *
     * @param tableSize the size of the table to create
     */
    private Collection<Node>[] createTable(int tableSize) {
        return new Collection[tableSize];
    }

    // TODO: Implement the methods of the Map61B Interface below
    // Your code won't compile until you do so!

    @Override
    public void clear(){
        length=0;
        size=16;
        buckets=createTable(size);
    }

    @Override
    public boolean containsKey(K key){
        return find(key)!=null;
    }

    @Override
    public V get(K key){
        Node node=find(key);
        if(node==null){
            return null;
        }
        return node.value;
    }

    @Override
    public int size(){
        return length;
    }

    @Override
    public void put(K key,V value){
        if((double)length/size>loadFactor){
            resize();
        }
        Collection<Node> bucket=getBucket(key);
        if(bucket==null){
            buckets[getIndex(key)]=createBucket();
        }
        Node n=find(key);
        if(n==null){
            buckets[getIndex(key)].add(createNode(key,value));
            length++;
        }
        else{
            n.value=value;
        }
    }

    @Override
    public Set<K> keySet(){
        if(length==0){
            return null;
        }
        HashSet<K> hashSet=new HashSet<>();
        for(Collection<Node> bucket:buckets){
            if(bucket!=null){
                for(Node n:bucket){
                    hashSet.add(n.key);
                }
            }
        }
        return hashSet;
    }

    @Override
    public V remove(K key){
        Collection<Node> bucket=getBucket(key);
        if(bucket==null){
            return null;
        }
        Node n=find(key);
        if(n==null){
            return null;
        }
        V value=n.value;
        bucket.remove(n);
        length--;
        return value;
    }

    @Override
    public V remove(K key,V value){
        Collection<Node> bucket=getBucket(key);
        if(bucket==null){
            return null;
        }
        Node n=find(key);
        if(n==null||!(n.value.equals(value))){
            return null;
        }
        V val=n.value;
        bucket.remove(n);
        length--;
        return val;
    }

    @Override
    public Iterator<K> iterator(){
        return keySet().iterator();
    }

    /**辅助方法*/

    /**获取对应的键所在桶的索引*/
    private int getIndex(K key){
        return Math.floorMod(key.hashCode(),size);
    }

    /**获取指定键所在的桶*/
    private Collection<Node> getBucket(K key){
        int bucketIndex=getIndex(key);
        return buckets[bucketIndex];
    }

    /**找到特定的键的位置*/
    private Node find(K key){
        Collection<Node> bucket=getBucket(key);
        if(bucket!=null){
            for(Node node:bucket){
                if(key.equals(node.key)){
                    return node;
                }
            }
        }
        return null;
    }

    /**重构桶的大小*/
    private void resize(){
        MyHashMap<K,V> temp=new MyHashMap<>(size*2);
        for(K key:this){
            temp.put(key,get(key));
        }
        size*=2;
        buckets=temp.buckets;
    }
}
