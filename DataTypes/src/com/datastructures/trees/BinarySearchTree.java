package com.datastructures.trees;

import org.jetbrains.annotations.NotNull;

public class BinarySearchTree {

    private BSTNode root;

    /**
     * Constructs a <code>BinarySearchTree</code> object without a root.
     */
    public BinarySearchTree() {
        this.root = null;
    }

    /**
     * Constructs a <code>BinarySearchTree</code> object with a given root.
     * @param root a <code>BSTNode</code> object representing the root of this <code>BinarySearchTree</code> instance
     */
    public BinarySearchTree(BSTNode root) {
        this.root = root;
    }

    /**
     * Returns the root of this <code>BinarySearchTree</code> object.
     * @return a <code>BSTNode</code> object representing the root of this <code>BinarySearchTree</code> instance
     */
    public BSTNode getRoot() {
        return root;
    }

    /**
     * Sets the root of this <code>BinarySearchTree</code> object. Cannot be called from outside this class in order to preserve the BST invariant.
     * @param root a <code>BSTNode</code> object representing the root of this <code>BinarySearchTree</code> instance
     */
    private void setRoot(BSTNode root) {
        this.root = root;
    }

    /**
     * Returns the height of this <code>BinarySearchTree</code> object.
     * @return an <code>int</code> representing the height of this <code>BinarySearchTree</code> object.
     */
    public int treeHeight() {
        return this.treeHeight(this.getRoot());
    }

    /**
     * Helper method for <code>inorderTraversal()</code>.
     * @param root a <code>BSTNode</code> object representing the root of this <code>BinarySearchTree</code> instance
     */
    private int treeHeight(BSTNode root) {
        if (root == null) {
            return -1;
        }
        return Math.max(this.treeHeight(root.getLeft()), this.treeHeight(root.getRight())) + 1;
    }

    /**
     * Prints the nodes of this <code>BinarySearchTree</code> object using inorder traversal.
     */
    public void inorderTraversal() {
        this.inorderTraversal(this.getRoot());
    }

    /**
     * Helper method for <code>inorderTraversal()</code>.
     * @param root a <code>BSTNode</code> object representing the root of this <code>BinarySearchTree</code> instance
     */
    private void inorderTraversal(BSTNode root) {
        if (root != null) {
            this.inorderTraversal(root.getLeft());
            System.out.println(root.getKey());
            this.inorderTraversal(root.getRight());
        }
    }

    /**
     * Searches for and returns the node with the give key in this <code>BinarySearchTree</code> object.
     * @param key the key of the <code>BSTNode</code> object to search for
     * @return a <code>BSTNode</code> object representing the node in this <code>BinarySearchTree</code> instance with the given key
     */
    public BSTNode treeSearch(int key) {
        return this.treeSearch(this.getRoot(), key);
    }

    /**
     * Helper method for <code>treeSearch(int key)</code>.
     * @param root a <code>BSTNode</code> object representing the root of this <code>BinarySearchTree</code> instance
     * @param key the key of the <code>BSTNode</code> object to search for
     * @return a <code>BSTNode</code> object representing the node in this <code>BinarySearchTree</code> instance with the given key
     */
    private BSTNode treeSearch(BSTNode root, int key) {
        if (root == null || key == root.getKey()) {
            return root;
        }
        if (key < root.getKey()) {
            return this.treeSearch(root.getLeft(), key);
        } else {
            return this.treeSearch(root.getRight(), key);
        }
    }

    /**
     * Searches for and returns the node with the minimum key in this <code>BinarySearchTree</code> object.
     * @return a <code>BSTNode</code> object representing the node with the minimum key in this <code>BinarySearchTree</code> object
     */
    public BSTNode treeMinimum() {
        return this.treeMinimum(this.getRoot());
    }

    /**
     * Helper method for <code>treeMinimum()</code>.
     * @param root a <code>BSTNode</code> object representing the root of this <code>BinarySearchTree</code> instance
     * @return a <code>BSTNode</code> object representing the node with the minimum key in this <code>BinarySearchTree</code> object
     */
    private BSTNode treeMinimum(@NotNull BSTNode root) {
        while (root.getLeft() != null) {
            root = root.getLeft();
        }
        return root;
    }

    /**
     * Searches for and returns the node with the maximum key in this <code>BinarySearchTree</code> object.
     * @return a <code>BSTNode</code> object representing the node with the maximum key in this <code>BinarySearchTree</code> object
     */
    public BSTNode treeMaximum() {
        return this.treeMaximum(this.getRoot());
    }

    /**
     * Helper method for <code>treeMaximum()</code>.
     * @param root a <code>BSTNode</code> object representing the root of this <code>BinarySearchTree</code> instance
     * @return a <code>BSTNode</code> object representing the node with the maximum key in this <code>BinarySearchTree</code> object
     */
    private BSTNode treeMaximum(@NotNull BSTNode root) {
        while (root.getRight() != null) {
            root = root.getRight();
        }
        return root;
    }

    /**
     * Searches for and returns the successor of the node with the given key in this <code>BinarySearchTree</code> instance.
     * @param key the key of the <code>BSTNode</code> object whose successor to search for
     * @return a <code>BSTNode</code> object representing the successor of the node with the given key in this <code>BinarySearchTree</code> object
     */
    public BSTNode treeSuccessor(int key) {
        BSTNode node = treeSearch(this.getRoot(), key);
        if (node.getRight() != null) {
            return treeMinimum(node.getRight());
        }
        BSTNode y = node.getParent();
        while (y != null && node == y.getRight()) {
            node = y;
            y = y.getParent();
        }
        return y;
    }

    /**
     * Inserts the given node into this <code>BinarySearchTree</code> object.
     * @param node a <code>BSTNode</code> object representing the node to insert into this <code>BinarySearchTree</code> object
     */
    public void treeInsert(BSTNode node) {
        BSTNode node1 = null;
        BSTNode node2 = this.getRoot();
        while (node2 != null) {
            node1 = node2;
            if (node.getKey() < node2.getKey()) {
                node2 = node2.getLeft();
            } else {
                node2 = node2.getRight();
            }
        }
        node.setParent(node1);
        if (node1 == null) {
            this.setRoot(node);
        } else if (node.getKey() < node1.getKey()) {
            node1.setLeft(node);
        } else {
            node1.setRight(node);
        }
    }

    public void treeInsert(int x) {
        BSTNode node = new BSTNode(x);
        BSTNode node1 = null;
        BSTNode node2 = this.getRoot();
        while (node2 != null) {
            node1 = node2;
            if (node.getKey() < node2.getKey()) {
                node2 = node2.getLeft();
            } else {
                node2 = node2.getRight();
            }
        }
        node.setParent(node1);
        if (node1 == null) {
            this.setRoot(node);
        } else if (node.getKey() < node1.getKey()) {
            node1.setLeft(node);
        } else {
            node1.setRight(node);
        }
    }

    /**
     * Replaces the subtree rooted at node <code>replacee</code> with the subtree rooted at node <code>replacement</code>.
     * @param replacee The <code>BSTNode</code> object representing the node to replace in this <code>BinarySearchTree</code> object.
     * @param replacement The <code>BSTNode</code> object representing the root of the subtree replacing <code>replacee</code> in this <code>BinarySearchTree</code> object.
     */
    private void transplant(@NotNull BSTNode replacee, BSTNode replacement) {
        if (replacee.getParent() == null) {
            this.setRoot(replacement);
        } else if (replacee == replacee.getParent().getLeft()) {
            replacee.getParent().setLeft(replacement);
        } else {
            replacee.getParent().setRight(replacement);
        }
        if (replacement != null) {
            replacement.setParent(replacee.getParent());
        }
    }

    /**
     * Deletes the given node from this <code>BinarySearchTree</code> object.
     * @param node A <code>BSTNode</code> object representing the node to delete from this <code>BinarySearchTree</code> object
     */
    public void treeDelete(@NotNull BSTNode node) {
        if (node.getLeft() == null) {
            this.transplant(node, node.getRight());
        } else if (node.getRight() == null) {
            this.transplant(node, node.getLeft());
        } else {
            BSTNode y = treeMinimum(node.getRight());
            if (y.getParent() != node) {
                this.transplant(y, y.getRight());
                y.setRight(node.getRight());
                y.getRight().setParent(y);
            }
            this.transplant(node, y);
            y.setLeft(node.getLeft());
            y.getLeft().setParent(y);
        }
    }

    /**
     * Left-rotates this <code>BinarySearchTree</code> object at the node <code>node</code>.
     * @param node a <code>BSTNode</code> object representing the node about which to left-rotate this <code>BinarySearchTree</code> object.
     */
    public void rotateLeft(@NotNull BSTNode node) {
        if (node.getRight() == null) {
            throw new NullPointerException("Input node has a null right neighbor.");
        }
        BSTNode node1 = node.getRight();
        node.setRight(node1.getLeft());
        if (node1.getLeft() != null) {
            node1.getLeft().setParent(node);
        }
        node1.setParent(node.getParent());
        if (node.getParent() == null) {
            this.setRoot(node1);
        } else if (node == node.getParent().getLeft()) {
            node.getParent().setLeft(node1);
        } else {
            node.getParent().setRight(node1);
        }
        node1.setLeft(node);
        node.setParent(node1);
    }

    /**
     * Right-rotates this <code>BinarySearchTree</code> object at the node <code>node</code>.
     * @param node a <code>BSTNode</code> object representing the node about which to right-rotate this <code>BinarySearchTree</code> object.
     */
    public void rotateRight(@NotNull BSTNode node) {
        BSTNode node1 = node.getLeft();
        node.setLeft(node1.getRight());
        if (node1.getRight() != null) {
            node1.getRight().setParent(node);
        }
        node1.setParent(node.getParent());
        if (node.getParent() == null) {
            this.setRoot(node1);
        } else if (node == node.getParent().getRight()) {
            node.getParent().setRight(node1);
        } else {
            node.getParent().setLeft(node1);
        }
        node1.setRight(node);
        node.setParent(node1);
    }

}
