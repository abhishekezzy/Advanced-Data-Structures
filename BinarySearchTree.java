package binarysearchtree;
import java.util.*;
public class BinarySearchTree 
{    
    class Node
    {
        int key;
        Node left, right, parent;
        
        public Node(int item)
        {
            key = item;
            left = right = parent = null;
        }
    }
    
    Node root;
    
    public BinarySearchTree()
    {
        root = null;
    }
    
    //used for searching a specific node in the tree and also for inserting new nodes
    public Node find(int k, Node R)
    {
        if(R.key == k)
            return R;
        else if(R.key > k)
        {
            if(R.left != null)
                return find(k, R.left);
            return R;
        }
        else
        {
            if(R.right != null)
                return find(k, R.right);
            return R;
        }
    }
    
    public void insert(int k)
    {
        Node child = new Node(k);
        if(root == null)
            root = child;
        else
        {    
            Node p = find(k, root);
            if(p.left == null)
            {
                p.left = child;
                child.parent = p;
            }
            else
            {
                p.right = child;
                child.parent = p;
            }
        }
    }
    
    //transplant replaces node u with node v such that node u's parent is node v's parent
    // and node v is the child of node u's parent.
    public void transplant(Node u, Node v)
    {
        if(u.parent == null)
            root = v;
        else if(u.parent.left == u)
            u.parent.left = v;
        else
            u.parent.right = v;
        if(v != null)
            v.parent = u.parent;
    }
    
    public void delete(int n)
    {
        Node p = find(n, root);
        if(p.key != n)
        {    
            System.out.println("Element not found!");
            return;
        }
        
        if(p.left == null)
            transplant(p, p.right);
        else if(p.right == null)
            transplant(p, p.left);
        else
        {
            Node y = next(p.key);
            if(y.parent != p)
            {
                transplant(y,y.right);
                y.right = p.right;
                y.right.parent = y;
            }
            transplant(p,y);
            y.left = p.left;
            y.left.parent = y;
        }
    }
    public void inOrder()
    {
        inOrderTraversal(root);
    }
    
    public void inOrderTraversal(Node root)
    {
        if(root != null)
        {
            inOrderTraversal(root.left);
            System.out.println(root.key);
            inOrderTraversal(root.right);
        }
    }
    
    public Node rightAncestor(Node R)
    {
        if(R.parent == null)
            return null;
        
        if(R.parent.key <= R.key )
            return rightAncestor(R.parent);
        else
            return R.parent;
    }
    
    public Node leftDescendant(Node R)
    {
        while(R.left != null)
            R = R.left;
        return R;
    }
    
    public Node next(int n)
    {
        Node N = find(n, root);
        Node p;
        if(N.right != null)
            p = leftDescendant(N.right);
        else
            p = rightAncestor(N);
        return p;
    }
    
    public ArrayList<Integer> rangeSearch(int x, int y)
    {
         ArrayList<Integer> l = new ArrayList<Integer>();  
         Node p = find(x, root);
         while(p.key <= y)
         {
             if(p.key >= x)
                 l.add(p.key);
             p = next(p.key);
             if(p == null)
                 break;
         }
         return l;
    }
    
    public static void main(String[] args) 
    {
        BinarySearchTree tree = new BinarySearchTree();
        
        tree.insert(50);
        tree.insert(30);
        tree.insert(20);
        tree.insert(40);
        tree.insert(70);
        tree.insert(60);
        tree.insert(80);
        
        tree.inOrder();
        
        Node k = tree.next(50);
        if(k == null)
            System.out.println("Not Found");
        else
            System.out.println(k.key);
        
        ArrayList<Integer> l = new ArrayList<Integer>(tree.rangeSearch(40, 1200));
        for(int i = 0; i < l.size(); i++)
            System.out.print(l.get(i) + " ");
        System.out.println("");
        
        tree.delete(50);
        tree.delete(20);
        tree.delete(80);
        tree.inOrder();
    }  
}
