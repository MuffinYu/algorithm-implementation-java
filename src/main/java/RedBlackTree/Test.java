package RedBlackTree;



/**
 * @Author kosong.yu
 * @Date 2019-10-08
 * @Description
 */
public class Test {
   public static void main(String[] args) {
//      test1();
//      test2();
//      test3();
//      deltest1();
      deltest2();
   }


   /**
    *     20b
    *    /   \
    *  10r   30r
    *
    */
   public static void test1() {
      System.out.println("test1 start");
      RedBlackTree T = new RedBlackTree();
      RedBlackTreeNode node1 = T.RB_NODE(10);
      T.RB_INSERT(T, node1);
      RedBlackTreeNode node2 = T.RB_NODE(20);
      T.RB_INSERT(T, node2);
      RedBlackTreeNode node3 = T.RB_NODE(30);
      T.RB_INSERT(T, node3);
      T.preorder(T.getRoot());
      T.midorder(T.getRoot());
      System.out.println("test1 end");

   }

   /**
    * expect:
    *           13b
    *         /     \
    *        8r      17r
    *       / \      /  \
    *      1b 11b   15b  25b
    *       \            / \
    *       6r         22r 27r
    * preorder: 13,8,1,6,11,17,15,25,22,27
    */
   public static void test2() {
      System.out.println("test2 start");
      RedBlackTree T = new RedBlackTree();
      int[] arr = {13, 8, 17, 1, 11, 15, 25, 6, 22, 27};
      RedBlackTreeNode node = null;
      for (int i:arr) {
         System.out.println(i);
         node = T.RB_NODE(i);
         T.RB_INSERT(T, node);
      }
      T.preorder(T.getRoot());
      System.out.println("test2 end");
   }

   /**
    *
    *      30b
    *    /     \
    *   10b     60r
    *    \      / \
    *    20r   40b 80b
    *      \       / \
    *      50r    70r 90r
    *  preorder:
    *  30b, 10b, 20r, 60r, 40b, 50r, 80b, 70r, 90r
    */
   public static RedBlackTree  test3() {
      System.out.println("test3 start");
//      int a[] = {10, 40, 30, 60, 90, 70, 20, 50, 80};
      int[] a = {10, 40, 30, 60, 90, 70, 20, 50, 80};

      RedBlackTree T3 = new RedBlackTree();
      RedBlackTreeNode node = null;
      for (int i:a) {
         System.out.println(i);
         node = T3.RB_NODE(i);
         T3.RB_INSERT(T3, node);
      }
      T3.preorder(T3.getRoot());
      System.out.println("test3 end");
      return T3;

   }

   public static void deltest1() {
      System.out.println("deltest1 start");
      RedBlackTree T = new RedBlackTree();
      RedBlackTreeNode nil = RedBlackTree.nil;

      RedBlackTreeNode n5 = new RedBlackTreeNode(NodeColor.Black, 5, nil, nil, nil);
      RedBlackTreeNode n12 = new RedBlackTreeNode(NodeColor.Black, 12, nil, nil, nil);
      RedBlackTreeNode n18 = new RedBlackTreeNode(NodeColor.Black, 18, nil, nil, nil);
      RedBlackTreeNode n10 = new RedBlackTreeNode(NodeColor.Black, 10, n5, nil, nil);
      RedBlackTreeNode n16 = new RedBlackTreeNode(NodeColor.Red, 16, n12, n18, n10);

      n5.setParent(n10);
      n12.setParent(n16);
      n18.setParent(n16);
      n10.setRight(n16);

      T.setRoot(n10);
//      int[] a = {10, 18, 16, 12, 5};
//      for (int i:a) {
//         System.out.println(i);
//         node = T.RB_NODE(i);
//         T.RB_INSERT(T, node);
//      }
      T.preorder(T.getRoot());
      System.out.println("will del");

      T.RB_DELETE(T, n5);
      T.preorder(T.getRoot());
      System.out.println("deltest1 end");
   }

   public static void deltest2() {
      String red = NodeColor.Red;
      String black = NodeColor.Black;

      System.out.println("deltest2 start");
      RedBlackTree T = new RedBlackTree();
      RedBlackTreeNode nil = RedBlackTree.nil;
      RedBlackTreeNode root = new RedBlackTreeNode(black, 50, nil, nil, nil);

      RedBlackTreeNode n30 = new RedBlackTreeNode(red, 30, nil, nil, root);
      RedBlackTreeNode n70 = new RedBlackTreeNode(red, 70, nil, nil, root);
      root.setLeft(n30);
      root.setRight(n70);
      RedBlackTreeNode n20 = new RedBlackTreeNode(black, 20, nil, nil, n30);
      RedBlackTreeNode n40 = new RedBlackTreeNode(black, 40, nil, nil, n30);
      n30.setLeft(n20);
      n30.setRight(n40);
      RedBlackTreeNode n60 = new RedBlackTreeNode(black, 60, nil, nil, n70);
      RedBlackTreeNode n80 = new RedBlackTreeNode(black, 80, nil, nil, n70);
      n70.setLeft(n60);
      n70.setRight(n80);

      RedBlackTreeNode n15 = new RedBlackTreeNode(black, 15, nil, nil, n20);
      RedBlackTreeNode n25 = new RedBlackTreeNode(black, 25, nil, nil, n20);
      n20.setLeft(n15);
      n20.setRight(n25);
      RedBlackTreeNode n35 = new RedBlackTreeNode(black, 35, nil, nil, n40);
      RedBlackTreeNode n45 = new RedBlackTreeNode(black, 45, nil, nil, n40);
      n40.setLeft(n35);
      n40.setRight(n45);
      RedBlackTreeNode n55 = new RedBlackTreeNode(black, 55, nil, nil, n60);
      RedBlackTreeNode n65 = new RedBlackTreeNode(black, 65, nil, nil, n60);
      n60.setLeft(n55);
      n60.setRight(n65);
      RedBlackTreeNode n75 = new RedBlackTreeNode(black, 75, nil, nil, n80);
      RedBlackTreeNode n85 = new RedBlackTreeNode(black, 85, nil, nil, n80);
      n80.setLeft(n75);
      n80.setRight(n85);
      T.setRoot(root);

      T.preorder(T.getRoot());
      System.out.println("will del n60");
      T.RB_DELETE(T, n55);

      T.preorder(T.getRoot());
      System.out.println("deltest2 end");
   }
}