package edu.wmich.cs3310.hw5.main;

import java.util.LinkedList;

/**
 * Binary search tree implementation
 */
public class BinaryStreeImplicit {
    private Mydata[] tree; // holds all the nodes of the binary search tree
    private int treeSize; // number of nodes in the binary search tree
    private int lastIndexUsed; // the tree may not be full,
    // so specifies the array bound

    /**
     * Simple constructor
     */
    public BinaryStreeImplicit() {
        tree = new Mydata[40];
        treeSize = 0;
        lastIndexUsed = -1;
    }

    /**
     * @return the index of the root of the tree or -1 if    tree is empty
     */
    int root() {
        if (tree != null && treeSize > 0) {
            return 0;
        } else {
            return -1;
        }
    }

    /**
     * Return height of i-th node
     *
     * @param i node number
     * @return height
     */
    int height(int i) {
        if (i == root()) {
            return 0;
        }
        int k = 1;
        int y = k;
        int h = 0;
        while ((i + 1) > y) {
            k = k * 2;
            y = y + k;
            h++;
        }
        return h;
    }

    /**
     * Return index for left child node
     *
     * @param i node number
     * @return left child node number
     */
    int leftchild(int i) {
        if (i == 0) {
            return 1;
        }
        int h = height(i);
        int k = 1;
        int y = k;
        for (int j = 1; j < h; j++) {
            k = k * 2;
            y = y + k;
        }
        return ((i + k) * 2) - y;
    }

    /**
     * Return index for right child node
     *
     * @param i node number
     * @return right child node number
     */
    int rightchild(int i) {
        return leftchild(i) + 1;
    }

    /**
     * Return index of parent node
     *
     * @param i node number
     * @return index of parent node
     */
    int parent(int i) {
        if (i == 0) {
            return -1;
        }
        if (i < 3) {
            return 0;
        }
        int h_this = height(i);
        int k = 1;
        int y = 1;
        for (int j = 1; j < h_this; j++) {
            k = k * 2;
            y = y + k;
        }
        int k2 = 1;
        int y2 = 1;
        for (int j = 1; j < (h_this - 1); j++) {
            k2 = k2 * 2;
            y2 = y2 + k2;
        }
        return y2 + ((i - y) / 2);
    }

    /**
     * Public inorder print function
     */
    void inorderTraversal() {
        System.out.println("Inorder:");
        if (treeSize == 0) {
            System.out.println();
        }
        printInorder(0);
    }

    /**
     * Private inorder print function
     *
     * @param i node number
     */
    private void printInorder(int i) {
        if (i < tree.length && tree[i] != null) {
            printInorder(leftchild(i));
            printNode(i);
            printInorder(rightchild(i));
        }
    }

    /**
     * Prints one node
     *
     * @param i node number
     */
    private void printNode(int i) {
        if (i < tree.length && tree[i] != null) {
            System.out.println(tree[i]);
        }
    }

    /**
     * Public preorder print function
     */
    void preorderTraversal() {
        System.out.println("Preorder:");
        if (treeSize == 0) {
            System.out.println();
        }
        printPreorder(0);
    }

    /**
     * Private preorder print function
     *
     * @param i node number
     */
    private void printPreorder(int i) {
        if (i < tree.length && tree[i] != null) {
            printNode(i);
            printPreorder(leftchild(i));
            printPreorder(rightchild(i));
        }
    }

    /**
     * Public postorder print function
     */
    void postorderTraversal() {
        System.out.println("Postorder:");
        if (treeSize == 0) {
            System.out.println();
        }
        printPostorder(0);
    }

    /**
     * Private postorder print function
     *
     * @param i node number
     */
    private void printPostorder(int i) {
        if (i < tree.length && tree[i] != null) {
            printPostorder(leftchild(i));
            printPostorder(rightchild(i));
            printNode(i);
        }
    }

    /**
     * Insert data to tree
     *
     * @param x new inserted data
     * @return index of data
     */

    int insert(Mydata x) {
        lastIndexUsed = insert(0, x);
        if (lastIndexUsed > -1) {
            treeSize++;
        }
        return lastIndexUsed;
    }

    /**
     * Private inserting with pre-selected root
     *
     * @param i node number
     * @param x new inserted data
     * @return index of data
     */
    private int insert(int i, Mydata x) {
        if (i >= tree.length) {
            extend();
        }
        if (tree[i] == null) {
            tree[i] = x;
            return i;
        }
        int res = tree[i].getStuName().compareTo(x.getStuName());
        if (res == 0) {
            tree[i] = x;
            return i;
        } else {
            if (res > 0) {
                return insert(leftchild(i), x);
            } else {
                return insert(rightchild(i), x);
            }
        }
    }

    /**
     * Extend internal array
     */
    private void extend() {
        Mydata[] treeNew = new Mydata[tree.length * 2];
        System.arraycopy(tree, 0, treeNew, 0, tree.length);
        tree = treeNew;
    }

    /**
     * Delete selected information
     *
     * @param x data to remove
     * @return index of data
     */
    int delete(Mydata x) {
        int place = search(x);
        if (place < 0) {
            return place;
        }
        treeSize--;
        LinkedList<Integer> ls1 = new LinkedList<>();
        LinkedList<Mydata> ls2 = new LinkedList<>();
        tree[place] = null;
        ls1.add(leftchild(place));
        ls1.add(rightchild(place));
        while (!ls1.isEmpty()) {
            int pl = ls1.pollFirst();
            if (pl < tree.length && tree[pl] != null) {
                ls2.add(tree[pl]);
                tree[pl] = null;
                ls1.add(leftchild(pl));
                ls1.add(rightchild(pl));
            }
        }
        for (Mydata md : ls2) {
            insert(md);
        }
        lastIndexUsed = place;
        return place;
    }

    /**
     * Search data in binary tree
     *
     * @param x data
     * @return result of search
     */
    int search(Mydata x) {
        lastIndexUsed = search(0, x);
        return lastIndexUsed;
    }

    /**
     * Private search data in binary tree
     *
     * @param i node number
     * @param x data
     * @return result of search
     */
    private int search(int i, Mydata x) {
        if (i >= tree.length || i < 0) {
            return -1;
        }
        if (tree[i] == null) {
            return -1;
        }
        int res = tree[i].getStuName().compareTo(x.getStuName());
        if (res == 0) {
            return i;
        } else {
            if (res > 0) {
                return search(leftchild(i), x);
            } else {
                return search(rightchild(i), x);
            }
        }
    }

    /**
     * Return data at selected index
     *
     * @param i node number
     * @return Found data
     */
    public Mydata getElement(int i) {
        if (i > -1 && i < tree.length) {
            return tree[i];
        }
        return null;
    }
}