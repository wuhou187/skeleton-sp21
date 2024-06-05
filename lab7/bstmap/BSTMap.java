package bstmap;
import java.util.*;
public class BSTMap<K extends Comparable<K>,V> implements Map61B<K,V>{
    class Node{
        K key;
        V value;
        Node left;
        Node right;

        Node(K key,V value){
            this.key=key;
            this.value=value;
            left=null;
            right=null;
        }
    }
        private int size;
        private Node root;

        public BSTMap(){
            clear();
        }

        /**清空BSTMap*/
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
            boolean isNewNode=put_Helper(key,value);
            if(isNewNode){
                size+=1;
            }
        }

        /**是否包含指定的键*/
        @Override
        public boolean containsKey(K key){
            return findKey(root,key)!=null;
        }

        /**获取指定键的值*/
        @Override
        public V get(K key){
            Node node=findKey(root,key);
            return node==null?null: node.value;
        }

        /**返回所有的键的集合*/
        @Override
        public Set<K> keySet(){
            return new HashSet<>(keyInOrder(root));
        }

        /**使用keySet()返回迭代器*/
        @Override
        public Iterator<K> iterator(){
            return keySet().iterator();
        }

        /**删除指定的键*/
        @Override
        public V remove(K key){
            Node node=findKey(root,key);
            if(node==null){
                return null;
            }
            root=innerRemove(root,key);
            size--;
            return node.value;
        }

        @Override
        public V remove(K key,V value){
            Node node=findKey(root,key);
            if(node==null){
                return null;
            }
            root=innerRemove(root,key);
            size--;
            return node.value;
        }

        public void printInOrder(){
            if(root==null){
                System.out.println("There is no BSTMap");
                return;
            }
            ArrayList<K> keys=keyInOrder(root);
            for(K key:keys){
                System.out.println("key: "+key);
            }
        }

        /**辅助方法*/
        /**put的辅助方法*/
        private boolean put_Helper(K key,V value){
            Node pointer=root;
            if(root==null){
                root=new Node(key,value);
                return true;
            }
            while(pointer!=null){
                int cmp=key.compareTo(pointer.key);
                if(cmp==0){
                    /**如果当期节点已经存在，返回false，不能让相同节点插入*/
                    return false;
                }
                else if(cmp<0){
                    if(pointer.left==null){
                        pointer.left=new Node(key,value);
                        return true;
                    }
                    else{
                        pointer=pointer.left;
                    }
                }
                else{
                    if(pointer.right==null){
                        pointer.right=new Node(key,value);
                        return true;
                    }
                    else{
                        pointer=pointer.right;
                    }
                }
            }
            return false;
        }

        /**找到只当的键*/
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

        /**找到右边最小的键*/
        private Node nextOfNode(Node root){
            if(root==null){
                return null;
            }
            if(root.right==null){
                return null;
            }
            else{
                Node currentNode=root.right;
                while(currentNode.left!=null){
                    currentNode=currentNode.left;
                }
                return currentNode;
            }
        }
        /**交换右子树最小值与当前节点的值(在删除有两个子节点的节点时使用)*/
        private void swapValue(Node node1,Node node2){
            K tempKey=node1.key;
            node1.key=node2.key;
            node2.key=tempKey;

            V tempVal=node1.value;
            node1.value= node2.value;;
            node2.value=tempVal;
        }

        /**remove的辅助方法*/
        private Node innerRemove(Node root,K key){
            if(root==null){
                return null;
            }
            int cmp=key.compareTo(root.key);
            if(cmp<0){
                root.left=innerRemove(root.left,key);
                return root;
            }
            else if(cmp>0){
                root.right=innerRemove(root.right,key);
                return root;
            }
            /**删除根节点*/
            else{
                if(root.left==null&&root.right==null){
                    return null;
                }
                else if(root.left!=null&&root.right!=null){
                    Node node=nextOfNode(root);
                    swapValue(root,node);
                    root.right=innerRemove(root.right,key);
                    return root;
                }
                else{
                    return root.left==null?root.right:root.left;
                }
            }
        }

        /**将键放入数组链表当中*/
        private ArrayList<K> keyInOrder(Node root){
            ArrayList<K> keys=new ArrayList<>();
            if(root.left!=null){
                keys.addAll(keyInOrder(root.left));
            }
            keys.add(root.key);
            if(root.right!=null){
                keys.addAll(keyInOrder(root.right));
            }
            return keys;
        }
}