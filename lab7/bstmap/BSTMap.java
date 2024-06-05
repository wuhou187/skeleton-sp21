package bstmap;
import java.util.*;
public class BSTMap<K extends Comparable<K>,V> implements Map61B<K,V>{
    class Node{
        private K key;
        private V value;
        private Node left;
        private Node right;
        public Node(K key,V value){
            this.key=key;
            this.value=value;
            this.left=null;
            this.right=null;
        }
    }
        private int size;
        private Node root;
        public BSTMap(){
            clear();
        }

        @Override
        public void clear(){
            root=null;
            size=0;
        }

        @Override
        public int size(){
            return size;
        }

        @Override
        public void put(K key,V value){
            boolean isNewNode=put_Helper(root,key,value);
            if(isNewNode){
                size+=1;
            }
        }

        /**put的辅助方法，不能重复添加已有的节点*/
        private boolean put_Helper(Node T,K key,V value){
            if(root==null){
                root=new Node(key,value);
                return true;
            }
            Node pointer=root;
            while(pointer!=null){
                int cmp=key.compareTo(pointer.key);
                if(cmp<0){
                    if(pointer.left==null){
                        pointer.left=new Node(key,value);
                        return true;
                    }
                    else{
                        pointer=pointer.left;
                    }
                }
                else if(cmp>0){
                    if(pointer.right==null){
                        pointer.right=new Node(key,value);
                        return true;
                    }
                    else{
                        pointer=pointer.right;
                    }
                }
                /**如果有相同的节点，立即返回false*/
                else{
                    return false;
                }
            }
            return false;
        }

        /**辅助方法，找指定的key*/
        private Node findKey(Node root,K key){
            if(root==null){
                return null;
            }
            int cmp=key.compareTo(root.key);
            if(cmp==0){
                return root;
            }
            else if(cmp<0){
                return findKey(root.left,key);
            }
            else{
                return findKey(root.right,key);
            }
        }

        @Override
        public boolean containsKey(K key){
            return findKey(root,key)!=null;
        }

        @Override
        public V get(K key){
            Node node=findKey(root,key);
            if(root==null){
                return null;
            }
            return node.value;
        }

        @Override
        public Set<K> keySet(){
            throw new UnsupportedOperationException();
        }

    @Override
    public V remove(K key) {
        throw new UnsupportedOperationException("Remove not supported");
    }

    // 未实现的 remove 方法，抛出 UnsupportedOperationException
    @Override
    public V remove(K key, V value) {
        throw new UnsupportedOperationException("Remove not supported");
    }

    /** Returns BSTMap Iterator utilizing Set Iterator. */
    public Iterator<K> iterator() {
        return keySet().iterator();
    }

}
