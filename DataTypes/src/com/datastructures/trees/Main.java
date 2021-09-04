package com.datastructures.trees;

public class Main {

    public static void print(Object object) {
        System.out.println(object);
        System.out.println();
    }

    public static void main(String[] args) {

        BinarySearchTree t = new BinarySearchTree();
        t.treeInsert(4);
        t.treeInsert(2);
        t.treeInsert(6);
        t.treeInsert(5);
        t.treeInsert(3);
        t.treeInsert(1);

        printTree(t.getRoot(), "");
        System.out.println();
        t.rotateLeft(t.getRoot());
        printTree(t.getRoot(), "");
        System.out.println();
        t.rotateRight(t.getRoot());
        printTree(t.getRoot(), "");


    }

    public static void printTree(BSTNode node, String prefix) {

        if (node == null) {
            System.out.println(prefix + " +");
            return;
        }

        System.out.println(prefix + " + " + node.getKey());
        printTree(node.getLeft(), prefix + " ");
        printTree(node.getRight(), prefix + " ");

    }

}
