import java.io.Serializable;

class bstNode<T> implements Serializable {
    T data;
    bstNode<T> left;
    bstNode<T> right;

    bstNode(T d) {
        data = d;
    }

    public String toString() {
        return data.toString();
    }
}

public class BST<T extends Comparable<T>> implements Serializable {
    bstNode<T> root;

    public void insert(T key) {
        bstNode<T> n = new bstNode<T>(key);
        if (root == null) {
            root = n;
            return;
        }
        bstNode<T> prev = root, temp = root;
        while (temp != null) {
            prev = temp;
            if (temp.data.compareTo(key) > 0) {
                temp = temp.left;
            } else {
                temp = temp.right;
            }
        }
        if (prev.data.compareTo(key) > 0) {
            prev.left = n;
        } else {
            prev.right = n;
        }
    }

    public void LNR(bstNode<T> n) {
        if (n == null)
            return;
        LNR(n.left);
        System.out.println(n);
        LNR(n.right);
    }

    public void LRN(bstNode<T> n) {
        if (n == null)
            return;
        LRN(n.left);
        LRN(n.right);
        System.out.println(n);
    }

    int maxDepth(bstNode<T> n) {
        if (n == null)
            return 0;
        else {
            /* compute the depth of each subtree */
            int lDepth = maxDepth(n.left);
            int rDepth = maxDepth(n.right);

            /* use the larger one */
            if (lDepth > rDepth)
                return (lDepth + 1);
            else
                return (rDepth + 1);
        }
    }

    public void NLR(bstNode<T> n) {
        if (n == null)
            return;
        System.out.println(n);
        NLR(n.left);
        NLR(n.right);
    }

    public T find(T key) {
        bstNode<T> temp = root;

        while (temp != null && temp.data.compareTo(key) != 0) {
            if (temp.data.compareTo(key) > 0)
                temp = temp.left;
            else
                temp = temp.right;
        }
        if (temp == null)
            return null;
        return temp.data;
    }

    public bstNode<T>[] findPT(T key) {
        bstNode<T> temp = root;
        bstNode<T> prev = root;

        while (temp != null && temp.data.compareTo(key) != 0) {
            prev = temp;
            if (temp.data.compareTo(key) > 0)
                temp = temp.left;
            else
                temp = temp.right;
        }
        if (temp == null)
            return null;
        return new bstNode[] { temp, prev };
    }

    public bstNode<T> Minimum() {
        bstNode<T> temp = root;
        if (temp == null)
            return null;
        while (temp.left != null) {
            temp = temp.left;
        }
        return temp;
    }

    public bstNode<T> Maximum() {
        bstNode<T> temp = root;
        if (temp == null)
            return null;
        while (temp.right != null) {
            temp = temp.right;
        }
        return temp;
    }

    public void deleteNochild(bstNode<T> t, bstNode<T> p) {
        if (t == root) {
            root = null;
            return;
        }
        if (p.left != null) {
            if (t.data.compareTo(p.left.data) == 0)
                p.left = null;
            else
                p.right = null;
        } else
            p.right = null;
        System.out.println("2 child");
    }

    public void deleteOnechild(bstNode<T> t, bstNode<T> p) {

        bstNode<T> temp = t;
        if (t == root) {
            if (t.left == null)
                root = t.right;
            else
                root = t.left;
            return;
        }
        if (t.left == null)
            temp = t.right;
        else
            temp = t.left;
        if (p.left != null) {
            if (t.data.compareTo(p.left.data) == 0)
                p.left = temp;
            else
                p.right = temp;
        } else
            p.right = temp;
    }

    public bstNode<T> maxNode(bstNode<T> n) {
        if (n.right == null)
            return n;
        return maxNode(n.right);
    }

    public bstNode<T> minNode(bstNode<T> n) {
        if (n.left == null)
            return n;
        return minNode(n.left);
    }

    public void Delete(T key) {
        bstNode<T>[] nodes = findPT(key); // find bstNode to delete that return bstNode t and its parent p references
        if (nodes == null) {
            System.out.println("item doesn't exist");
            return;
        }
        bstNode<T> temp = nodes[0];
        bstNode<T> prev = nodes[1];
        if (temp.left == null && temp.right == null) {
            deleteNochild(temp, prev);
        } else if (temp.left == null || temp.right == null) { // call deleteOnechild(t,p)
            deleteOnechild(temp, prev);
        } else {
            System.out.println("delete 2");
            bstNode<T> min = minNode(temp.right);
            nodes = findPT(min.data);
            prev = nodes[1];
            if (min.left == null && min.right == null) {
                deleteNochild(min, prev);
            } else
                deleteOnechild(min, prev);
            temp.data = min.data;
        }

    }

    public void PrintRange(bstNode<Integer> r, int k1, int k2) { // Function for Task 1
        if (r == null)
            return;
        PrintRange(r.left, k1, k2);
        if (r.data <= k2 && r.data >= k1)
            System.out.println(r);
        PrintRange(r.right, k1, k2);
    }

}
