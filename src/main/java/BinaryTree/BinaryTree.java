package BinaryTree;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Stack;

/**
 * @Author kosong.yu
 * @Date 2019-11-01
 * @Description 二叉树
 */
public class BinaryTree {

  @Getter
  @Setter
  private Object data;

  @Getter
  @Setter
  private BinaryTree left;

  @Getter
  @Setter
  private BinaryTree right;

  @Getter
  @Setter
  private BinaryTree root;


  public BinaryTree(BinaryTree left, BinaryTree right, Object data) {
    super();
    this.left = left;
    this.right = right;
    this.data = data;
  }
  public BinaryTree(Object data) {
    this(null, null, data);
  }
  public BinaryTree() {
    super();
  }

  public void create(Object[] objs) {
    ArrayList<BinaryTree> datas = new ArrayList<BinaryTree>();
    for (Object obj:objs) {
      datas.add(new BinaryTree(obj));
    }
    root = datas.get(0);
    int loopCount = (int) Math.floor(objs.length / 2);
    for (int i = 0; i < loopCount; i++) {
      datas.get(i).left = datas.get(i * 2 + 1);
      // 避免偶数的时候越界
      if (i * 2 + 2 < datas.size()){
        datas.get(i).right = datas.get(i * 2 + 2);
      }
    }
  }

  /**
   * 递归
   * 前序遍历
   * @param root
   */
  public void preorder(BinaryTree root) {
    if (root != null) {
      System.out.print(root.getData()+" ");
      preorder(root.getLeft());
      preorder(root.getRight());
    }
  }

  /**
   * 递归
   * 中序遍历
   * @param root
   */
  public void midorder(BinaryTree root) {
    if (root != null) {
      midorder(root.getLeft());
      System.out.print(root.getData()+" ");
      midorder(root.getRight());
    }
  }


  /**
   * 递归
   * 后序遍历
   * @param root
   */
  public void postorder(BinaryTree root) {
    if (root != null) {
      postorder(root.getLeft());
      postorder(root.getRight());
      System.out.print(root.getData()+" ");
    }
  }

  /**
   * 循环
   * 前序遍历
   */
  public void preorderloop(BinaryTree root) {
    if (root != null) {
      Stack<BinaryTree> stack = new Stack<BinaryTree>();
      //栈不为空时，或者p不为空时循环
      while (root != null || !stack.isEmpty()) {
        if (root != null) {
          System.out.print(root.getData()+ " ");
          stack.push(root);
          root = root.getLeft();
        } else {
          root = stack.pop().getRight();
        }
      }
    }
  }

  /**
   * 循环
   * 中序遍历
   * @param root
   */
  public void midorderloop(BinaryTree root) {
    if (root != null) {
      Stack<BinaryTree> stack = new Stack<BinaryTree>();
      while (root != null || !stack.isEmpty()) {
        if (root != null) {
          stack.push(root);
          root = root.getLeft();
        } else {
          root = stack.pop();
          System.out.print(root.getData()+ " ");
          root = root.getRight();
        }
      }
    }
  }

  /**
   * 循环
   * 后序遍历
   * @param root
   */
  public void postorderloop(BinaryTree root) {
    if (root != null) {
      Stack<BinaryTree> stack = new Stack<BinaryTree>();
      Stack<Integer> tag = new Stack<Integer>();

      while (root != null || !stack.isEmpty()) {
        if (root != null) {
          stack.push(root);
          //第一次访问
          tag.push(1);
          root = root.getLeft();
        } else {
          if (tag.peek() == 2) {
            System.out.print(stack.pop().getData() + " ");
            tag.pop();
          } else {
            tag.pop();
            //第二次访问
            tag.push(2);
            root = stack.peek().getRight();
          }
        }
      }
    }
  }



}