package LinkedList;


import lombok.Getter;
import lombok.Setter;

/**
 * @Author kosong.yu
 * @Date 2019-11-02
 * @Description
 */
public class LinkedList {
  private class Node {

    @Setter
    @Getter
    private Object value;

    @Setter
    @Getter
    private Node next;
    public Node(Object value) {
      this.value = value;
    }
  }

  @Setter
  @Getter
  private Node head;


  public LinkedList() {
    head = null;
  }

  public void add(Object value) {
    Node newNode = new Node(value);
    Node tail = head;
    if (head == null) {
      head = newNode;
    } else {
      while (tail.next != null) {
        tail = tail.next;
      }
      tail.next = newNode;
    }
  }

  /**
   *
   * 判断链表是否有环，若有，返回环的交点；否则返回 null
   * 龟兔赛跑式
   * @param head
   * @return
   * @Description：
   * 1. 申明两个节点，p1、p2，进行循环，每次 p1 前进一步，p2 前进两步；
   * 2. 若没有环，p2 会先到达终点，此时结束循环，判断没有环，返回 null；
   * 3. 若有环，p2 先进入环中进行循环，p1 最终也会进入环中；p2 速度比 p1 快，经过若干次循环，p2
   * 肯定会在环中追上 p1，p1 和 p2 相等，判断有环；
   * 4. 保存 p1 的位置，p1 循环，到回到之前位置所走的步数就是环的长度；
   * 5. p1 和 p2 回到起点，p2 先前进等于环的长度的位置，p1 和 p2 一次循环，下次相遇时，即时环的交节点；
   *
   * 证明：假设链表有环，环的相交点之前的长度为 a，环的长度为 b：
   * 循环 x 次后，p1 和 p2 在环中相遇，则 2x - b = x，循环次数就是环的长度；
   *
   */
  public Node hasCircle(Node head) {
    if (head == null || head.getNext() == null) {
      return null;
    }
    Node p1 = head.getNext();
    Node p2 = head.getNext().getNext();
    while (p1 != p2) {
      if (p2 == null || p1 == null) {
        // 1. p1 或者 p2 到最后节点了，证明没有环
        return null;
      }
      p1 = p1.getNext();
      p2 = p2.getNext().getNext();
    }
    // 2. 走到此处证明有环
    Node tmp = p1;
    // 求环的长度
    int size = 0;
    while (tmp != p1) {
      p1 = p1.getNext();
      size++;
    }

    p1 = p2 = head;
    while (size-- > 0) {
      p2 = p2.getNext();
    }
    while (p1 != p2) {
      p1 = p1.getNext();
      p2 = p2.getNext();
    }
    return p1;
  }

  /**
   * 判断两个链表是否相交，若相交，返回相交的起始点；否则返回 null
   * @param heada
   * @param headb
   * @return
   *
   * @Description：
   * 1. 假设 heada 的长度比 headb短， 同时遍历两个链表；
   * 2. 较短的链表 heada 先遍历完成，pa 变成 headb 的头节点；继续遍历，等 headb 第一次遍历完成，pb 变成 heada 的头节点；
   * 4. 此时 pa 领先 pb 的步数就是 headb 的长度 和 heada 长度的差值；
   * 5. 继续遍历，若相交，则相交点的时候，pa 肯定等于 pb，结束循环，返回相交点；若不相交，最后 pa pb 同时到达
   * headb 和 heada 的尾节点，循环终止，完成遍历，返回null；
   */
  public Node getIntersectionNode(Node heada, Node headb) {
    if (heada == null || heada == null) {
      return null;
    }
    Node pa = heada;
    Node pb = headb;

    while (pa != pb) {
      pa = pa == null ? headb : pa.getNext();
      pb = pb == null ? heada : pb.getNext();
    }
    return pa;
  }
}