package RedBlackTree;

import lombok.Getter;
import lombok.Setter;


/**
 * @Author kosong.yu
 * @Date 2019-09-30
 * @Description
 *
 * 算法导论里是这样定义一棵红黑树的：
 *  1、每个结点或是红色的，或是黑色的
 *  2、根节点是黑色的 (*)
 *  3、每个叶结点（NIL）是黑色的 (*)
 *  4、如果一个节点是红色的，则它的两个儿子都是黑色的
 *  5、对于每个结点，从该结点到其子孙结点（nil黑节点）的所有路径上包含相同数目的黑色结点
 */
public class RedBlackTree {
   /**
    * 空节点
    * 默认颜色为黑色
    */
   public static RedBlackTreeNode nil = new RedBlackTreeNode();

   /**
    * 记录根节点
    */
   @Getter
   @Setter
   private RedBlackTreeNode root = new RedBlackTreeNode();

   /**
    * 构造空树
    */
   public RedBlackTree() {
      root = nil;
   }

   /**
    * 生成一个新节点，默认颜色为红色
    * @param key
    * @return
    */
   public RedBlackTreeNode RB_NODE(int key) {
      RedBlackTreeNode node = new RedBlackTreeNode(NodeColor.Red, key, nil, nil, nil);
      return node;
   }

   /**
    * 判断节点是否为空
    * @param node
    * @return
    */
   public boolean IsNil(RedBlackTreeNode node) {
      if (node == nil) {
         return true;
      }else {
         return false;
      }
   }

   /**
    * 插入节点
    * @param T
    * @param z
    */
   public void RB_INSERT(RedBlackTree T, RedBlackTreeNode z) {
      // 临时变量节点y,存储临时节点，默认为nil
      RedBlackTreeNode y = RedBlackTree.nil;
      // x初始为根节点
      RedBlackTreeNode x = T.getRoot();
      // 循环二分查找合适的插入点
      while (IsNil(x) == false) {
         // 保存当前节点，作为结果的跟节点
         y = x;
         if (z.getKey() < x.getKey()){
            // 查找左节点
            x = x.getLeft();
         }else {
            // 查找右节点
            x = x.getRight();
         }
      }
      // 临时节点y设置为插入点的父节点
      z.setParent(y);

      if (IsNil(y) == true) {
         // 空树时设置z为根节点
         T.setRoot((z));
      }else if (z.getKey() < y.getKey()){
         // 插入左节点
         y.setLeft(z);
      }else {
         // 插入右节点
         y.setRight(z);
      }
      // 将插入节点的左右节点设为nil，颜色为红色，已经在构造时设置过，可以省略
      z.setLeft(RedBlackTree.nil);
      z.setRight(RedBlackTree.nil);
      z.setColor(NodeColor.Red);
      // 插入调整
      RB_INSERT_FIXUP(T, z);
   }

   /**
    * 插入节点后平衡
    * @param T
    * @param m
    */
   public void RB_INSERT_FIXUP(RedBlackTree T, RedBlackTreeNode m) {

      // 父节点是红色
      while (m != null && IsNil(m) == false &&  m.getParent().getColor() == NodeColor.Red) {
         // 父节点
         RedBlackTreeNode f = m.getParent();
         // 爷爷节点
         RedBlackTreeNode g = f.getParent();
         // 叔叔节点
         RedBlackTreeNode u = RedBlackTree.nil;
         if (f == g.getLeft()) {
            // 父节点是左节点

            u = g.getRight();
            if (u.getColor() == NodeColor.Red){
               // 叔叔节点是红色
               /**
                * 属于情况3，即叔叔节点也为红色，执行以下操作，并继续循环
                * f节点设为黑色
                * u节点设为黑色
                * g节点设为红色
                * 从g节点继续上溯循环判断，是否满足红黑树特性4
                */
               f.setColor(NodeColor.Black);
               u.setColor(NodeColor.Black);
               g.setColor(NodeColor.Red);
               m = g;
               continue;
            } else {
               // 叔叔节点是黑色
               // 判断插入节点是否是右节点
               if (m == f.getRight()) {
                  // 父节点是左节点，插入节点是右节点，叔叔节点是黑色
                  /**
                   * 图1类型，属于情况4，插入节点、父节点和祖父节点三点不一线，属于阶段1，做以下操作：
                   * f节点左旋
                   * 变成图2类型，情况4的阶段2，继续操作
                   */
                  RedBlackTreeNode tmp = m;
                  m = f;
                  f = tmp;
                  LEFT_ROTATE(T, m);
               }
               /**
                * 图2类型，情况4的阶段2，执行以下操作：
                * 父节点颜色设为黑色
                * 祖父节点颜色设为红色
                * 对祖父节点右旋
                */
               f.setColor(NodeColor.Black);
               g.setColor(NodeColor.Red);
               RIGHT_ROTATE(T, g);
            }
         } else {
            // 父节点是右节点
            u = g.getLeft();
            if (u.getColor() == NodeColor.Red) {
               // 叔叔节点是红色
               /**
                * 属于情况3，即叔叔节点也为红色，执行以下操作，并继续循环
                * f节点设为黑色
                * u节点设为黑色
                * g节点设为红色
                * 从g节点继续上溯循环判断，是否满足红黑树特性4
                */
               f.setColor(NodeColor.Black);
               u.setColor(NodeColor.Black);
               g.setColor(NodeColor.Red);
               m = g;
               continue;
            } else {
               // 叔叔节点是黑色
               // 判断插入节点是否是右节点
               if (m == f.getLeft()) {
                  // 父节点是右节点，插入节点是左节点，叔叔节点是黑色
                  /**
                   * 图3类型，属于情况4，插入节点、父节点和祖父节点三点不一线，属于阶段1，做以下操作：
                   * f节点右旋
                   * 变成图4类型，情况4的阶段2，继续操作
                   */
                  RedBlackTreeNode tmp = m;
                  m = f;
                  f = tmp;
                  RIGHT_ROTATE(T, m);
               }
               /**
                * 图4类型，情况4的阶段2，执行以下操作：
                * 父节点颜色设为黑色
                * 祖父节点颜色设为红色
                * 对祖父节点左旋
                */

               f.setColor(NodeColor.Black);
               g.setColor(NodeColor.Red);
               LEFT_ROTATE(T, g);
            }
         }
      }
      // 根节点设为黑色
      T.getRoot().setColor(NodeColor.Black);
   }


   /**
    * 删除节点
    * @param T
    * @param z
    */
   public void RB_DELETE(RedBlackTree T, RedBlackTreeNode z) {
      RedBlackTreeNode y = z;
      RedBlackTreeNode x = RedBlackTree.nil;
      String yOriginColor = y.getColor();
      if (z.getLeft() == RedBlackTree.nil) {
         // z没有左节点
         x = z.getRight();
         RB_TRANSPLANT(T, z, z.getRight());
      } else if (z.getRight() == RedBlackTree.nil) {
         // z没有右节点
         x = z.getLeft();
         RB_TRANSPLANT(T, z, z.getLeft());
      } else {
         y = TREE_MINIMUM(z.getRight());
         yOriginColor = y.getColor();
         x = y.getRight();
         if (y.getParent() == z) {
            // y就是 z 的右节点
            x.setParent(y);
         } else {
            // y 是 z 右子树的最小节点
            // 用 y 的值替换 z位置的值
            RB_TRANSPLANT(T, y, y.getRight());
            y.setRight(z.getRight());
            y.getRight().setParent(y);
         }
         RB_TRANSPLANT(T, z, y);
         y.setLeft(z.getLeft());
         y.getLeft().setParent(y);
         y.setColor(z.getColor());
      }

      if (yOriginColor == NodeColor.Black) {
         // y 是红色，直接删除 y
         // 否则需要重新平衡树
         RB_DELETE_FIXUP(T, x);
      }
   }
   /**
    * 删除节点调整
    * @param T
    * @param x
    */
   public void RB_DELETE_FIXUP(RedBlackTree T, RedBlackTreeNode x){
      String Black = NodeColor.Black;
      String Red = NodeColor.Red;
      //临时结点
      RedBlackTreeNode w = null;
      //非根结点且为黑色
      while( x != T.getRoot() && x.getColor() == Black ){
         //x为父结点左孩子
         if( x == x.getParent().getLeft() ){
            //w为兄弟结点
            w = x.getParent().getRight();
            //case1：w兄弟结点为红色， w一定有两个子节点
            if( w.getColor() == Red){
               //w设为黑色
               w.setColor(Black);
               //被删结点的父结点设为黑色
               x.getParent().setColor( Red );
               //对x的父结点左旋
               LEFT_ROTATE(T, x.getParent());
               //更新x的兄弟结点
               w = x.getParent().getRight();
            }
            //case2：w兄弟结点和两个孩子结点都为黑
            if( w.getLeft().getColor() == Black && w.getRight().getColor() == Black ){
               //w设为黑色
               w.setColor(Red);
               //重设x为x的父结点
               x = x.getParent();
            }
            else {
               // case3: 兄弟节点右节点为黑色
               if( w.getRight().getColor() == Black ){
                  //w的左孩子设为黑
                  w.getLeft().setColor(Black);
                  //w设为红
                  w.setColor(Red);
                  //右旋
                  RIGHT_ROTATE(T, w);
                  //更新w
                  w = x.getParent().getRight();
               }
               //case4：w兄弟结点为黑，w的右孩子为红
               w.setColor(x.getParent().getColor());
               x.getParent().setColor(Black);
               w.getRight().setColor(Black);
               LEFT_ROTATE(T, x.getParent());
               x = T.getRoot();
            }
         }
         //x为父结点右孩子
         else{
            w = x.getParent().getLeft();
            if( w.getColor() == Red ){
               w.setColor( Black );
               x.getParent().setColor(Red );
               RIGHT_ROTATE(T, x.getParent());
               w = x.getParent().getLeft();
            }
            if( w.getRight().getColor() == Black && w.getLeft().getColor() == Black ){
               w.setColor(Red);
               x = x.getParent();
            }
            else if( w.getLeft().getColor() == Black ){
               w.getRight().setColor(Black);
               w.setColor(Red);
               LEFT_ROTATE(T, w);
               w = x.getParent().getLeft();
            }
            w.setColor(x.getParent().getColor());
            x.getParent().setColor(Black);
            w.getLeft().setColor(Black);
            RIGHT_ROTATE(T, x.getParent());
            x = T.getRoot();
         }
      }
      x.setColor(Black);
   }

   /**
    * 查找 x 左子树最小的节点
    * @param x
    * @return
    */
   public RedBlackTreeNode TREE_MINIMUM(RedBlackTreeNode x) {
      while (IsNil(x.getLeft()) == false) {
         x = x.getLeft();
      }
      return x;
   }

   /**
    * 节点替代
    * 把根节点为o的子树替换为根节点为v的子树
    *
    * o 为父节点的左子树:
    *      r          r
    *     /           /
    *    o            n
    *   / \          / \
    *  ol  or       ol  or
    *
    *  o为父节点的右子树:
    *    r           r
    *     \           \
    *      o           n
    *     / \          / \
    *    ol  or       ol  or
    *
    *
    * @param T
    * @param o 旧节点
    * @param n 新节点
    */
   public void RB_TRANSPLANT(RedBlackTree T, RedBlackTreeNode o, RedBlackTreeNode n) {
      // u为根节点
      if (IsNil(o.getParent())) {
         T.setRoot(o);
      }
      // o为左子树
      else if (o == o.getParent().getLeft()) {
         o.getParent().setLeft(n);
      }
      // o为右子树
      else {
         o.getParent().setRight(n);
      }
      // 父节点替换
      n.setParent(o.getParent());
   }

   /**
    *  左旋(对节点x进行左旋)：
    *      px                              px
    *     /                               /
    *    x                               y
    *   /  \       --(左旋)--            / \
    *  lx   y                          x  ry
    *     /   \                       /  \
    *    ly   ry                     lx  ly
    *
    */
   public void LEFT_ROTATE(RedBlackTree T, RedBlackTreeNode x){
      // 定义y节点
      RedBlackTreeNode y = x.getRight();
      // y 左节点 设为 x右节点
      x.setRight((y.getLeft()));
      // x 设为 y左节点父节点
      y.getLeft().setParent(x);
      // x父节点 设为y父节点
      y.setParent(x.getParent());
      // y 设为x父节点左/右节点或者根节点
      if (IsNil(x.getParent()) == true) {
         // x为根节点， y设为根节点
         T.setRoot(y);
      } else if (x.getParent().getLeft() == x) {
         // x为左节点，y设为左节点
         x.getParent().setLeft(y);
      } else {
         // x为右节点，y设为右节点
         x.getParent().setRight(y);
      }
      // x 设为 y左节点
      y.setLeft(x);
      // y 设为x父节点
      x.setParent(y);
   }

   /**
    * 右旋示意图(对节点x进行右旋)：
    *            px                               px
    *           /                                /
    *          x                                y
    *         /  \      --(右旋)--             /  \
    *        y   rx                          ly   x
    *       / \                                  / \
    *      ly  ry                               ry  rx
    */
   public void RIGHT_ROTATE(RedBlackTree T, RedBlackTreeNode x){
      // 左节点
      RedBlackTreeNode y = x.getLeft();
      // y的右节点 设为 x左节点
      x.setLeft(y.getRight());
      y.getRight().setParent(x);
      // x节点父节点设为 y父节点
      y.setParent(x.getParent());
      if (IsNil(y.getParent()) == true){
         T.setRoot(y);
      }
      else if (x.getParent().getRight() == x) {
         x.getParent().setRight(y);
      } else {
         x.getParent().setLeft(y);
      }
      // x设为y右节点
      y.setRight(x);
      // y设为x父节点
      x.setParent(y);
   }

   /**
    * 寻找目标key的节点
    * @param T
    * @param key
    * @return
    */
   public static RedBlackTreeNode searchNode(RedBlackTree T, int key) {
      RedBlackTreeNode root = T.getRoot();
      RedBlackTreeNode target = null;
      RedBlackTreeNode next = root;
      while (target == null && next != RedBlackTree.nil) {
         if (next.getKey() == key) {
            target = next;
         } else if (next.getKey() < key){
            next = next.getRight();
         } else {
            next = next.getLeft();
         }
      }
      return target;
   }
   /**
    * 前序遍历
    * @param t
    */
   public void preorder(RedBlackTreeNode t) {
      if (IsNil(t) == false) {
         System.out.println(t.getKey() + t.getColor());
         preorder(t.getLeft());
         preorder(t.getRight());
      }
   }

   /**
    * 中序遍历
    * @param t
    */
   public void midorder(RedBlackTreeNode t) {
      if (IsNil(t) == false) {
         midorder(t.getLeft());
         System.out.println(t.getKey() + t.getColor());
         midorder(t.getRight());
      }
   }

   /**
    * 后序遍历
    * @param t
    */
   public void postorder(RedBlackTreeNode t) {
      if (IsNil(t) == false) {
         postorder(t.getLeft());
         postorder(t.getRight());
         System.out.println(t.getKey());
      }
   }
}