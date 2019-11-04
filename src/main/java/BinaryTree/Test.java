package BinaryTree;

/**
 * @Author kosong.yu
 * @Date 2019-11-02
 * @Description
 */
public class Test {
  public static void main(String[] args) {

    BinaryTree binaryTree = new BinaryTree();
    Integer[] arr = {8, 4, 12, 2, 6, 10, 14, 1, 3, 5, 7, 9, 11, 13, 15};
    // create 方法接受的层序遍历结果
    binaryTree.create(arr);

    // 中序遍历，从小到大的排序
    binaryTree.midorder(binaryTree.getRoot());
    System.out.println("\nloop mid order");

    binaryTree.midorderloop(binaryTree.getRoot());
  }
}